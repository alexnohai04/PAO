package org.example;

import Services.BankService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void start(BankService myBank){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bine ai venit in lumea digital banking!");
        myBank.createBank();
        System.out.println("Pentru ca nicio banca nu functioneaza fara ATM-uri, cate ai vrea sa creezi?");
        int nrAtm = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<nrAtm;i++){
            myBank.addAtm();
        }
        System.out.println("Banca a fost initializata cu succes!");
    }

    static void menu(){
        System.out.println("Alege o actiune!:");
        System.out.println("0 - pentru a te opri");
        System.out.println("1 - Intra in cont");
        System.out.println("2 - Intra in mod Administrator");
    }

    public static void main(String[] args) {
        BankService myBank = new BankService();
        Scanner scanner = new Scanner(System.in);
        start(myBank);
    }
}