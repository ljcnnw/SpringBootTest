package com.testspringboot.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UrlPathHelper;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String DEFAULT_PATH = "/pub/swagger";

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();

    }

    /**
     * SwaggerUI资源访问
     *
     * @param servletContext
     * @param order
     * @return
     * @throws Exception
     */
//    @Bean
//    public SimpleUrlHandlerMapping swaggerUrlHandlerMapping(ServletContext servletContext,
//                                                            @Value("${swagger.mapping.order:10}") int order) throws Exception {
//        SimpleUrlHandlerMapping urlHandlerMapping = new SimpleUrlHandlerMapping();
//        Map<String, ResourceHttpRequestHandler> urlMap = new HashMap<>();
//        {
//            PathResourceResolver pathResourceResolver = new PathResourceResolver();
//            pathResourceResolver.setAllowedLocations(new ClassPathResource("META-INF/resources/webjars/"));
//            pathResourceResolver.setUrlPathHelper(new UrlPathHelper());
//
//            ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
//            resourceHttpRequestHandler.setLocations(Arrays.asList(new ClassPathResource("META-INF/resources/webjars/")));
//            resourceHttpRequestHandler.setResourceResolvers(Arrays.asList(pathResourceResolver));
//            resourceHttpRequestHandler.setServletContext(servletContext);
//            resourceHttpRequestHandler.afterPropertiesSet();
//            //设置新的路径
//            urlMap.put(DEFAULT_PATH + "/webjars/**", resourceHttpRequestHandler);
//        }
//        {
//            PathResourceResolver pathResourceResolver = new PathResourceResolver();
//            pathResourceResolver.setAllowedLocations(new ClassPathResource("META-INF/resources/"));
//            pathResourceResolver.setUrlPathHelper(new UrlPathHelper());
//
//            ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
//            resourceHttpRequestHandler.setLocations(Arrays.asList(new ClassPathResource("META-INF/resources/")));
//            resourceHttpRequestHandler.setResourceResolvers(Arrays.asList(pathResourceResolver));
//            resourceHttpRequestHandler.setServletContext(servletContext);
//            resourceHttpRequestHandler.afterPropertiesSet();
//            //设置新的路径
//            urlMap.put(DEFAULT_PATH + "/**", resourceHttpRequestHandler);
//        }
//        urlHandlerMapping.setUrlMap(urlMap);
//        //调整DispatcherServlet关于SimpleUrlHandlerMapping的排序
//        urlHandlerMapping.setOrder(order);
//        return urlHandlerMapping;
//    }

}
