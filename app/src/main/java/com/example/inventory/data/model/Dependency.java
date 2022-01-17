package com.example.inventory.data.model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Dependency implements Comparable , Serializable {
    public static final String TAG = "dependency";
    String name;
    String shortname;
    String description;
    String image;

    public Dependency(String name, String shortname, String description, String image) {
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.image = image;
    }

    public Dependency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Dependency)obj).getName().equals(getName());
    }


    @Override
    public int compareTo(Object obj) {
        //return ((Dependency)obj).getName().compareTo(getName());
        if (equals(obj))
            return ((Dependency)obj).getDescription().compareTo(getName());
        else
            return ((Dependency)obj).getName().compareTo(getName());
    }



}
