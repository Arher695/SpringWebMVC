package ru.netology.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final var converter = new GsonHttpMessageConverter();
        converter.setGson(gson());
        converters.add(converter);
    }
}
