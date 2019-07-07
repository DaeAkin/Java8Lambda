package com.donghyeon.ex.tutorial2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.function.Predicate;

public class StandardFunctionalPerson {
     public static void main(String[] args) {
          List<Person> people = Arrays.asList(
                  new Person("동현","민",24),
                  new Person("인규","박",22),
                  new Person("석호","조",26),
                  new Person("경훈","민",23)
          );

          // 1단계 : 성으로 정렬하기
         Collections.sort(people, (p1,p2) -> p1.getLastName().compareTo(p2.getLastName()));

         System.out.println("printAll");
         // 2단계 : 전부다 출력하기
            perform(people, p -> true);

            System.out.println("printAll Conditionally");
         // 3단계 : 성이 민씨인 사람만 출력하기
            perform(people, p -> p.getLastName().startsWith("민"));

      }

      public static void perform(List<Person> people, Predicate<Person> predicate) {
          for (Person p : people) {
              if(predicate.test(p))
                System.out.println(p);
          }
      }
}





