package kr.ac.bohyun.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.bohyun.cse.model.Cart;
import kr.ac.bohyun.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCartItem(CartItem cartItem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}
	
	public void deleteCartItem(CartItem cartItem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}

	public void removeCartItem(CartItem cartItem) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}

	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems=cart.getCartItems();
		//Eagar로 줌, Lazy로 넣었다면 null값이 들어왔을것임
		
		for(CartItem item: cartItems){
			removeCartItem(item);
		}
	}

	@SuppressWarnings("unchecked")
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session=sessionFactory.getCurrentSession();
		TypedQuery<CartItem> query=session.createQuery("from CartItem where cart.id=? and product.id=?");
		query.setParameter(0, cartId);
		query.setParameter(1, productId);
		return (CartItem)query.getSingleResult();
	}
	
}
