package davifilipe.cardapioeletronico.Modelo

import java.time.LocalDateTime

/**
 * Created by Davi Pacheco on 20/10/2016.
 */
class Tempo extends Entidade {

    @Delegate
    Date tempo

    Tempo() { tempo = new Date() }

    Tempo(Date tempo) {
        this.tempo = tempo
    }



}
