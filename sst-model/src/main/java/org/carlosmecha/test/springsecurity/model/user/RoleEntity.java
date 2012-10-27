/**
 * 
 */
package org.carlosmecha.test.springsecurity.model.user;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * Implements a default Role entity.
 * 
 * @author Carlos Mecha
 * 
 */
@Entity(name = "Role")
@Access(AccessType.FIELD)
@Table(name = "role", schema = "sst")
@NamedQueries({
        @NamedQuery(name = "RoleEntity.FIND_ALL", query = "SELECT r FROM Role r"),
        @NamedQuery(name = "RoleEntity.FIND_BY_ID", query = "SELECT r FROM Role r WHERE r.id = :id"),
        @NamedQuery(name = "RoleEntity.FIND_BY_USERNAME",
            query = "SELECT r FROM Role r WHERE r.user.username = :username") })
public class RoleEntity implements Role {

    @SuppressWarnings("unused")
    // Later...
    private final static Logger log = Logger.getLogger(RoleEntity.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq",
        allocationSize = 1,
        schema = "sst",
        sequenceName = "role_id_seq")
    private long id;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH },
        fetch = FetchType.LAZY,
        targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    /**
     * Default constructor.
     * 
     * @param user
     *            User assigned to this role. This user will be set the new role into its list of
     *            roles.
     * @param role
     *            Role type.
     */
    public RoleEntity(final User user, final RoleType role) {
        this.user = user;
        this.role = role;
        this.user.getRoles().add(this);
    }

    @SuppressWarnings("unused")
    private RoleEntity() {
        // Used by JPA
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.Role#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.Role#getUser()
     */
    public User getUser() {
        return user;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.Role#getRole()
     */
    public RoleType getRole() {
        return role;
    }

}
