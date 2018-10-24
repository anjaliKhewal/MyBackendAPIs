/**
 * 
 */
package com.myapp.manager.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.service.products.MyProducts;
import com.myapp.service.products.ProductService;

/**
 * @author Anjali
 *
 */
@Component
public class ProductManagerImpl implements ProductManager{
		
	@Autowired
	ProductService productService;
	
	public MyProducts getMyProducts(int id) {
		
		return productService.getMyProductsFromDb(id);
	}

}
