package kr.dev.yeobi.restdocs.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yeobi 2019.05.24
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
