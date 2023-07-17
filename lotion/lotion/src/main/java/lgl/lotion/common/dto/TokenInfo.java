package lgl.lotion.common.dto;

import lombok.Data;

@Data
public class TokenInfo {
	private String token;
	private String userId;
	private String workIp;
}
