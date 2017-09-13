package com.li.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Car implements Serializable{
	private static final long serialVersionUID = -7920637559961112368L;
	
	private Map<Integer,ShopItem> car = new HashMap<Integer,ShopItem>();
	
	//�����Ʒʱ������Ʒ���뵽���ﳵ���жϹ��ﳵ����û��Ҫ��ӵ�ͼ��
	@SuppressWarnings("null")
	public void add(Book book){
		//���ﳵ��û�и���Ʒ����룬����ڸ���Ʒʱ������Ʒ����+1
		ShopItem shopitem = null;
		if(car.containsKey(book.getId())){
			//�Ȼ��shopItem�������޸�����
			shopitem = car.get(book.getId());
			shopitem.setNum(shopitem.getNum()+1);
		}else{
			shopitem = new ShopItem(book, 1);
		}
		//��shopItem�����map��
		car.put(book.getId(), shopitem);
	}
	
	
	public Collection<ShopItem> getAllItems(){
		return car.values();
	}
	
	
	public void delete(Book book){
		//���ﳵ��û�и���Ʒ����룬����ڸ���Ʒʱ������Ʒ����-1
		ShopItem shopitem = null;
		if(car.containsKey(book.getId())){
			//�Ȼ��shopItem�������޸�����
			shopitem = car.get(book.getId());
			shopitem.setNum(shopitem.getNum()-1);
		}else{
			shopitem = new ShopItem(book, 1);
		}
		//��shopItem�����map��
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
			//��֤����>0,�����Ƴ�����Ʒ
			if(num>0){
				//��ȡshopItem����
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