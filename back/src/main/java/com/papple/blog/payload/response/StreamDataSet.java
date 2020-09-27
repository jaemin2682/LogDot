package com.papple.blog.payload.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.papple.blog.models.User;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StreamDataSet {

    private User user;
    private SseEmitter emitter;

    public StreamDataSet(User user, SseEmitter emitter){
        this.user = user;
        this.emitter = emitter;
    }

}