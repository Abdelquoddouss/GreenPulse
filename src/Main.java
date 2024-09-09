import service.GestionUser;
import ui.Menu;
import configuration.Connextion;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection conn = Connextion.getInstance().getConnection();
        GestionUser gestionUser = new GestionUser();

        Menu menu = new Menu(gestionUser);
        menu.affichageMenu();





    }
}

