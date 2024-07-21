package com.Item;

public class Device {
    private String description;
    private int amount;
    private float price;
    private int[] date;
    private String invoice;
    private String idTeacher;
    
    public Device() {
        description = "";
        amount = 0;
        price = 0;
        date = new int[]{0,0,0};
        invoice = "";
        idTeacher = "";
    }
    
    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public String getDate() {
        return date[0]+"/"+date[1]+"/"+date[2];
    }

    public String getInvoice() {
        return invoice;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDate(int[] date) {
        this.date = date;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String exportDevice() {
        return description+"#"+amount+"#"+price+"#"+date[0]+"/"+date[1]+"/"+date[2]+"#"+invoice+"#"+idTeacher;
    }
}