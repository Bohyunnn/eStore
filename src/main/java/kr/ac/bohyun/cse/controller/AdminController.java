package kr.ac.bohyun.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.bohyun.cse.model.Product;
import kr.ac.bohyun.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductService productService;

	@RequestMapping
	public String AdminPage() {
		return "admin";
	}

	@RequestMapping("/productInventory")
	public String getProducts(Model model) {
		// Controller-> Model-> View
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "productInventory";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		// product.setCategory("컴퓨터"); //-> 초기화
		model.addAttribute("product", product);
		return "addProduct";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println("From data has some errors.");
			List<ObjectError> errors = result.getAllErrors();
			// 에러를 가져옴

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
				// 에러 메시지 가져옴
			}
			return "addProduct";
		}

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage.isEmpty() == false) {
			System.out.println("----------------- file start -----------------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("----------------- file end -----------------");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		product.setImageFileName(productImage.getOriginalFilename());

		productService.addProduct(product);
		// if (!productService.addProduct(product)) { // product객체-> dao-> DB
		// System.out.println("Adding Product cannot be done");
		// }
		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
		// @PathVariable: URL에 변수를 넣어주는 기능
		Product product = productService.getProductsById(id);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFileName());

		if (Files.exists(savePath)) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		productService.deleteProduct(product);

		// if (!productService.deleteProduct(id)) {
		// System.out.println("Deleting Product cannot be done");
		// }

		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductsById(id);
		model.addAttribute("product", product);
		return "updateProduct";
	}

	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
		// System.out.println(product);

		if (result.hasErrors()) {
			System.out.println("From data has some errors.");
			List<ObjectError> errors = result.getAllErrors();
			// 에러를 가져옴

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
				// 에러 메시지 가져옴
			}
			return "updateProduct";
		}
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage.isEmpty() == false) {
			System.out.println("----------------- file start -----------------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("----------------- file end -----------------");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		product.setImageFileName(productImage.getOriginalFilename());

		productService.updateProduct(product);

		// if (!productService.updateProduct(product)) { // product객체-> dao-> DB
		// System.out.println("Updating Product cannot be done");
		// }
		return "redirect:/admin/productInventory";
	}

}
