package theknife;
import java.util.Scanner;
import theknife.model.Ristorante;

public class TheKnife {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        // -----TEST : Creo un ristorante-----
        Ristorante r1 = new Ristorante("La Ruota", "Italia", "Gallarate", "Via Giacomo Leopardi 4", "Ristorante italiano/pizzeria", 20, true, true);
        System.out.println(r1);
        System.out.println();

        // -----------------------------------------------------
        System.out.println("======= THE KNIFE =======");
        System.out.println("1) Accedi");
        System.out.println("2) Registrati");
        System.out.println("3) Continua come ospite");
        System.out.println("0) Esci");
        
        System.out.println("Scelta: ");
        int scelta = in.nextInt();
        in.nextLine();

        switch (scelta) {
            case 1:
                System.out.println("Funzione login...");
                break;
            case 2:
                System.out.println("Funzione registrazione...");
                break;
            case 3:
                System.out.println("Accesso come ospite...");
                break;
            default:
                System.out.println("Ciao!");
                break;
        }
        in.close();
    }
}
