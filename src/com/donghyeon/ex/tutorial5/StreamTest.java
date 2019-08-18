package com.donghyeon.ex.tutorial5;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        /** intermediate operation : 체인으로 연결되는 메소드들.
         *  stateful intermediate operation :
         *  terminal operation : 이 특징을 가진 메소드를 호출해야 stream들이 일을 시작함.
         */


        List<String> lists = Arrays.asList("Donghyeon", "Seohyun", "Java", "Swift", "Swift");
        /** Returns a stream consisting of the elements of this stream that match the given predicate.
         This is an intermediate operation.
         Intermediate operations return a new stream.
         They are always lazy; executing an intermediate operation such as filter() does not actually perform any filtering, but instead creates a new stream that, when traversed, contains the elements of the initial stream that match the given predicate. Traversal of the pipeline source does not begin until the terminal operation of the pipeline is executed.*/

        lists.stream().filter(p -> p.startsWith("S"));
        /**Returns a stream consisting of the results of applying the given function to the elements of this stream.
         This is an intermediate operation. */
        lists.stream().map(p -> "이 값으로 모두 변경 !");

        /** IntStream으로 변환함. */
        lists.stream().mapToInt(p -> Integer.parseInt(p));

        /** mapToLong -> LongStream
         *  mapToDouble -> DoubleStream
         */
// 진짜 모르겠네 flapMap ㅋㅋ
//        lists.stream().flatMap(Collection::stream);

        /** distinct() : stateful intermediate operation 중복을 제거해준다
         * NOTE : parallel 에서는 distinct() 메소드는 상대적으로 많은 비용이 듭니다.
         * 계층을 큰 배리어로 감싸기 때문에 연속적인 버퍼링 오버헤드가 생깁니다.
         * unordered stream을 사용해서 요소들을 제거하거나 제약을 하면
         * parallel 프로그래밍에서 더 효과적인 성능을 발휘할 수 있습니다.
         * 만약 어쩔 수 없이 parallel 프로그래밍에서 distinct() 함수를 사용해야한다면
         *  BaseStream.sequential()을 함께 사용하면 성능을 향상시킬 수 있습니다.*/
        System.out.println(
                lists.stream()
                        .distinct().sequential()
                        .collect(Collectors.toList()));

        /**  Returns a stream consisting of the elements of this stream, sorted according to natural order.
         If the elements of this stream are not Comparable, a java.lang.ClassCastException may be thrown when the terminal operation is executed.
         For ordered streams, the sort is stable. For unordered streams, no stability guarantees are made. */
        /** 기본적으로 오름차순으로 sorted 해줍니다.
         *  Comparator를 구현해서 자신이 원하는대로 정렬을 할 수 있습니다.
         *  */
        //
        System.out.println(" sorted() : " +
                lists.stream().sorted()
                        .collect(Collectors.toList()));


        /** peek() : intermediate operation.
         *  값들을 순회해서 어떠한 동작을 수행시킴. */
        lists.stream()
                .peek(e -> System.out.println("Mapped value :" + e))
                .collect(Collectors.toList());

        /** limit() : stateful intermediate operation.
         * 값의 갯수를 제한한다.
         * parallel 프로그래밍에서 limit() 메소드는 많은 비용이 들 수 있습니다.
         * BaseStream.sequential()을 함께 사용하면 성능을 향상시킬 수 있습니다
         */
        System.out.println(" limit() : " +
                lists.stream()
                        .limit(2)
                        .collect(Collectors.toList()));

        /** skip() : stateful intermediate operation.
         * 처음의 요소부터 n까지 제외해준다.
         *  parallel 프로그래밍에서 skip() 메소드는 많은 비용이 들 수 있습니다.
         *  BaseStream.sequential()을 함께 사용하면 성능을 향상시킬 수 있습니다
         */
        System.out.println(" skip() : " +
                lists.stream()
                        .skip(2)
                        .collect(Collectors.toList()));

        /** foreach() :  terminal operation.
         *  요소들을 순회합니다.
         */
        lists.stream()
                .forEach(p -> System.out.println(p.toString() + "으갸갸갸 foreach다 !"));

        /** toArray() : terminal operation.
         *  array로 변환해줍니다.
         */
        Object[] arrays =
                lists.stream()
                        .toArray();
        System.out.println("array의 갯수 : " + arrays.length);

        // 보류
        /** <A> A[] toArray(IntFunction<A[]> generator) : terminal operation */
        lists.stream()
                .filter(p -> p.length() == 4)
                .toArray();



        /** reduce() **/
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println( "reduce() v1 :" + letters.stream()
                .reduce("", (partialString, element) -> partialString + element));

        /** or */
        System.out.println("reduce() v2 :" + letters.stream()
                .reduce("", String::concat));

        /** min() : terminal operation.
         * comparator에 의해 정렬되는 것 중 가장 작은걸 뽑아준다. */
        /** replace 할수 있다고 IDE가 알려주는데 다시한번 확인해보자 */

        System.out.println( "min() : " + lists.stream()
                .min((p1,p2) -> p1.compareTo(p2)).get());

        /** max() : terminal operation
         * comparator에 의해 정렬되는 것 중 가장 큰걸 뽑아준다.
         */
        System.out.println( "max() : " + lists.stream()
                .max((p1,p2) -> p1.compareTo(p2)).get());

        /** count() : terminal operation
         *
         * */

        System.out.println( "count() :" + lists.stream()
            .count());




    }
}
