package com.li.util;

import com.li.service.BookClassService;
import com.li.service.BookService;
import com.li.service.CarService;
import com.li.service.UserService;
import com.li.serviceImp.BookClassServiceImp;
import com.li.serviceImp.BookServiceImp;
import com.li.serviceImp.CarServiceImp;
import com.li.serviceImp.UserServiceImp;

public class Factory {
	public static BookClassService getBookClassService(){
		return new BookClassServiceImp();
	}
	
	public static BookService getBookService(){
		return new BookServiceImp();
	}
	
	
	public static UserService getUserService(){
		return new UserServiceImp();
	}
	
	public static CarService getCarService(){
		return new CarServiceImp();
	}
}
