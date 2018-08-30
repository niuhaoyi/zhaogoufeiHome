package com.baidu.entity;

import lombok.Data;

@Data
public class Goods {

	private Integer gid;
	private String gname;
	
	
	private Integer cid;
	private String cname;
	
	
	private Integer count;
	private double price;
}
