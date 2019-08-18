package com.donghyeon.ex.tutorial4;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTest {
    public static void main(String[] args) {

        /** FunctionalInterface 정리
         *  Predicate -> boolean test(T t) : 어떤 조건
         *  Function -> R apply(T t) : 형을 변환 할 수 있고, 같은 형을 사용할 수 있음.
         *  Consumer -> void accept(T t) : 어떤 동작을 수행
         *  Supplier -> T get();
         */

        // not null object
        Optional<Person> notNullPerson = personGenerator();
        // null object
        Optional<Person> nullPerson = null;

        List<Person> lists = Arrays.asList(personGenerator().get(),personGenerator().get());

        /** person의 값이 존재한다면, Predicate 함수인터페이스를 이용해 조건에 맞는 객체들만 필터링을 한다.
         필터된 객체들의 값이 없다면 빈 Optional 반환하고, 값이 있다면 Optional<T>을 반환 할 것이다.*/
        notNullPerson.filter(p -> p.getAge() == 24);

        /** 값이 존재하면 Function 동작을 수행하고, 결과 값이 null이 아니면 Optional을 반환
         * 값이 없으면 빈 Optional을 반환한다. */
        notNullPerson.map(p -> p.getAge());

        lists.stream().flatMap(p -> Stream.of(p)); /** map과 비슷한 역할을 함. */

        /** Option 안에 값이 있으면 Option을 해제하고 값이 없으면 NoSuchElementException 예외를 던진다. */
        notNullPerson.get();

        /** 값이 있으면 true 값이 없으면 false */
        notNullPerson.isPresent();

        /** 값이 존재하면 Consumer 동작을 수행한다, 값이 존재하지 않는다면 아무동작도 하지 않음.*/
        notNullPerson.ifPresent( p-> {
            System.out.println(p.getLastName() + " Hello !");
        });


        /** Optional 객체의 생성자는 private으로 직접적인 인스턴스 생성이 불가능하다
         *  그대신 아래의 세가지 함수를 이용하여 인스턴스를 생성할 수 있다.
         */
        /** null이 아닌 값으로 Optional 객체를 반환한다. of()안에 있는 파라미터가 null이면 NullPointerException이 난다.*/
        Optional.of(notNullPerson);
        /** 값이 있으면 값이 있는 Optional 객체 반환, 값이 없으면 빈 Optional 객체 반환 */
        Optional.ofNullable(nullPerson);
        /** 빈 Optional 객체를 생성한다 */
        Optional.empty();

        /** 값이 있다면 그 값을 return 하고, 값이 없다면 orElse() 안에 있는 값을 return 한다.*/
        notNullPerson.orElse(new Person());

        /** 값이 있다면 그 값을 return 하고, 값이 없다면 다른 메소드를 호출해서 그 리턴값으로 return 한다.*/
        notNullPerson.orElseGet(() -> personGenerator().get());

        /** 값이 있다면 그 값을 return 하고 없으면 supplier가 만들어주는 Exception을 호출한다 */
        notNullPerson.orElseThrow(IllegalStateException::new);


        /** flatMap과 Map의 차이점. **/
        System.out.println(
        Optional
                .of("string")
                .map(s1 -> Optional.of("STRING")).get());

        System.out.println(
        Optional
                .of("string")
                .flatMap(s2 -> Optional.of("STRING")).get());


        List<List<String>> list = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b"));
        System.out.println(list);

        System.out.println(list
                .stream()
                .map(Collection::stream)
                .collect(Collectors.toList()));

        System.out.println(list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }


    public static Optional<Person> personGenerator() {
        Person person = new Person("민", "동현", 24);
        Optional<Person> personOptional = Optional.of(person);
        return personOptional;
    }
}
