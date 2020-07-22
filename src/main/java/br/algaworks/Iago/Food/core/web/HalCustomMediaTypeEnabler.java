package br.algaworks.Iago.Food.core.web;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

// Fonte: https://github.com/spring-projects/spring-hateoas/issues/263#issuecomment-358969098

/**
 * Classe criada para ajustar um problema do Spring ao utilizar o padrão HAL quando utilizamos
 * MediaTypes customizados.
 */
@Component
public class HalCustomMediaTypeEnabler {
	
    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    public HalCustomMediaTypeEnabler(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    public void enableVndHalJson() {
        for (HttpMessageConverter<?> converter : requestMappingHandlerAdapter.getMessageConverters()) {
            if (converter instanceof MappingJackson2HttpMessageConverter 
            		&& converter.getSupportedMediaTypes().contains(MediaTypes.HAL_JSON)) {
                
            	MappingJackson2HttpMessageConverter messageConverter = (MappingJackson2HttpMessageConverter) converter;
                messageConverter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON, 
                		IagoMediaTypes.V1_APPLICATION_JSON, IagoMediaTypes.V2_APPLICATION_JSON));
            }
        }
    }
    
}