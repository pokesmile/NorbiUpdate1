package com.norbiupdate1.pokesmile.norbiupdate1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enorsza on 2015.02.02..
 */
public class MyArrayAdapter extends ArrayAdapter<Update1Codes> implements Filterable {
    private final Context context;
    private List<Update1Codes> allValues = null;
    private List<Update1Codes> allValuesInFilter = null;
    private dataFilter filter = new dataFilter();

    public MyArrayAdapter(Context context, final List<Update1Codes> values) {
        super(context, R.layout.row_layout, values);
        this.context = context;
        this.allValues = values;
        this.allValuesInFilter = values;
    }

    public int getCount() {
        return allValuesInFilter.size();
    }

    public Update1Codes getItem(int position) {
        return allValuesInFilter.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class dataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null) {
                results = new FilterResults();

                final List<Update1Codes> list = allValues;

                int count = list.size();
                Log.d("List.size()", String.valueOf(list.size()));
                final List<Update1Codes> nlist = new ArrayList<>(count);

                Update1Codes filterableString;

                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i);
                    if (filterableString.getFoodName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        nlist.add(filterableString);
                    }
                }

                results.values = nlist;
                results.count = nlist.size();
            } else {
                results.values = allValues;
                results.count = allValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allValuesInFilter = (ArrayList<Update1Codes>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView update1CodeTextView = (TextView) rowView.findViewById(R.id.update1Code);
        TextView foodNameTextView = (TextView) rowView.findViewById(R.id.foodName);
        TextView cHTextView = (TextView) rowView.findViewById(R.id.CH);
        TextView gITextView = (TextView) rowView.findViewById(R.id.GI);

        update1CodeTextView.setText(String.valueOf(allValuesInFilter.get(position).getUpdate1Code()));
        foodNameTextView.setText(allValuesInFilter.get(position).getFoodName());
        cHTextView.setText(String.valueOf(allValuesInFilter.get(position).getcH()));
        gITextView.setText(String.valueOf(allValuesInFilter.get(position).getgI()));

        // Change the icon for Windows and iPhone
        int code = allValuesInFilter.get(position).getUpdate1Code();
        if (code == 1) {
            update1CodeTextView.setBackgroundResource(R.drawable.low_sign);
        } else if (code == 2) {
            update1CodeTextView.setBackgroundResource(R.drawable.mid_sign);
        } else if (code == 3) {
            update1CodeTextView.setBackgroundResource(R.drawable.high_sign);
        }

        return rowView;
    }

    public void resetData() {
//        allValues = all;
        allValuesInFilter = allValues;
    }
}
