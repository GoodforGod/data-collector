package io.university.storage.impl;

import io.university.storage.IStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
public abstract class BasicJpaStorage<T, ID extends Serializable>
        extends BasicStorageUtils<T, ID>
        implements IStorage<T, ID> {

    protected final Logger logger = LoggerFactory.getLogger(BasicJpaStorage.class);
    protected final JpaRepository<T, ID> repository;

    public BasicJpaStorage(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public boolean exist(ID id) {
        try {
            return isIdValid(id) && repository.existsById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<T> find(ID id) {
        try {
            return isIdValid(id) ? repository.findById(id) : Optional.empty();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<T> save(T t) {
        try {
            return isValid(t) ? Optional.ofNullable(repository.save(t)) : Optional.empty();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<T> save(Collection<T> t) {
        try {
            if(isNotValid(t))
                return Collections.emptyList();

            return repository.saveAll(t);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean delete(T t) {
        if (isNotValid(t))
            return false;

        try {
            repository.delete(t);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(ID id) {
        if (isIdNotValid(id))
            return false;

        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
