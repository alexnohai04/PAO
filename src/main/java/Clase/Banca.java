package Clase;

import java.util.ArrayList;
import java.util.List;

public class Banca {
    private String nume;
    private static Banca instance;
    private List<Client> clienti = new ArrayList<>();
    protected List<Atm> atms = new ArrayList<>();;
    private Banca() {}

    public void addClient(Client client){this.clienti.add(client); }
    public void addAtm(Atm atm){this.atms.add(atm); }

    public static Banca getInstance() {
        if (instance == null) {
            instance = new Banca();
        }
        return instance;
    }


    public Banca(String nume, List<Client> clienti) {
        this.nume = nume;
        this.clienti = clienti;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public static void setInstance(Banca instance) {
        Banca.instance = instance;
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    public List<Atm> getAtms() {
        return atms;
    }

    public void addAtmToBank(Atm a){
        atms.add(a);
    }
    public void setAtms(List<Atm> atms) {
        this.atms = atms;
    }
}
