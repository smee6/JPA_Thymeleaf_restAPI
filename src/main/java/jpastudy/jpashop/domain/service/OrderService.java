package jpastudy.jpashop.domain.service;


import jpastudy.jpashop.domain.*;
import jpastudy.jpashop.domain.item.Item;
import jpastudy.jpashop.repository.ItemRepository;
import jpastudy.jpashop.repository.MemberRepository;
import jpastudy.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService
{
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    //order
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //member 조회
        Member member = memberRepository.findOne(memberId);
        //item
        Item item = itemRepository.findOne(itemId);
        //delivery
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //order item
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //order 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        //order 저장
        orderRepository.save(order);

        return order.getId();
    }

    //**주문 취소
    @Transactional
    public void cancelOrder (Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    //주문 검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }


}
