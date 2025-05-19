package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorManager {

    public List<Actor> getAll() {
        List<Actor> actors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT actor_id, first_name, last_name FROM actor");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int actorId = rs.getInt("actor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                Actor actor = new Actor(actorId, firstName, lastName);
                actors.add(actor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public void post(Actor actor) {
        String sql = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.executeUpdate();
            System.out.println("Actor insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void put(Actor actor) {
        String sql = "UPDATE actor SET first_name = ?, last_name = ?, last_update = CURRENT_TIMESTAMP WHERE actor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.setInt(3, actor.getActorId()); 
            stmt.executeUpdate();
            System.out.println("Actor actualizado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int actorId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps1 = conn.prepareStatement("DELETE FROM film_actor WHERE actor_id = ?");
        ps1.setInt(1, actorId);
        ps1.executeUpdate();

        PreparedStatement ps2 = conn.prepareStatement("DELETE FROM actor WHERE actor_id = ?");
        ps2.setInt(1, actorId);
        ps2.executeUpdate();

        ps1.close();
        ps2.close();
        System.out.println("Actor eliminado correctamente.");
    }
}