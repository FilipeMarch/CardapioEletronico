package davifilipe.cardapioeletronico.Controle

import davifilipe.cardapioeletronico.Modelo.Entidade

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
@Singleton
class Escrivao {

    def Salvar(Entidade entidade) {

        Bib()."${entidade.colecao}".insert entidade as Map

    }

    DB Bib() {
        Bibliotecario.instance.Bib()
    }

}
