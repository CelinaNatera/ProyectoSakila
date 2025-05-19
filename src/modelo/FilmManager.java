package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {

    public List<Film> getAll() {
        List<Film> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM film")) {

            while (rs.next()) {
                Film f = new Film();
                f.setId(rs.getInt("film_id"));
                f.setTitle(rs.getString("title"));
                f.setDescription(rs.getString("description"));
                f.setReleaseYear(rs.getInt("release_year"));
                f.setRentalDuration(rs.getInt("rental_duration"));
                f.setRentalRate(rs.getDouble("rental_rate"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void post(Film f) {
        String sql = "INSERT INTO film (title, description, release_year, rental_duration, rental_rate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getTitle());
            stmt.setString(2, f.getDescription());
            stmt.setInt(3, f.getReleaseYear());
            stmt.setInt(4, f.getRentalDuration());
            stmt.setDouble(5, f.getRentalRate());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void put(Film f) {
        String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, rental_duration = ?, rental_rate = ? WHERE film_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getTitle());
            stmt.setString(2, f.getDescription());
            stmt.setInt(3, f.getReleaseYear());
            stmt.setInt(4, f.getRentalDuration());
            stmt.setDouble(5, f.getRentalRate());
            stmt.setInt(6, f.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id)throws SQLException  {
    	
    	 Connection conn = DatabaseConnection.getConnection();

    	    PreparedStatement ps1 = conn.prepareStatement("DELETE FROM film_actor WHERE film_id = ?");
    	    ps1.setInt(1, id);
    	    ps1.executeUpdate();

    	    PreparedStatement ps2 = conn.prepareStatement("DELETE FROM inventory WHERE film_id = ?");
    	    ps2.setInt(1, id);
    	    ps2.executeUpdate();

    	    PreparedStatement ps3 = conn.prepareStatement("DELETE FROM film WHERE film_id = ?");
    	    ps3.setInt(1, id);
    	    ps3.executeUpdate();

    	    ps1.close();
    	    ps2.close();
    	    ps3.close();
    	} 	
    		
    }