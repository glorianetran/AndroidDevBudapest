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

import com.example.meda.shoppinglist.data.ShoppingItem;

public class EditActivity extends AppCompatActivity {

    EditText etItemName;
    Spinner itemSpinner;
    EditText etPrice;
    EditText etItemDescription;
    CheckBox etPurchased;
    Button btnAdd;

    ShoppingItem itemToEdit;
    public static final String EDITED_ITEM = "EDITED_ITEM";
    public static final String EDIT_POSITION = "EDIT_POSITION";
    public static final String POSITION = "POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItemName = (EditText) findViewById(R.id.etItemName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        itemSpinner = (Spinner) findViewById(R.id.itemSpinner);
        etItemDescription = (EditText) findViewById(R.id.etItemDescription);
        etPurchased = (CheckBox) findViewById(R.id.etPurchased);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //Combo Box for which type of item
        Spinner spinner = (Spinner) findViewById(R.id.itemSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_array, R.layout.text_view);
        adapter.setDropDownViewResource(R.layout.text_view);
        spinner.setAdapter(adapter);


        //this intent grabs the intent form the shopping list adapter. Takes the item to
        //edit (ShoppingItem object) and then puts it in the
        final Intent intentToEdit = getIntent();
        itemToEdit = (ShoppingItem) intentToEdit.getSerializableExtra(EDITED_ITEM);

        //final Intent intentToEdit = getIntent();
        final int position = (int) intentToEdit.getSerializableExtra(POSITION);

        //Todo need to get spinner data and load it

        String spinnerText = itemToEdit.getItemType();
        int spinnerNumber = 0;

        if(spinnerText.equals(getString(R.string.Food))){
            spinnerNumber = 0;
        }else if(spinnerText.equals(getString(R.string.electronic))){
            spinnerNumber = 1;
        }else if(spinnerText.equals(getString(R.string.clothing))){
            spinnerNumber = 2;
        }else if(spinnerText.equals(getString(R.string.random))){
            spinnerNumber = 3;
        }

        itemSpinner.setSelection(spinnerNumber);
        etItemName.setText(itemToEdit.getItemName());
        etPrice.setText(itemToEdit.getPrice());
        etItemDescription.setText(itemToEdit.getItemDescription());
        etPurchased.setChecked(itemToEdit.isPurchased());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((etItemName.getText().toString()).equals(""))){
                    Toast.makeText(EditActivity.this, R.string.enterName, Toast.LENGTH_LONG).show();
                }else if((etPrice.getText().toString()).equals("")){
                    Toast.makeText(EditActivity.this, R.string.enterPrice, Toast.LENGTH_LONG).show();
                }else {
                    //creates new shopping item on clicking button
                    ShoppingItem newItem = new ShoppingItem(etItemName.getText().toString(),
                            itemSpinner.getSelectedItem().toString(), etItemDescription.getText().toString(),
                            etPrice.getText().toString(), etPurchased.isChecked());

                    //this gets the intent from the shoppingListAdapter and then adds the new
                    //edited item & position to send back to the main activity

                    Intent intentToEditReturn = new Intent();
                    intentToEditReturn.putExtra(EDITED_ITEM, newItem);
                    intentToEditReturn.putExtra(EDIT_POSITION, position);
                    setResult(RESULT_OK, intentToEditReturn);
                    finish();
                }
            }
        });

    }
}
