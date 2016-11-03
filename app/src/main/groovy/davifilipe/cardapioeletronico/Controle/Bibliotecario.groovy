package davifilipe.cardapioeletronico.Controle

import com.gmongo.GMongoClient
import com.mongodb.DB

/**
 * Created by Davi Pacheco on 16/10/2016.
 */
@Singleton
class Bibliotecario {

    static final String HOST = "localhost"
    static final int PORTA = 27017
    static final String BIBLIOTECA = "CardapioEletronico"

    private GMongoClient mongo
    private DB db

    def DB Bib() {
        if (!mongo) {
            mongo = new GMongoClient(HOST, PORTA)
            db = mongo.getDB(BIBLIOTECA)
        }
        db
    }


}
