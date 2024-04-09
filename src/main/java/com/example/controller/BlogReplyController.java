package com.example.controller;

import com.example.domain.BlogDTO;
import com.example.domain.BlogReplyDTO;
import com.example.service.BlogReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class BlogReplyController {
    private final BlogReplyService blogReplyService;

    @PostMapping("/new/{idx}")
    public ResponseEntity<Long> register(@PathVariable("idx") Long idx, @RequestBody BlogReplyDTO blogReplyDTO) {
        blogReplyDTO.setIdx(idx);
        Long rIdx = blogReplyService.save(blogReplyDTO);
        return new ResponseEntity<>(rIdx, HttpStatus.OK);
    }

    @GetMapping("/list/{idx}")
    public List<BlogReplyDTO> getRepliesList(@PathVariable("idx") Long idx) {
        return blogReplyService.getRepliesList(idx);
    }

    @PutMapping("/modify/{rIdx}")
    public ResponseEntity<String> modifyReply(@PathVariable Long rIdx, @RequestBody BlogReplyDTO blogReplyDTO) {
        blogReplyService.modifyReply(rIdx, blogReplyDTO);
        return ResponseEntity.ok("댓글 수정 완료!");
    }


    @DeleteMapping("/delete/{rIdx}")
    public ResponseEntity<String> deleteReplies(@PathVariable Long rIdx) {
        try {
            blogReplyService.delete(rIdx);

            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제에 실패했습니다.");
        }
    }

    @GetMapping("/count/{idx}")
    public ResponseEntity<Long> countRepliesByIdx(@PathVariable long idx) {
        long repliesCount = blogReplyService.countRepliesByIdx(idx);
        return ResponseEntity.ok(repliesCount);
    }
}
