package br.com.cardinal.ui;

import br.com.cardinal.model.*;
import br.com.cardinal.repository.BookRepository;
import br.com.cardinal.repository.LoanRepository;
import br.com.cardinal.repository.UserRepository;
import br.com.cardinal.service.BookService;
import br.com.cardinal.service.LoanService;
import br.com.cardinal.service.UserService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuConsole {

    private final Scanner scanner = new Scanner(System.in);
    private final BookRepository bookRepo = new BookRepository();
    private final LoanRepository loanRepo = new LoanRepository();
    private final UserRepository userRepo = new UserRepository();

    public void begin() {
        int opcao;

        do {
            displayMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> registerBook();
                case 2 -> registerUser();
                case 3 -> registerLoan();
                case 4 -> listBook();
                case 5 -> listUser();
                case 6 -> listLoan();
                case 7 -> changeBook();
                case 8 -> changeUser();
                case 9 -> changeLoan();
                case 10 -> finishLoan();
                case 0 -> System.out.println("Shutdown the system...");
                default -> System.out.println("Invalid Option!");
            }

        } while (opcao != 0);
    }

    private void displayMenu() {
        System.out.println("""
            === ARCHIVE SWORD ART ONLINE ===
            1 - Register Book
            2 - Register User
            3 - Register Loan
            4 - List Books
            5 - List Users
            6 - List Loans
            7 - Change Book Status
            8 - Change User Status
            9 - Change Loan Status
            10 - Finish Loan
            0 - Exit
        """);
    }

    /* =====================
       Actions
       ===================== */

    private void registerBook() {
        System.out.print("Title: ");
        String nomeBook = scanner.nextLine();
        System.out.print("Author: ");
        String authorBook = scanner.nextLine();
        System.out.print("Description: ");
        String descriptionBook = scanner.nextLine();
        System.out.print("Category: ");
        String categoryBook = scanner.nextLine();
        System.out.print("AVAILABLE (YES,NO) ");
        StatusBook statusBook = StatusBook.valueOf(scanner.nextLine().toUpperCase());

        Book book = new Book(
                nomeBook, authorBook, descriptionBook, categoryBook, statusBook
        );
        bookRepo.save(book);

        System.out.println("the book is registered! \n");
    }

    private void registerUser() {
        System.out.print("Username: ");
        String nomeUser = scanner.nextLine();

        System.out.print("Email: ");
        String emailUser = scanner.nextLine();

        System.out.print("STATUS (ACTIVE, BLOCKED) ");
        StatusUser statusOfUser = StatusUser.valueOf(scanner.nextLine().toUpperCase());

        User user = new User(
                nomeUser, emailUser,statusOfUser
        );

        userRepo.save(user);
        System.out.println("User is registered! \n");
    }

    private void registerLoan() {
        System.out.print("ID of book: ");
        int bookId = scanner.nextInt();
        System.out.print("ID of User: ");
        int userId = scanner.nextInt();
        System.out.println("Date of register: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        Date date_loan = Date.valueOf(LocalDate.now());
        System.out.println("Expected Return Date: ");
        int daysDate = scanner.nextInt();
        Date date_return_preview = Date.valueOf(LocalDate.now().plusDays(daysDate));
        System.out.println("Actual return Date: ");
        int daysDateReturn = scanner.nextInt();
        Date date_return_real = Date.valueOf(LocalDate.now().plusDays(daysDate+daysDateReturn));
        System.out.println(date_return_real);
        System.out.println("STATUS (ACTIVE,FINISHED,LATE) ");

        StatusLoan statusLoan = StatusLoan.ACTIVE;

        Loan loan = new Loan(
                bookId, userId,date_loan,date_return_preview,date_return_real,statusLoan
        );

        loanRepo.save(loan);
        System.out.println("Loan is registered! \n");
    }

    private void listBook() {
        bookRepo.listBooks()
                .forEach(g -> System.out.println(
                        " ID "+g.getId() + " - Title " + g.getTitle() + " -  Description " + g.getDescription() + " - Available " + g.getAvailable() + "\n"
                ));
    }
    private void listUser() {
        userRepo.listUsers()
                .forEach(g -> System.out.println(
                        " ID "+g.getId() + " - Usuário " + g.getNome() + " -  Email " + g.getEmail() + " - Status " + g.getStatus() + "\n"
                ));
    }

    private void listLoan() {
        loanRepo.getLoans()
                .forEach(g -> System.out.println(
                        " Book ID "+g.getBookId() + " - User ID " + g.getUser_id() + " -  Date Loan " + g.getDate_loan() + " - Status " + g.getStatus() + "\n"
                ));
    }
    private void changeBook() {
        System.out.print("ID of book: ");
        int bookId = scanner.nextInt();
        System.out.print("Avaliable Book: " + bookRepo.getBookId(bookId).getAvailable());
        String statusId = String.valueOf(bookRepo.getBookId(bookId).getAvailable());
        BookService.updateBook(bookId,statusId);
        System.out.println("New Available Book: " + bookRepo.getBookId(bookId).getAvailable());
    }
    private void changeUser() {
        System.out.print("ID of user: ");
        int userId = scanner.nextInt();
        System.out.println("Status current: " + userRepo.getUserId(userId).getStatus());
        String statusId = String.valueOf(userRepo.getUserId(userId).getStatus());
        UserService.updateUser(userId,statusId);
        System.out.print("New Status: " + userRepo.getUserId(userId).getStatus() +"\n");
    }
    private void changeLoan() {
        System.out.print("ID of book: ");
        int bookId = scanner.nextInt();
        System.out.print("Status Loan: ");
        StatusLoan loanStatus = StatusLoan.valueOf( scanner.nextLine().toUpperCase());

        LoanService.updateLoan(bookId,loanStatus);
    }
    private void finishLoan() {
        System.out.print("ID of Loan: ");
        int loanId = scanner.nextInt();

        LoanService.finishedLoan(loanId);
    }

}
