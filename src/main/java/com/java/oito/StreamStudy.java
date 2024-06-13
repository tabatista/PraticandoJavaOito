package com.java.oito;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JAVA 8 - STREAMS
 */
public class StreamStudy {

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
                .limit(3) //exibe somente ate 2 elementos da lista - se colocar um limite maior que existe, nao quebra o codigo
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

        Stream<Integer> map = list.stream().limit(3).map(e -> e * 2);

        map.forEach(StreamStudy::print); //imprime
        //se duplicar a linha acima tentando imprimir pela 2a vez, da erro
        //operacao final chama uma vez e encerra o stream
        //se precisar fazer algo mais, deve criar outro stream

        //o count retorna a quantidade de itens que ficou - no caso limitamos a 3 elementos, entao ira contar apenas 3
        long count = list.stream().limit(3).count();
        print(count);

        //o min pega o menor valor - deve passar o comparador
        Optional<Integer> min = list.stream().filter(e -> e % 2 == 0)
                .min(Comparator.naturalOrder()); //comparador na ordem natural, considerando numeros
        print(min.get());

        //collect - pega o resultado e transforma numa lista
        List<Integer> newList = list.stream().filter(e -> e % 2 == 0)
                .collect(Collectors.toList()); //maneira mais simples

        //estamos multiplicando os numeros da lista por 3 e depois agrupamos por numeros pares
        Map<Boolean, List<Integer>> collectGroup = list.stream().map(e -> e * 3)
                .collect(Collectors.groupingBy(e -> e % 2 == 0)); //agrupa num mapa de true e false - true o que cai na condicao
        print(collectGroup); //imprime os true como os numeros pares - e false os impares
        //o tipo da chave ali é um boolean pois estamos agrupando por uma comparacao boolean na parte de numeros pares

        //outro exemplo do groupingBy
        Map<Integer, List<Integer>> collectGroupInt = list.stream().filter(e -> e != 0)
                .collect(Collectors.groupingBy(e -> e % 3)); //dividos por resto 3
        print(collectGroupInt); // key 0 = sao numeros dividos por 3 com resto 0 | key 1 = numeros que restam 1, e assim por diante

        //joining - agrupa strings - so trabalha com string
        List<String> listaString = Arrays.asList("Oi", "OLA", "EAI");
        String collectString = listaString.stream().collect(Collectors.joining());
        print(collectString);

        //criando string a partir do stream de uma lista de interger
        String collectStringByInt = list.parallelStream().map(String::valueOf).collect(Collectors.joining(";")); //junta separando por ponto e virgula
        print(collectStringByInt);

        //POR QUE USAR STREAM?
        //streams sao loops implicitos - a responsabilidade eh do streams
        //for - while - do..whiçe = sao loops explicitos = vc tem que controlar o loop de forma manual, pode ocasionar erros e deixa o codigo mais complexo

        //ao inves de usar o .stream(), podemos usar o .parallelStream(), que chama em multithread
        //o parallel nao serah usado em todos os contextos...

        //eh possivel criar streams de outras maneiras que nao seja atraves de uma lista
        //exemplos
        //Arrays.stream(Object[]);
        //Stream.of(Object[]);
        //IntStream.range(int, int);
        //a partir de linhas de um arquivos BufferedReader.lines();
        //dentre outros...
    }

    public static void print(Object text) {
        System.out.println(text);
    }

}