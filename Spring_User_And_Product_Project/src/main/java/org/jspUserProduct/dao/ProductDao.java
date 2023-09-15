package org.jspUserProduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jspUserProduct.dto.Product;
import org.jspUserProduct.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private EntityManager manager;

    // Save Product And FindProduct By User Id And Find Product By Brands and Find Product By Category
	// In This DTO
	public Product SaveProduct(Product product, int user_id) {
		EntityTransaction transaction = manager.getTransaction();
		User u = manager.find(User.class, user_id);
		if (u != null) {
			u.getProducts().add(product);
			product.setUser(u);
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findProductByUserId(int id) {
		Query q = manager.createQuery("select p from Product p where id=?1 ");
		q.setParameter(1, id);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Product> findProductByBrand(String brand) {
		Query q = manager.createQuery("select p from Product p where brand=?1 ");
		q.setParameter(1, brand);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Product> findProductByCategory(String category) {
		Query q = manager.createQuery("select p from Product p where category=?1 ");
		q.setParameter(1, category);
		return q.getResultList();
	}

}
