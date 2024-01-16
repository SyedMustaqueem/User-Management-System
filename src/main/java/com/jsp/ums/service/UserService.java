package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequestDto;
import com.jsp.ums.responsedto.UserResponseDto;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {
	public ResponseEntity<ResponseStructure<UserResponseDto>>  saveDetails(UserRequestDto userRequestDto);
	//public ResponseEntity<ResponseStructure<User>> saveDetails(User user);
	
	public ResponseEntity<ResponseStructure<UserResponseDto>> updateDetails(int userId, User user);
	
	public ResponseEntity<ResponseStructure<List<UserResponseDto>>>  getDetails(User user);

	public ResponseEntity<ResponseStructure<UserResponseDto>> deleteDetail(int  userId);
}

