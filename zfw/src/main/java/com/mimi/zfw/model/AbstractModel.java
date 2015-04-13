package com.mimi.zfw.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6539982236830027288L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
