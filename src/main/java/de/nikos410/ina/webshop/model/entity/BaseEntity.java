package de.nikos410.ina.webshop.model.entity;

/**
 * A basic class which describes an entity that is identified by an id.
 * Other entities should be based on this class.
 * <br>
 * <strong>IDs do not need to be unique among different entity types.</strong>
 * For example, it is ok to create a person and a vehicle with the id 42 (See also: {@link #equals}).
 */
public abstract class BaseEntity implements Comparable<BaseEntity> {

    private long id = 0;

    /**
     * Return the id that identifies this entity.
     *
     * @return the id that identifies this entity.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id that identifies this entity.
     *
     * @param id the id that identifies this entity.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the entity.
     *
     * @return a string representation of this entity.
     * @implSpec Concrete entities must implement this and should include all their properties.
     */
    @Override
    public abstract String toString();

    /**
     * Indicates whether some other object is "equal to" this one according to it's id.
     *
     * @param obj the reference object with which to compare.
     * @return true if {@link #canEquals} is true and this object has the same id as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof BaseEntity other) {
            return canEquals(other) && other.getId() == this.getId();
        }

        return false;
    }

    /**
     * The default implementation of {@link #equals} considers to entities to be equal if their id is equal.
     * <p>
     * This behaviour obviously makes no sense when comparing to different types of entites.
     * This method allows a concrete implementation to specify which other entities can be considered as equal to this.
     *
     * A typical implementation looks like this:
     * <pre>
     * public class User extends BaseEntity {
     *
     *     [...]
     *
     *     protected boolean canEquals(BaseEntity other) {
     *         return other instanceof User;
     *     }
     *
     *     [...]
     * </pre>
     *
     * @param other the reference object with which to compare.
     * @return true if the reference object can be considered as equal to this.
     */
    protected abstract boolean canEquals(BaseEntity other);

    /**
     * Returns a hash code value for the entity.
     * <br>
     * Implemented according to Effective Java - 3rd edition, item 11.
     *
     * @return the hash code value for this object.
     */
    @Override
    public final int hashCode() {
        return Long.hashCode(getId());
    }

    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     * <br>
     * Compares entities by their id.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     * @see #getId()
     */
    @Override
    public int compareTo(BaseEntity o) {
        return Long.compare(getId(), o.getId());
    }
}
