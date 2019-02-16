package io.university.storage;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
public interface IStorage<T, ID extends Serializable> {

    boolean exist(ID id);

    Optional<T> find(ID id);
    List<T> findAll();

    Optional<T> save(T t);
    List<T> save(List<T> t);

    boolean delete(T t);
    boolean deleteById(ID id);
}
