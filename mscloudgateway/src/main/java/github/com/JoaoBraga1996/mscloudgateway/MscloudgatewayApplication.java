package github.com.JoaoBraga1996.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);

	}

	@Bean
	RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/clientes/**").uri("lb://mscliente"))
				.route(r -> r.path("/contas/**").uri("lb://msconta"))
				.route(r -> r.path("/cartoes/**").uri("lb://msconta"))
		        .route(r -> r.path("/seguros/**").uri("lb://msseguro")).build();

	}
}
