package com.jsp.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequestDto;
import com.jsp.ums.responsedto.UserResponseDto;
import com.jsp.ums.serviceImpl.UserServiceImpl;
import com.jsp.ums.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserCotroller {

	@Autowired
	UserServiceImpl userServiceImpl;

	//Inserting User Data
	@PostMapping("/user/save")
	public ResponseEntity<ResponseStructure<UserResponseDto>>  saveDetails(@RequestBody @Valid UserRequestDto userRequestDto) {
		return userServiceImpl.saveDetails(userRequestDto);
	}

	//Updating User
	@PutMapping("/user/{userId}")
	public ResponseEntity<ResponseStructure<UserResponseDto>> upadteDetails(@PathVariable int userId, @RequestBody User user){
		return 	userServiceImpl.updateDetails(userId,user);
	}


	@GetMapping("/user/details")
	public ResponseEntity<ResponseStructure<List<UserResponseDto>>> getDetails(User user){
		return userServiceImpl.getDetails(user);
	}

	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<ResponseStructure<UserResponseDto>> deleteDetail(@PathVariable int userId){
		return userServiceImpl.deleteDetail(userId);
	}
	//get user details by name ;
	@GetMapping("/user/{userName}")
	public ResponseEntity<ResponseStructure<List<UserResponseDto>>> getNameDetails(@PathVariable String userName){
		return userServiceImpl.getNameDetails(userName);
	}
}
