package br.com.cardinal.repository;

import br.com.cardinal.db.ConnectionFactory;
import br.com.cardinal.model.Book;
import br.com.cardinal.model.StatusBook;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    /* ======================
       CREATE
       ====================== */
    public void save(Book book) {
        String sql = """
                INSERT INTO book (title, author, description, category,available)
                VALUES (?,?,?,?,?)
               """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getDescription());
            stmt.setString(4, book.getCategory());
            stmt.setString(5, book.getAvailable().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving the book", e);
        }
    }

    /* ======================
       READ (por ID)
       ====================== */
    public Book getBookId(int id) {
        String sql = """
                SELECT * FROM book WHERE id = ?;
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return mapBook(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the book id", e);
        }
    }

    public List<Book> listBooks() {
        String sql = " SELECT * FROM book";
        List<Book> books = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }

            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Error listing books", e);
        }
    }

    /* ======================
       UPDATE
       ====================== */

    public void update(int id, String avaliable) {
        String sql = """
                UPDATE book
                SET avaliable = ?
                WHERE id = ?;
       """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, avaliable);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the book", e);
        }
    }

    /* ======================
       DELETE
       ====================== */

    public void delete(int id) {
        String sql = """
                DELETE FROM book WHERE id = ?;
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the book", e);
        }
    }

    /* ======================
       Mapper
       ====================== */

    private Book mapBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("description"),
                rs.getString("category"),
                StatusBook.valueOf(rs.getString("available").toUpperCase())
        );
    }
}
