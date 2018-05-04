package com.example.meda.shoppinglist;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meda.shoppinglist.adapter.ShoppingListAdapter;
import com.example.meda.shoppinglist.data.ShoppingItem;

public class MainActivity extends AppCompatActivity {

    ImageView ivIcon;
    TextView tvLabel;
    TextView tvPrice;
    CheckBox checkBoxPurchase;
    Button btnDelete;
    Button btnItemDetails;
    ShoppingListAdapter shoppingListAdapter;
    ShoppingItem newItem;
    LinearLayout linearLayout;

    public static final int REQUEST_CODE_NEW_ITEM = 101;
    public static final int REQUEST_CODE_EDIT_ITEM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivIcon = (ImageView) findViewById(R.id.ivIcon);
        tvLabel = (TextView) findViewById(R.id.tvLabel);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        checkBoxPurchase = (CheckBox) findViewById(R.id.checkBoxPurchase);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnItemDetails = (Button) findViewById(R.id.btnItemDetails);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        //Adapter stuff
        shoppingListAdapter = new ShoppingListAdapter(this);
        final RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // RecyclerView layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(shoppingListAdapter);

    }

    //inflates menu on main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_price_item:

                //everytime I click the button I want to get all the shoppingListAdapter integers
                double totalPrice = shoppingListAdapter.totalPrice();

                Snackbar.make(linearLayout, getString(R.string.price) + totalPrice
                        , Snackbar.LENGTH_LONG).show();

                break;

            case R.id.action_add_item:

                Intent intentShowDetails = new Intent(
                        MainActivity.this,
                        NewItemActivity.class
                );

                // start Activity and get result
                startActivityForResult(new Intent(
                        MainActivity.this,
                        NewItemActivity.class), REQUEST_CODE_NEW_ITEM);

                break;

            //Change delete option to delete
            case R.id.action_delete:
                shoppingListAdapter.removeAll();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_CODE_NEW_ITEM) {
            if (resultCode == RESULT_OK) {
                newItem = (ShoppingItem) data.getSerializableExtra(
                        NewItemActivity.KEY_NEW_ITEM);

                //adds item to recycler view
                shoppingListAdapter.addTodo(newItem);

            }
        } else if (requestCode == REQUEST_CODE_EDIT_ITEM) {
            if (resultCode == RESULT_OK) {

                newItem = (ShoppingItem) data.getSerializableExtra(
                        EditActivity.EDITED_ITEM);

                int position = (int) data.getSerializableExtra(EditActivity.EDIT_POSITION);

                shoppingListAdapter.editItem(position, newItem);

            }
        }
    }


}
