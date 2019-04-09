package io.university.api.importer;

import io.university.api.model.IUpdatable;
import io.university.api.storage.IStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
public abstract class BasicImporter<T extends IUpdatable<T>, ID extends Serializable> implements IImporter<T> {

    private static final Logger logger = LoggerFactory.getLogger(BasicImporter.class);

    private final IStorage<T,ID> storage;

    public BasicImporter(IStorage<T, ID> storage) {
        this.storage = storage;
    }

    public T postOrUpdateSpeciality(T model) {
//        T modelToUpdate = storage.find(model.getId()).orElse(department);
//        modelToUpdate.update(department);
//        if (!departmentStorage.save(modelToUpdate).isPresent()) {
//            logger.warn("Model not updated!");
//            throw new NotUpdatedException();
//        }
//
//        return modelToUpdate;
        return model;
    }
}
