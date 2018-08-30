package com.baidu.entity;

import lombok.Data;

@Data
public class Cat {

	private Integer id;
	private Integer gid;//商品编号
	private String gname;//商品名称
	private double price;//单价
	private int num;//数量
	private Integer totalPrice;//商品总价
}
