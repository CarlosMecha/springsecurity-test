package org.carlosmecha.test;

import static org.junit.Assert.assertFalse;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.carlosmecha.test.springsecurity.model.user.User;
import org.carlosmecha.test.springsecurity.model.user.UserEntity;
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
    public void testSaveOrderWithItems() throws Exception {
        /*
         * final Order order = new Order();
         * order.getItems().add(new Item());
         * entityManager.persist(order);
         * entityManager.flush();
         * assertNotNull(order.getId());
         */
    }

    @Test
    @Transactional
    public void testSaveAndGet() throws Exception {
        /*
         * final Order order = new Order();
         * order.getItems().add(new Item());
         * entityManager.persist(order);
         * entityManager.flush();
         */
        // Otherwise the query returns the existing order (and we didn't set the
        // parent in the item)...
        /*
         * entityManager.clear();
         * final Order other = entityManager.find(Order.class, order.getId());
         * assertEquals(1, other.getItems().size());
         * assertEquals(other, other.getItems().iterator().next().getOrder());
         */
    }

    @Test
    @Transactional
    public void testSaveAndFind() throws Exception {
        /*
         * final Order order = new Order();
         * final Item item = new Item();
         * item.setProduct("foo");
         * order.getItems().add(item);
         * entityManager.persist(order);
         * entityManager.flush();
         */
        // Otherwise the query returns the existing order (and we didn't set the
        // parent in the item)...
        /*
         * entityManager.clear();
         * final Order other = (Order) entityManager.createQuery(
         * "select o from Order o join o.items i where i.product=:product").setParameter(
         * "product", "foo").getSingleResult();
         * assertEquals(1, other.getItems().size());
         * assertEquals(other, other.getItems().iterator().next().getOrder());
         */
    }

}
