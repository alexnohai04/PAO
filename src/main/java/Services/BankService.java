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
        try{
        Client c = new Client("Ana","0712345678");
        BasicPlan bp = new BasicPlan(12,LocalDate.parse("2021-10-10"));
        CardBancar cont = new CardBancar("Economii","RO4214ZBR31",100,bp,567);
        c.addAccount(cont);
        clients.add(c);
        clientMap.put(c.getNume(), c);

        System.out.println("Clientul de test a fost adăugat cu succes.");
    } catch (DateTimeParseException e) {
        System.out.println("Data specificată este invalidă: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("A apărut o eroare la crearea clientului de test: " + e.getMessage());
    }
    }
    public void addAtm(){
        Atm a = new Atm();
        Banca b = Banca.getInstance();
        System.out.println("Introdu strada pe care vrei sa construiesti ATM-ul:");
        a.setStrada(scanner.nextLine());
        System.out.println("Numarul:");
        a.setNumar(readInt());
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
        System.out.println("Nr de telefon:");
        client.setNrTelefon(scanner.nextLine());

        System.out.println("Vrei sa deschizi un cont bancar?");
        System.out.println("1.Da");
        System.out.println("2.Nu");
        int choice = readInt();
        if (choice == 1) {
            AuditService.logAction("Cont Bancar Creat");
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

            int card=readInt();
            if (card == 1) {
                AuditService.logAction("Card Bancar adaugat");
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
                        AuditService.logAction("Abonament standard adaugat");
                        crb.setAbonament(b);
                    } else if (abonament == 2) {
                        AuditService.logAction("Abonament premium adaugat");
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
            int x = readInt();

            ContBancar contClient = client.getConturi().get(x);

                System.out.println("Cui vrei sa-i trimiti o suma de bani?");
                for (int i=0;i<clients.size();i++){
                System.out.print(i);
                System.out.print(" ");
                System.out.print(clients.get(i).getNume());
                System.out.println();
            }
            int i = readInt();

                Tranzactie tr = new Tranzactie();
                Client destinatar = clients.get(i);
                tr.setDestinatar(destinatar);
                System.out.println("In ce cont vreti sa trimiteti banii?");
                for (int j=0;j < destinatar.getConturi().size();j++){
                    System.out.print(j);
                    System.out.print(" ");
                    System.out.print(destinatar.getConturi().get(j).getNume());
                    System.out.println();
                }
            int j = readInt();
            ContBancar contDestinatar = destinatar.getConturi().get(j);
            System.out.println("Ce suma doriti sa trimiteti?");
            int suma = readInt();
            if(contClient.getSold() > suma) {
                contClient.sendMoney(suma);
                contDestinatar.recieveMoney(suma);
                AuditService.logAction("Tranzactie efectuata");
                System.out.println("Tranzactia a fost aprobata!");
                tr.setSuma(suma);

            }
            else {
                AuditService.logAction("Tranzactie esuata");
                System.out.println("Fonduri insuficiente!");
            }
            client.addTranz(tr);

        }

        clients.add(client);
        clientMap.put(client.getNume(), client);
    }

    public void connect(){
        {
            Client client = new Client();
            System.out.println("Nume:");
            client.setNume(scanner.nextLine());
            if (clientMap.containsKey(client.getNume())) {
                client = clientMap.get(client.getNume());
                System.out.print("Bine ai revenit ");
                System.out.print(client.getNume());
                System.out.print("!");
                System.out.println();
            }
            else {
                System.out.println("Nu exista niciun cont inregistrat cu acest nume, te invitam sa creezi un cont nou!");
                addClient();
                return;
            }
            boolean menu = true;
            while(menu) {
                System.out.println("0. Exit");
                System.out.println("1. Creeaza un cont bancar");
                System.out.println("2. Efectueaza o tranzactie");
                System.out.println("3. Interogare sold");
                System.out.println("4. Extras de cont");
                System.out.println("5. Depune Bani");
                System.out.println("6. Retrage Bani");
                System.out.println("Alege o actiune:");
                int action = readInt();
                switch (action) {
                    case 0:
                        menu = false;
                        break;
                    case 1:
                        AuditService.logAction("Cont bancar creat");
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

                        int card = readInt();
                        if (card == 1) {
                            AuditService.logAction("Card Bancar adaugat");
                            CardBancar crb = new CardBancar(cb);
                            System.out.println("CVV:");
                            crb.setCvv(readInt());
                            System.out.println("Ce tip de abonament doriti?");
                            System.out.println("1.Standard");
                            System.out.println("2.Premium");
                            int abonament = readInt();
                            ;
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
                                AuditService.logAction("Abonament standard cumparat");
                                crb.setAbonament(b);
                            } else if (abonament == 2) {
                                AuditService.logAction("Abonament Premium adaugat");
                                PremiumPlan p = new PremiumPlan(b);
                                System.out.println("Cashback(%):");
                                p.setCashback(readInt());
                            }
                            client.addAccount(crb);
                        } else {
                            client.addAccount(cb);
                        }
                        break;
                    case 2:

                        System.out.println("Din ce cont vreti sa trimiteti banii?");
                        for (int j = 0; j < client.getConturi().size(); j++) {
                            System.out.print(j);
                            System.out.print(" ");
                            System.out.print(client.getConturi().get(j).getNume());
                            System.out.println();
                        }
                        int x = readInt();

                        ContBancar contClient = client.getConturi().get(x);

                        System.out.println("Cui vrei sa-i trimiti o suma de bani?");
                        for (int i = 0; i < clients.size(); i++) {
                            System.out.print(i);
                            System.out.print(" ");
                            System.out.print(clients.get(i).getNume());
                            System.out.println();
                        }
                        int i = readInt();

                        Tranzactie tr = new Tranzactie();
                        Client destinatar = clients.get(i);
                        tr.setDestinatar(destinatar);
                        System.out.println("In ce cont vreti sa trimiteti banii?");
                        for (int j = 0; j < destinatar.getConturi().size(); j++) {
                            System.out.print(j);
                            System.out.print(" ");
                            System.out.print(destinatar.getConturi().get(j).getNume());
                            System.out.println();
                        }
                        int j = readInt();
                        ContBancar contDestinatar = destinatar.getConturi().get(j);
                        System.out.println("Ce suma doriti sa trimiteti?");
                        int suma = readInt();
                        if (contClient.getSold() > suma) {
                            contClient.sendMoney(suma);
                            contDestinatar.recieveMoney(suma);
                            AuditService.logAction("Tranzactie efectuata");
                            System.out.println("Tranzactia a fost aprobata!");
                            tr.setSuma(suma);

                        } else {System.out.println("Fonduri insuficiente!");
                            AuditService.logAction("Tranzactie esuata");}

                        client.addTranz(tr);
                        break;
                    case 3:
                        for( ContBancar cont: client.getConturi())
                        {
                            AuditService.logAction("Sold bancar afisat");
                            System.out.printf("In contul %s aveti suma de: %d%n", cont.getNume(), cont.getSold());
                            System.out.println();
                        }
                        break;
                    case 4:
                        AuditService.logAction("Extras de cont afisat");
                        System.out.println("Extras de cont");
                        for (Tranzactie tranz : client.getTranzactii())
                        {

                            System.out.print("Id: ");
                            System.out.print(tranz.getId());
                            System.out.println();
                            System.out.print("Destinatar: ");
                            System.out.print(tranz.getDestinatar().getNume());
                            System.out.println();
                            System.out.print("Suma: ");
                            System.out.print(tranz.getSuma());
                            System.out.println();
                            System.out.println();

                        }
                        break;
                    case 5:
                        int index = 0;

                        for (int l = 0; l < client.getConturi().size(); l++) {
                            ContBancar cont = client.getConturi().get(l);
                            if (cont instanceof CardBancar) {
                                CardBancar cardx = (CardBancar) cont;
                                System.out.printf("%d. Nume: %s, Sold: %d, CVV: %d%n", index, cardx.getNume(), cardx.getSold(), cardx.getCvv());
                                index++;
                            }
                        }

                        // Selectarea unui card anume
                        System.out.println("Introduceti indicele cardului pe care doriti sa il folositi:");
                        int selectedIndex = scanner.nextInt();

                        if (selectedIndex >= 0 && selectedIndex < client.getConturi().size()) {
                            CardBancar selectedCard = (CardBancar) client.getConturi().get(selectedIndex);
                            System.out.printf("Ati selectat cardul cu numele: %s, sold: %d, CVV: %d%n",
                                    selectedCard.getNume(), selectedCard.getSold(), selectedCard.getCvv());
                            System.out.println("Cati bani doriti sa depuneti?");
                            int bani = readInt();
                            selectedCard.recieveMoney(bani);
                            AuditService.logAction("Suma depusa la ATM");
                            System.out.println("Suma a fost depusa!");
                        } else {
                            System.out.println("Indice invalid.");
                        }
                        break;
                    case 6:
                        int index2 = 0;

                        for (int l = 0; l < client.getConturi().size(); l++) {
                            ContBancar cont = client.getConturi().get(l);
                            if (cont instanceof CardBancar) {
                                CardBancar cardx = (CardBancar) cont;
                                System.out.printf("%d. Nume: %s, Sold: %d, CVV: %d%n", index2, cardx.getNume(), cardx.getSold(), cardx.getCvv());
                                index2++;
                            }
                        }

                        // Selectarea unui card anume
                        System.out.println("Introduceti indicele cardului pe care doriti sa il folositi:");
                        int selectedIndex2 = scanner.nextInt();

                        if (selectedIndex2 >= 0 && selectedIndex2 < client.getConturi().size()) {
                            CardBancar selectedCard = (CardBancar) client.getConturi().get(selectedIndex2);
                            System.out.printf("Ati selectat cardul cu numele: %s, sold: %d, CVV: %d%n",
                                    selectedCard.getNume(), selectedCard.getSold(), selectedCard.getCvv());
                            System.out.println("Cati bani doriti sa extrageti?");
                            int bani = readInt();
                            if(bani < selectedCard.getSold()) {
                                selectedCard.sendMoney(bani);
                                AuditService.logAction("Suma retrasa de la ATM");
                                System.out.println("Suma a fost retrasa cu succes!");
                            }
                            else System.out.println("Sold insuficient");
                        } else {
                            System.out.println("Indice invalid.");
                        }
                        break;


                }

                clients.add(client);
                clientMap.put(client.getNume(), client);
            }
        }
    }
    public void displayClientDetails() {
        System.out.println("Introdu numele clientului:");
        String numeClient = scanner.nextLine();


            if (clientMap.containsKey(numeClient)) {
                Client client = clientMap.get(numeClient);
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


        System.out.println("Nu există un client cu acest nume.");
    }



}
