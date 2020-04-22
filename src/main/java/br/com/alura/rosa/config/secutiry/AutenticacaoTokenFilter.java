package br.com.alura.rosa.config.secutiry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.rosa.modelo.Usuario;
import br.com.alura.rosa.repository.UsuarioRepository;


public class AutenticacaoTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;
	
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		//System.out.println("token = " + token);
		boolean tokenValid = tokenService.isTokenValid(token);
		//System.out.println(tokenValid);
		
		if(tokenValid) {
			
			Long idUsuario = tokenService.getIdUsuario(token);
			Usuario usuario = usuarioRepository.findById(idUsuario).get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length()); //7 é oara retirar a parte do bearer
	}

}