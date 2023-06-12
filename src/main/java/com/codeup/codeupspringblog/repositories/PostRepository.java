package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByTitle(String title);
}
// this interface extends the JpaRepository which allows us to access crud functionality. it allows us to use things like .all or .insert which was made manually previously