package com.mimi.zfw.service;

import java.io.Serializable;
import java.util.List;

import com.mimi.zfw.plugin.BaseExample;
import com.mimi.zfw.plugin.IBaseModel;

public interface IBaseService<M extends IBaseModel<PK>, E extends BaseExample, PK extends Serializable> {

    public M get(PK id);

    public int countAll();

    public List<M> listAll();

    public List<M> query(E example);

    public int countQuery(E example);

    public boolean exists(PK id);

    public M save(M model);

    public List<M> saveBatch(List<M> modelList);

    public void saveOrUpdate(M model);

    public void saveOrUpdateBatch(List<M> modelList);

    public void update(M model);

    public void delete(PK id);

    public void updateBatch(List<M> modelList);

    public void deleteBatch(List<PK> ids);

    public void deleteObjectBatch(List<M> modelList);
}
