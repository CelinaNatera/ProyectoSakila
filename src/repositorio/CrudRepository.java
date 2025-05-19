package repositorio;

import java.util.List;

public interface CrudRepository<T> {
    void post(T entity);          
    T get(int id);                 
    List<T> getAll();              
    void put(T entity);            
    void delete(int id);          
}
