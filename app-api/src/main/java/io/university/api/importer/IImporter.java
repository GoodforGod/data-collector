package io.university.api.importer;

import io.university.api.model.IUpdatable;

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
public interface IImporter<T extends IUpdatable<T>> {

    T importOrUpdate(T t);
}
