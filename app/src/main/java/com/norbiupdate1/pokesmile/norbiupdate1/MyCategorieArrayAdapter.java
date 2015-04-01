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
            case "Italok":
                categorieImageTextView.setBackgroundResource(R.drawable.drinks);
                break;
            case "Csokoládék":
                categorieImageTextView.setBackgroundResource(R.drawable.chocolates);
                break;
            case "Cukor hozzáadásával készült mártások":
                categorieImageTextView.setBackgroundResource(R.drawable.sauces);
                break;
            case "Desszertek, csokoládék, jégkrémek":
                categorieImageTextView.setBackgroundResource(R.drawable.desserts);
                break;
            case "Édesítőszerek":
                categorieImageTextView.setBackgroundResource(R.drawable.sweeteners);
                break;
            case "Egyéb":
                categorieImageTextView.setBackgroundResource(R.drawable.others);
                break;
            case "Feltétek – Hal":
                categorieImageTextView.setBackgroundResource(R.drawable.fishes);
                break;
            case "Feltétek – Marha, borjú":
                categorieImageTextView.setBackgroundResource(R.drawable.beefs);
                break;
            case "Feltétek – Sertés":
                categorieImageTextView.setBackgroundResource(R.drawable.pig_meat);
                break;
            case "Feltétek – Szárnyasok":
                categorieImageTextView.setBackgroundResource(R.drawable.fried_chicken);
                break;
            case "Feltétek – Zöldség alapú feltét":
                categorieImageTextView.setBackgroundResource(R.drawable.fried_vegetables);
                break;
            case "Főételek – Főzelék":
                categorieImageTextView.setBackgroundResource(R.drawable.lentils_stew);
                break;
            case "Főételek – Húsalapú":
                categorieImageTextView.setBackgroundResource(R.drawable.meat_based_dishes);
                break;
            case "Főételek – Rizses":
                categorieImageTextView.setBackgroundResource(R.drawable.main_courses_with_rice);
                break;
            case "Főételek – Tésztás":
                categorieImageTextView.setBackgroundResource(R.drawable.pastas);
                break;
            case "Főételek – Zöldségalapú":
                categorieImageTextView.setBackgroundResource(R.drawable.vegetable_dishes);
                break;
            case "Fűszerek, öntetek, kenyérrevalók":
                categorieImageTextView.setBackgroundResource(R.drawable.spices);
                break;
            case "Gyümölcsök":
                categorieImageTextView.setBackgroundResource(R.drawable.fruits);
                break;
            case "Húsok, húskészítmények":
                categorieImageTextView.setBackgroundResource(R.drawable.meats);
                break;
            case "Jégkrémek":
                categorieImageTextView.setBackgroundResource(R.drawable.ice_creams);
                break;
            case "Köretek – Burgonyaalapú":
                categorieImageTextView.setBackgroundResource(R.drawable.fried_potatoes);
                break;
            case "Köretek – Gabona alapú köretek":
                categorieImageTextView.setBackgroundResource(R.drawable.dumplings);
                break;
            case "Köretek – Gyümölcs alapú köretek":
                categorieImageTextView.setBackgroundResource(R.drawable.fruit_side_dishes);
                break;
            case "Köretek – Rizs alapú köretek":
                categorieImageTextView.setBackgroundResource(R.drawable.rice);
                break;
            case "Köretek – Tejtermék, tojás alapú köretek":
                categorieImageTextView.setBackgroundResource(R.drawable.egg_salads);
                break;
            case "Köretek – Tésztaköret":
                categorieImageTextView.setBackgroundResource(R.drawable.spaghetti);
                break;
            case "Levesek":
                categorieImageTextView.setBackgroundResource(R.drawable.soups);
                break;
            case "Lisztek, tészták, pékáruk, gabonaipari termékek":
                categorieImageTextView.setBackgroundResource(R.drawable.breads);
                break;
            case "Mártások":
                categorieImageTextView.setBackgroundResource(R.drawable.sauces);
                break;
            case "Nassok, olajos magvak":
                categorieImageTextView.setBackgroundResource(R.drawable.snacks);
                break;
            case "Pizzák":
                categorieImageTextView.setBackgroundResource(R.drawable.pizzas);
                break;
            case "Saláták":
                categorieImageTextView.setBackgroundResource(R.drawable.salads);
                break;
            case "Szendvicsek":
                categorieImageTextView.setBackgroundResource(R.drawable.sandwiches);
                break;
            case "Tejtermékek":
                categorieImageTextView.setBackgroundResource(R.drawable.milk_products);
                break;
            case "Update készételek":
                categorieImageTextView.setBackgroundResource(R.drawable.norbiupdate1icon);
                break;
            case "Update1 Bisztrós termékek":
                categorieImageTextView.setBackgroundResource(R.drawable.norbiupdate1icon);
                break;
            case "Zöldség alapú köretek":
                categorieImageTextView.setBackgroundResource(R.drawable.vegetable_side_dishes);
                break;
            case "Zöldségek, zöldségkészítmények, savanyúságok":
                categorieImageTextView.setBackgroundResource(R.drawable.vegetables);
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
