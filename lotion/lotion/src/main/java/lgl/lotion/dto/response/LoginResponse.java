package lgl.lotion.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginResponse {
	@JsonProperty("accessToken")
	public String accessToken;

	@JsonProperty("refreshToken")
	public String refToken;

	@JsonInclude(Include.NON_NULL)
	public LoginUserInfo userInfo;

	public LoginResponse() {
	};

}
