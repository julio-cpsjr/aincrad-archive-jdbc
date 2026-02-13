package br.com.cardinal.repository;

import br.com.cardinal.db.ConnectionFactory;
import br.com.cardinal.model.StatusUser;
import br.com.cardinal.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    /* ======================
       CREATE
       ====================== */

    public void save(User user){
        String sql = """ 
                INSERT INTO user (nome, email, status)
                VALUES (?, ?, ?)
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getStatus().name());
            stmt.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException("Error saving the user", e);
        }
    }

    /* ======================
       READ (por ID)
       ====================== */

    public User getUserId(int id){
        String sql = """
                SELECT * FROM user WHERE id = ?;
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(!rs.next()) return null;

            return mapUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the user id",e);
        }
    }

    public List<User> listUsers() {
        String sql = """
                SELECT * FROM user;
        """;
        List<User> users = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                users.add(mapUser(rs));
            }

            return users;
        }catch (SQLException e) {
            throw new RuntimeException("Error getting the users",e);
        }
    }

    /* ======================
       UPDATE
       ====================== */

    public  void update(int id,String status) {
        String sql = """
                UPDATE user
                SET status = ?
                WHERE id = ?;
        """;


        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Error updating the user",e);
        }
    }

    /* ======================
       DELETE
       ====================== */

    public void delete(int id) {
        String sql = """
                DELETE FROM user WHERE id = ?;
        """;

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException("Error deleting the user",e);
        }

    }

    /* ======================
       Mapper
       ====================== */

    public User mapUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                StatusUser.valueOf(rs.getString("status"))
        );
    }






}
