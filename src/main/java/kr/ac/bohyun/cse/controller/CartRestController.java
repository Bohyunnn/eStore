package kr.ac.bohyun.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.bohyun.cse.model.Cart;
import kr.ac.bohyun.cse.model.CartItem;
import kr.ac.bohyun.cse.model.Product;
import kr.ac.bohyun.cse.model.User;
import kr.ac.bohyun.cse.service.CartItemService;
import kr.ac.bohyun.cse.service.CartService;
import kr.ac.bohyun.cse.service.ProductService;
import kr.ac.bohyun.cse.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

//	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
//	public ResponseEntity<Cart> getCartById(@PathVariable(value = "cartId") int cartId) {
//		Cart cart = cartService.getCartById(cartId);
//		// cart��ü�� Json���� Serialization�Ͽ� response body�� JSON���� �Ѱ���
//		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
//	}
	
	@RequestMapping("/{cartId}")
	public ResponseEntity<Cart> getCartById (@PathVariable(value = "cartId") int cartId) {
	 Cart cart = cartService.getCartById(cartId);

	 HttpHeaders headers = new HttpHeaders();
	 headers.setCacheControl("max-age=10");

	 return new ResponseEntity<Cart>(cart, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value = "cartId") int cartId) {
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value = "productId") int productId) {
		// Product ��������
		Product product = productService.getProductsById(productId);
		// �α����� ����ڰ� �������� �ľ� (spring security)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		// Cart ��������
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();

		// Cart�� Product�� ���� ������ CartItem�� �ִ��� Ȯ��
		List<CartItem> cartItems = cart.getCartItems();
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
//				if (product.getUnitInStock() < cartItem.getQuantity()) {
//					return null;
//				}
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.addCartItem(cartItem); // save
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		// Cart �� Product�� ���� ������ CartItem�� ���� ���
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		cart.getCartItems().add(cartItem);
		cartItemService.addCartItem(cartItem); // save
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> deleteItem(@PathVariable(value = "productId") int productId) {

		// Product ��������
		Product product = productService.getProductsById(productId);

		// �α����� ����ڰ� �������� �ľ� (spring security)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		// Cart ��������
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();

		// Cart�� Product�� ���� ������ CartItem�� �ִ��� Ȯ��
		List<CartItem> cartItems = cart.getCartItems();
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() - 1);

//				if (cartItem.getQuantity() < 0) {
//					return null;
//				}

				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.deleteCartItem(cartItem); // delete save

				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/cartitem/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value = "productId") int productId) {

		// 1. �α����� ����ڰ� �������� �ľ� (spring security)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		// 2. Cart ��������
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();

		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

}
