package database.Repository;

import Clase.Client;

import java.util.List;

public class ClientDAOTest {

    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();

        // Testare creare client
        Client newClient = new Client();
        newClient.setNume("Ion Popescu");
        newClient.setNrTelefon("0722123456");
        clientDAO.createClient(newClient);
        System.out.println("Client creat: " + newClient);

        // Testare citire client
        Client clientFromDb = clientDAO.readClient(newClient.getId());
        System.out.println("Client citit: " + clientFromDb);

        // Testare actualizare client
        clientFromDb.setNrTelefon("0744987654");
        clientDAO.updateClient(clientFromDb);
        Client updatedClient = clientDAO.readClient(clientFromDb.getId());
        System.out.println("Client actualizat: " + updatedClient);

        // Testare citire toti clientii
        List<Client> allClients = clientDAO.getAllClients();
        System.out.println("Toti clientii:");
        for (Client client : allClients) {
            System.out.println(client);
        }
//
//        // Testare stergere client
//        clientDAO.deleteClient(newClient.getId());
//        Client deletedClient = clientDAO.readClient(newClient.getId());
//        System.out.println("Client sters (ar trebui sa fie null): " + deletedClient);
    }
}
