package at.fhtw.bif.swen.persistence.repositories;

import java.util.List;

public interface IRepository<T, I> {
     List<T> getAll();
     void save(T t);
     void update(T t);
     void delete(T t);
     T getById(I id);
     List<T> search(String searchString);
}
