import affichage.Menu;
import services.GestionUtilisateur;

public class Main {
    public static void main(String[] args) {
        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
        Menu menu = new Menu(gestionUtilisateur);
        menu.MenuPrincipal();
    }
}

