package com.example.meda.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.meda.shoppinglist.data.ShoppingItem;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView tvItemDetails;
    public static final String ITEM_DETAIL = "ITEM_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        tvItemDetails = (TextView) findViewById(R.id.tvItemDetails);
        Intent intentDetails =
                getIntent();
        tvItemDetails.setText(((ShoppingItem)
                intentDetails.getSerializableExtra(ITEM_DETAIL)).getItemDescription());

    }
}
