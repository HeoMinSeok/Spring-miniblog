package com.example.entity;

import com.example.domain.BlogDTO;
import com.example.domain.BlogReplyDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rIdx;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Blog blog;


    public static BlogReply toEntity(BlogReplyDTO blogReplyDTO) {
        BlogReply blogReply = new BlogReply();

        blogReply.setRIdx(blogReplyDTO.getRIdx());
        blogReply.setContent(blogReplyDTO.getContent());

        if (blogReplyDTO.getIdx() != null) {
            Blog blog = new Blog();
            blog.setIdx(blogReplyDTO.getIdx());
            blogReply.setBlog(blog);
        }

        return blogReply;
    }
}
