package fr.diginamic.configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	private static final String AUTHORITIES_KEY = "auth";

	private Key key;

	private long tokenValidityInMilliseconds;

	private long tokenValidityInMillisecondsForRemenberMe;

	@PostConstruct
	public void init() {

		byte[] keyBytes;
		String secret = "Q2h1Y2sgTm9ycmlzIGEgcsOpdXNzaSDDoCB0cm91dmVyIGxhIHBhZ2UgNDA0ISEgQ04gbmUgbWVudCBqYW1haXMsIGMnZXN0IGxhIHbDqXJpdMOpIHF1aSBzZSB0cm9tcGUuIENOIHBldXQgc2Ugc291dmVuaXIgZHUgZnV0dXIuIAo=";

		if (!StringUtils.isEmpty(secret)) {
			log.warn("Warning: the JWT key used is not Base64-encoded. "
					+ "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
			keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		} else {
			log.debug("Using a Base64-encoded JWT secret key");
			keyBytes = Decoders.BASE64.decode(
					"Q2h1Y2sgTm9ycmlzIGEgcsOpdXNzaSDDoCB0cm91dmVyIGxhIHBhZ2UgNDA0ISEgQ04gbmUgbWVudCBqYW1haXMsIGMnZXN0IGxhIHbDqXJpdMOpIHF1aSBzZSB0cm9tcGUuIENOIHBldXQgc2Ugc291dmVuaXIgZHUgZnV0dXIuIAo=");
		}
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.tokenValidityInMilliseconds = 1000 * 3600;
		this.tokenValidityInMillisecondsForRemenberMe = 1000 * 3600 * 10;

	}

	public String createToken(Authentication authentication, boolean rememberMe) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date validity;
		if (rememberMe) {
			validity = new Date(now + this.tokenValidityInMillisecondsForRemenberMe);
		} else {
			validity = new Date(now + this.tokenValidityInMilliseconds);
		}

		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);

	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT signature.");
			log.trace("Invalid JWT signature trace: {}", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT token.");
			log.trace("Expired JWT token trace: {}", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT token.");
			log.trace("Unsupported JWT token trace: {}", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT token compact of handler are invalid.");
			log.trace("JWT token compact of handler are invalid trace: {}", e);
		}
		return false;
	}
}
