/**
 * 
 */
package org.carlosmecha.test.springsecurity.model.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.carlosmecha.test.springsecurity.model.wish.Wish;
import org.carlosmecha.test.springsecurity.model.wish.WishEntity;

/**
 * Default user implementation.
 * 
 * @author Carlos Mecha
 * 
 */
@Entity(name = "User")
@Access(AccessType.FIELD)
@Table(name = "user_credentials", schema = "sst")
@NamedQueries({
        @NamedQuery(name = "UserEntity.FIND_ALL", query = "SELECT u FROM User u"),
        @NamedQuery(name = "UserEntity.FIND_BY_ID", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "UserEntity.FIND_BY_USERNAME",
            query = "SELECT u FROM User u WHERE u.username = :username") })
public class UserEntity implements User {

    @SuppressWarnings("unused")
    // Later...
    private final static Logger log = Logger.getLogger(UserEntity.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_credentials_id_seq")
    @SequenceGenerator(name = "user_credentials_id_seq",
        allocationSize = 1,
        schema = "sst",
        sequenceName = "user_credentials_id_seq")
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Calendar creationDate;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "user",
        targetEntity = WishEntity.class)
    private final List<Wish> wishes = new ArrayList<Wish>();

    @SuppressWarnings("unused")
    private UserEntity() {
        // Used by JPA
    }

    /**
     * Default user constructor.
     * 
     * @param username
     *            User name.
     */
    public UserEntity(final String username) {
        this.username = username;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#getUsername()
     */
    public String getUsername() {
        return username;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#getPassword()
     */
    public String getPassword() {
        return password;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#getCreationDate()
     */
    public Date getCreationDate() {
        return (creationDate == null) ? null : creationDate.getTime();
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#getWishes()
     */
    public Collection<Wish> getWishes() {
        return wishes;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#setPassword(java.lang.String)
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.user.User#setCreationDate(java.util.Date)
     */
    public void setCreationDate(final Date date) {
        if (date == null) {
            creationDate = null;
        } else {
            if (creationDate == null) {
                creationDate = Calendar.getInstance();
            }
            creationDate.setTime(date);
        }

    }

}
