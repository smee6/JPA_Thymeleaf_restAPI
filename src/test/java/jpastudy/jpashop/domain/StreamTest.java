package jpastudy.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void stream(){
        List<User> users = List.of(new User("Henry Kim", 10), new User("Lola Park", 5), new User("Prince Ken", 53));
        List<String> nameList =
                users.stream()                                //Stream<User>.
                              .map(user -> user.getName())    //Stream<String>
                              .collect(Collectors.toList());  // List<String>
    nameList.forEach(name->System.out.println(name));

    //-------------------
    System.out.println("***********************00000------------------------------------00000***********************");
    users.stream().filter(user-> user.getAge() >=20).forEach(user-> System.out.println(user.getName()));

    //----------------------
    System.out.println("***********************00000-----------------------------------000000***********************");
    List<String> names = users.stream().filter(user -> user.getAge() >= 20).map(user -> user.getName())
                .collect(Collectors.toList());
    names.forEach(System.out::println);

    //----------------------
    System.out.println("***********************00000-----------------------------------000000***********************");

        int sum = users.stream().mapToInt(user -> user.getAge()).sum();
        System.out.println(sum);


    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private int age;

    }
}
