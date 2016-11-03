package davifilipe.cardapioeletronico.Modelo

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
class Cliente extends Entidade {

    Cliente() {
        super("Clientes")
    }

    List<Cliente> amigo_de
    List<Contexto> visitou
    List<Estabelecimento> estabs_deSempre
    List<Produto> produtos_deSempre

    String foto
    String email
    String telefone
    String nome

}
