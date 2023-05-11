package at.fhtw.bif.swen.persistence.repositories;

import java.util.List;

public interface IRepository<T> {
     List<T> getAll();
     void save(T t);
     void update(T t);
     void delete(T t);
}
