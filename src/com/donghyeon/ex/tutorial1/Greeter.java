package com.donghyeon.ex.tutorial1;

public class Greeter {

     public static void main(String[] args) {

        Greeting helloWorldGreeting = new HelloWorldGreeting();

        helloWorldGreeting.perform();

        Greeting anonymousHello = new Greeting() {
            @Override
            public void perform() {
                System.out.println("anonymous Hello!");
            }
        };

        anonymousHello.perform();

        Greeting lambdaHelloWorld = () -> System.out.println("lambda Hello!");

        lambdaHelloWorld.perform();


      }
}
