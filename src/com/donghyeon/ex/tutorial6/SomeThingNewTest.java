package com.donghyeon.ex.tutorial6;

public class SomeThingNewTest {


    /** 인터페이스를 반환하기 때문에 변수로 받아서 쓰면 된다.
     *  일회성 특징이 큰듯..
     *  참고자료 : JPA의 Specification
     */
    public SomeInterface test(Integer a) {
       return () ->  "아아아아";
   }

    public static void main(String[] args) {
         SomeThingNewTest someThingNewTest = new SomeThingNewTest();
         SomeInterface someInterface = someThingNewTest.test(50);
         System.out.println(someInterface.function());

    }

}
