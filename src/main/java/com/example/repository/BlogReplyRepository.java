package com.example.repository;

import com.example.entity.BlogReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogReplyRepository extends JpaRepository<BlogReply, Long> {
    List<BlogReply> findByBlog_idx(Long idx);
}
