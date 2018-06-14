package kr.ac.bohyun.cse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.bohyun.cse.dao.CartDao;
import kr.ac.bohyun.cse.model.Cart;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	
	public Cart getCartById(int cartId){
		return cartDao.getCartById(cartId);
	}
	
}
