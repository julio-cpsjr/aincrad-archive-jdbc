package br.com.cardinal.service;

import br.com.cardinal.model.*;
import br.com.cardinal.repository.BookRepository;
import br.com.cardinal.repository.LoanRepository;
import br.com.cardinal.repository.UserRepository;

import java.time.LocalDate;

public class LoanService {
    private final BookRepository bookRepository;
    private static LoanRepository loanRepository;
    private final UserRepository userRepository;

    public LoanService() {
        bookRepository = new BookRepository();
        loanRepository = new LoanRepository();
        userRepository = new UserRepository();
    }

    public void checkLoan(int bookId, int userId,int loanId ,Loan loan) {
        Book book = bookRepository.getBookId(bookId);
        User user = userRepository.getUserId(userId);
        StatusUser statusUser = StatusUser.ACTIVE;
        StatusBook statusBook = StatusBook.YES;


        if(book.getAvailable() != statusBook ){
            throw new RuntimeException("Book is not avaliable");
        }
        if(user.getStatus()!= statusUser ){
            throw new RuntimeException("User is blocked");
        }


        //bookRepository.update(bookId,StatusBook.YES);
        //userRepository.update(userId,StatusUser.ACTIVE);
        loanRepository.update(loanId,StatusLoan.ACTIVE);
    }

    public static void updateLoan(int bookId, StatusLoan statusLoan){
        loanRepository.update(bookId, statusLoan);
        System.out.println("Loan status updated");
    }


    public static void finishedLoan(int loanId){
        Loan loan = loanRepository.getLoanId(loanId);
        StatusLoan statusLoan = StatusLoan.ACTIVE;

        if (loan.getStatus() != statusLoan){
            throw new RuntimeException("Loan status is not active");
        }

        loanRepository.finish(
                loanId,
                "FINISHED",
                LocalDate.now()
        );
    }
}
