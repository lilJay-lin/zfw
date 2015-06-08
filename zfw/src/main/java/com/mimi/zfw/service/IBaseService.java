package com.mimi.zfw.service;

import java.io.Serializable;
import java.util.List;

import com.mimi.zfw.plugin.BaseExample;
import com.mimi.zfw.plugin.IBaseModel;

public interface IBaseService<M extends IBaseModel<PK>, E extends BaseExample, PK extends Serializable> {

    /**
     * 不过滤del_flag
     * @param id
     * @return
     */
    public M get(PK id);

    /**
     * 不过滤del_flag
     * @return
     */
    public int countAll();

    /**
     * 不过滤del_flag
     * @return
     */
    public List<M> listAll();

    public List<M> query(E example);

    public int countQuery(E example);

    /**
     * 不过滤del_flag
     * @param id
     * @return
     */
    public boolean exists(PK id);

    public M save(M model);

    public List<M> saveBatch(List<M> modelList);

    public void saveOrUpdate(M model);

    public void saveOrUpdateBatch(List<M> modelList);

    public void update(M model);

    /**
     * 真实删除
     * @param id
     */
    public void delete(PK id);

    public void updateBatch(List<M> modelList);

    /**
     * 真实删除
     * @param ids
     */
    public void deleteBatch(List<PK> ids);

    /**
     * 真实删除
     * @param modelList
     */
    public void deleteObjectBatch(List<M> modelList);
}
