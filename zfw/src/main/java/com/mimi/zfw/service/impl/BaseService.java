package com.mimi.zfw.service.impl;

import java.io.Serializable;
import java.util.List;

import com.mimi.zfw.plugin.BaseExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.plugin.IBaseModel;
import com.mimi.zfw.service.IBaseService;

public abstract class BaseService<M extends IBaseModel<PK>, E extends BaseExample, PK extends Serializable>
	implements IBaseService<M, E, PK> {

    protected IBaseDao<M, E, PK> baseDao;

    public abstract void setBaseDao(IBaseDao<M, E, PK> baseDao);

    public M save(M model) {
	baseDao.insert(model);
	return model;
    }

    public void saveOrUpdate(M model) {
	if(exists(model.getId())){
	    baseDao.insert(model);
	}else{
	    baseDao.updateByPrimaryKey(model);
	}
    }

    public void update(M model) {
	baseDao.updateByPrimaryKey(model);
    }

    public void delete(PK id) {
	baseDao.deleteByPrimaryKey(id);
    }

    public M get(PK id) {
	return baseDao.selectByPrimaryKey(id);
    }

    public int countAll() {
	return baseDao.countByExample(null);
    }

    public List<M> listAll() {
	return baseDao.selectByExample(null);
    }

    public List<M> query(E example) {
	return baseDao.selectByExample(example);
    }

    public int countQuery(E example) {
	return baseDao.countByExample(example);
    }

    public boolean exists(PK id) {
	return baseDao.selectByPrimaryKey(id) != null;
    }

    public List<M> saveBatch(List<M> modelList) {
	for(int i=0;i<modelList.size();i++){
	    baseDao.insert(modelList.get(i));
	}
	return modelList;
    }

    public void saveOrUpdateBatch(List<M> modelList) {
	for(int i=0;i<modelList.size();i++){
	    saveOrUpdate(modelList.get(i));
	}
    }

    public void updateBatch(List<M> modelList) {
	for(int i=0;i<modelList.size();i++){
	    baseDao.updateByPrimaryKey(modelList.get(i));
	}
    }

    public void deleteBatch(List<PK> ids) {
	for(int i=0;i<ids.size();i++){
	    baseDao.deleteByPrimaryKey(ids.get(i));
	}
    }

    public void deleteObjectBatch(List<M> modelList) {
	for(int i=0;i<modelList.size();i++){
	    baseDao.deleteByPrimaryKey(modelList.get(i).getId());
	}
    }

}
