package at.fhtw.bif.swen.presentation.service;

import java.util.List;

public interface IService<T, ID> {
    List<T> getAll();

    T getById(ID id);
    T add(T t);
}
