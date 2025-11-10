package theknife.model;

import java.util.List;
import java.util.Optional;

public interface RecensioneService {

    void salva(Recensione r);
    void update (Recensione r);
    Optional<Recensione> byId(String recensioneId);
    List<Recensione> perRistorante(String ristoranteId);

}

