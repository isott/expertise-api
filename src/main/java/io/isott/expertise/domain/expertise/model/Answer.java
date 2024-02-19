package io.isott.expertise.domain.expertise.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Answer {

    private Long questionId;
    private IssueStatus hasIssue;
    private String description;
    private List<String> photoUrls;

    public enum IssueStatus {
        YES,
        NO
    }
}
