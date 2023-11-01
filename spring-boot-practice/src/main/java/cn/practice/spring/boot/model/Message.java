package cn.practice.spring.boot.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Message {
    private Long id;

    @NotEmpty(message = "Text is required.")
    private String text;

    @NotEmpty(message = "Summary is required.")
    private String summary;

    private LocalDateTime created = LocalDateTime.now();
}
