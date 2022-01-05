package cn.bhshare.kg.config;

import cn.bhshare.kg.hook.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 通过@Bean注解，将我们定义的拦截器注册到Spring容器
     * @return
     */
    @Bean
    public UserLoginInterceptor loginInterceptor(){
        return new UserLoginInterceptor();
    }

    /**
     * 重写接口中的addInterceptors方法，添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过registry来注册拦截器，通过addPathPatterns来添加拦截路径,excludePathPatterns排除路径
//        registry.addInterceptor(this.loginInterceptor()).addPathPatterns("/modify**","/do_modify**","/doheadicon","/headicon").excludePathPatterns();
//        //管理员登录
//        registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/admin/logout","/admin/dologin","/admin/getGifCode","/admin/error");

    }



}
