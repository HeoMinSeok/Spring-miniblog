package com.example.domain;

import com.example.entity.BlogReply;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// BlogReplyDTO 클래스
@Getter
@Setter
@Builder
public class BlogReplyDTO {
    private Long rIdx;
    private String content;
    private Long idx;

    public static BlogReplyDTO toDTO(BlogReply blogReply) {
        if (blogReply == null) {
            return null;
        }
        return BlogReplyDTO.builder()
                .rIdx(blogReply.getRIdx())
                .content(blogReply.getContent())
                .build();
    }
}

