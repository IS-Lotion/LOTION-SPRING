package lgl.lotion.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lgl.lotion.dto.request.LoginRequest;
import lgl.lotion.dto.response.LoginResponse;

public interface LoginService {

	public LoginResponse loginUser(LoginRequest body, HttpServletRequest request, HttpServletResponse response);

	public void logout(String auth, HttpServletResponse response);

	public void refreshToken(HttpServletRequest request, HttpServletResponse response);
}
