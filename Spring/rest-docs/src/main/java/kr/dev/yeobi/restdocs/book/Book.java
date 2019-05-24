package kr.dev.yeobi.restdocs.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yeobi 2019.05.24
 */
@Getter
@Setter
@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    @Column
    private Long id;

    @JsonProperty
    @Column
    private String title;

    @JsonProperty
    @Column
    private String author;

    @JsonProperty
    @Column(name = "published_at")
    private Date publishedAt;

}
