/**
 * 
 */
package com.myapp.facade.rest.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.manager.products.ProductManager;
import com.myapp.service.products.MyProducts;


/**
 * @author Anjali
 *
 */
@RestController
@RequestMapping("/products")
public class ProductControllerImpl {
	
	private static final int PRODUCT_ID = 10;
	private static final String SUCCESS_STATUS ="success";
	private static final String ERR_STATUS ="error";
	private static final int  CODE_SUCCESS = 200;
	private static final int AUTH_FAILURE =  401;
	private static final String APPLICATION_JSON_VALUE 	= "application/json";
	
	@Autowired
	ProductManager productManager;
	
	@RequestMapping(value= "/updateproduct", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces = {APPLICATION_JSON_VALUE})
	public ProductResponseData updateproduct(@RequestParam(value="id") int id, @RequestBody ProductRequest request){
		
		ProductResponseData response = new ProductResponseData();
		if(PRODUCT_ID == id) {
			
			int productId = request.getProductId();
			String productName = request.getProductName();
			double quanity = request.getQuantity();
			
			response.setCode(CODE_SUCCESS);
			response.setStatus(SUCCESS_STATUS);
			
		} else {
			response.setCode(AUTH_FAILURE);
			response.setStatus(ERR_STATUS);
			
		}
		return response;
	}
	
	@RequestMapping(value = "/getproduct", method=RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ProductResponseData getproduct(@RequestParam(value="id") int id){
		
		ProductResponseData response = new ProductResponseData();
		if(id>0) {
					
			//db call
			//MyDbDetails myDetails = new MyDbDetails();
			//MyProducts myprod = new MyProducts();
			MyProducts myprod = productManager.getMyProducts(id);
			
			if(myprod!=null) {
			response.setCode(CODE_SUCCESS);
			response.setStatus(SUCCESS_STATUS);
			response.setProductId(myprod.getProductId());
			response.setProductName(myprod.getProductName());
			response.setQuantity(myprod.getQuantity());
			}
			else {
				response.setCode(201);
				response.setStatus("not found");
				response.setProductId(id);
				response.setProductName("No Item available in db");
				response.setQuantity(0);	
			}
			
		} else {
			response.setCode(AUTH_FAILURE);
			response.setStatus(ERR_STATUS);
			response.setProductId(0);
			response.setProductName("Not a valid product");
			response.setQuantity(0);
		}
		return response;
	}
}
