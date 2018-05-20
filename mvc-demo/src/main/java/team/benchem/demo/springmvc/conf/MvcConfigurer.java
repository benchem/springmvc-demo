package team.benchem.demo.springmvc.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.benchem.demo.springmvc.interceptors.GateWayInterceptor;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Autowired
    GateWayInterceptor gateWayInterceptor;

    //跨域访问配置
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://scm.lonntec.cn", "https://scm.lonntec.cn")
                .allowedMethods("GET", "POST");
    }

    //拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gateWayInterceptor)
                .addPathPatterns("/**");
    }
}
