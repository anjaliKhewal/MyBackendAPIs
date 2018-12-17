/**
 * 
 */
package com.myapp.service.products;

import com.myapp.facade.rest.products.ProductRequest;

/**
 * @author Anjali
 *
 */
public interface ProductService {
	
	public MyProducts getMyProductsFromDb(int id);
	
	public int createProduct(ProductRequest myproducts);

}
