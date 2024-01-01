package com.team1060.golf.product.api.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchProductRequest {
    private String brand;
    private LocalDateTime regDate;
    private String sortBy;
    private String sortOrder;
    private int page;
    private int size;
    private String searchKeyword;
}
