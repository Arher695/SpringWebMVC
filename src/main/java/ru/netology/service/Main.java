package ru.netology.service;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.netology.config.WebConfig;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", baseDir.getAbsolutePath());

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);
        applicationContext.refresh();

        DispatcherServlet servlet = new DispatcherServlet(applicationContext);
        Tomcat.addServlet(context, "dispatcher", servlet);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
        System.out.println("Server started at http://localhost:8080");
        tomcat.getServer().await();
    }
}
//Создаётся встроенный Tomcat.
//Устанавливается порт 8080.
//Добавляется веб-контекст (корневой путь /).
//Создаётся Spring-контекст (AnnotationConfigWebApplicationContext).
//В контекст регистрируется конфигурация WebConfig.class.
//Контекст инициализируется (refresh()).
//Создаётся DispatcherServlet — главный сервлет Spring MVC.
//Сервлет регистрируется в Tomcat под именем "dispatcher" и привязывается к пути /.
//Tomcat стартует и ожидает запросы.
