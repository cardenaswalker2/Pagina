package com.agency.platform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String clientEmail;
    private String status; // PLANNING, IN_PROGRESS, TESTING, COMPLETED
    private int progress; // 0 to 100
    private String description;
    private double price;
    private List<Milestone> timeline;
    private List<String> documents;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Milestone {
        private String title;
        private String date;
        private boolean completed;
    }
}
