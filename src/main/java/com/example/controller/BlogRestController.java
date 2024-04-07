package com.example.controller;

import com.example.domain.BlogDTO;
import com.example.entity.Blog;
import com.example.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogRestController {
    private final BlogService blogService;

   @GetMapping("/list")
    public List<BlogDTO> getBlogList() {
       return blogService.getBlogList();
   }

   @PostMapping("/write")
    public void writeBlog(@RequestBody BlogDTO blogDTO) {
        blogService.writeBlog(blogDTO);
   }

    @GetMapping("/get/{idx}")
    public BlogDTO getBlog(@PathVariable("idx") Long idx) {
        return blogService.getBlog(idx);
    }

    @PutMapping("/edit/{idx}")
    public ResponseEntity<BlogDTO> editBlog(@PathVariable Long idx, @RequestBody BlogDTO dto) {
       return blogService.editBlog(idx, dto);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<String> deletePost(@PathVariable Long idx) {
        try {
            blogService.delete(idx);
            return ResponseEntity.ok("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제에 실패했습니다.");
        }
    }
}
