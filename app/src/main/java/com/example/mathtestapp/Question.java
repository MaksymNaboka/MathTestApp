package com.example.mathtestapp;
import java.io.Serializable;
public class Question implements Serializable {
    float a,b,c;
    int operation,missing;
    boolean Simple;
    Question(float a, float b, float c, int operation, int missing, boolean Simple){
        this.a=a;
        this.b=b;
        this.c=c;
        this.operation=operation;
        this.missing=missing;
        this.Simple=Simple;
    }
}
