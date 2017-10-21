package com.milera.marcelo.recyclerviewstaggeredgrid.model;

import java.io.Serializable;

/**
 * Created by MARCELO on 20/10/2017.
 */

public class Pokemon implements Serializable {
    private String name;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
