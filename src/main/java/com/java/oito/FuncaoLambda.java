package com.java.oito;

import java.util.Arrays;
import java.util.List;

/**
 * JAVA 8: FUNCOES LAMBDA
 */
public class FuncaoLambda {

    public static void main(String[] args) {
        //java 7
        new Thread(new Runnable() {
            @Override
            public void run() {
                print("Java 7");
            }
        }).run();

        //java 8
        new Thread(() -> print("Java 8")).run();

        //como o java sabe que esta implementando o Runnable na funcao lambda acima?
        //Entra no padrao SAM - Single Abstract Method
        //Qualquer interface que tenha apenas um metodo abstrado
        //nesse caso a Thread espera receber a classe Runnable
        //e a classe Runnable possui apenas o metodo run, que eh abstrato, da forma abaixo:
        //public abstract void run();

        //eh importante destacar que um metodo em uma interface nao precisa estar escrito explicitamente o "abstract"
        //para indicar que ele eh abstrato - qualquer metodo em uma interface eh abstrato

        //a anotacao @FunctionalInterface forca a interface a ter apenas um metodo abstrato - como eh o caso do Runnable
        //nao eh anotacao obrigatoria, da pra usar o lambda como no exemplo acima no padrao SAM se a interface tiver apenas um metodo

        //a funcao nao existe apenas para reduzir o codigo
        //serve tambem para utilizar o stream
        //stream eh um fluxo de dados

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream()
                .filter(e -> (e % 2) == 0) //filtra para exibir apenas numeros pares
                .forEach((e) -> print(e)); //funcao for imprimindo o que existe na lista
    }

    public static void print(Object text) {
        System.out.println(text);
    }

}