package Clase;

import java.time.LocalDate;
import java.util.Date;

public class BasicPlan {

    private int id;
    private int pret;

    private LocalDate startDate;

    public BasicPlan(){};
    public BasicPlan(int pret, LocalDate startDate) {
        this.pret = pret;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
