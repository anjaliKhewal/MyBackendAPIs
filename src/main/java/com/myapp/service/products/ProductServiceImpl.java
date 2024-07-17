/**
 * 
 */
package com.myapp.service.products;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.facade.rest.products.ProductRequest;
import com.myapp.service.dbconnection.MyDbDetails;

/**
 * @author Anjali
 *
 */
@Component
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	MyDbDetails myDbDetails;
	
	public MyProducts getMyProductsFromDb(int id) {
		MyProducts myproducts = new MyProducts();
		//myproducts = myDbDetails.crud(id);
		
		// create session and call read and create methods
		Session session = myDbDetails.createSession();
		myproducts = read(session, id);
		
		return myproducts;
	}

	private MyProducts read(Session session, int id) {
		// TODO Auto-generated method stub
		
		Query q = session.createQuery("select prods from MyProducts prods where prods.id= :id");
		q.setParameter("id", id);
		
		List<MyProducts> products = q.list();
		  System.out.println("Reading product records...");
		   System.out.printf("%-30.30s  %-30.30s  %-30.30s%n", "Id", "Name","Quantity");
		   MyProducts prodReturn = new MyProducts();
		   if(products!= null && products.size()>0 ) {
		   for (MyProducts p : products) {
			   System.out.printf("%-30.30s  %-30.30s  %-30.30s%n", p.getProductId(), p.getProductName(),p.getQuantity());
			   		prodReturn = p;
		           }		
		   }
		return prodReturn;
	}
	
	private void create(Session session, ProductRequest prodCreate) {
		
		MyProducts myprod = new MyProducts();
		
		myprod.setProductId(prodCreate.getProductId());
		myprod.setProductName(prodCreate.getProductName());
		myprod.setQuantity(prodCreate.getQuantity());
		
		session.beginTransaction();
		session.save(myprod);
		session.getTransaction().commit();
	}

	public int createProduct(ProductRequest myproduct) {
		// TODO Auto-generated method stub
		Session session = myDbDetails.createSession();
		
		create(session, myproduct);
		
		return 0;
	}

}
