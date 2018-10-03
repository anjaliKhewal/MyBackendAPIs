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
	
	private static final String AUTH_KEY = "secure";
	private static final String SUCCESS_STATUS ="success";
	private static final String ERR_STATUS ="error";
	private static final int  CODE_SUCCESS = 200;
	private static final int AUTH_FAILURE =  401;
	
	@RequestMapping(value= "/pay", method=RequestMethod.POST)
	public BaseResponse pay(@RequestParam(value="key") String key, @RequestBody PaymentRequest request){
		
		BaseResponse response = new BaseResponse();
		if(AUTH_KEY.equalsIgnoreCase(key)) {
			
			int userID = request.getUserId();
			String itemId = request.getItemId();
			double discount = request.getDiscount();
			
			response.setCode(CODE_SUCCESS);
			response.setStatus(SUCCESS_STATUS);
			
		} else {
			response.setCode(AUTH_FAILURE);
			response.setStatus(ERR_STATUS);
			
		}
		return response;
	}
	
	@RequestMapping(value = "/payback", method=RequestMethod.GET)
	public BaseResponse payback(@RequestParam(value="key") String key){
		
		BaseResponse response = new BaseResponse();
		if(AUTH_KEY.equalsIgnoreCase(key)) {
			
			int userID = 1;
			String itemId = "Pencil";
			double discount = 10.5;
			
			//db call
			MyDbDetails myDetails = new MyDbDetails();
			MyProducts myprod = new MyProducts();
			myprod = myDetails.crud();
			
			response.setCode(CODE_SUCCESS);
			response.setStatus(SUCCESS_STATUS);
			response.setProductId(myprod.getProductId());
			response.setProductName(myprod.getProductName());
			response.setQuantity(myprod.getQuantity());
			
			
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
