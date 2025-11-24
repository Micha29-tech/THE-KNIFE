package theknife.model;
import java.util.Optional;
import java.util.List;

public interface UtentiService {

    boolean registra(Utente utente,String passwordChiara);

    Optional<Utente> login(String username,String passwordChiara);

    Optional<Utente> byUsername(String username);

    List<Utente> tutti();
 
    
}
