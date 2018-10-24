/**
 * 
 */
package com.myapp.service.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		
		myproducts = myDbDetails.crud(id);
		
		return myproducts;
		
	}

}
