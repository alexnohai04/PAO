package Clase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private int id;

    private String nume;

    private String nrTelefon;
    private List<ContBancar> conturi = new ArrayList<>();
    private List<Tranzactie> tranzactii = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Client(){};

    public Client(String nume, String nrTelefon) {
        this.nume = nume;
        this.nrTelefon = nrTelefon;
        this.conturi = new ArrayList<>();
        this.tranzactii= new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public List<ContBancar> getConturi() {
        return conturi;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public void addAccount(ContBancar cont){ this.conturi.add(cont); }
    public void addTranz(Tranzactie tranz){ this.tranzactii.add(tranz); }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", conturi=" + conturi +
                ", tranzactii=" + tranzactii +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getNume(), client.getNume()) && Objects.equals(getNrTelefon(), client.getNrTelefon()) && Objects.equals(getConturi(), client.getConturi()) && Objects.equals(getTranzactii(), client.getTranzactii());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getNrTelefon(), getConturi(), getTranzactii());
    }
}
