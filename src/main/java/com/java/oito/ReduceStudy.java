package com.java.oito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class ReduceStudy {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        //a funcao do reduce é pegar todos os elementos do reduce e transformar numa coisa só
        //juntar/agrupar os elementos de alguma forma
        //tem que ser uma funcao associativa
        //a subtração não é uma funcao associativa, como no caso da soma ou multiplicação ou string, pois se acrescentarmos um "parallel" (dividir o stream em vários grupos) na subtração o resultado não seria o mesmo que nos outros.

        //o reduce recebe o acumulador

        //soma
        //função associativa -> (1 + 2) + (3 + 4) + (5 + 6)
        Optional<Integer> soma1 = list.stream()
                .reduce((n1, n2) -> n1 + n2); //soma todos os numeros, pegando sempre os dois primeiros e depois somando o resultado com o próximo
                //.reduce(Integer::sum); //faz a mesma cois que acima, mas com o method reference
        print(soma1.get());


        // ------- valor de identidade
        Integer soma2 = list.stream()
                .reduce(0, (n1, n2) -> n1 + n2); //o numero mais aquele que dá sempre seu valor
        print(soma2);
        //exemplos
        // soma: 0 + 1 sempre será 1
        // multiplicacao: 1 * 1 = sempre será 1
        // string: "" = uma string vazia concatenada com uma outra string retorna exatamente a outra string
        //se o stream estiver vazio, ele retorna o próprio valor de identidade - diferente do outro que retorna um optional vazio
        //se colocar um valor diferente do padrao, ele vai sempre começar com aquele valor
        //exemplo se colocamos zero na soma o resultado é 21, que é o certo, mas se colocamos o 1, ele retorna 22, pois inicia com o 1 para seguir o restante da soma - se usar o parallel o valor pode aumentar, pois irá utilizar esse valor várias vezes.


        //-----reduce - menor valor
        OptionalDouble menorValor = DoubleStream.of(1.5, 2.0, 6.7)
                .reduce((d1, d2) -> Math.min(d1, d2));
                //.reduce(Math::min); //method reference
        //o valor de identidade nesse caso poderia ser um Double.POSITIVE_INFINITY
        print(menorValor.getAsDouble());

        //-----FUNCAO DE COMBINACAO
        Integer soma3 = list.stream()
                .reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2); //3 ARGUMENTOS - separados por virgula
        print(soma3);

        //o java por debaixo dos panos só utiliza a funcao de combinacao com o parallel, mesmo que declare ela explicitamente

        //isso abre a possibilidade de fazer um reduce - map + combiner
        String reduce = list.stream()
                .parallel()
                //.map(n1 -> n1.toString()) //transforma os numeros em string
                //.reduce((n1, n2) -> n1 + n2);
                .reduce( //no lugar do map usamos a funcao de acumulacao
                        "",
                        (n1, n2) -> n1.toString().concat(n2.toString()), //isso serve para caso se ganhe performance fazendo tudo numa linha só no lugar do map
                        (n1, n2) -> n1 + n2
                );
        print(reduce);

        //o reduce trabalha com valores imutáveis, diferente do collect que trabalha com trabalha com mutáveis
        //por exemplo nao daria para usar um stringbuilder e utilizar o append para utilizar o mesmo objeto com o reduce
        //no exemplo da string com o concat ele sempre cria um objeto novo
        //no collect é possível trabalhar com o stringbuilder - essa é a maior diferença entre eles


    }

    public static void print(Object text) {
        System.out.println(text);
    }

}