package com.example.service;

import com.example.domain.BlogReplyDTO;
import com.example.entity.BlogReply;
import com.example.repository.BlogReplyRepository;
import com.example.repository.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogReplyService {
    private final BlogReplyRepository blogReplyRepository;
    private final BlogRepository blogRepository;

    public Long save(BlogReplyDTO blogReplyDTO) {
        BlogReply blogReply = blogReplyRepository.save(BlogReply.toEntity(blogReplyDTO));
        return blogReply.getRIdx();
    }

    public List<BlogReplyDTO> getRepliesList(Long idx) {
        List<BlogReply> blogReplyList = blogReplyRepository.findByBlog_idx(idx);

        return blogReplyList.stream().map(BlogReplyDTO::toDTO).collect(Collectors.toList());
    }

    public void delete(Long rIdx) {
        blogReplyRepository.deleteById(rIdx);
    }


    public void modifyReply(Long rIdx, BlogReplyDTO blogReplyDTO) {
        BlogReply blogReply = blogReplyRepository.findById(rIdx)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        // 수정할 내용이 있다면 적용
        if (blogReplyDTO.getContent() != null) {
            blogReply.setContent(blogReplyDTO.getContent());
        }

        blogReplyRepository.save(blogReply);
    }
}
