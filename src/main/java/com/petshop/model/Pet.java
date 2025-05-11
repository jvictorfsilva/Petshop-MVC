package com.petshop.model;

public class Pet {
    private int id;
    private String name;
    private int age;
    private String type;
    private String breed;
    private int clientId;

    public Pet(int id, String name, int age, String type, String breed, int clientId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.breed = breed;
        this.clientId = clientId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
}