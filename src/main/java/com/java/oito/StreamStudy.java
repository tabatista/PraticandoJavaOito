package com.java.oito;

import java.util.Arrays;
import java.util.List;

/***
 * JAVA 8 - STREAMS
 */
public class Stream {

    public static void main(String[] args) {

        //stream eh um fluxo de dados
        //eh possivel manipular esses dados

        List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 0);

        list.stream()
                .skip(2) //pula os 2 primeiros itens da lista
                .forEach((e) -> print(e));

        //o skip eh uma operacao intermediaria
        // eh possivel fazer varias operacoes intermediarias antes de fechar o stream
        //o foreach eh uma operacao final, ou seja, nao eh possivel fazer mais nada apos ela

        list.stream()
                .limit(3) //exibe somente ate 2 elementos da lista
                .forEach((e) -> print(e));

        list.stream()
                .distinct() //nao processa elementos repetidos -- mesma ideia de um banco de dados sql
                .forEach((e) -> print(e));

        list.stream()
                .filter(e -> (e % 2) == 0) //cria um filtro mais personalizado -- nese exemplo estamos filtrando os numeros pares
                .forEach((e) -> print(e));


        list.stream()
                .map(e -> e * 2) //o map faz uma transformacao de dados -- nesse caso estamos multiplicando cada item da lista por 2
                .forEach((e) -> print(e));

        //OBS.: a lista original nao eh modificada pelo stream

        //idealmente eh melhor fazer os filtros antes do map -- embora o stream tenha a inteligencia de filtrar antes do map, porem eh bom deixar legivel a ordem ideal

        //OPERACOES INTERMEDIARIAS [filtros primeiros]
        // skip, limit, distinct, filter, map

        //OPERACOES FINAIS
        // count, min e max
        // collect: toList, groupingBy, joining

    }

    public static void print(Object text) {
        System.out.println(text);
    }

}