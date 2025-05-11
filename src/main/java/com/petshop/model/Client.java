package com.petshop.model;

public class Client {
    private int id;
    private String name;
    private String cpf;
    private String phone;

    public Client(int id, String name, String cpf, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Client{id=" + id + ", name='" + name + "', cpf='" + cpf + "', phone='" + phone + "'}";
    }
}