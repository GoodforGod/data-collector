package io.university.api.exporter;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
public interface IExporter<T> {

    T export(T t);

    T exportIfPossible(T t);
}
