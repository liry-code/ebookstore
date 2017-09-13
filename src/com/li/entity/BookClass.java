package com.li.entity;

import java.io.Serializable;

public class BookClass implements Serializable {
	private static final long serialVersionUID = -1595893308203374663L;
	
	private Integer id;
	private String className;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
