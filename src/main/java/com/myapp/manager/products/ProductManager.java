/**
 * 
 */
package com.myapp.manager.products;

import com.myapp.facade.rest.products.ProductRequest;
import com.myapp.service.products.MyProducts;

/**
 * @author Anjali
 *
 */
public interface ProductManager {
	
	public MyProducts getMyProducts(int id);
	
	public int createProduct(ProductRequest myproducts);

}
