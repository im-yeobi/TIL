package kr.dev.yeobi.restdocs.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yeobi 2019.05.24
 */
public class BookDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        @JsonProperty
        private Long id;

        @JsonProperty
        private String title;

        @JsonProperty
        private String author;

        @JsonProperty
        private Date publishedAt;

        @Builder
        public Response(Long id, String title, String author, Date publishedAt) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.publishedAt = publishedAt;
        }

        public static Response of(Book book) {
            return Response.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .publishedAt(book.getPublishedAt())
                    .build();
        }
    }

}
