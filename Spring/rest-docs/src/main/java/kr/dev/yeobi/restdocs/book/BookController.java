package kr.dev.yeobi.restdocs.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeobi 2019.05.24
 */
@RequestMapping("/book")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @GetMapping(value = "/{id}")
    public BookDto.Response findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

}
