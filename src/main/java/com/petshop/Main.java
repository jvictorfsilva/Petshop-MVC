import com.petshop.view.PetShopApp;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/petshop_db";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            SwingUtilities.invokeLater(() -> {
                try {
                    PetShopApp app = new PetShopApp(connection);
                    app.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error initializing application!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
