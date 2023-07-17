package lgl.lotion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

// .yml에서 값 저장
@Component
@Data
public class PropConfig {
	public String localIp;
	
	@Value("${server.jwt-key}")
	String jwtKey;
	
	@Value("${server.jwt-access-time}")
	Integer jwtAccessTime;
	
	@Value("${server.jwt-refresh-time}")
	Integer jwtRefreshTime;
}
