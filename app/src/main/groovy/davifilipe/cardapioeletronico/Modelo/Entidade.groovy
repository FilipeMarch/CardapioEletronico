package davifilipe.cardapioeletronico.Modelo

import org.bson.types.ObjectId

import java.util.stream.Collectors

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
abstract class Entidade {

    ObjectId id
    String colecao

    Entidade(ObjectId ID = null, String colecao) {
        id = ID
        this.colecao = colecao
    }

    Entidade() {}

    Object asType(Class clazz) {

        if (clazz == Map) {

            [(getClass().getSimpleName()): propriedades()]

        } else {
            super.asType(clazz)
        }

    }

    private Map propriedades() {

        Map props = [:]

        if (id) {
            props += [_id: id]
        }

        props << properties.findAll {

            it.key != "class" && it.value

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
