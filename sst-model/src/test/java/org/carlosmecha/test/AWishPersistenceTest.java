package org.carlosmecha.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.carlosmecha.test.springsecurity.model.user.User;
import org.carlosmecha.test.springsecurity.model.user.UserEntity;
import org.carlosmecha.test.springsecurity.model.wish.Wish;
import org.carlosmecha.test.springsecurity.model.wish.WishEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("javadoc")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AWishPersistenceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveUsers() throws Exception {
        final User u1 = new UserEntity("Agapito");
        u1.setCreationDate(new Date());
        u1.setPassword("Pass");

        final User u2 = new UserEntity("Maria");
        u2.setCreationDate(new Date());
        u2.setPassword("pAss");

        final User u3 = new UserEntity("Mike");
        u3.setCreationDate(new Date());
        u3.setPassword("paSs");

        entityManager.persist(u1);
        entityManager.persist(u2);
        entityManager.persist(u3);
        entityManager.flush();
        assertFalse((u1.getId() == 0));
    }

    @Test
    @Transactional
    public void testSaveUserWithWishes() throws Exception {

        final User user = new UserEntity("Agapito");
        user.setCreationDate(new Date());
        user.setPassword("Pass");

        final Wish wish = new WishEntity(user);
        wish.setWish("Learn Spring...");

        user.getWishes().add(wish);

        entityManager.persist(user);
        entityManager.flush();

        assertFalse((user.getId() == 0));
        assertFalse((wish.getId() == 0));

    }

    @Test
    @Transactional
    public void testSaveAndGet() throws Exception {
        final User user = new UserEntity("Agapito");
        user.setCreationDate(new Date());
        user.setPassword("Pass");

        final Wish wish = new WishEntity(user);
        wish.setWish("Learn Spring...");

        user.getWishes().add(wish);

        entityManager.persist(user);
        entityManager.flush();

        // Otherwise the query returns the existing user.

        entityManager.clear();
        final User other = entityManager.find(UserEntity.class, user.getId());
        assertEquals(1, other.getWishes().size());
        assertEquals(other, other.getWishes().iterator().next().getUser());
    }

    @Test
    @Transactional
    public void testSaveAndFind() throws Exception {
        final User user = new UserEntity("Agapito");
        user.setCreationDate(new Date());
        user.setPassword("Pass");

        final Wish wish = new WishEntity(user);
        wish.setWish("Learn Spring...");

        user.getWishes().add(wish);

        entityManager.persist(user);
        entityManager.flush();

        // Otherwise the query returns the existing user.

        entityManager.clear();
        final User other = entityManager
                .createNamedQuery("UserEntity.FIND_BY_USERNAME", User.class).setParameter(
                    "username", "Agapito").getSingleResult();
        assertEquals(1, other.getWishes().size());
        assertEquals(other, other.getWishes().iterator().next().getUser());

        final Wish otherWish = entityManager.createNamedQuery("WishEntity.FIND_BY_ID", Wish.class)
                .setParameter("id", other.getWishes().iterator().next().getId()).getSingleResult();
        assertEquals(other.getWishes().iterator().next(), otherWish);

    }

}
