package at.fhtw.bif.swen.presentation.service;

import java.util.List;

public interface IService<T, ID> {
    List<T> getAll();
    T getById(ID id);
    void save(T t);
    void update(T t);
    void delete(T t);

}
