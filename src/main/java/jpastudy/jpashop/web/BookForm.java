package jpastudy.jpashop.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookForm {
    private Long id;
    @NotBlank(message = "이름을 써주세요")
    private String name;
    private int price;
    @Min(value = 1 ,message ="stock quantity error")
    private int stockQuantity;
    private String author;
    private String isbn;
}