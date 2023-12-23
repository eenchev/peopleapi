package dev.evgeni.peopleapi.web;

import java.io.IOException;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class HeaderLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Remote Addr:" + request.getRemoteAddr());
        System.out.println("Remote Host:" + request.getRemoteHost());

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getHeader("SomeOurHeader") != null) {
            System.out.println("SomeOurHeader " + httpRequest.getHeader("SomeOurHeader"));
        }

        chain.doFilter(request, response);
    }

}
