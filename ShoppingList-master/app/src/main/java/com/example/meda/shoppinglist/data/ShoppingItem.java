package com.example.meda.shoppinglist.data;


import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by meda on 4/12/16.
 */
public class ShoppingItem extends SugarRecord implements Serializable {

    private String itemName;
    private String itemType;
    private String price;
    private String itemDescription;
    private boolean purchased;

    //sugar needs this
    public ShoppingItem() {
    }

    public ShoppingItem(String itemName, String itemType, String itemDescription, String price, boolean purchased) {
        this.itemDescription = itemDescription;
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
        this.purchased = purchased;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }


}
