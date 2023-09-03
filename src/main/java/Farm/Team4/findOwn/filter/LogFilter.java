package Farm.Team4.findOwn.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter 진입");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();
        String randomId = UUID.randomUUID().toString();

        try{
            log.info("request: randomId-[{}] requestURL-[{}]", randomId, requestURI);
            chain.doFilter(request, response);
        } catch(Exception e) {
            throw e;
        }finally {
            log.info("log filter 종료");
        }
    }

    @Override
    public void destroy() {
        log.info("log filter 종료");
    }
}
