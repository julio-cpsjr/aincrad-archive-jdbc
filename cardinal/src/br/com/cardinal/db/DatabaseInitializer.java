package br.com.cardinal.db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS book (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    author TEXT NOT NULL,
                    description TEXT NOT NULL,
                    category TEXT NOT NULL,
                    available TEXT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS user (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL,
                    status TEXT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS loan(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    book_id INTEGER NOT NULL,
                    user_id INTEGER NOT NULL,
                    date_loan TEXT NOT NULL,
                    date_return_preview TEXT NOT NULL,
                    date_return_real TEXT NOT NULL,
                    status TEXT NOT NULL,
                    FOREIGN KEY(book_id)  REFERENCES book(id),
                    FOREIGN KEY(user_id)  REFERENCES user(id)
                )
            """);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar banco", e);
        }
    }
}
