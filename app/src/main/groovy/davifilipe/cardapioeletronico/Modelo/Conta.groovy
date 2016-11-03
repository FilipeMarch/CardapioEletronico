package davifilipe.cardapioeletronico.Modelo

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
class Conta {

    List<Pedido> pedidos

    Double Total() {}

    def Dividir(String como) { new Divisao(como) }

}

class Divisao {

  Divisao(String como) {

      properties[como]()

  }

    def DeIgualPraIgual = {}

    def TantoPraCada = {}

    def PorMinhaConta = {}

    def CadaUmPorSi = {}

}
