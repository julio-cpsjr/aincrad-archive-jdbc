package br.com.cardinal.service;

import br.com.cardinal.model.StatusBook;
import br.com.cardinal.repository.BookRepository;

public class BookService {
    private static BookRepository bookRepository;

    public BookService(){
        bookRepository = new BookRepository();
    }

    public static void updateBook(int bookId, String statusBook){

        if(statusBook.equals("YES")){
            statusBook = "NO";
        }else {statusBook = "YES";}


        bookRepository.update(bookId, statusBook);
        System.out.println("Book status updated");
    }
}
