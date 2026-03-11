package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong lastId = new AtomicLong(0);


    public Map<Long, Post> all() {
        return posts.entrySet().stream()
                .filter(entry -> !entry.getValue().isRemoved())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id))
                .filter(post -> !post.isRemoved());
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            // Создание нового поста
            long newId = lastId.incrementAndGet();
            final var newPost = new Post(newId, post.getContent());
            posts.put(newId, newPost);
            return newPost;
        } else {
            // Обновление существующего
            Post existing = posts.get(post.getId());
            if (existing == null || existing.isRemoved()) {
                throw new NotFoundException("Post with id " + post.getId() + " does not exist or was removed");
            }
            posts.put(post.getId(), post);
            return post;
        }
    }

    public void removeById(long id) {
        Post post = posts.get(id);
        if (post == null) {
            throw new NotFoundException("Post with id " + id + " not found");
        }
        post.setRemoved(true); // помечаем как удалённый
    }

    /*//восстанавливать посты
    public void restoreById(long id) {
        Post post = posts.get(id);
        if (post == null) throw new NotFoundException("...");
        post.setRemoved(false);
    }*/
}
//Хранит посты в памяти (ConcurrentHashMap — потокобезопасно).
//Генерирует ID (AtomicLong).
//Реализует мягкое удаление: removed = true, но объект остаётся в Map.
//
//Нет @Repository — но работает, так как @ComponentScan видит его через PostService.
