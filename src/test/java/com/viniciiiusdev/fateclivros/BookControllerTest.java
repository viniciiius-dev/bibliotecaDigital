package com.viniciiiusdev.fateclivros;


import com.viniciiiusdev.fateclivros.controller.BookController;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    private static final Logger log = LoggerFactory.getLogger(BookControllerTest.class);
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BookServiceImpl bookService;
    List<Book> books = new ArrayList<>();

    @BeforeEach
    public void setup(){
        Book book1 = new Book();
        book1.setName("The Great Gatsby");
        book1.setAuthor("F. Scott Fitzgerald");
        book1.setPublisher("Scribner");
        book1.setLanguage("English");
        book1.setYear(1925);
        book1.setPages(180);
        book1.setIsbn("9780743273565");

        Book book2 = new Book();
        book2.setName("1984");
        book2.setAuthor("George Orwell");
        book2.setPublisher("Secker & Warburg");
        book2.setLanguage("English");
        book2.setYear(1949);
        book2.setPages(328);
        book2.setIsbn("9780451524935");

        Book book3 = new Book();
        book3.setName("To Kill a Mockingbird");
        book3.setAuthor("Harper Lee");
        book3.setPublisher("J.B. Lippincott & Co.");
        book3.setLanguage("English");
        book3.setYear(1960);
        book3.setPages(281);
        book3.setIsbn("9780061120084");

        books.add(book1);
        books.add(book2);
        books.add(book3);

    }
    @Test
    @DisplayName("Get BookList")
    public void testGetBooks_returnListOfBooks() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(books);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book").
                accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3));

    }
    @Test
    @DisplayName("Get book by valid ISBN")
    public void testGetBookByIsbn_whenValidIsbn_returnBook() throws Exception {
        String isbn = "9780743273565";
        Mockito.when(bookService.getBookByIsbn(isbn)).thenReturn(books.getFirst());

        RequestBuilder request = MockMvcRequestBuilders.get("/book/9780743273565").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.isbn").value(isbn)).
                andExpect(jsonPath("$.name").value("The Great Gatsby"));

    }
}
