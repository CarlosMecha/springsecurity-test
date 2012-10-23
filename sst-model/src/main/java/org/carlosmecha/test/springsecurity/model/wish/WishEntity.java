/**
 * 
 */
package org.carlosmecha.test.springsecurity.model.wish;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.carlosmecha.test.springsecurity.model.user.User;
import org.carlosmecha.test.springsecurity.model.user.UserEntity;

/**
 * Default implementation of a wish entity.
 * 
 * @author Carlos Mecha
 * 
 */
@Entity(name = "Wish")
@Access(AccessType.FIELD)
@Table(name = "wish", schema = "sst")
@NamedQueries({
        @NamedQuery(name = "WishEntity.FIND_ALL", query = "SELECT w FROM Wish w"),
        @NamedQuery(name = "WishEntity.FIND_BY_ID", query = "SELECT w FROM Wish w WHERE w.id = :id") })
public class WishEntity implements Wish {

    @SuppressWarnings("unused")
    // Later...
    private final static Logger log = Logger.getLogger(WishEntity.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_id_seq")
    @SequenceGenerator(name = "wish_id_seq",
        allocationSize = 1,
        schema = "sst",
        sequenceName = "wish_id_seq")
    private long id;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH },
        fetch = FetchType.LAZY,
        targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "wish")
    private String wish;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Calendar creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Calendar lastModificationDate;

    @SuppressWarnings("unused")
    private WishEntity() {
        // Used by JPA
    }

    /**
     * Default constructor.
     * 
     * @param user
     *            The owner.
     */
    public WishEntity(final User user) {
        this.user = user;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#getUser()
     */
    public User getUser() {
        return user;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#getWish()
     */
    public String getWish() {
        return wish;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#getCreationDate()
     */
    public Date getCreationDate() {
        return (creationDate == null) ? null : creationDate.getTime();
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#getLastModificationDate()
     */
    public Date getLastModificationDate() {
        return (lastModificationDate == null) ? null : lastModificationDate.getTime();
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#setWish(java.lang.String)
     */
    public void setWish(final String wish) {
        this.wish = wish;
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#setCreationDate(java.util.Date)
     */
    public void setCreationDate(final Date date) {
        if (date == null) {
            this.creationDate = null;
        } else {
            if (this.creationDate == null) {
                this.creationDate = Calendar.getInstance();
            }
            this.creationDate.setTime(date);
        }
    }

    /**
     * @see org.carlosmecha.test.springsecurity.model.wish.Wish#setLastModificationDate(java.util.Date)
     */
    public void setLastModificationDate(final Date date) {
        if (date == null) {
            this.lastModificationDate = null;
        } else {
            if (this.lastModificationDate == null) {
                this.lastModificationDate = Calendar.getInstance();
            }
            this.lastModificationDate.setTime(date);
        }
    }

}
