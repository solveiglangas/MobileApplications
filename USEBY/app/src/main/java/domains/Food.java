package domains;

import java.util.Date;

public class Food {
    long id;
    String name;
    int quantity;
    Date date;

    public Food(long id, String name, int quantity, Date date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




}
