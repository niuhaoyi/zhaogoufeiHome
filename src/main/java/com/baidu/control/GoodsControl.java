package com.baidu.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.entity.Cat;
import com.baidu.entity.Goods;
import com.baidu.mapper.IGoodsMapper;

@Controller
public class GoodsControl {

	@Autowired
	private IGoodsMapper igm;
	
	//列表
	@RequestMapping("goodslist")
	@ResponseBody
	public List<Goods> findGoods(){
		return igm.findGoods();
	}
	//列表
	@RequestMapping("findCats")
	@ResponseBody
	public List<Cat> findCats(){
		return igm.findCats();
	}
	
	//添加到购物车
	@RequestMapping("addToCat")
	@ResponseBody
	public Map<String, Object> addToCat(String ids){
		Map<String,Object> map = new HashMap<String,Object>();
		String msg="";
		boolean flag=false;
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//根据商品的编号完成购物的添加
			//判断商品的库存是否大于0
			Goods goods=igm.findGoodsById(id);
			//把商品加入购物车
			if(goods.getCount()>0) {
				
				
				
				//判断该商品是否已经在购物车中存在
				Cat cat = igm.findCatByGid(id);
				if(cat!=null) {
					//购物车该商品数量加1
					igm.updateCat(id);
				}else {
					//在购物车增加该商品
					igm.insertCat(goods);
				}
				flag=true;
				//减少商品的库存
				igm.updateGoodsKucun(id);
				
				msg+=","+goods.getGname()+"商品添加到购物车成功";
			}else {
				msg+=","+goods.getGname()+"的库存不足";
			}
		}
		map.put("msg", msg.substring(1));
		map.put("flag", flag);
		return map;
	}
}
