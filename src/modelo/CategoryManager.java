package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection;

    public CategoryManager() {
    	try {
        connection = DatabaseConnection.getConnection();
    
    } catch (SQLException e) {
        System.out.println("Error al conectar con la base de datos: " + e.getMessage());
    }
}  
    public List<Category> getAll() {
        List<Category> categorias = new ArrayList<>();
        try {
            String query = "SELECT category_id, name FROM category";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Category categoria = new Category();
                categoria.setId(rs.getInt("category_id"));
                categoria.setName(rs.getString("name"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener categorias: " + e.getMessage());
        }
        return categorias;
    }

    public void post(Category categoria) {
        try {
            String query = "INSERT INTO category (name, last_update) VALUES (?, NOW())";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, categoria.getName());
            pstmt.executeUpdate();
            System.out.println("Categoría agregada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar categoria: " + e.getMessage());
        }
    }

    public void put(Category categoria) {
        try {
            String query = "UPDATE category SET name = ?, last_update = NOW() WHERE category_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, categoria.getName());
            pstmt.setInt(2, categoria.getId());
            pstmt.executeUpdate();
            System.out.println("Categoria actualizada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            String borrarRelacion = "DELETE FROM film_category WHERE category_id = ?";
            PreparedStatement psRelacion = connection.prepareStatement(borrarRelacion);
            psRelacion.setInt(1, id);
            psRelacion.executeUpdate();
            psRelacion.close();

            String query = "DELETE FROM category WHERE category_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();

            System.out.println("Categoria eliminada correctamente.");
        } catch (SQLException e) {
        	System.out.println("Error al eliminar categoria: " + e.getMessage());
        }
    }
}