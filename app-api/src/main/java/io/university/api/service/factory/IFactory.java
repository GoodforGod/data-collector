package io.university.api.service.factory;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
public interface IFactory<T> {

    /**
     * Builds 1 entity
     */
    T build();

    /**
     * Builds N entities
     */
    List<T> build(int n);
}
