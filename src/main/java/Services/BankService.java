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
        a.setNumar(readInt());
        scanner.nextLine();
        b.addAtmToBank(a);
    }
    public int readInt(){
        boolean ok = true;
        int number = 0;
        while(ok) {
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                ok = false;
            } catch (InputMismatchException e) {
                System.out.println("Input invalid. Te rog introdu un numar.");
                scanner.nextLine();
            }
        }
            return number;
    }
    public void showAtm(){
        Banca b = Banca.getInstance();
        for (Atm atm : b.getAtms()){
            System.out.print("\nStrada: ");
            System.out.print(atm.getStrada());
            System.out.print("\nNumarul: ");
            System.out.print(atm.getNumar());
            System.out.println();
        }
    }
    public void addClient(){
        Client client = new Client();
        System.out.println("Nume:");
        client.setNume(scanner.nextLine());
        if (clientMap.containsKey(client.getNume())) {
            client = clientMap.get(client.getNume());
            System.out.println("Bine ai revenit ");
        }
        else {

            System.out.println("Nr de telefon:");
            client.setNrTelefon(scanner.nextLine());
        }
        System.out.println("Vrei sa deschizi un cont bancar?");
        System.out.println("1.Da");
        System.out.println("2.Nu");
        int choice = readInt();
        scanner.nextLine();
        if (choice == 1) {
            ContBancar cb = new ContBancar();
            System.out.println("Nume cont bancar:");
            cb.setNume(scanner.nextLine());
            System.out.println("IBAN:");
            cb.setIBAN(scanner.nextLine());
            System.out.println("Sold-ul contului:");
            cb.setSold(readInt());
            System.out.println("Doresti sa atasezi un card bancar contului?:");
            System.out.println("1.Da");
            System.out.println("2.Nu");

            int card=readInt();;
            if (card == 1) {
                CardBancar crb = new CardBancar(cb);
                System.out.println("CVV:");
                crb.setCvv(readInt());
                System.out.println("Ce tip de abonament doriti?");
                System.out.println("1.Standard");
                System.out.println("2.Premium");
                int abonament = readInt();;
                BasicPlan b = new BasicPlan();
                System.out.println("Pret:");
                b.setPret(readInt());
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
                        p.setCashback(readInt());
                    }
            client.addAccount(crb);
            } else {
                client.addAccount(cb);
            }

        }

        System.out.println("Doresti sa efectuezi o tranzactie?");
        System.out.println("1.Da");
        System.out.println("2.Nu");
        int choice4 = readInt();;
        if (choice4 == 1) {
            System.out.println("Din ce cont vreti sa trimiteti banii?");
            for (int j=0;j<client.getConturi().size();j++){
                System.out.print(j);
                System.out.print(" ");
                System.out.print(client.getConturi().get(j).getNume());
                System.out.println();
            }
            int x = readInt();;

            ContBancar contClient = client.getConturi().get(x);

                System.out.println("Cui vrei sa-i trimiti o suma de bani?");
                for (int i=0;i<clients.size();i++){
                System.out.print(i);
                System.out.print(" ");
                System.out.print(clients.get(i).getNume());
                System.out.println();
            }
            int i = readInt();;

                Tranzactie tr = new Tranzactie();
                Client destinatar = clients.get(i);
                tr.setDestinatar(destinatar);
                System.out.println("In ce cont vreti sa trimiteti banii?");
                for (int j=0;i<destinatar.getConturi().size();j++){
                    System.out.print(j);
                    System.out.print(" ");
                    System.out.print(destinatar.getConturi().get(j).getNume());
                    System.out.println();
                }
            int j =readInt();
            ContBancar contDestinatar = destinatar.getConturi().get(j);
            System.out.println("Ce suma doriti sa trimiteti?");
            int suma =readInt();
            if(contClient.getSold() > x) {
                contClient.sendMoney(x);
                contDestinatar.recieveMoney(x);
            }
            else System.out.println("Fonduri insuficiente!");

                client.addTranz(tr);

        }

        clients.add(client);
        clientMap.put(client.getNume(), client);
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

                return;
            }
        }


        System.out.println("Nu există un client cu acest nume.");
    }



}
