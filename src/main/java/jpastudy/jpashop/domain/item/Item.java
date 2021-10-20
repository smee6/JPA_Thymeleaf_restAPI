package jpastudy.jpashop.domain.item;

import jpastudy.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    //상품이름 - 가격 - 재고수량
    private String name;
    private int price;
    private int stockQuantity;


    //==비즈니스 로직==//
    // 주문 취소 => 재고수량 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    // 주문이 체결 -> 재고수량 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
