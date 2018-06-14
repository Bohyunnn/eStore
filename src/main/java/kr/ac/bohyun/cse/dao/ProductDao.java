package kr.ac.bohyun.cse.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.bohyun.cse.model.Product;

@Repository
@Transactional
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		return product;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product");
		List<Product> products = query.list();
		return products;
	}

	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}

	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

	// private JdbcTemplate jdbcTemplate;

	// @Autowired
	// public void setDataSource(DataSource dataSource) {
	// jdbcTemplate = new JdbcTemplate(dataSource);
	// }
	//
	// public List<Product> getProducts() {
	// String sqlStatement = "select * from product"; // record->object
	//
	// return jdbcTemplate.query(sqlStatement, new RowMapper<Product>() {
	//
	// @Override
	// public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
	// Product product = new Product();
	// product.setId(rs.getInt("id"));
	// product.setName(rs.getString("name"));
	// product.setPrice(rs.getInt("price"));
	// product.setCategory(rs.getString("category"));
	// product.setManufacturer(rs.getString("manufacturer"));
	// product.setUnitInStock(rs.getInt("unitInStock"));
	// product.setDescription(rs.getString("description"));
	// return product;
	// }
	//
	// });
	// }
	//
	// public boolean addProduct(Product product) {
	//
	// String name = product.getName();
	// int price = product.getPrice();
	// String category = product.getCategory();
	// String manufacturer = product.getManufacturer();
	// int unitInStock = product.getUnitInStock();
	// String description = product.getDescription();
	//
	// String sqlStatement = "insert into product
	// (name,price,category,manufacturer,"
	// + "unitInStock,description) values (?,?,?,?,?,?)";
	// return (jdbcTemplate.update(sqlStatement,
	// new Object[] { name, price, category, manufacturer, unitInStock,
	// description }) == 1);
	// }
	//
	// public boolean deleteProduct(int id) {
	// String sqlStatement = "delete from product where id=?";
	// return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	// }
	//
	// public Product getProductById(int id) {
	// String sqlStatement = "select * from product where id=?";
	// return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id }, new
	// RowMapper<Product>() {
	//
	// @Override
	// public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
	// Product product = new Product();
	// product.setId(rs.getInt("id"));
	// product.setName(rs.getString("name"));
	// product.setPrice(rs.getInt("price"));
	// product.setCategory(rs.getString("category"));
	// product.setManufacturer(rs.getString("manufacturer"));
	// product.setUnitInStock(rs.getInt("unitInStock"));
	// product.setDescription(rs.getString("description"));
	// return product;
	// }
	//
	// });
	// }
	//
	// public boolean updateProduct(Product product) {
	// int id = product.getId(); // update�� id �������°�! ��������!
	// String name = product.getName();
	// int price = product.getPrice();
	// String category = product.getCategory();
	// String manufacturer = product.getManufacturer();
	// int unitInStock = product.getUnitInStock();
	// String description = product.getDescription();
	//
	// String sqlStatement = "update product set name=?, price=?, category=?,
	// manufacturer=?, "
	// + "unitInStock=?,description=? where id=?";
	// return (jdbcTemplate.update(sqlStatement,
	// new Object[] {name, price, category, manufacturer, unitInStock,
	// description, id }) == 1);
	//
	// }
}