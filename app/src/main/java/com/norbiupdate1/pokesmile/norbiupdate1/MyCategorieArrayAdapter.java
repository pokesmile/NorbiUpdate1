package com.norbiupdate1.pokesmile.norbiupdate1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        ImageView categorieImageTextView = (ImageView) rowView.findViewById(R.id.categorie_picture);
        TextView categorieNameTextView = (TextView) rowView.findViewById(R.id.categorie_name);

        categorieNameTextView.setText(categ);

        switch(categ){
            case "Italok":
                categorieImageTextView.setImageResource(R.drawable.drinks);
                break;
            case "Csokoládék":
                categorieImageTextView.setImageResource(R.drawable.chocolates);
                break;
            case "Cukor hozzáadásával készült mártások":
                categorieImageTextView.setImageResource(R.drawable.sauces);
                break;
            case "Desszertek, csokoládék, jégkrémek":
                categorieImageTextView.setImageResource(R.drawable.desserts);
                break;
            case "Édesítőszerek":
                categorieImageTextView.setImageResource(R.drawable.sweeteners);
                break;
            case "Egyéb":
                categorieImageTextView.setImageResource(R.drawable.others);
                break;
            case "Feltétek – Hal":
                categorieImageTextView.setImageResource(R.drawable.fishes);
                break;
            case "Feltétek – Marha, borjú":
                categorieImageTextView.setImageResource(R.drawable.beefs);
                break;
            case "Feltétek – Sertés":
                categorieImageTextView.setImageResource(R.drawable.pig_meat);
                break;
            case "Feltétek – Szárnyasok":
                categorieImageTextView.setImageResource(R.drawable.fried_chicken);
                break;
            case "Feltétek – Zöldség alapú feltét":
                categorieImageTextView.setImageResource(R.drawable.fried_vegetables);
                break;
            case "Főételek – Főzelék":
                categorieImageTextView.setImageResource(R.drawable.lentils_stew);
                break;
            case "Főételek – Húsalapú":
                categorieImageTextView.setImageResource(R.drawable.meat_based_dishes);
                break;
            case "Főételek – Rizses":
                categorieImageTextView.setImageResource(R.drawable.main_courses_with_rice);
                break;
            case "Főételek – Tésztás":
                categorieImageTextView.setImageResource(R.drawable.pastas);
                break;
            case "Főételek – Zöldségalapú":
                categorieImageTextView.setImageResource(R.drawable.vegetable_dishes);
                break;
            case "Fűszerek, öntetek, kenyérrevalók":
                categorieImageTextView.setImageResource(R.drawable.spices);
                break;
            case "Gyümölcsök":
                categorieImageTextView.setImageResource(R.drawable.fruits);
                break;
            case "Húsok, húskészítmények":
                categorieImageTextView.setImageResource(R.drawable.meats);
                break;
            case "Jégkrémek":
                categorieImageTextView.setImageResource(R.drawable.ice_creams);
                break;
            case "Köretek – Burgonyaalapú":
                categorieImageTextView.setImageResource(R.drawable.fried_potatoes);
                break;
            case "Köretek – Gabona alapú köretek":
                categorieImageTextView.setImageResource(R.drawable.dumplings);
                break;
            case "Köretek – Gyümölcs alapú köretek":
                categorieImageTextView.setImageResource(R.drawable.fruit_side_dishes);
                break;
            case "Köretek – Rizs alapú köretek":
                categorieImageTextView.setImageResource(R.drawable.rice);
                break;
            case "Köretek – Tejtermék, tojás alapú köretek":
                categorieImageTextView.setImageResource(R.drawable.egg_salads);
                break;
            case "Köretek – Tésztaköret":
                categorieImageTextView.setImageResource(R.drawable.spaghetti);
                break;
            case "Levesek":
                categorieImageTextView.setImageResource(R.drawable.soups);
                break;
            case "Lisztek, tészták, pékáruk, gabonaipari termékek":
                categorieImageTextView.setImageResource(R.drawable.breads);
                break;
            case "Mártások":
                categorieImageTextView.setImageResource(R.drawable.sauces);
                break;
            case "Nassok, olajos magvak":
                categorieImageTextView.setImageResource(R.drawable.snacks);
                break;
            case "Pizzák":
                categorieImageTextView.setImageResource(R.drawable.pizzas);
                break;
            case "Saláták":
                categorieImageTextView.setImageResource(R.drawable.salads);
                break;
            case "Szendvicsek":
                categorieImageTextView.setImageResource(R.drawable.sandwiches);
                break;
            case "Tejtermékek":
                categorieImageTextView.setImageResource(R.drawable.milk_products);
                break;
            case "Update készételek":
                categorieImageTextView.setImageResource(R.drawable.norbiupdate1icon);
                break;
            case "Update1 Bisztrós termékek":
                categorieImageTextView.setImageResource(R.drawable.norbiupdate1icon);
                break;
            case "Zöldség alapú köretek":
                categorieImageTextView.setImageResource(R.drawable.vegetable_side_dishes);
                break;
            case "Zöldségek, zöldségkészítmények, savanyúságok":
                categorieImageTextView.setImageResource(R.drawable.vegetables);
                break;
            default:
                categorieImageTextView.setImageResource(R.drawable.generalfood);
                break;
        }

        return rowView;
    }

    public List<String> getCategories(){
        return allValues;
    }
}
