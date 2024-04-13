package Clase;

import java.util.*;

public class ContBancar {

    private String nume;
    private String IBAN;
    private int sold;

    public ContBancar(String nume, String IBAN, int sold) {
        this.nume = nume;
        this.IBAN = IBAN;
        this.sold = sold;

    }
    public ContBancar (){}

    public String getNume() {
        return nume;
    }

    public String getIBAN() {
        return IBAN;
    }

    public int getSold() {
        return sold;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContBancar that)) return false;
        return getSold() == that.getSold() && Objects.equals(getNume(), that.getNume()) && Objects.equals(getIBAN(), that.getIBAN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getIBAN(), getSold());
    }
}
