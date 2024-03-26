package com.example.demo.security;
import javax.servlet.Filter; 
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MonFiltre implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
            throws IOException, ServletException {
        // Logique de prétraitement si nécessaire

        // Passer la requête et la réponse à la chaîne de filtres suivante
        filterChain.doFilter(servletRequest, servletResponse);

        // Logique de post-traitement si nécessaire
    }

    @Override
    public void destroy() {
        // Destruction du filtre
    }
}
