package com.example.luiz1.nocash.Model;

import java.lang.reflect.Array;

/**
 * Created by augus on 18/06/2018.
 */

public class Imagens {
    private String[] img;

    public Imagens() {
        this.img = new String[]{"a", "b", "c", "d", "e"};
    }



    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }
}
