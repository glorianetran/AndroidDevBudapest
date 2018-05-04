package com.example.meda.weatherinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.meda.weatherinfo.DetailsActivity;
import com.example.meda.weatherinfo.R;
import com.example.meda.weatherinfo.data.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meda on 4/27/16.
 */
public class CityAdapter
        extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;

    public static final String CITY_NAME = "KEY_CITY_NAME";

    private List<City> items = new ArrayList<City>();

    public CityAdapter(Context context) {
        this.context = context;
        items = City.listAll(City.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_list, parent, false);
        return new ViewHolder(rowView);
    }

    //What to do with the information from adding the new item
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //Sets the item name
        holder.btnCity.setText(items.get(position).getCity());

        //Brings you to new activity
        holder.btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowDetails = new Intent(context,
                        DetailsActivity.class
                );
                intentShowDetails.putExtra(CITY_NAME, items.get(holder.getAdapterPosition()));
                context.startActivity(intentShowDetails);
            }
        });

        //delete button
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addTodo(City item) {
        items.add(0, item);
        item.save();
        notifyItemInserted(0);
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

    //holds the view for items in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnCity;
        private ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            btnCity = (Button) itemView.findViewById(R.id.btnCity);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);

        }
    }

}