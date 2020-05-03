package com.upgrad.myntra.api.controllers;



import com.upgrad.myntra.service.business.CustomerService;
import com.upgrad.myntra.service.entity.CustomerAuthEntity;
import com.upgrad.myntra.service.entity.CustomerEntity;
import com.upgrad.myntra.service.exception.AuthenticationFailedException;
import com.upgrad.myntra.service.exception.AuthorizationFailedException;
import com.upgrad.myntra.service.exception.SignUpRestrictedException;
import com.upgrad.myntra.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upgrad.myntra.api.model.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RequestMapping("/customer")
public class CustomerController {
	@Autowired private CustomerService customerService;

	/**
	 * A controller method for customer signup.
	 *
	 * @param //signupCustomerRequest - This argument contains all the attributes required to store customer details in the database.
	 * @return - ResponseEntity<SignupCustomerResponse> type object along with Http status CREATED.
	 * @throws //SignUpRestrictedException
	 */
	@PostMapping("/signup")
	public ResponseEntity<SignupCustomerResponse> signup( final SignupCustomerRequest signupCustomerRequest)throws SignUpRestrictedException {
		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setUuid(UUID.randomUUID().toString());
		customerEntity.setFirstName(signupCustomerRequest.getFirstName());
		customerEntity.setLastName(signupCustomerRequest.getLastName());
		customerEntity.setContactNumber(signupCustomerRequest.getContactNumber());
		customerEntity.setEmail(signupCustomerRequest.getEmailAddress());
		customerEntity.setPassword(signupCustomerRequest.getPassword());
		customerEntity.setSalt("1234@abc");
		CustomerEntity createdUserEntity=customerService.saveCustomer(customerEntity);
		SignupCustomerResponse userResponse=new SignupCustomerResponse().id(createdUserEntity.getUuid()).status("CUSTOMER SUCCESSFULLY REGISTERED");
		return new ResponseEntity<SignupCustomerResponse>(userResponse, HttpStatus.CREATED);
	}
	/**
	 * A controller method for customer authentication.
	 *
	 * @param //authorization - A field in the request header which contains the customer credentials as Basic authentication.
	 * @return - ResponseEntity<LoginResponse> type object along with Http status OK.
	 * @throws //AuthenticationFailedException
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse>login(@RequestHeader("authorization") final String authentication) throws AuthenticationFailedException {
		byte[] decoded= Base64.getDecoder().decode(authentication.split("Basic ")[1]);
		String decodedText=new String(decoded);
		if(decodedText.indexOf(":")==-1)
			throw new AuthenticationFailedException("ATH-003","Incorrect format of decoded customer name and password");
		String[] decodedArray=decodedText.split(":");
		CustomerAuthEntity userAuthTokenEntity=customerService.authenticate(decodedArray[0],decodedArray[1]);


		CustomerEntity user=userAuthTokenEntity.getCustomer();
		LoginResponse loginResponse=new LoginResponse().id(user.getUuid())
				.emailAddress(user.getEmail()).firstName(user.getFirstName())
				.lastName(user.getLastName()).contactNumber(user.getContactNumber()).message("LOGGED IN SUCCESSFULLY");
		HttpHeaders httpHeader=new HttpHeaders();
		httpHeader.add("access-token",userAuthTokenEntity.getAccessToken());
		return new ResponseEntity<LoginResponse>(loginResponse,httpHeader,HttpStatus.OK);

	}

	/**
	 * A controller method for customer logout.
	 *
	 * @param //authorization - A field in the request header which contains the JWT token.
	 * @return - ResponseEntity<LogoutResponse> type object along with Http status OK.
	 * @throws //AuthorizationFailedException
	 */
	@PostMapping("/logout")
	public ResponseEntity<LogoutResponse>logout(@RequestHeader("authorization") final String authentication) throws AuthorizationFailedException {
		String decodedText=authentication;
		CustomerAuthEntity customerAuthEntity=customerService.logout(decodedText);
		LogoutResponse lr=new LogoutResponse().id(customerAuthEntity.getUuid()).message("LOGGED OUT SUCCESSFULLY");
		return new ResponseEntity<LogoutResponse>(lr,HttpStatus.OK);
	}

	/**
	 * A controller method for updating customer password.
	 *
	 * @param //updatePasswordRequest - This argument contains all the attributes required to update customer password in the database.
	 * @param //authorization         - A field in the request header which contains the JWT token.
	 * @return - ResponseEntity<LogoutResponse> type object along with Http status OK.
	 * @throws //AuthorizationFailedException
	 * @throws //UpdateCustomerException
	 */
	@PostMapping("/password")
	public ResponseEntity<UpdatePasswordResponse>updateCustomerPassword(UpdatePasswordRequest updatePasswordRequest,@RequestHeader("authorization") final String authentication)throws Exception {
		CustomerEntity customer=customerService.getCustomer(authentication);
		CustomerEntity updatedCustomer=customerService.updateCustomerPassword(updatePasswordRequest.getOldPassword(),updatePasswordRequest.getNewPassword(),customer);
		UpdatePasswordResponse updatePasswordResponse=new UpdatePasswordResponse().id(updatedCustomer.getUuid()).status("CUSTOMER PASSWORD UPDATED SUCCESSFULLY");
		return new ResponseEntity<UpdatePasswordResponse>(updatePasswordResponse,HttpStatus.OK);
	}

}
