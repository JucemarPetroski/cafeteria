package com.senac.aula_api.infra.config;

import com.senac.aula_api.application.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import java.util.List;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private com.senac.aula_api.domain.repository.UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

    if (path.equals("/auth/login")
        || path.equals("/usuario/login")
        || path.equals("/usuario/cadastrese")
        || path.startsWith("/swagger")
        || path.startsWith("/v3/api-docs")
        || path.startsWith("/webjars")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            try {
                // validar token e recuperar subject (email)
                var subject = tokenService.validarToken(tokenJWT);
                if (subject == null || subject.isBlank()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("{\"error\":\"Token inválido\"}");
                    return;
                }
                var usuarioOpt = usuarioRepository.findByEmail(subject);
                if (usuarioOpt.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("{\"error\":\"Usuário não encontrado\"}");
                    return;
                }
                var usuario = usuarioOpt.get();
                // popular SecurityContext para que controllers e outras partes do Spring
                // possam obter o usuário autenticado via SecurityContextHolder
                // mapear role (string) para GrantedAuthority
                String role = usuario.getRole() != null ? usuario.getRole() : "ROLE_USER";
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // também expor o usuário no request para uso direto nas controllers
                request.setAttribute("usuario", usuario);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\":\"Token inválido ou expirado\"}");
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Token não informado!");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
