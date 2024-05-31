package com.example.lab3_new;

public class Student {
    private int id;
    private String name;
    private String age;
    private float score;

    public Student(int id, String name, String age, float score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public float getScore() {return score;}
}
