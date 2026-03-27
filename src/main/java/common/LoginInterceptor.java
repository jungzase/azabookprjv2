package common;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") == null) {
            String requestUri = request.getRequestURI().substring(request.getContextPath().length());
            String queryString = request.getQueryString();
            String target = requestUri + (queryString == null ? "" : "?" + queryString);

            response.sendRedirect(
                request.getContextPath()
                    + "/member/login?redirectURL="
                    + URLEncoder.encode(target, StandardCharsets.UTF_8.name())
            );
            return false;
        }
        return true;
    }
}


