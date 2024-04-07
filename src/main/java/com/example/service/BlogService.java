package com.example.service;

import com.example.domain.BlogDTO;
import com.example.entity.Blog;
import com.example.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public List<BlogDTO> getBlogList() {
        List<Blog> blogList = blogRepository.findAllByOrderByIdxDesc();

        return blogList.stream().map(blog -> BlogDTO.toDTO(blog)).collect(Collectors.toList());
    }

    public void writeBlog(BlogDTO dto) {
        Blog blog = Blog.toEntity(dto);

        blogRepository.save(blog);
    }

    public BlogDTO getBlog(Long idx) {
        return BlogDTO.toDTO(blogRepository.findById(idx).orElseThrow(() -> new NullPointerException("blog null!")));
    }

    public ResponseEntity<BlogDTO> editBlog(Long idx, BlogDTO dto) {
        Blog blog = blogRepository.findById(idx)
                .orElseThrow(() -> new NullPointerException("blog null!"));

        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());

        blogRepository.save(blog);

        BlogDTO blogDTO = BlogDTO.toDTO(blog);
        return new ResponseEntity<>(blogDTO, HttpStatus.OK);
    }

    public void delete(Long idx) {
        blogRepository.deleteById(idx);
    }
}
