package com.li.serviceImp;

import java.sql.SQLException;

import com.li.dao.CarDao;
import com.li.entity.Book;
import com.li.service.CarService;

public class CarServiceImp implements CarService {

	private CarDao cardao = new CarDao();
	public void findbook(Book book) throws SQLException {
		try {
			cardao.findbook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
