package com.luisovando.technicaltest.entities;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Transacted {
    double total;
    int userId;

    public Transacted(int userId, double total) {
        this.total = total;
        this.userId = userId;
    }

    @JsonGetter("sum")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @JsonGetter("user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
