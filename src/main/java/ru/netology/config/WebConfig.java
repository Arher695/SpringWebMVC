package ru.netology.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.netology")
public class WebConfig {

}
//@Configuration — класс является источником бинов.
//@EnableWebMvc — включает поддержку Spring Web MVC (обработка @RestController, JSON, etc).
//@ComponentScan("ru.netology") — сканирует все классы в пакете:
//
//Находит @Controller, @Service, @Repository → создаёт из них Spring-бины.
//
//Таким образом, PostController, PostService, PostRepository становятся бинами.
