package com.baidu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baidu.entity.Cat;
import com.baidu.entity.Goods;

@Mapper
public interface IGoodsMapper {

	//查询商品列表
	@Select("SELECT t1.*,t2.cname from t_goods t1 "
			+ "LEFT JOIN t_categcry t2 on t1.cid=t2.cid ")
	public List<Goods> findGoods();
	
	//根据商品的编号查询商品信息
	@Select("SELECT t1.*,t2.cname from t_goods t1 "
			+ "LEFT JOIN t_categcry t2 on t1.cid=t2.cid where t1.gid=#{gid}")
	public Goods findGoodsById(String id);

	//根据商品的编号查询该商品是否在购物车中已经存在
	@Select("select * from t_cat where gid=#{id}")
	public Cat findCatByGid(String id);

	//该商品在购物车中已经存在，则更新购物车中的商品数量
	@Update("update t_cat set count=count+1 where gid=#{id}")
	public void updateCat(String id);

	@Insert("insert into t_cat(gid,count,price) values(#{gid},1,#{price})")
	public void insertCat(Goods goods);

	@Update("update t_goods set count=count-1 where gid=#{gid}")
	public void updateGoodsKucun(String id);
	
	@Select("SELECT t1.id,t2.gname,t1.price,t1.count as num,t1.price*t1.count as totalPrice from t_cat t1 LEFT JOIN t_goods t2 on t1.gid=t2.gid")
	public List<Cat> findCats(); 
}
