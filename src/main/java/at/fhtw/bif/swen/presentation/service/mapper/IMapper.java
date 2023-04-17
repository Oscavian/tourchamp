package at.fhtw.bif.swen.presentation.service.mapper;

public interface IMapper<E, T> {

    T fromEntity(E entity);
    E toEntity(T dto);

}

