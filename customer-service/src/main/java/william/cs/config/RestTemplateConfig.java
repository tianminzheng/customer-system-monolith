package william.cs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author ZhangShenao
 * @date 2023/7/20 2:06 PM
 * @description: RestTemplate配置类
 */
@Configuration
public class RestTemplateConfig {
    
    //注入RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        //设置超时时间
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(3000);
//        httpRequestFactory.setConnectionRequestTimeout(3000);
//        httpRequestFactory.setReadTimeout(3000);
//        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        
        //指定消息转换器
//        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter(); //使用Gson进行json格式的数据转换
//        restTemplate.setMessageConverters(Collections.singletonList(gsonHttpMessageConverter));
        
        //设置拦截器
        //restTemplate.setInterceptors();
        
        //设置异常处理机制
        //restTemplate.setErrorHandler();
        return new RestTemplate();
    }
}
