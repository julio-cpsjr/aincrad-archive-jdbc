package br.com.cardinal.repository;

import br.com.cardinal.db.ConnectionFactory;
import br.com.cardinal.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    /* ======================
       CREATE
       ====================== */

    public void save(Loan loan){
        String sql = """
                INSERT INTO loan (book_id, user_id,date_loan,date_return_preview, date_return_real,status)
                VALUES (?, ?, ?, ?, ?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getBookId());
            stmt.setInt(2, loan.getUser_id());
            stmt.setDate(3, (Date) loan.getDate_loan());
            stmt.setDate(4, (Date) loan.getDate_return_preview());
            stmt.setDate(5, (Date) loan.getDate_return_real());
            stmt.setString(6, loan.getStatus().name());
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Error saving the loan", e);
        }
    }

    /* ======================
       READ (for ID)
       ====================== */

    public Loan getLoanId(int id)  {
        String sql = """
                SELECT * FROM loan WHERE id = ?;
        """;
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return  mapLoan(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error reading loan for ID",e);
        }
    }

    public List<Loan> getLoans()  {
        String sql = "SELECT * FROM loan";
        List<Loan> loans = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    loans.add(mapLoan(rs));
                }

                return loans;
        } catch (SQLException e) {
            throw new RuntimeException("Error listing loans",e);
        }

    }

    /* ======================
       UPDATE
       ====================== */

    public void update(int id, StatusLoan status) {
        String sql = """
                UPDATE loan
                SET  status = ?
                WHERE loan.id = ?;
        """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(6, status.name());
            stmt.setInt(7, id);

            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Error updating loan",e);
        }
    }

    /* ======================
       DELETE
       ====================== */

    public void delete(int id){
        String sql = """
                DELETE FROM loan WHERE id = ?;
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the loan", e);
        }
    }

    /* ======================
       UPDATE - Finished
       ====================== */

    public void finish(int loanId, String status, LocalDate finishDate) {
        String sql = """ 
                UPDATE loan
                SET status = ?, date_return_real = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, status);
            stmt.setDate(2, Date.valueOf(finishDate));
            stmt.setInt(3, loanId);

        }catch(SQLException e){
            throw new RuntimeException("Error finishing the loan",e);
        }
    }




    /* ======================
       Mapper
       ====================== */

    private Loan mapLoan(ResultSet rs) throws SQLException {
        return new Loan(
                rs.getInt("id"),
                rs.getInt("book_id"),
                rs.getInt("user_id"),
                rs.getDate("date_loan"),
                rs.getDate("date_return_preview"),
                rs.getDate("date_return_real"),
                StatusLoan.valueOf(rs.getString("status"))
        );
    }
}
