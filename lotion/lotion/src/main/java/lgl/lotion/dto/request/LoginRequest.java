package lgl.lotion.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequest {
	@JsonProperty
	private String userId;
	
	@JsonProperty
	private String userPwd;
	
}
