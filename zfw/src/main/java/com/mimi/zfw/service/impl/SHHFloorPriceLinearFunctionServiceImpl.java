package com.mimi.zfw.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.ResidenceCommunityMapper;
import com.mimi.zfw.mybatis.dao.SHHFloorPriceLinearFunctionMapper;
import com.mimi.zfw.mybatis.dao.SecondHandHouseMapper;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunityExample;
import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunction;
import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunctionExample;
import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.mybatis.pojo.SecondHandHouseExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.ISHHFloorPriceLinearFunctionService;
import com.mimi.zfw.util.MathUtil;

@Service
public class SHHFloorPriceLinearFunctionServiceImpl
		extends
		BaseService<SHHFloorPriceLinearFunction, SHHFloorPriceLinearFunctionExample, String>
		implements ISHHFloorPriceLinearFunctionService {

	@Resource
	private SHHFloorPriceLinearFunctionMapper shhfplfm;

	@Resource
	private ResidenceCommunityMapper rcm;

	@Resource
	private SecondHandHouseMapper shhm;
	
	private Date lastStart = null;
	
	private Date lastEnd = null;
	
	private boolean lastSuccess = true;

	@Override
	public Date getLastStart() {
		return lastStart;
	}

	@Override
	public void setLastStart(Date lastStart) {
		this.lastStart = lastStart;
	}

	@Override
	public Date getLastEnd() {
		return lastEnd;
	}

	@Override
	public void setLastEnd(Date lastEnd) {
		this.lastEnd = lastEnd;
	}

	@Override
	public boolean isLastSuccess() {
		return lastSuccess;
	}

	@Override
	public void setLastSuccess(boolean lastSuccess) {
		this.lastSuccess = lastSuccess;
	}

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<SHHFloorPriceLinearFunction, SHHFloorPriceLinearFunctionExample, String> baseDao) {
		this.baseDao = baseDao;
		this.shhfplfm = (SHHFloorPriceLinearFunctionMapper) baseDao;
	}

	@Override
	public String resetFunction() {
		if(isComputing()){
			return "计算正在进行中";
		}
		setLastStart(new Date(System.currentTimeMillis()));
		setLastEnd(null);
		// 删除所有旧公式
		SHHFloorPriceLinearFunctionExample fe = new SHHFloorPriceLinearFunctionExample();
		fe.or().andDelFlagEqualTo(false);
		SHHFloorPriceLinearFunction f = new SHHFloorPriceLinearFunction();
		f.setDelFlag(true);
		shhfplfm.updateByExampleSelective(f, fe);

		// 计算添加新公式
		ResidenceCommunityExample rce = new ResidenceCommunityExample();
		rce.or().andDelFlagEqualTo(false);
		List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
		String[] forwards = { Constants.HOUSE_FORWARD_E,
				Constants.HOUSE_FORWARD_EN, Constants.HOUSE_FORWARD_ES,
				Constants.HOUSE_FORWARD_EW, Constants.HOUSE_FORWARD_N,
				Constants.HOUSE_FORWARD_S, Constants.HOUSE_FORWARD_SN,
				Constants.HOUSE_FORWARD_W, Constants.HOUSE_FORWARD_WN,
				Constants.HOUSE_FORWARD_WS };
		if (rcList != null && !rcList.isEmpty()) {
			// 所有小区
			for (int i = 0; i < rcList.size(); i++) {
				ResidenceCommunity rc = rcList.get(i);
				// 所有朝向和一公共朝向
				for (int k = 0; k < forwards.length + 1; k++) {
					SecondHandHouseExample shhe = new SecondHandHouseExample();
					if (k == forwards.length) {
						shhe.or().andResidenceCommunityIdEqualTo(rc.getId())
								.andTotalPriceIsNotNull()
								.andGrossFloorAreaIsNotNull();
					} else {
						shhe.or().andResidenceCommunityIdEqualTo(rc.getId())
								.andForwardEqualTo(forwards[k])
								.andTotalPriceIsNotNull()
								.andGrossFloorAreaIsNotNull();
					}
					shhe.setLimitStart(0);
					shhe.setLimitSize(50);
					shhe.setOrderByClause("update_date desc");
					List<SecondHandHouse> shhList = shhm.selectByExample(shhe);

					if (shhList != null && !shhList.isEmpty()) {
						double a;
						double b;
						double[] x = new double[shhList.size()];
						double[] y = new double[shhList.size()];
						double tempTotal = 0;
						for (int j = 0; j < shhList.size(); j++) {
							SecondHandHouse shh = shhList.get(j);
							if(shh.getGrossFloorArea()<1){
								x[j] = shh.getCurFloor();
								y[j] = shh.getTotalPrice()*10000
										/ 100;
								tempTotal+=y[j];
								continue;
							}
							x[j] = shh.getCurFloor();
							y[j] = shh.getTotalPrice()*10000
									/ shh.getGrossFloorArea();
							tempTotal+=y[j];
						}
						a = MathUtil.getXc(x, y);
						b = MathUtil.getC(x, y, a);
						
						if(Double.isNaN(a) || Double.isNaN(b)){
							a = 0;
							b = tempTotal/shhList.size();
						}
						SHHFloorPriceLinearFunction f2 = new SHHFloorPriceLinearFunction();
						f2.setId(UUID.randomUUID().toString());
						f2.setResidenceCommunityId(rc.getId());
						f2.setParamA(a);
						f2.setParamB(b);
						if (k != forwards.length) {
							f2.setForward(forwards[k]);
						}
						shhfplfm.insertSelective(f2);
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean isComputing(){
		if(getLastStart()!=null && getLastEnd()==null){
			Calendar oc = Calendar.getInstance();
			oc.setTime(getLastStart());
			oc.add(Calendar.HOUR_OF_DAY, 20);
			Calendar c = Calendar.getInstance();
			if(c.before(oc)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> analyse(String residenceCommunityId,
			String residenceCommunityName, String forward, Integer curFloor,
			Integer plusValue) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.isBlank(forward)){
			resultMap.put("error", "朝向不能为空");
			return resultMap;
		}
		if(curFloor==null){
			resultMap.put("error", "楼层不能为空");
			return resultMap;
		}
		if(plusValue==null){
			plusValue = 0;
		}
		if(StringUtils.isBlank(residenceCommunityId)){
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andNameEqualTo(residenceCommunityName).andDelFlagEqualTo(false);
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if(rcList==null || rcList.isEmpty()){
				resultMap.put("error", Constants.ASSESSMENT_ERROR_NO_RESULT);
				return resultMap;
			}
			residenceCommunityId = rcList.get(0).getId();
		}
		
		SHHFloorPriceLinearFunctionExample fe = new SHHFloorPriceLinearFunctionExample();
		fe.or().andResidenceCommunityIdEqualTo(residenceCommunityId).andForwardEqualTo(forward).andDelFlagEqualTo(false);
		List<SHHFloorPriceLinearFunction> fList = shhfplfm.selectByExample(fe);
		if(fList==null || fList.isEmpty()){
			fe.clear();
			fe.or().andResidenceCommunityIdEqualTo(residenceCommunityId).andForwardIsNull().andDelFlagEqualTo(false);
			fList = shhfplfm.selectByExample(fe);
			if(fList==null || fList.isEmpty()){
				resultMap.put("error", Constants.ASSESSMENT_ERROR_NO_RESULT);
				return resultMap;
			}
		}
		SHHFloorPriceLinearFunction f = fList.get(0);
		int price = (int) ((f.getParamA()*curFloor+f.getParamB())*(1+plusValue*0.01));
		resultMap.put("price", price);
		return resultMap;
	}

}
