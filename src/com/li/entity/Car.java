package com.li.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Car implements Serializable{
	private static final long serialVersionUID = -7920637559961112368L;
	
	private Map<Integer,ShopItem> car = new HashMap<Integer,ShopItem>();
	
	//添加商品时，将商品加入到购物车，判断购物车中有没有要添加的图书
	@SuppressWarnings("null")
	public void add(Book book){
		//购物车中没有该商品则加入，如存在该商品时，则商品数量+1
		ShopItem shopitem = null;
		if(car.containsKey(book.getId())){
			//先获得shopItem对象，在修改数量
			shopitem = car.get(book.getId());
			shopitem.setNum(shopitem.getNum()+1);
		}else{
			shopitem = new ShopItem(book, 1);
		}
		//将shopItem存放在map中
		car.put(book.getId(), shopitem);
	}
	
	
	public Collection<ShopItem> getAllItems(){
		return car.values();
	}
	
	
	public void delete(Book book){
		//购物车中没有该商品则加入，如存在该商品时，则商品数量-1
		ShopItem shopitem = null;
		if(car.containsKey(book.getId())){
			//先获得shopItem对象，在修改数量
			shopitem = car.get(book.getId());
			shopitem.setNum(shopitem.getNum()-1);
		}else{
			shopitem = new ShopItem(book, 1);
		}
		//将shopItem存放在map中
		car.put(book.getId(), shopitem);
	}
	
	
	public int getTotalPrice(){
		int total = 0;
		for (ShopItem si : getAllItems()) {
			total+=si.getAllPrice();
		}
		return total;
	}
	
	public void getClear(){
		car.clear();
	}


	public void doModify(Integer id, Integer num) {
		if(car.containsKey(id)){
			//保证数量>0,否则移出该商品
			if(num>0){
				//获取shopItem对象
				ShopItem shopitem = car.get(id);
				shopitem.setNum(num);
			}else{
				car.remove(id);
			}
		}
	}

	public void remove(Integer id) {
		if(car.containsKey(id)){
			car.remove(id);
		}
	}
}