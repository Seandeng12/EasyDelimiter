package test;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Person {

    private String name;

    private Integer age;

    private String gentle;

    private String hobby;
}
