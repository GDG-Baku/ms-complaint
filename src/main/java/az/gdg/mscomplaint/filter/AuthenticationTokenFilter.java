package az.gdg.mscomplaint.filter;

import az.gdg.mscomplaint.client.AuthenticationClient;
import az.gdg.mscomplaint.model.client.auth.UserInfo;
import az.gdg.mscomplaint.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static az.gdg.mscomplaint.model.client.auth.HttpHeader.X_AUTH_TOKEN;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private AuthenticationClient authenticationClient;

    @Autowired
    public void setAuthenticationClient(AuthenticationClient authenticationClient) {
        this.authenticationClient = authenticationClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String authToken = request.getHeader(X_AUTH_TOKEN);
            if (authToken != null) {
                UserInfo userInfo = authenticationClient.validateToken(authToken);
                if (userInfo == null) {
                    throw new RuntimeException("User info is not valid");
                } else {
                    UserAuthentication userAuthentication = new UserAuthentication(userInfo.getUserId(),
                            true,
                            userInfo.getRole());
                    SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                }
            }
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}
