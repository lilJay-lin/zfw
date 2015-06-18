package com.mimi.zfw.plugin;


public abstract class BaseExample {

    public abstract void setOrderByClause(String orderByClause) ;

    public abstract String getOrderByClause() ;

    public abstract void setDistinct(boolean distinct) ;

    public abstract boolean isDistinct();

    public abstract void setLimitStart(Integer limitStart);

    public abstract Integer getLimitStart();

    public abstract void setLimitSize(Integer limitSize) ;

    public abstract Integer getLimitSize();
}
