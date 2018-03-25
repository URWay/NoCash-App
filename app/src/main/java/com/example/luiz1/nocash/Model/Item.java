package com.example.luiz1.nocash.Model;

import android.view.View;

import java.util.List;

public class Item {
    public int type;
    public String headerText, bottomText, imageURL;


    public Item(int type, String headerText, String bottomText, String imageURL) {
        this.type = type;
        this.headerText = headerText;
        this.bottomText = bottomText;
        this.imageURL = imageURL;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public interface ItemClickListener{
        void onClick(View view, int position, boolean islongclick);
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
