/**
 * 
 */
package org.carlosmecha.test.springsecurity.model.user;

import java.util.Collection;
import java.util.Date;

import org.carlosmecha.test.springsecurity.model.DatabaseEntity;
import org.carlosmecha.test.springsecurity.model.wish.Wish;

/**
 * Defines a default user.
 * 
 * @author Carlos Mecha
 * 
 */
public interface User extends DatabaseEntity {

    /**
     * Obtains the private database id for this entity.
     * 
     * @return Entity ID.
     */
    public Long getId();

    /**
     * Gets the user name. It should be unique.
     * 
     * @return User name.
     */
    public String getUsername();

    /**
     * Gets the user password.
     * 
     * @return Password. Not encrypted.
     */
    public String getPassword();

    /**
     * Gets when this entity has been created.
     * 
     * @return Creation date.
     */
    public Date getCreationDate();

    /**
     * Collects the wishes owned by this user.
     * 
     * @return Collection of wishes.
     */
    public Collection<Wish> getWishes();

    /**
     * Changes the password.
     * 
     * @param password
     *            New password.
     */
    public void setPassword(final String password);

    /**
     * Sets a new creation date.
     * 
     * @param date
     *            Date.
     */
    public void setCreationDate(final Date date);

}
