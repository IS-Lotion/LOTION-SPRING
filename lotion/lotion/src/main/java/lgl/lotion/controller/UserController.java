package lgl.lotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lgl.lotion.dto.request.LoginRequest;
import lgl.lotion.dto.response.LoginResponse;
import lgl.lotion.service.LoginService;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	public LoginService loginService;

	@PostMapping("/login")
	public LoginResponse loginUser(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LoginRequest body) {
		return loginService.loginUser(body, request, response);

	}
}
