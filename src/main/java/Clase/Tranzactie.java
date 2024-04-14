package Clase;

public class Tranzactie {

    private static int nextId = 1;
    private final int id;
    private int suma;
    private Client destinatar;


    public Tranzactie(int suma, Client destinatar) {
        this.suma = suma;
        this.destinatar = destinatar;
        this.id= nextId ++;
    }
    public Tranzactie(){
        this.id= nextId ++;
    };

    public int getId() {
        return id;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public Client getDestinatar() {
        return destinatar;
    }

    public void setDestinatar(Client destinatar) {
        this.destinatar = destinatar;
    }
}
