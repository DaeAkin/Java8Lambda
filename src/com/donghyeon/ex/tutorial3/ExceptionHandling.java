package com.donghyeon.ex.tutorial3;

import java.util.function.BiConsumer;

public class ExceptionHandling {

     public static void main(String[] args) {
          int[] numbers = {1,2,3,4};
          int divide = 0;
        process(numbers,divide, wrapperLambda((v,k) -> System.out.println(v/k)));
      }

      public static void process(int[] number, int divide, BiConsumer<Integer,Integer> consumer) {
         System.out.println("-- process --");
          for (int num: number) {
                  consumer.accept(num, divide);
          }
      }

    private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
        return (v, k) ->  {
            try {
                consumer.accept(v, k);
            }
            catch (ArithmeticException e) {
                System.out.println("Exception caught in wrapper lambda");
            }

        };
    }
}
