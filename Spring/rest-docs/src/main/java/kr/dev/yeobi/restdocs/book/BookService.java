package kr.dev.yeobi.restdocs.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yeobi 2019.05.24
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDto.Response findById(Long id) {
        return bookRepository.findById(id)
                .map(BookDto.Response::of).get();
    }

}
