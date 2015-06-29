package com.mimi.zfw.plugin;

import java.io.Serializable;

public interface IBaseModel<PK extends Serializable> extends Serializable {

    public PK getId();
}
