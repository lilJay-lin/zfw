package com.mimi.zfw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.service.IAssessmentItemService;
import com.mimi.zfw.service.IAssessmentParameterService;
import com.mimi.zfw.service.IRentalHousingService;
import com.mimi.zfw.service.IResidenceCommunityService;
import com.mimi.zfw.service.ISHHFloorPriceLinearFunctionService;
import com.mimi.zfw.service.ISecondHandHouseService;

@Controller
public class PGController {
	private static final Logger LOG = LoggerFactory.getLogger(PGController.class);  
	@Resource
	private IAssessmentItemService aiService;

	@Resource
	private IAssessmentParameterService apServcie;

	@Resource
	private ISHHFloorPriceLinearFunctionService shhfplfService;
	@Resource
	private IResidenceCommunityService rcService;
	@Resource
	private IRentalHousingService rhService;
	@Resource
	private ISecondHandHouseService shhService;

	@RequestMapping(value = "/pg", method = { RequestMethod.GET })
	public String pg(HttpServletRequest request) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		List<AssessmentItem> ais = aiService.getAll();
		List<AssessmentParameter> aps = apServcie.getAll();
		for (int i = 0; i < ais.size(); i++) {
			AssessmentItem ai = ais.get(i);
			Map<String, Object> aim = new HashMap<String, Object>();
			aim.put("name", ai.getName());
			aim.put("type", ai.getType());
			List<Map<String, Object>> apms = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < aps.size(); j++) {
				AssessmentParameter ap = aps.get(j);
				if (ai.getId().equals(ap.getAssessmentItemId())) {
					Map<String, Object> apm = new HashMap<String, Object>();
					apm.put("name", ap.getName());
					apm.put("value", ap.getValue());
					apms.add(apm);
				}
			}
			aim.put("params", apms);
			items.add(aim);
		}
		request.setAttribute("results", items);
		return "ui/pg/index";
	}

	@RequestMapping(value = "/pg/analyse", method = { RequestMethod.GET })
	public @ResponseBody
	Object analyse(HttpServletRequest request, String residenceCommunityName,
			String residenceCommunityId, Float grossFloorArea, String forward,
			Integer curFloor, Integer plusValue) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, Object> resultMap = shhfplfService.analyse(
					residenceCommunityId, residenceCommunityName, forward,
					curFloor, plusValue);
			if (StringUtils.isBlank((String) resultMap.get("error"))) {

				int zfTotalNum = rhService.countRentalHousingByParams(null,
						null, null, null, null, null);
				int esfTotalNum = shhService.countSecondHandHouseByParams(null,
						null, null, null, null, null);
				int averagePrice = 0;
				ResidenceCommunity rc = null;
				if (StringUtils.isNotBlank(residenceCommunityId)) {
					rc = rcService.get(residenceCommunityId);
				} else {
					rc = rcService.getByName(residenceCommunityName);
				}
				if (rc != null) {
					averagePrice = rc.getShhAveragePrice();
				}
				int price = (int) resultMap.get("price");
				int totalPrice = (int) (price * grossFloorArea / 10000);
				jo.put("price", price);
				jo.put("totalPrice", totalPrice);
				jo.put("zfTotalNum", zfTotalNum);
				jo.put("esfTotalNum", esfTotalNum);
				jo.put("averagePrice", averagePrice);
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", resultMap.get("error"));
			}
		} catch (Exception e) {
			LOG.error("二手房评估出错！",e);
			jo.put("success", false);
			jo.put("msg", "评估出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/pg/miniAnalyse", method = { RequestMethod.GET })
	public @ResponseBody
	Object miniAnalyse(HttpServletRequest request,
			String residenceCommunityName, String residenceCommunityId,
			Float grossFloorArea, String forward, Integer curFloor) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, Object> resultMap = shhfplfService.analyse(
					residenceCommunityId, residenceCommunityName, forward,
					curFloor, 0);
			if (StringUtils.isBlank((String) resultMap.get("error"))) {
				int price = (int) resultMap.get("price");
				int totalPrice = (int) (price * grossFloorArea / 10000);
				jo.put("totalPrice", totalPrice);
				jo.put("success", true);
			} else if (Constants.ASSESSMENT_ERROR_NO_RESULT.equals(resultMap
					.get("error"))) {
				jo.put("success", false);
			} else {
				jo.put("success", false);
				jo.put("msg", resultMap.get("error"));
			}
		} catch (Exception e) {
			LOG.error("二手房简化评估出错！",e);
			e.printStackTrace();
			jo.put("success", false);
			jo.put("msg", "评估出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/pg", method = { RequestMethod.GET })
	public String toMIIndex(HttpServletRequest request) {
		request.setAttribute("lastStart", shhfplfService.getLastStart());
		request.setAttribute("lastEnd", shhfplfService.getLastEnd());
		request.setAttribute("computing", shhfplfService.isComputing());
		request.setAttribute("lastSuccess", shhfplfService.isLastSuccess());
		return "mi/pg/index";
	}

	@RequestMapping(value = "/mi/pg/compute", method = { RequestMethod.POST })
	public @ResponseBody
	Object compute(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		try{
        	String errorStr = shhfplfService.resetFunction();
        	if(StringUtils.isNotBlank(errorStr)){
        		jo.put("success", false);
        		jo.put("msg", errorStr);
        	}else{
        		jo.put("success", true);
        		shhfplfService.setLastSuccess(true);
        		shhfplfService.setLastEnd(new Date(System.currentTimeMillis()));
        	}
    	}catch(Exception e){
    		jo.put("success", false);
    		LOG.error("------二手房评估线性回归方程计算失败------",e);
    		shhfplfService.setLastSuccess(false);
    		shhfplfService.setLastEnd(new Date(System.currentTimeMillis()));
    	}
		return jo.toString();
	}
}
