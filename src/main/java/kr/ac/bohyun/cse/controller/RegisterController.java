package kr.ac.bohyun.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.bohyun.cse.model.Cart;
import kr.ac.bohyun.cse.model.ShippingAddresss;
import kr.ac.bohyun.cse.model.User;
import kr.ac.bohyun.cse.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public String registerUser(Model model) {

		User user = new User();
		ShippingAddresss shippingAddress = new ShippingAddresss();

		user.setShippingAddress(shippingAddress);

		model.addAttribute("user", user);

		return "registerUser";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserPost(@Valid User user, BindingResult result, Model model) {
		// ����ڰ� �Է��� ������ User ��ü�� data-binding��. // @Valid�� UserŬ������ ���� �ֳ����̼ǿ�����
		// ������ ����� data-binding�� ����.

		if (result.hasErrors()) {
			return "registerUSer";
		}

		List<User> userList = userService.getAllUsers();
		for (int i = 0; i < userList.size(); i++) {
			// �ߺ� ���� üũ
			if (user.getUsername().equals(userList.get(i).getUsername())) {
				model.addAttribute("usernameMsg", "Username already exists");
				return "registerUser";
			}
		}

		user.setEnabled(true);

		if (user.getUsername().equals("admin")) // admin�� ���, ���ٱ��� ����
			user.setAuthority("ROLE_ADMIN");
		else
			user.setAuthority("ROLE_USER");

		//User �����, Cart�� ����ǰ���!
		Cart cart = new Cart();
		user.setCart(cart);
		
		userService.addUser(user); // ����� ���� ����

		return "registerUserSuccess";
	}
}
