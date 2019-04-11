/**
 * 
 */
package br.com.gleisonandrade.bancoapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gleisonandrade.bancoapi.util.SimpleMessageUtil;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Autowired
	private SimpleMessageUtil simpleMessageUtil;
	
	
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.useDefaultResponseMessages(false)
        	.globalResponseMessage(RequestMethod.GET, simpleMessageUtil.getGlobalResponseMessage(RequestMethod.GET))
			.globalResponseMessage(RequestMethod.POST, simpleMessageUtil.getGlobalResponseMessage(RequestMethod.GET))
			.globalResponseMessage(RequestMethod.PUT, simpleMessageUtil.getGlobalResponseMessage(RequestMethod.GET))
			.globalResponseMessage(RequestMethod.DELETE, simpleMessageUtil.getGlobalResponseMessage(RequestMethod.GET))
        	.select()
            .apis(RequestHandlerSelectors.basePackage("br.com.gleisonandrade.bancoapi.resources"))
            .paths(PathSelectors.any())          
            .build()
            .apiInfo(apiEndPointsInfo());
    }
    
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Sistema Bancário Simplificado com Spring Boot 2")
            .description("Sistema simplicado que realiza o gerenciamento de bancos e principais operações.")
            .contact(new Contact("Gleison Andrade", "https://github.com/gleisonandrade", "gleisondeandradeesilva@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
    
    
}