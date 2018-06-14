package kr.ac.bohyun.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.bohyun.cse.dao.ProductDao;
import kr.ac.bohyun.cse.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	public void addProduct(Product product) {
		productDao.updateProduct(product);
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	public Product getProductsById(int id) {
		return productDao.getProductById(id);
	}

	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

}
