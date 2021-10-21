package jpastudy.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpastudy.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    //Order와의 관계 N:1 ... 지금 아이템이 N이지? N이 주인이지 그럼 외래키 가져야지? 예~~
    //조인컬럼 있어야겠지 then~
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    //Item 과의 관계는 N:1이다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //가격과 수량
    private int orderPrice;
    private int count;

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();

        //OrderItem과 Item 연결
        orderItem.setItem(item);
        //주문 상품 가격 저장
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직 : 주문 취소 ==//
    public void cancel() {
        getItem().addStock(this.count);
    }

    //==비즈니스 로직 : 주문상품 전체 가격 조회 ==//
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


}
