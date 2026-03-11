package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        service.removeById(id);

    }
    //Принимает HTTP-запросы.
    //@RestController = @Controller + @ResponseBody → автоматически сериализует ответ в JSON.
    //@RequestMapping("/api/posts") — базовый путь для всех методов.
    //Каждый метод:
    //
    //GET /api/posts → возвращает список постов.
    //GET /api/posts/{id} → возвращает один пост.
    //POST /api/posts → создаёт или обновляет.
    //DELETE /api/posts/{id} → помечает как удалённый.
    //
    //Все данные автоматически конвертируются в/из JSON.
}
