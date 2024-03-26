package com.aad.agro.Config;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.aad.agro.Model.Users;
import com.aad.agro.Repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	    private String secretKey="57186FDFA7787454E3EF961375F7B57186FDFA7787454E3EF961375F7B";
		    @Autowired
    private UserRepository userRepository;

		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
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
	
		public String generateToken(String email) {
			Optional<Users> userop = userRepository.findByEmail(email);
			if (userop == null) {
				throw new UsernameNotFoundException("User not found with email: " + email);
			}
			Users user = userop.get();
			String role = user.getRole(); 
			Boolean fill= user.isFilled();
			Long id= user.getId();
			Map<String, Object> claims = new HashMap<>();
			claims.put("role", role); 
			claims.put("filled", fill); 
			claims.put("id", id); 
			return createToken(claims, email);
		}
	
		private String createToken(Map<String, Object> claims, String userName) {
			return Jwts.builder()
					.setClaims(claims)
					.setSubject(userName)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
					.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
		}
	
		private Key getSignKey() {
			byte[] keyBytes = Decoders.BASE64.decode(secretKey);
			return Keys.hmacShaKeyFor(keyBytes);
		}
    }
