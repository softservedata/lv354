package com.softserve.edu.opencart.data.application;

public class TestStatus {

    protected boolean isLogged;

    public TestStatus() {
        isLogged = false;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

}
