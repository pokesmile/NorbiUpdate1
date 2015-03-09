package com.norbiupdate1.pokesmile.norbiupdate1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by enorsza on 2015.02.02..
 */
public class MyCategorieArrayAdapter extends ArrayAdapter<String>{
    private final Context context;
    private List<String> allValues = null;

    public MyCategorieArrayAdapter(Context context, final List<String> values) {
        super(context, R.layout.row_layout, values);
        this.context = context;
        this.allValues = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.categories_row_layout, parent, false);
        String categ = allValues.get(position);

        TextView categorieImageTextView = (TextView) rowView.findViewById(R.id.categorie_picture);
        TextView categorieNameTextView = (TextView) rowView.findViewById(R.id.categorie_name);

        categorieNameTextView.setText(categ);
        categorieImageTextView.setText(null);

        switch(categ){
            case "":
                categorieImageTextView.setBackgroundResource(R.drawable.generalfood);
                break;
            default:
                categorieImageTextView.setBackgroundResource(R.drawable.generalfood);
                break;
        }

        return rowView;
    }

    public List<String> getCategories(){
        return allValues;
    }
}
