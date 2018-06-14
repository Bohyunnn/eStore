package kr.ac.bohyun.cse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.bohyun.cse.dao.CartItemDao;
import kr.ac.bohyun.cse.model.Cart;
import kr.ac.bohyun.cse.model.CartItem;

@Service
public class CartItemService {

	
	@Autowired
	private CartItemDao cartItemDao;
	
	public void addCartItem(CartItem cartItem){
		cartItemDao.addCartItem(cartItem);
	}
	
	public void deleteCartItem(CartItem cartItem){
		cartItemDao.deleteCartItem(cartItem);
	}
	
	public void removeCartItem(CartItem cartItem){
		cartItemDao.removeCartItem(cartItem);
	}
	
	public void removeAllCartItems(Cart cart){
		cartItemDao.removeAllCartItems(cart);
	}
	
	public CartItem getCartItemByProductId(int cartId, int productId){
		return cartItemDao.getCartItemByProductId(cartId, productId);
	}
}
