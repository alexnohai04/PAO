package Clase;

import java.time.LocalDate;
import java.util.Date;

public class BasicPlan {

    private int pret;

    private LocalDate startDate;
    private LocalDate endDate;
    public BasicPlan(){};
    public BasicPlan(int pret, LocalDate startDate, LocalDate endDate) {
        this.pret = pret;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
