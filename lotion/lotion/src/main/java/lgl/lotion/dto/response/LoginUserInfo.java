package lgl.lotion.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class LoginUserInfo {
	@JsonInclude(Include.NON_NULL)
	private String userId;
	
	@JsonInclude(Include.NON_NULL)
	private String userNm;

}
