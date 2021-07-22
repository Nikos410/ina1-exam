package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Defines a standardized way of storing and accessing entities.
 *
 * @param <T> the datatype contained in this repository.
 */
public interface Repository<T extends BaseEntity> {

    /**
     * Returns all elements in this repository.
     *
     * @return a collection containing all elements in this repository.
     */
    Collection<T> findAll();

    /**
     * Returns a specific element if it is present in this repository.
     *
     * @param id the id of the element to return.
     * @return an Optional containing the element with the given id, or an empty Optional if the element is not present
     * in this repository.
     */
    Optional<T> findOneById(long id);

    /**
     * Adds a new element (id = 0) to this repository.
     * <br>
     * The element must not be present in this repository already. If it is use {@link #update}
     *
     * @param newEntity the element to add to this repository.
     * @return the added entity.
     */
    T add(T newEntity);

    /**
     * Updates the data described in this entity. For example, a database-based repository should persist the data to
     * the database.
     * <br>
     * The element must be present in this repository already. If it isn't use {@link #add}
     *
     * @param updatedEntity The entity to update.
     */
    void update(T updatedEntity);

    /**
     * Remove a specific from this repository.
     *
     * @param id the id of the element to delete.
     */
    void remove(long id);
}
