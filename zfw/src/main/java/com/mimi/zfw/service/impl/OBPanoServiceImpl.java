package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.OBPanoMapper;
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOBPanoService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class OBPanoServiceImpl extends
	BaseService<OBPano, OBPanoExample, String> implements IOBPanoService {

    @Resource
    private OBPanoMapper obpm;
    @Resource
    private IUserService userService;

    @Resource
    @Override
    public void setBaseDao(IBaseDao<OBPano, OBPanoExample, String> baseDao) {
	this.baseDao = baseDao;
	this.obpm = (OBPanoMapper) baseDao;
    }

    @Override
    public List<OBPano> getPanosByOBId(String id) {
	OBPanoExample pe = new OBPanoExample();
	pe.or().andOfficeBuildingIdEqualTo(id).andDelFlagEqualTo(false);
	pe.setOrderByClause("update_date desc");
	return obpm.selectByExample(pe);
    }

    @Override
    public int updateBatchOBPano(String oBPanoIds, OBPano oBPano) {

	if (oBPano == null) {
	    return 0;
	}

	String[] ids = oBPanoIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}

	OBPanoExample example = new OBPanoExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	oBPano.setLastEditor(userService.getCurUserId());

	int i = obpm.updateByExampleSelective(oBPano, example);

	return i;
    }

    @Override
    public List<OBPano> findOBPanoByExample(OBPanoExample example,
	    Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new OBPanoExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	example.setOrderByClause("update_date desc");
	List<OBPano> list = obpm.selectByExample(example);

	return list;
    }

    @Override
    public int countOBPanoByExample(OBPanoExample example) {

	if (example == null) {
	    example = new OBPanoExample();
	    example.or().andDelFlagEqualTo(false);
	}
	return obpm.countByExample(example);
    }

    @Override
    public Map<String, String> addOBPano(OBPano oBPano) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (oBPano == null) {
	    resMap.put("msg", "写字楼全景内容不能为空");
	    return resMap;
	}
	resMap = checkOBPano(oBPano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	oBPano.setCreater(curId);
	oBPano.setLastEditor(curId);
	oBPano.setDelFlag(false);
	oBPano.setId(UUID.randomUUID().toString());
	obpm.insertSelective(oBPano);

	return resMap;
    }

    @Override
    public Map<String, String> updateOBPano(OBPano oBPano) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (oBPano == null) {
	    resMap.put("msg", "写字楼全景内容不能为空");
	    return resMap;
	}
	resMap = checkOBPano(oBPano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}

	oBPano.setLastEditor(userService.getCurUserId());
	obpm.updateByPrimaryKeySelective(oBPano);

	return resMap;
    }

    @Override
    public Map<String, String> checkOBPano(OBPano oBPano) {
	Map<String, String> resMap = new HashMap<String, String>();

	if (oBPano == null) {
		resMap.put("msg", "全景内容不能为空");
		return resMap;
	}
	if (StringUtils.isBlank(oBPano.getOfficeBuildingId())) {
		resMap.put("msg", "全景所属写字楼不能为空");
		return resMap;
	}

	String name = oBPano.getName();
	String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "全景名");
	if(StringUtils.isNotBlank(errStr)){
	    resMap.put("field","name");
	    resMap.put("msg", errStr);
	    return resMap;
	}

	
//	if(StringUtils.isBlank(oBPano.getPreImageUrl())){
//	    resMap.put("msg","全景缩略图不能为空");
//	    return resMap;
//	}
	
	String contentUrl = oBPano.getContentUrl();
	errStr = FormatUtil.checkFormate(contentUrl, true, FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "全景内容路径");
	if(StringUtils.isNotBlank(errStr)){
		resMap.put("field","contentUrl");
		resMap.put("msg", errStr);
		return resMap;
	}
	
	String description = oBPano.getDescription();
	errStr = FormatUtil.checkFormate(description,false,FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "描述");
	if(StringUtils.isNotBlank(errStr)){
		resMap.put("field","description");
		resMap.put("msg", errStr);
		return resMap;
	}

	return resMap;
    }
}
