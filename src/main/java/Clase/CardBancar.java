package Clase;

import java.util.Objects;

public class CardBancar extends ContBancar {

    private int id;

    private BasicPlan abonament;
    private int cvv;
    public CardBancar(){};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardBancar(String nume, String IBAN, int sold, BasicPlan abonament, int cvv) {
        super(nume, IBAN, sold);
        this.abonament = abonament;
        this.cvv = cvv;
    }
    public CardBancar(ContBancar c){
        super(c.getNume(),c.getIBAN(),c.getSold());
    }
    public CardBancar(BasicPlan abonament, int cvv) {
        this.abonament = abonament;
        this.cvv = cvv;
    }

    public BasicPlan getAbonament() {
        return abonament;
    }

    public void setAbonament(BasicPlan abonament) {
        this.abonament = abonament;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardBancar that)) return false;
        if (!super.equals(o)) return false;
        return getCvv() == that.getCvv() && Objects.equals(getAbonament(), that.getAbonament());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAbonament(), getCvv());
    }

    @Override
    public String toString() {
        return "CardBancar{" +
                "abonament=" + abonament +
                ", cvv=" + cvv +
                '}';
    }
}
