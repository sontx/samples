package dev.sontx.samples.multitenancysharedschema.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TenantContextHolder.clear();
        if (request instanceof HttpServletRequest httpServletRequest) {
            var tenantId = httpServletRequest.getHeader("X-Tenant-ID");
            TenantContextHolder.setTenantId(tenantId);
        }
        chain.doFilter(request, response);
    }
}
