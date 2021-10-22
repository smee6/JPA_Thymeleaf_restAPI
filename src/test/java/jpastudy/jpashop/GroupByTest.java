package jpastudy.jpashop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByTest {
    @Test
    public void groupby() {

        List<Dish> dishList = Arrays.asList(new Dish("pork", 700, Type.MEAT),
                new Dish("spaghetti", 523, Type.NOODLE),
                new Dish("tomato", 255, Type.VEGE),
                new Dish("cabage", 120, Type.VEGE)
        );

        //Dish 이름만 출력

        dishList.stream().map(Dish::getName).collect(Collectors.toList());

        String collect = dishList.stream().map(Dish::getName).collect(Collectors.joining("..."));

        Integer collect1 = dishList.stream().collect(Collectors.summingInt(dish -> dish.getCalory()));

        System.out.println(collect);
        System.out.println(collect1);

        //dish의 타입별로 그룹핑
        Map<Type, List<Dish>> collect2 = dishList.stream().collect(Collectors.groupingBy(dish -> dish.getType()));
        System.out.println(collect2);
    }

    static class Dish{
        String name;
        int calory;
        Type type;

        public Type getType() {
            return type;
        }

        public Dish(String name, int calory, Type type) {
            this.name = name;
            this.calory = calory;
            this.type = type;
        }

        public Dish(String name, int calory) {
            this.name = name;
            this.calory = calory;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCalory() {
            return calory;
        }

        public void setCalory(int calory) {
            this.calory = calory;
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    enum Type {
        MEAT,FISH,NOODLE,VEGE
    }
}
