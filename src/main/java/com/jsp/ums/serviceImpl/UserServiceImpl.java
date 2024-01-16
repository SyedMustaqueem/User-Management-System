package com.jsp.ums.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.repository.UserRepository;
import com.jsp.ums.requestdto.UserRequestDto;
import com.jsp.ums.responsedto.UserResponseDto;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	ResponseStructure<UserResponseDto> structure;

	@Autowired
	ResponseStructure<List<UserResponseDto>> listStructure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> saveDetails(UserRequestDto userRequestDto){//User user) {
		User user=mapToUserRequest(userRequestDto);
		user = userRepository.save(user);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Data Saved Sucessfuly");
		structure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponseDto>> (structure,HttpStatus.CREATED);

		//return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<UserResponseDto>> updateDetails(int userId, User user) {
		/*(performing using or else throw Exception)
		 * User update=userRepository.findById(userId).orElseThrow(()->new
		 * RuntimeException()); 
		 * user.setUserId(userId); 
		 * userRepository.save(user);
		 */
		User update=userRepository.findById(userId)
				.map(u -> {
					user.setUserId(userId); 
					return userRepository.save(user);
				})
				.orElseThrow();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Data Updated Sucessfully");
		structure.setData(mapToUserResponse(update));
		return new ResponseEntity<ResponseStructure<UserResponseDto>> (structure,HttpStatus.CREATED);
	}


public ResponseEntity<ResponseStructure<List<UserResponseDto>>> getNameDetails(String userName) {
	List<User> byName = userRepository.findByName(userName);
	List<UserResponseDto> responses=new ArrayList<>();
	for(User user:byName) {
		UserResponseDto userResponseDto=mapToUserResponse(user);
		responses.add(userResponseDto);
	}
	listStructure.setStatus(HttpStatus.OK.value());
	listStructure.setMessage("Data fetched Sucessfully");
	listStructure.setData(responses);
	return new ResponseEntity<ResponseStructure<List<UserResponseDto>>> (listStructure,HttpStatus.OK);
}

	
	
	public ResponseEntity<ResponseStructure<List<UserResponseDto>>>  getDetails(User user) {
		List<User> userList = userRepository.findAll();

		List<UserResponseDto> responses = new ArrayList<>();
		for (User user1 : userList) {
			UserResponseDto userResponseDto=mapToUserResponse(user1);
			responses.add(userResponseDto);
		}
		listStructure.setStatus(HttpStatus.OK.value());
		listStructure.setMessage("Data Updated Sucessfully");
		listStructure.setData(responses);
		return new ResponseEntity<ResponseStructure<List<UserResponseDto>>> (listStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<UserResponseDto>> deleteDetail(int  userId) {

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Data deleted Sucessfully");
		structure.setData(mapToUserResponse(userRepository.findById(userId).map(u -> {
			userRepository.delete(u);
			return u;
		}).orElseThrow(() -> new RuntimeException())));
		return new ResponseEntity<ResponseStructure<UserResponseDto>> (structure,HttpStatus.OK);
	}

	
	private User mapToUserRequest(UserRequestDto request) {
		return User.builder()
				.userName(request.getUserName())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
		
		//		User user=new User();
		//		user.setUserName(request.getUserName());
		//		user.setEmail(request.getEmail());
		//		user.setPassword(request.getPassword());
		//		return user;
		
	}
	private UserResponseDto mapToUserResponse(User user) {
		return UserResponseDto.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.build();
	}





}
