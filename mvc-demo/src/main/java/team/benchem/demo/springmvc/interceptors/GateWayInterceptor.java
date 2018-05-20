package team.benchem.demo.springmvc.interceptors;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class GateWayInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String clientToken = request.getHeader("Suf-Token");
        String httpMethod = request.getMethod();
        String servicePath = request.getRequestURI();
        String requestBody;
        if("POST".equals(httpMethod)){
            requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } else if("GET".equals(httpMethod)){
            requestBody = request.getQueryString();
        } else {
            requestBody  = "<null>";
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("statecode", 0);
        responseBody.put("clientToken", clientToken);
        responseBody.put("httpMethod", httpMethod);
        responseBody.put("servicePath", servicePath);
        responseBody.put("requestBody", requestBody);
        response.setContentType("application/json");
        response.getWriter().write(responseBody.toJSONString());
        response.getWriter().flush();

        return false;
    }

}
