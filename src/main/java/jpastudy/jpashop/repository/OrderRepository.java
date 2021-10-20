package jpastudy.jpashop.repository;

import jpastudy.jpashop.domain.Order;
import jpastudy.jpashop.domain.OrderSearch;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return em.createQuery("select o from Order o",Order.class).getResultList();
    }
}