import service.GestionConsommation;
import service.GestionUser;
import ui.Menu;
import configuration.Connextion;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection conn = Connextion.getInstance().getConnection();
        GestionUser gestionUser = new GestionUser();

        GestionConsommation gestionConsommation = new GestionConsommation(conn);

        Menu menu = new Menu(gestionUser, gestionConsommation);
        menu.affichageMenu();





    }
}

