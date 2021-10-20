package jpastudy.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_id")
    private Long id;

    //Member와 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //delivery 1:1관계
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //OrderItem 과의 관계 1:N   <= cf) n이 주인 : 외래키 가짐 : 그쪽에다가 조인 어쩌고 선언
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 1:N에서 1쪽은 캐스케이드 해줘야 한다 => 그래야 같이 움직이지
    private List<OrderItem> orderItems =new ArrayList<>();
    
    //주문 날짜와 시간
    private LocalDateTime orderDate;

    //주문상태
    private OrderStatus status;

    //------- 연관관계 메서드 ------ //
    // M:1 관계
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    // 1:1관계
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    // 1:N 관계
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
