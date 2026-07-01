package com.agency.platform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "blogs")
public class Blog {
    @Id
    private String id;
    private String title;
    private String slug;
    private String summary;
    private String content;
    private String category;
    private List<String> tags;
    private String author;
    private LocalDateTime createdAt;
}
