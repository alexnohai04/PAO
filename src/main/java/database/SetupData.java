package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupData {

    public void createTable() {
        String[] createTableSql = {
                """
                CREATE TABLE IF NOT EXISTS Atm (
                id INT PRIMARY KEY AUTO_INCREMENT,
                strada VARCHAR(255) NOT NULL,
                numar INT NOT NULL
        )
                """,
                """
        CREATE TABLE IF NOT EXISTS Banca (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nume VARCHAR(255) NOT NULL
        );
                """,
                """

        CREATE TABLE IF NOT EXISTS BasicPlan (
                id INT PRIMARY KEY AUTO_INCREMENT,
                pret INT NOT NULL,
                startDate DATE NOT NULL
        );
                """,
                """
        CREATE TABLE IF NOT EXISTS ContBancar (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nume VARCHAR(255) NOT NULL,
                IBAN VARCHAR(255) NOT NULL,
                sold INT NOT NULL,
                clientId INT,
                FOREIGN KEY (clientId) REFERENCES Client(id)
        );
            """,
                            """
        CREATE TABLE IF NOT EXISTS CardBancar (
                id INT PRIMARY KEY AUTO_INCREMENT,
                abonamentId INT,
                cvv INT NOT NULL,
                contBancarId INT,
                FOREIGN KEY (abonamentId) REFERENCES BasicPlan(id),
                FOREIGN KEY (contBancarId) REFERENCES ContBancar(id)
        );
                """,
                """
        CREATE TABLE IF NOT EXISTS Client (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nume VARCHAR(255) NOT NULL,
                nrTelefon VARCHAR(20) NOT NULL
        );
                """,
                """
        CREATE TABLE IF NOT EXISTS PremiumPlan (
                id INT PRIMARY KEY AUTO_INCREMENT,
                cashback INT NOT NULL,
                basicPlanId INT,
                FOREIGN KEY (basicPlanId) REFERENCES BasicPlan(id)
        );
                """,
                """
        CREATE TABLE IF NOT EXISTS Tranzactie (
                id INT PRIMARY KEY AUTO_INCREMENT,
                suma INT NOT NULL,
                destinatarId INT,
                clientId INT,
                FOREIGN KEY (destinatarId) REFERENCES Client(id),
                FOREIGN KEY (clientId) REFERENCES Client(id)
        );
                """,
                """
        CREATE TABLE IF NOT EXISTS BancaClient (
                bancaId INT,
                clientId INT,
                PRIMARY KEY (bancaId, clientId),
                FOREIGN KEY (bancaId) REFERENCES Banca(id),
                FOREIGN KEY (clientId) REFERENCES Client(id)
        );
        """,
        """
        CREATE TABLE IF NOT EXISTS BancaAtm (
                bancaId INT,
                atmId INT,
                PRIMARY KEY (bancaId, atmId),
                FOREIGN KEY (bancaId) REFERENCES Banca(id),
                FOREIGN KEY (atmId) REFERENCES Atm(id)
        );
        """
                

        };

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            for (String sql : createTableSql) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}