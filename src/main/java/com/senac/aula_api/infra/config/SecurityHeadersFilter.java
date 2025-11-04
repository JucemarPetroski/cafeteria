package com.senac.aula_api.infra.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro responsável por adicionar cabeçalhos de segurança e cache em todas as respostas.
 * Observação: não forçamos Content-Length=0 globalmente porque isso pode invalidar respostas com corpo.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityHeadersFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Cache-control: sem cache, sem armazenamento, idade máxima = 0, deve ser revalidado
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // Connection / Keep-Alive
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Keep-Alive", "timeout=60");

        // Segurança de conteúdo
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        // X-XSS-Protection está deprecated em muitos navegadores; 0 para desabilitar
        response.setHeader("X-XSS-Protection", "0");

        // Vary header
        response.setHeader("Vary", "Origin, Access-Control-Request-Method, Access-Control-Request-Headers");

        // Nota: não setamos Content-Length explicitamente aqui para evitar problemas com corpo de resposta.

        filterChain.doFilter(request, response);
    }
}
