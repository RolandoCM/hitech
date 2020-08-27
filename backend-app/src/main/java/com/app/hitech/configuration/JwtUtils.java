package com.app.hitech.configuration;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.app.hitech.service.impl.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author casti
 *
 */
@Component
public class JwtUtils {

	private static final Logger LOG = Logger.getLogger(JwtUtils.class);
	@Value("${hiProperties.app.jwtSecret}")
	private String jwtSecret;

	@Value("${hiProperties.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			LOG.error("Invalid JWT signature: {}" + e.getMessage());
		} catch (MalformedJwtException e) {
			LOG.error("Invalid JWT token: {}" + e.getMessage());
		} catch (ExpiredJwtException e) {
			LOG.error("JWT token is expired: {}" + e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOG.error("JWT token is unsupported: {}" + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOG.error("JWT claims string is empty: {}" + e.getMessage());
		}

		return false;
	}
}
