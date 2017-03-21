package com.fcore.cloud.zuul;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.fcore.cloud.zuul.filter.AccessFilter;

/**
 * @SpringCloudApplication包含：
 * @SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
 *
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
	}
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	 @Bean
	    public ZuulFallbackProvider zuulFallbackProvider() {
	        return new ZuulFallbackProvider() {
	            @Override
	            public String getRoute() {
	                return "api-gateway";
	            }

	            @Override
	            public ClientHttpResponse fallbackResponse() {
	                return new ClientHttpResponse() {
	                    @Override
	                    public HttpStatus getStatusCode() throws IOException {
	                        return HttpStatus.OK;
	                    }

	                    @Override
	                    public int getRawStatusCode() throws IOException {
	                        return 200;
	                    }

	                    @Override
	                    public String getStatusText() throws IOException {
	                        return "OK";
	                    }

	                    @Override
	                    public void close() {

	                    }

	                    @Override
	                    public InputStream getBody() throws IOException {
	                        return new ByteArrayInputStream("fallback".getBytes());
	                    }

	                    @Override
	                    public HttpHeaders getHeaders() {
	                        HttpHeaders headers = new HttpHeaders();
	                        headers.setContentType(MediaType.APPLICATION_JSON);
	                        return headers;
	                    }
	                };
	            }
	        };
	    }
}
