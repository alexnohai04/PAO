package Services;


import Clase.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class BankService {

    protected List<Atm> atmList = new ArrayList<>();
    protected List<BasicPlan> basicPlans = new ArrayList<>();
    protected List<PremiumPlan> premiumPlans = new ArrayList<>();
    protected List<ContBancar> contBancarList = new ArrayList<>();
    protected List<CardBancar> cardBancarList = new ArrayList<>();
    protected List<Client> clients = new ArrayList<>();
    protected List<Tranzactie> tranzactieList = new ArrayList<>();
    private Map<String, Client> clientMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    public void createBank(){
        System.out.println("Pentru ca esti primul utilizator te invit sa dai un nume bancii!");
        Banca b = Banca.getInstance();
        if(b != null){
        b.setNume(scanner.nextLine());
        }
    }
    public void addAtm(){
        Atm a = new Atm();
        Banca b = Banca.getInstance();
        System.out.println("Introdu strada pe care vrei sa construiesti ATM-ul:");
        a.setStrada(scanner.nextLine());
        System.out.println("Numarul:");
        a.setNumar(scanner.nextInt());
        scanner.nextLine();
        b.addAtmToBank(a);
    }
    public void addClient(){
        Client c = new Client();
        System.out.println("Nume:");
        c.setNume(scanner.nextLine());
        System.out.println("Nr de telefon:");
        c.setNrTelefon(scanner.nextLine());
        System.out.println("Vrei sa deschizi un cont bancar?");
        System.out.println("1.Da");
        System.out.println("2.Nu");
        int choice = scanner.nextInt();
        if (choice == 1) {
            ContBancar cb = new ContBancar();
            System.out.println("Nume cont bancar:");
            cb.setNume(scanner.nextLine());
            System.out.println("IBAN:");
            cb.setIBAN(scanner.nextLine());
            System.out.println("Sold-ul contului:");
            cb.setSold(scanner.nextInt());
            System.out.println("Doresti sa atasezi un card bancar contului?:");
            System.out.println("1.Da");
            System.out.println("2.Nu");

            int card=scanner.nextInt();
            if (card == 1) {
                CardBancar crb = new CardBancar(cb);
                System.out.println("CVV:");
                crb.setCvv(scanner.nextInt());
                System.out.println("Ce tip de abonament doriti?");
                System.out.println("1.Standard");
                System.out.println("2.Premium");
                int abonament = scanner.nextInt();
                BasicPlan b = new BasicPlan();
                System.out.println("Pret:");
                b.setPret(scanner.nextInt());
                scanner.nextLine();

                boolean ok = false;
                while (ok == false) {
                    System.out.println("Data de incepere (format: yyyy-MM-dd):");
                    String input = scanner.nextLine();
                    try {
                        LocalDate date = LocalDate.parse(input);
                        b.setStartDate(date);
                        ok = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Formatul datii introduse este invalid.");
                    }
                }
                    if (abonament == 1) {
                        crb.setAbonament(b);
                    } else if (abonament == 2) {
                        PremiumPlan p = new PremiumPlan(b);
                        System.out.println("Cashback(%):");
                        p.setCashback(scanner.nextInt());
                    }

            }

        }
        clients.add(c);
    }

    public void displayClientDetails() {
        System.out.println("Introdu numele clientului:");
        String numeClient = scanner.nextLine();

        for (Client client : clients) {
            if (client.getNume().equals(numeClient)) {
                System.out.println("Nume: " + client.getNume());
                System.out.println("Nr de telefon: " + client.getNrTelefon());

                // Afișăm detalii despre conturile bancare asociate clientului
                for (ContBancar cont : client.getConturi()) {
                    System.out.println("Cont bancar:");
                    System.out.println("Nume cont bancar: " + cont.getNume());
                    System.out.println("IBAN: " + cont.getIBAN());
                    System.out.println("Sold: " + cont.getSold());

                    // Verificăm dacă contul bancar are un card asociat
                    if (cont instanceof CardBancar) {
                        CardBancar card = (CardBancar) cont;
                        System.out.println("Card bancar:");
                        System.out.println("CVV: " + card.getCvv());

                        // Verificăm tipul de abonament al cardului
                        BasicPlan abonament = card.getAbonament();
                        if (abonament != null) {
                            BasicPlan basicPlan = (BasicPlan) abonament;
                            System.out.println("Tipul abonamentului: Standard");
                            System.out.println("Pret: " + basicPlan.getPret());
                            System.out.println("Data de incepere: " + basicPlan.getStartDate());
                        } else if (abonament instanceof PremiumPlan) {
                            PremiumPlan premiumPlan = (PremiumPlan) abonament;
                            System.out.println("Tipul abonamentului: Premium");
                            System.out.println("Cashback: " + premiumPlan.getCashback() + "%");
                            System.out.println("Data de incepere: " + premiumPlan.getStartDate());
                        }
                    }
                }

                return; // Ne oprim din căutare după ce am găsit clientul
            }
        }

        // Afisăm un mesaj dacă nu am găsit clientul cu numele introdus
        System.out.println("Nu există un client cu acest nume.");
    }



}
