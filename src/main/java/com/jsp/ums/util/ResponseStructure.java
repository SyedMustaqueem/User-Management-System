package com.jsp.ums.util;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ResponseStructure<T> {
 private int status;
 private String message;
 private T data;
	/*
	 * public static void main(String[] args) {
	 * ResponseStructure<UserRepository>=res }
	 */
}
