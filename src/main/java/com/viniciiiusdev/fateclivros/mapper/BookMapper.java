package com.viniciiiusdev.fateclivros.mapper;

import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.service.BookService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Component
public class BookMapper {

    public List<BookListFrontendDTO> toFrontendBookList(Collection<Book> bookList) {
        ArrayList<BookListFrontendDTO> finalBookList = new ArrayList<>();
        for (Book book : bookList){
            String status;
            if (book.getBookStock() > 0){
                status = "disponível";
            }
            else{
                status = "indisponível";
            }
            finalBookList.add(new BookListFrontendDTO(
                    book.getBookId(),
                    book.getImageUrl(),
                    book.getGenre(),
                    book.getName(),
                    book.getAuthor(),
                    book.getDescription(),
                    status,
                    book.getPublisher(),
                    book.getLanguage(),
                    book.getYear(),
                    book.getPages(),
                    book.getIsbn(),
                    book.getBookStock()

            ));
        }
        finalBookList.sort(Comparator.comparing(BookListFrontendDTO::id));
        return finalBookList;
    }
}
