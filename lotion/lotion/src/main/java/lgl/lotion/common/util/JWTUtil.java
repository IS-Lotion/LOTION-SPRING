package lgl.lotion.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lgl.lotion.common.dto.TokenInfo;
import lgl.lotion.config.PropConfig;
import lgl.lotion.model.UserModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
// 토큰 관련 
public class JWTUtil {
	@Autowired
	public PropConfig prop;

	private Key getSigningKey(String jwtKey) {
		byte[] keyBytes = jwtKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String getUserId(String token) {
		return extractAllClaims(token).get("userId", String.class);
	}

	public Claims extractAllClaims(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder().setSigningKey(getSigningKey(prop.getJwtKey())).build().parseClaimsJws(token)
				.getBody();
	}

	public boolean isTokenExpired(String token) {
		final Date expiration = extractAllClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	public TokenInfo getTokenInfo(String token) {
		Claims claims = extractAllClaims(token);
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setUserId(claims.get("userId", String.class));
		tokenInfo.setWorkIp(claims.get("workIp", String.class));
		tokenInfo.setToken(token);
		return tokenInfo;
	}

	public TokenInfo getRefreshTokenInfo(String token) {
		Claims claims = extractAllClaims(token);
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setUserId(claims.get("userId", String.class));
		return tokenInfo;
	}

	public String generateToken(UserModel user) {
		Claims claims = Jwts.claims();
		// 사용자 정보를 토큰에 저장
		claims.put("userId", user.getUserId());

		String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				// AT 만료기한을 10분으로 설정 ( 60초(60000) * yml파일에 설정된 값(분 단위))
				.setExpiration(new Date(System.currentTimeMillis() + (60000L * prop.getJwtAccessTime())))
				.signWith(getSigningKey(prop.getJwtKey()), SignatureAlgorithm.HS256).compact();

		return jwt;
	}

	public String generateRefreshToken(UserModel user) {
		Claims claims = Jwts.claims();
		claims.put("userId", user.getUserId());
		// claims.put("authCd", user.getAuthCd());

		String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				// AT 만료기한을 30분으로 설정 ( 60초(60000) * yml파일에 설정된 값(분단위))
				.setExpiration(new Date(System.currentTimeMillis() + (60000L * prop.getJwtRefreshTime())))
				.signWith(getSigningKey(prop.getJwtKey()), SignatureAlgorithm.HS256).compact();

		return jwt;
	}

	// 토큰 유효성 체크
	public boolean validateToken(String token) throws ExpiredJwtException {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey(prop.getJwtKey())).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
			throw e;
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

}
