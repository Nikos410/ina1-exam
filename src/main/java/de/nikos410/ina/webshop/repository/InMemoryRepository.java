package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.BaseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static java.text.MessageFormat.format;

/**
 * A basic {@link Repository} implementation that keeps data in memory and does not persist anything.
 *
 * @param <T> the datatype contained in this repository.
 */
public abstract class InMemoryRepository<T extends BaseEntity> implements Repository<T> {

    private final AtomicInteger lastId = new AtomicInteger(1);

    protected final Set<T> content = new HashSet<>();

    protected InMemoryRepository() {}

    /**
     * {@inheritDoc}
     *
     * @return a {@link Set} containing all elements in this repository.
     */
    @Override
    public Set<T> findAll() {
        // Return a new instance to prevent exposing the internal set.
        return new HashSet<>(content);
    }

    @Override
    public Optional<T> findOneById(long id) {
        return content.stream()
                .filter(e -> id == e.getId())
                .findAny();
    }

    @Override
    public T add(T newEntity) {
        if (newEntity.getId() > 0) {
            throw new IllegalArgumentException("Element is not a new entity.");
        }

        final long nextId = getNextId();
        newEntity.setId(nextId);

        content.add(newEntity);
        return newEntity;
    }

    private long getNextId() {
        return lastId.incrementAndGet();
    }

    /**
     * {@inheritDoc}
     * <br>
     * <strong>This is a No-Op for this repository type, since no data is persisted.</strong>
     *
     * @param updatedEntity The entity to update.
     */
    @Override
    public void update(T updatedEntity) {
        // No-Op for this repository type, since no data is persisted.
    }

    @Override
    public void remove(long id) {
        final T toDelete = findOneById(id).orElseThrow(() -> new IllegalArgumentException(format("Element with id {0} is not present.", id)));
        content.remove(toDelete);
    }
}