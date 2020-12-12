package com.echain.common.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyMenu{
	private String icon,title,jump,name,havsub;
	private Integer type;
	private List<MyMenu> list;
}