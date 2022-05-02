package com.example.use_by;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private List<Food> items;

    public CustomListAdapter(Context context, List<Food> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        Food item = (Food) getItem(position);
        return item.getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.food_row, parent, false);
        }
        
        Food currentItem = (Food) getItem(position);

        TextView itemName = (TextView) view.findViewById(R.id.list_item_name);
        TextView itemStatus = (TextView) view.findViewById(R.id.list_item_status);

        itemName.setText(currentItem.getName());

        int daysToExpiration = currentItem.getDaysUntilExpired();
        int daysToEatNow = Integer.parseInt(context.getResources().getString(R.string.num_days_eat_now));

        if (daysToExpiration < 0 ) {
            itemStatus.setText(context.getResources().getString(R.string.status_expired));
            itemStatus.setTextColor(context.getColor(R.color.status_expired));
        } else if (daysToExpiration <= daysToEatNow) {
            itemStatus.setText(context.getResources().getString(R.string.status_eat_now));
            itemStatus.setTextColor(context.getColor(R.color.status_eat_now));
        } else {
            itemStatus.setText(context.getResources().getString(R.string.status_good));
            itemStatus.setTextColor(context.getColor(R.color.status_good));;
        }

        return view;
    }
}
