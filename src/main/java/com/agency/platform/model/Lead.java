package com.agency.platform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "leads")
public class Lead {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String interest;
    private String message;
    private LocalDateTime createdAt;
}
