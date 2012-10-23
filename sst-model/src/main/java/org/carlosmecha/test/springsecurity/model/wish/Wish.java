/**
 * 
 */
package org.carlosmecha.test.springsecurity.model.wish;

import java.util.Date;

import org.carlosmecha.test.springsecurity.model.DatabaseEntity;
import org.carlosmecha.test.springsecurity.model.user.User;

/**
 * Defines a wish object.
 * 
 * @author Carlos Mecha
 * 
 */
public interface Wish extends DatabaseEntity {

    /**
     * Obtains the private database id for this entity.
     * 
     * @return Entity ID.
     */
    public Long getId();

    /**
     * Gets the owner of this wish.
     * 
     * @return Owner.
     */
    public User getUser();

    /**
     * The wish.
     * 
     * @return Wish.
     */
    public String getWish();

    /**
     * Gets when this wish has been created.
     * 
     * @return Creation date.
     */
    public Date getCreationDate();

    /**
     * Gets when this entity has been modified.
     * 
     * @return Last modification date.
     */
    public Date getLastModificationDate();

    /**
     * Sets a new wish.
     * 
     * @param wish
     *            New wish.
     */
    public void setWish(final String wish);

    /**
     * Sets the creation date.
     * 
     * @param date
     *            Date.
     */
    public void setCreationDate(final Date date);

    /**
     * Sets the last modification date.
     * 
     * @param date
     *            Date.
     */
    public void setLastModificationDate(final Date date);

}
