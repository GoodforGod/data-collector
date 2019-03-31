package io.university.aggregator.service.validator;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
public interface IValidator<T> {

    /**
     * Returns valid list of entities with all correct links to save correctly
     */
    List<T> validate(List<T> list);
}
