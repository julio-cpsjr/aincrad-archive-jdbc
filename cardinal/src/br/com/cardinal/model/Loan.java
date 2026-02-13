package br.com.cardinal.model;

import java.util.Date;

public class Loan {
    private int id;
    private int bookId;
    private int user_id;
    private Date date_loan;
    private Date date_return_preview;
    private Date date_return_real;
    private StatusLoan status;


    //Constructor Full (Repository)
    public Loan(int id, int book_id, int user_id, Date date_loan, Date date_return_preview, Date date_return_real, StatusLoan status) {
        this.id = id;
        this.bookId = book_id;
        this.user_id = user_id;
        this.date_loan = date_loan;
        this.date_return_preview = date_return_preview;
        this.date_return_real = date_return_real;
        this.status = status;

    }

    //Constructor Lite (UI)
    public Loan(int book_id, int user_id, Date date_loan, Date date_return_preview, Date date_return_real, StatusLoan status) {
        this.bookId = book_id;
        this.user_id = user_id;
        this.date_loan = date_loan;
        this.date_return_preview = date_return_preview;
        this.date_return_real = date_return_real;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate_loan() {
        return date_loan;
    }

    public void setDate_loan(Date date_loan) {
        this.date_loan = date_loan;
    }

    public Date getDate_return_preview() {
        return date_return_preview;
    }

    public void setDate_return_preview(Date date_return_preview) {
        this.date_return_preview = date_return_preview;
    }

    public Date getDate_return_real() {
        return date_return_real;
    }

    public void setDate_return_real(Date date_return_real) {
        this.date_return_real = date_return_real;
    }

    public StatusLoan getStatus() {
        return status;
    }

    public void setStatus(StatusLoan status) {
        this.status = status;
    }
}
