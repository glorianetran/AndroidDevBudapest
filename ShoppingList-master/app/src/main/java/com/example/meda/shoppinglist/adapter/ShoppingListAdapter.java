package com.example.meda.shoppinglist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.meda.shoppinglist.EditActivity;
import com.example.meda.shoppinglist.ItemDetailsActivity;
import com.example.meda.shoppinglist.MainActivity;
import com.example.meda.shoppinglist.R;
import com.example.meda.shoppinglist.data.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meda on 4/10/16.
 */

public class ShoppingListAdapter
        extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private Context context;

    //Strings to use for icons
    public static final String electronic = "Electronic";
    public static final String random = "Miscellaneous";
    public static final String food = "Food";
    public static final String clothes = "Clothing";

    public static final String ITEM_DETAIL = "ITEM_DETAIL";
    public static final String EDITED_ITEM = "EDITED_ITEM";
    public static final String POSITION = "POSITION";


    private List<ShoppingItem> items = new ArrayList<ShoppingItem>();

    public ShoppingListAdapter(Context context) {
        this.context = context;
        items = ShoppingItem.listAll(ShoppingItem.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list, parent, false);
        return new ViewHolder(rowView);
    }

    //What to do with the information from adding the new item
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //changes icons for correct items
        String itemType = items.get(position).getItemType();
        if (itemType.equals(electronic)) {
            holder.ivIcon.setImageResource(R.drawable.apple);
        }else if(itemType.equals(food)){
            holder.ivIcon.setImageResource(R.drawable.grocery);
        }else if(itemType.equals(clothes)){
            holder.ivIcon.setImageResource(R.drawable.shirts);
        }else if(itemType.equals(random)){
            holder.ivIcon.setImageResource(R.drawable.random);
        }

        //Sets the item name
        holder.tvLabel.setText(items.get(position).getItemName());

        //Sets price
        holder.tvPrice.setText(context.getString(R.string.$) + items.get(position).getPrice());

        //sets the check box for if the user purchased the item
        holder.checkBoxPurchase.setChecked(items.get(position).isPurchased());
        holder.checkBoxPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingItem item = items.get(position);
                item.setPurchased(holder.checkBoxPurchase.isChecked());
                item.save();
            }
        });

        //delete button
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

        //item detail button
        holder.btnItemDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowDetails = new Intent(context,
                        ItemDetailsActivity.class
                );
                intentShowDetails.putExtra(ITEM_DETAIL, items.get(position));
                context.startActivity(intentShowDetails);

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentShowDetails = new Intent(context, EditActivity.class);
                intentShowDetails.putExtra(EDITED_ITEM, items.get(position));
                intentShowDetails.putExtra(POSITION, position);
                ((MainActivity)context).startActivityForResult(intentShowDetails, 2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addTodo(ShoppingItem item) {
        items.add(0, item);
        item.save();
        notifyItemInserted(0);

    }

    //calculates the totalPrice
    public double totalPrice(){
        double price = 0;
        for(ShoppingItem item: items){
            if(!item.isPurchased()) {
                price += Double.parseDouble(item.getPrice());
            }
        }
        return price;
    }

    public void removeItem(int position) {
        items.get(position).delete();
        items.remove(position);

        notifyItemRemoved(position);
    }

    public void removeAll() {
        while (items.size() > 0) {
            removeItem(0);
        }
        notifyDataSetChanged();
    }

    public void editItem(int position, ShoppingItem item){
        items.get(position).delete();
        items.remove(position);
        items.add(position,item);
        item.save();
        notifyItemChanged(position);
    }


    //holds the view for items in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvLabel;
        private TextView tvPrice;
        private CheckBox checkBoxPurchase;
        private Button btnDelete;
        private Button btnItemDetails;
        private Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvLabel = (TextView) itemView.findViewById(R.id.tvLabel);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            checkBoxPurchase = (CheckBox) itemView.findViewById(R.id.checkBoxPurchase);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnItemDetails = (Button) itemView.findViewById(R.id.btnItemDetails);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);


        }
    }

}

