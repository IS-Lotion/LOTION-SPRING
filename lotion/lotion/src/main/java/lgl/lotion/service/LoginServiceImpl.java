package lgl.lotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lgl.lotion.common.util.JWTUtil;
import lgl.lotion.dto.request.LoginRequest;
import lgl.lotion.dto.response.LoginResponse;
import lgl.lotion.dto.response.LoginUserInfo;
import lgl.lotion.model.UserModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	public JWTUtil jwtUtil;

	@Override
	// 로그인 처리 DTO 및 Model 간략화해야함
	public LoginResponse loginUser(LoginRequest loginReq, HttpServletRequest request, HttpServletResponse response) {
		try {
			log.debug("===>>> LoginServiceImpl > loginUser() > loginRequest : " + loginReq);

			// 필수 param없으면 에러 발생
			if (loginReq.getUserId() == null || loginReq.getUserPwd() == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				// 유효하지 않은 파라미터
				return new LoginResponse();
			}

			// 이후 db접근해서 추출
			UserModel user = new UserModel();
			user.setUserId("test");
			user.setUserNm("장주언");

			String tokenKey = jwtUtil.generateToken(user);
			String tokenKeyRef = jwtUtil.generateRefreshToken(user);

//			BaseResponse res = new BaseResponse(ResponseCode.C200);
			LoginResponse loginRes = new LoginResponse();

			loginRes.setAccessToken(tokenKey);
			loginRes.setRefToken(tokenKeyRef);

			// loginResponse에 로그인 사용자 정보 설정
			LoginUserInfo userInfo = new LoginUserInfo();
			userInfo.setUserId("test");
			userInfo.setUserNm("장주언");
			loginRes.setUserInfo(userInfo);

			// log.debug("===>>> LoginServiceImpl > loginUser() > loginRes : "+ loginRes);

//			res.setData(loginRes);
//			log.debug("===>>> LoginServiceImpl > loginUser() > res : " + res);

//			return res;
			return loginRes;
		} catch (Exception e) {
			log.warn(e.toString(), e);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return new LoginResponse();
		}

	}

	@Override
	public void logout(String auth, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		return null;
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		return null;
	}

}
