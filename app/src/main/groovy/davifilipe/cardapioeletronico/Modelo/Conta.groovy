package davifilipe.cardapioeletronico.Modelo

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
class Conta {

    List<Pedido> pedidos

    Double Total() {}

    def Dividir(Class como) { new Divisao(como) }

}

class Divisao {

  Divisao(Class tipo) {

  }

    class DeIgualPraIgual {}

    class TantoPraCada{}

    class PorMinhaConta {}

    class CadaUmPorSi {}

}
