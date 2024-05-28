package database.Repository;


import Clase.Banca;
import database.DatabaseConfiguration;

public class BancaDAOTest {
    public static void main(String[] args) {
        BancaDAO bancaDAO = new BancaDAO();

        // Creare banca
        Banca banca = new Banca();
        banca.setNume("Banca Transilvania");
        bancaDAO.createBanca(banca);
        System.out.println("Banca creată: " + banca);

        // Citire banca
        Banca readBanca = bancaDAO.readBanca(banca.getId());
        System.out.println("Banca citită: " + readBanca);

        // Actualizare banca
        banca.setNume("Banca Transilvania Actualizată");
        bancaDAO.updateBanca(banca);
        System.out.println("Banca actualizată: " + banca);

        // Citire banca actualizată
        Banca updatedBanca = bancaDAO.readBanca(banca.getId());
        System.out.println("Banca citită după actualizare: " + updatedBanca);

        // Ștergere banca
        bancaDAO.deleteBanca(banca.getId());
        Banca deletedBanca = bancaDAO.readBanca(banca.getId());
        System.out.println("Banca ștearsă (ar trebui să fie null): " + deletedBanca);

        // Închidere conexiune la baza de date
        DatabaseConfiguration.closeDatabaseConnection();
    }
}

