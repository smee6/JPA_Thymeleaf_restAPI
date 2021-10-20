package jpastudy.jpashop.domain;

import jpastudy.jpashop.domain.item.Book;
import jpastudy.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EntityTest {

    @Autowired
    EntityManager em;

    @Test @Rollback(value = false)
    public void entity() throws Exception {

        //멤버 생성
        Member member = new Member();
        member.setName("Mongta");
        Address address = new Address("대한 민국 Seoul","뉴욕 Harlem","5500번지");
        member.setAddress(address);
        em.persist(member);

        //주문 생성
        Order order = new Order();
        //오더와 멤버의 연결
        order.setMember(member);

        //딜리버리를 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //오더와 딜리버리를 연결
        order.setDelivery(delivery);

        //Item -> BOok 생성
        Book book = new Book();
        book.setName("Demian Book");
        book.setPrice(23000);
        book.setStockQuantity(15);
        book.setAuthor("SEO MYUNG IN");
        book.setIsbn("ab0-99dq50fe");
        em.persist(book);

        //order 아이템을 만들자
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);
        orderItem.setCount(2);
        orderItem.setOrderPrice(52000);

        //order 와 order 아이템 연결
        order.addOrderItem(orderItem);

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);


        em.persist(order);

    }


}