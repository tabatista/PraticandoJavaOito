package com.java.oito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * JAVA 8 - Method Reference
 */
public class MethodReference {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        //method reference
        list.stream()
                .forEach(System.out::println);

        //sem o method reference
        list.stream()
                .forEach((n) -> System.out.println(n));

        //metodos estaticos
        list.stream()
                .map((n) -> multiplicarPorDois(n))
                .forEach(System.out::println);

        list.stream()
                .map(MethodReference::multiplicarPorDois)
                .forEach(System.out::println);

        //construtoress
        list.stream()
                .map(n -> new BigDecimal(n))
                .forEach(System.out::println);

        list.stream()
                .map(BigDecimal::new)
                .forEach(System.out::println);

        //varias instancias
        list.stream()
                .map(n -> n.doubleValue())
                .forEach(System.out::println);

        list.stream()
                .map(Integer::doubleValue)
                .forEach(System.out::println);

        //unica instancia
        BigDecimal dois = new BigDecimal(2);
        list.stream()
                .map(BigDecimal::new)
                .map((b) -> dois.multiply(b))
                .forEach(System.out::println);

        list.stream()
                .map(BigDecimal::new)
                .map(dois::multiply)
                .forEach(System.out::println);
    }

    private static Integer multiplicarPorDois(Integer num) {
        return num * 2;
    }

}
