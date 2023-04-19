package at.fhtw.bif.swen.businesslogic.mapper;

public interface IMapper<T, E> {

    E toEntity(T dto);
    T fromEntity(E entity);

}

