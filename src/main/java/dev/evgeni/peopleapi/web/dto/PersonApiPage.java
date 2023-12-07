package dev.evgeni.peopleapi.web.dto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"meta", "content"})
public class PersonApiPage<T> {

    private List<T> content;

    @JsonProperty("metadata")
    private PaginationMetadata meta;

    public PersonApiPage(Page<T> springPage) {
        this.content = springPage.getContent();
        this.meta = PaginationMetadata.builder().currPage(springPage.getPageable().getPageNumber())
                .totalPages(springPage.getTotalPages()).pageSize(springPage.getSize())
                .totalElements(springPage.getTotalElements()).build();
    }

    public PersonApiPage(List<T> pageWithoutMeta, PageRequest pageRequest) {
        this.content = pageWithoutMeta;
        this.meta = PaginationMetadata.builder().currPage(pageRequest.getPageNumber())
                .pageSize(pageRequest.getPageSize()).build();
    }

    @Getter
    @Builder
    private static class PaginationMetadata {
        private final Integer currPage;
        private final Integer totalPages;
        private final Integer pageSize;
        private final Long totalElements;
    }

}
