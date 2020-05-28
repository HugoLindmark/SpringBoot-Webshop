package com.g09.webshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.NumberFormat;
import java.util.Locale;

public class CartItem {

    private Record record;
    private int quantity;


    public CartItem(Record record, int quantity) {
        this.record = record;
        this.quantity = quantity;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("totalPrice")
    public String totalPrice(){
        int result = this.quantity * this.record.getPrice();
        return FormatNumber.formatCurrency(result);
    }
}
