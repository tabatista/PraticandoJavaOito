package com.java.oito;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

/**
 * JAVA 8 - Optional
 */
public class OptionalStudy {

    public static void main(String[] args) throws Exception {
        //use o Optional somente se vc pode retornar null
        //nao use o optional como argumento em metodo, pois ira dificultar a vida sem necessidade
        // Ex: int retornaInt(OptionalInt num)

        String um = "1";
        //pode ou nao conter um interger = opicional
        Optional<Integer> num = converteEmNumero(um);
        print("isPresente: " + num.isPresent()); //se esta preenchido
        print("get: " + num.get()); //pega o valor dentro do optional

        num.ifPresent(n -> print("ifPresent: " + n)); //evita erro se o numero estiver vazio diferente do get - nao faz nada se nao houver esse valor

        //se o optional estiver vazio, retorna o valor do "else"declarado
        Integer num2 = converteEmNumero("TEXTO").orElse(2);
        print("orElse:" + num2);

        //o "orElseGet" recebe uma funcao lambda
        Integer num3 = converteEmNumero("Texto").orElseGet(() -> 3); //retorna o que quiser, nesse caso, o 3
        print("OrElseGet: " + num3);
        //a diferenca desse metodo eh poder invocar um metodo mais pesado

        //uso com stream
        Stream.of(1, 2, 3)
                .findFirst()
                .ifPresent(n -> print("Stream + optional: " + n));

        //tipos primitivos
        OptionalInt numPrimitivo = converteEmNumeroPrimtivo("4");
        numPrimitivo.ifPresent(i -> print("OptionalInt (tipo primitivo): " + i));

        //lanca uma excecao se nao tiver resultado
        converteEmNumero("Texto").orElseThrow(() -> new Exception("OrElseThrow"));
    }

    public static Optional<Integer> converteEmNumero(String numeroTexto) {
        try {
            Integer interger = Integer.valueOf(numeroTexto);
            return Optional.of(interger);
        } catch (Exception ex) {
            return Optional.empty();
            //return Optional.ofNullable(interger); //se o valor do interger for vazio, retorna um empty (vazio)
        }
    }

    //tipo primitivo
    public static OptionalInt converteEmNumeroPrimtivo(String numeroTexto) {
        try {
            int interger = Integer.parseInt(numeroTexto);
            return OptionalInt.of(interger);
        } catch (Exception ex) {
            return OptionalInt.empty();
            //return Optional.ofNullable(interger); //se o valor do interger for vazio, retorna um empty (vazio)
        }
    }

    public static void print(Object text) {
        System.out.println(text);
    }

}