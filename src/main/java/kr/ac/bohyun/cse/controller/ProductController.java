package kr.ac.bohyun.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.bohyun.cse.model.Product;
import kr.ac.bohyun.cse.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products")
	public String getProduct(Model model) {

		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "products";
	}

	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) {
		Product product = productService.getProductsById(productId);
		model.addAttribute("product", product);
		return "viewProduct";
	}
}
