package ru.netology.initializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {
    private static final String CONTEXT_DIR = "ru.netology";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Создаём обычный контекст и сканируем пакет
        final var context = new AnnotationConfigApplicationContext();
        context.scan(CONTEXT_DIR);
        context.refresh();

        // Оборачиваем в GenericWebApplicationContext (реализует WebApplicationContext)
        final var webAppContext = new GenericWebApplicationContext();
        webAppContext.setParent(context);
        webAppContext.refresh();

        final var servlet = new DispatcherServlet(webAppContext);
        final var registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
