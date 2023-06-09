package com.codeup.codeupspringblog.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//The above allows you to have access to the constructors. as well as getters and setters
@Table(name="posts")
public class Post {

    //From now on when we create this model which is similar to the bean we are creating our table as well as setting the fields for our bean and the columns of the table.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //this is just setting the primary key and having it auto increment

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;
    //This creates the column title
    //column defintion puts the type

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    //This creates the column body

    public Post(String title,String body){
        this.title = title;
        this.body = body;
    }
}
