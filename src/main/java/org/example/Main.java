package org.example;

import Services.AuditService;
import Services.BankService;
import java.util.Scanner;

public class Main {
    static void start(BankService myBank) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bine ai venit in lumea digital banking!");
        myBank.createBank();
        AuditService.logAction("Banca creata");
        System.out.println("Pentru ca nicio banca nu functioneaza fara ATM-uri, cate ai vrea sa creezi?");

        int nrAtm = myBank.readInt();
        AuditService.logAction("Numar ATM-uri de creat: " + nrAtm);

        for (int i = 0; i < nrAtm; i++) {
            myBank.addAtm();
            AuditService.logAction("ATM adaugat");
        }

        System.out.println("Banca a fost initializata cu succes!");
    }

    static void menu() {
        System.out.println("0 - pentru a te opri");
        System.out.println("1 - Creeaza un cont nou");
        System.out.println("2 - Intra in cont");
        System.out.println("3 - Intra in mod Administrator");
    }

    public static void main(String[] args) {
        BankService myBank = new BankService();
        Scanner scanner = new Scanner(System.in);
        start(myBank);
        boolean running = true;
        while (running) {
            menu();
            System.out.println("Alege o optiune:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Iesire din program...");
                    AuditService.logAction("Program terminat");
                    running = false;
                    break;
                case 1:
                    myBank.addClient();
                    AuditService.logAction("Client nou adaugat");
                    break;
                case 2:
                    myBank.connect();
                    AuditService.logAction("Client conectat");
                    break;
                case 3:
                    boolean ok = true;
                    while(ok) {
                        System.out.println("0.Exit");
                        System.out.println("1.Afiseaza date despre client dupa nume");
                        System.out.println("2.Adauga atm");
                        System.out.println("3.Afiseaza lista de atm-uri");
                        int choice2 = myBank.readInt();
                        switch (choice2) {
                            case 0:
                                ok = false;
                                AuditService.logAction("Iesire din mod Administrator");
                                break;
                            case 1:
                                myBank.displayClientDetails();
                                AuditService.logAction("Afisare date client");
                                break;
                            case 2:
                                myBank.addAtm();
                                AuditService.logAction("ATM adaugat din mod Administrator");
                                break;
                            case 3:
                                myBank.showAtm();
                                AuditService.logAction("Afisare lista ATM-uri");
                                break;
                        }
                    }
            }
        }
    }
}
