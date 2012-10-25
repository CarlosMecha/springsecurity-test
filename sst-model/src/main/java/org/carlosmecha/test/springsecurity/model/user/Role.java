/**
 * 
 */
package org.carlosmecha.test.springsecurity.model.user;

import org.carlosmecha.test.springsecurity.model.DatabaseEntity;

/**
 * Defines a role entity.
 * 
 * @author Carlos Mecha
 * 
 */
public interface Role extends DatabaseEntity {

    /**
     * Role types.
     * 
     * @author Carlos Mecha
     * 
     */
    public static enum RoleType {

        /**
         * A common user.
         */
        COMMON,

        /**
         * An administrator.
         */
        ADMIN,

    }

    /**
     * Obtains the private database id for this entity.
     * 
     * @return Entity ID.
     */
    public Long getId();

    /**
     * The user.
     * 
     * @return User.
     */
    public User getUser();

    /**
     * The role that the user has.
     * 
     * @return Role type.
     */
    public RoleType getRole();

}
