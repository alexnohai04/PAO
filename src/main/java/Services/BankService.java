package Services;


import Clase.*;

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
        System.out.println("Introdu strada pe care vrei sa construiesti ATM-ul:");
        a.setStrada(scanner.nextLine());
        System.out.println("Numarul:");
        a.setNumar(scanner.nextInt());
        scanner.nextLine();
    }
    public void addClient(){
        Client c = new Client();
        System.out.println("Nume:");
        c.setNume(scanner.nextLine());
        c.setNrTelefon(scanner.nextLine());
    }

 }
