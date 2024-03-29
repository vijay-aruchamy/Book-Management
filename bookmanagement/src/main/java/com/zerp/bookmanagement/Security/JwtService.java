package com.zerp.bookmanagement.Security;

import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 
import io.jsonwebtoken.io.Decoders; 
import io.jsonwebtoken.security.Keys; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.stereotype.Component; 

import java.security.Key; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.function.Function; 

@Component
public class JwtService { 

	public static final String SECRET = "mlRqH+WlsDW06IkKYDTah63ZCm6JedxbjuzVJN3CpI4="; 
	public String generateToken(String Email) { 
		Map<String, Object> claims = new HashMap<>(); 
		return createToken(claims, Email); 
	} 

	private String createToken(Map<String, Object> claims, String Email) { 
		return Jwts.builder() 
				.setClaims(claims) 
				.setSubject(Email) 
				.setIssuedAt(new Date(System.currentTimeMillis())) 
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
	} 

	private Key getSignKey() { 
		byte[] keyBytes= Decoders.BASE64.decode(SECRET); 
		return Keys.hmacShaKeyFor(keyBytes);   
	} 

	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		String username = claims.getSubject();
		if (username == null) {
			throw new IllegalArgumentException("Username not found in token");
		}
		return username;
	}
	

	public Date extractExpiration(String token) { 
		return extractClaim(token, Claims::getExpiration); 
	} 

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
		final Claims claims = extractAllClaims(token); 
		return claimsResolver.apply(claims); 
	} 

	private Claims extractAllClaims(String token) { 
		return Jwts 
				.parserBuilder() 
				.setSigningKey(getSignKey()) 
				.build() 
				.parseClaimsJws(token) 
				.getBody(); 
	} 

	private Boolean isTokenExpired(String token) { 
		return extractExpiration(token).before(new Date()); 
	} 

	public Boolean validateToken(String token, UserDetails userDetails) { 
		final String username = extractUsername(token); 
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
	}

  

} 

