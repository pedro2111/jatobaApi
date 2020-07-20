package br.com.jatoba.config.secutiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.jatoba.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		
		Usuario logado = (Usuario) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API do forum alura")
				.claim("usuario", logado.getNome().toString())
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token); //se o token for valido ele retorna true
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
			
		}
	}

	public Long getIdUsuario(String token) {
		
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return  Long.parseLong(body.getSubject());
		
	}
	
	

}
