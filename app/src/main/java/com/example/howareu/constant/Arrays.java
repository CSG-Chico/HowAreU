package com.example.howareu.constant;

import java.util.ArrayList;

public abstract class Arrays {


    public ArrayList<String> activitiesArrayList() {
        ArrayList<String> activitiesArrayList = new ArrayList<String>();

        return activitiesArrayList;
    }

    public static final ArrayList<String> todoArrayList() {
        ArrayList<String> todoArrayList = new ArrayList<String>();
        todoArrayList.add("Jog");
        todoArrayList.add("Cook");
        todoArrayList.add("Eat");
        todoArrayList.add("Sleep");
        todoArrayList.add("Bike");
        return todoArrayList;
    }

}
