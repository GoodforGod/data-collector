package io.university.postgres.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 06.04.2019
 */
public interface IUpdatable<T> {
    void update(T t);
}
