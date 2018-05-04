package com.example.meda.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meda.shoppinglist.R;
import com.example.meda.shoppinglist.data.ShoppingItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewItemActivity extends AppCompatActivity {

    //String variable used in data passing
    public static final String KEY_NEW_ITEM = "KEY_NEW_ITEM";

    EditText etItemName;
    Spinner itemSpinner;
    EditText etPrice;
    EditText etItemDescription;
    CheckBox etPurchased;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_new);

        etItemName = (EditText) findViewById(R.id.etItemName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        itemSpinner = (Spinner) findViewById(R.id.itemSpinner);
        etItemDescription = (EditText) findViewById(R.id.etItemDescription);
        etPurchased = (CheckBox) findViewById(R.id.etPurchased);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((etItemName.getText().toString()).equals(""))) {
                    Toast.makeText(NewItemActivity.this, "Please enter an item name.", Toast.LENGTH_LONG).show();
                } else if ((etPrice.getText().toString()).equals("")) {
                    Toast.makeText(NewItemActivity.this, "Please enter a price.", Toast.LENGTH_LONG).show();
                } else {

                    //creates new shopping item on clicking button
                    ShoppingItem newItem = new ShoppingItem(etItemName.getText().toString(),
                            itemSpinner.getSelectedItem().toString(), etItemDescription.getText().toString(),
                            etPrice.getText().toString(), etPurchased.isChecked());

                    //Passing data through Intents
                    //this specifically passes a new item
                    Intent intentResult = new Intent();
                    intentResult.putExtra(KEY_NEW_ITEM, newItem);
                    setResult(RESULT_OK, intentResult);
                    finish();
                }
            }
        });

        //Combo Box for which type of item
        Spinner spinner = (Spinner) findViewById(R.id.itemSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_array, R.layout.text_view);
        adapter.setDropDownViewResource(R.layout.text_view);
        spinner.setAdapter(adapter);

    }

}
