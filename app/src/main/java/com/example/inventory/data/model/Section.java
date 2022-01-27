package com.example.inventory.data.model;

public class Section {

    private int id;
    private int iddependency;
    private String name;
    private String shortname;
    private String description;
    private String image;

    public Section(int id, int iddependency, String name, String shortname, String description, String image) {
        this.id = id;
        this.iddependency = iddependency;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.image = image;
    }

    public Section() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddependency() {
        return iddependency;
    }

    public void setIddependency(int iddependency) {
        this.iddependency = iddependency;
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
}
