package davifilipe.cardapioeletronico.Modelo

import org.bson.types.ObjectId

import java.util.stream.Collectors

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
/*
Se você clicar nesse símbolo azul aqui em baixo,
vai ver todas as classes que herdam de Entidade
 */

abstract class Entidade {
    /*
    uma classe abstrata não pode ser instanciada, o.s.
    new Entidade() daria um erro. Ela serve para definir
    métodos e atributos que muitas outras classes tem em comum,
    através da herança.
    */


    //Objeto de ID do MongoDB
    ObjectId id
    /*
    No paradigma de orientação a documentos que o Mongo usa,
    os dados são inseridos em documentos, e contém atributos [Chave:Valor],
    como num Dictionary de python (Equivalente ao Map do Java e do Groovy).
    Os documentos são inseridos em Coleções, que é como se fossem pastas para organizar
    os documentos. Daí a classe Entidade representa qualquer objeto que possa ser inserido no banco de dados do mongo.
     */
    String colecao

    /*
    Construtores, equivalentes aos Init do Python
    Se você atribui um valor dentro dos parâmetros de um método,
    ele inicia o objeto com esse valor se nenhum objeto do tipo
    foi declarado nos argumentos (na chamada do método em outra classe)
     */
    Entidade(ObjectId ID = null, String colecao) {
        id = ID
        this.colecao = colecao
    }


    Entidade() {}

    //////////////////////////////////////////////////////////////////////

    /*

    Aqui é uma sobrescrição do método "as", que indica como transformar as instâncias
    de entidade em um Map (Dictionary)

     */
    Object asType(Class clazz) {

        //Verifica se o usuário quer converter a classe em um mapa
        if (clazz == Map) {

            [(getClass().getSimpleName()): propriedades()]
            /*
            Aqui ele tá criando um mapa que leva o nome simples da classe,
            e um mapa com suas propriedades que é feito no método abaixo
             */


        } else {
            /*
            Se for qualquer outra classe, ele pede pra classe Pai
             usar o método de conversão padrão;
            (Todos os objetos herdam da classe Object no Java/Groovy)

            */
            super.asType(clazz)
        }

    }

    private Map propriedades() {

        //Criando um mapa vazio para por as propriedades
        Map props = [:]

        /*
            O Conceito de verdade no groovy:

            0 = false
            null = false
            false = false
            String vazia ("") = false
            List vazia (List.size() = 0) = false
            Map vazio (Map.size() = 0) = false

         Aqui em baixo ele verifica se a entidade já possui um id,
         se sim adiciona no mapa de propriedades
         */
        if (id) {
            props += [_id: id]
        }

        /*
        O método .findAll é um dos métodos auxiliares
        para manipulação de coleções (Listas e Mapas) no Groovy.
        Ele funciona como um iterador, passando por cada item
        dentro da coleção, aplicando uma condição e retornando
        todos os items que correspondem a ela

        O operador "<<" insere uma coleção em outra e retorna
        a coleção já atualizada.

        properties é um atributo presente em todos os objetos
        que te permite acessar todos os atributos dessa classe.

         */
        props << properties.findAll {

            /*

             O Item da coleção pode ser acessado através da palavra
             "it" (isso), que vai ser sempre do tipo da coleção.
             Como no caso a coleção é um Map, o item vai ser uma Map.Entry,
             com uma chave e um valor.

             uma das propriedades de todos os objetos é a classe,
             então ele vai selecionar todas as propriedades que não sejam
             a classe e que o valor não seja verdadeiro conforme o conceito de
             verdade já descrito lá em cima.
             */
            it.key != "class" && it.value

            /*

              O método collectEntries está sendo aplicado no resultado do
              .findAll, e serve para converter cada item em um mapa
              em um item de outro tipo.

             */

        }.collectEntries {

            println("classe de valor: ${it.value.class.simpleName}")

            if (it.value instanceof Entidade) {

                [(it.key): it.value as Map]

            } else if (it.value instanceof List) {

                [(it.key): it.value.collect{ if (it instanceof Entidade) { it as Map } else { it }}]

            } else {

                [(it.key): it.value]

            }

        }

    }

}
