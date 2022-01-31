package com.burak.apigw.controller;

import com.burak.apigw.util.JwtUtil;
import com.burak.clients.customer.CustomerClient;
import com.burak.clients.customer.CustomerDTO;
import com.burak.clients.response.Response;
import com.burak.clients.response.ResponseError;
import com.burak.clients.response.ResponseSuccess;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class AuthRestController {

	@Autowired
	private JwtUtil jwtUtil;

	private final CustomerClient customerClient;

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody CustomerDTO customerDTO) {
		String token = jwtUtil.generateToken(customerDTO.email());

//		customerClient.getCustomer(customerDTO.email());
//
//		if(customerClient.getCustomer(customerDTO.email()).getStatusCode().equals(HttpStatus.OK)){
//			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(new ResponseSuccess(HttpStatus.OK));
//		}
//		else{
//			return new ResponseEntity<>(new ResponseError(HttpStatus.UNAUTHORIZED,"Unauthorized"), HttpStatus.UNAUTHORIZED);
//		}
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(new ResponseSuccess(HttpStatus.OK));

	}

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody CustomerDTO customerDTO) {

//		customerClient.saveCustomer(new CustomerDTO(customerDTO.firstName(),customerDTO.lastName(),customerDTO.email()));
		return new ResponseEntity<>(new ResponseSuccess(HttpStatus.OK), HttpStatus.OK);
	}

}
