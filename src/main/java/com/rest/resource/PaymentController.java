/**
 * 
 */
package com.rest.resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dbconnection.MyDbDetails;
import com.rest.dbconnection.MyProducts;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Anjali
 *
 */

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private static final int PRODUCT_ID = 10;
	private static final String SUCCESS_STATUS ="success";
	private static final String ERR_STATUS ="error";
	private static final int  CODE_SUCCESS = 200;
	private static final int AUTH_FAILURE =  401;
	
	@RequestMapping(value= "/pay", method=RequestMethod.POST)
	public BaseResponse pay(@RequestParam(value="id") int id, @RequestBody PaymentRequest request){
		
		BaseResponse response = new BaseResponse();
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
	
	@RequestMapping(value = "/payback", method=RequestMethod.GET)
	public BaseResponse payback(@RequestParam(value="id") int id){
		
		BaseResponse response = new BaseResponse();
		if(id>0) {
					
			//db call
			MyDbDetails myDetails = new MyDbDetails();
			MyProducts myprod = new MyProducts();
			myprod = myDetails.crud(id);
			
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
