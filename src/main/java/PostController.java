import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class PostController {
    @GetMapping
    public String getPosts("/post") {
        return "returns the post ";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable int id) {
        return "getting post with" + id;
    }

    @GetMapping("/post/create")
    public String getCreatePostForm() {
        return "Getting a created post form";
    }

    @PostMapping("/create")
    public String createPost() {
        return "A new post has been created";
    }
}
