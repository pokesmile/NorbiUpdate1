package com.norbiupdate1.pokesmile.norbiupdate1;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by enorsza on 2015.02.02..
 */
public class Menu1Fragment extends android.support.v4.app.ListFragment implements FilterFragment.OnFragmentInteractionListener, HeaderFragment.OnFragmentInteractionListener {
    private final static String MyPreferences = "MyPrefs";
    private final static String sortByPref = "SortBy";
    private final static String sorting = "Sorting";
    private final int FILTER_FRAGMENT = R.layout.fragment_filter;
    private final int HEADER_FRAGMENT = R.layout.fragment_header;
    private final int LIST_FRAGMENT = R.layout.menu1_layout;
    View rootView;
    SharedPreferences.Editor editor;
    private MyArrayAdapter adapter;
    private MyCategorieArrayAdapter categorieAdapter;
    private Update1CodesDataSource datasource;
    private List<Update1Codes> values;
    private List<String> categories;
    private SharedPreferences sharedPreferences;
    private FilterFragment filterFragment;
    private HeaderFragment headerFragment;
    private SearchView searchView;
    private ViewGroup newView;
    private ViewGroup mContainerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        filterFragment = new FilterFragment();
        headerFragment = new HeaderFragment();
        mContainerView = (ViewGroup) getActivity().findViewById(R.id.container_main);

        dataSourcePrepare();
        setSorting(sharedPreferences.getString(sortByPref, "name"), sharedPreferences.getBoolean(sorting, true));
        setCategories();
        setHeaderView("header");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setCategories(List<String> cats) {
        categories = cats;
    }

    public void setCategorieAdapter(MyCategorieArrayAdapter categorieAdapter) {
        this.categorieAdapter = categorieAdapter;
        this.adapter = null;
    }

    public void setAdapter(MyArrayAdapter adapter) {
        this.adapter = adapter;
        this.categorieAdapter = null;
    }

    public void filter(String text) {
        if (text == null) adapter.getFilter().filter(null);
        else adapter.getFilter().filter(text);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if (getListAdapter() instanceof MyCategorieArrayAdapter) {
            Toast.makeText(getActivity().getApplicationContext(),
                    categories.get(position), Toast.LENGTH_SHORT)
                    .show();
            if (categorieAdapter != null) {

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(LIST_FRAGMENT, container, false);
        return rootView;
    }

    private void dataSourcePrepare() {
        Log.d("dataSourcePrepare", "function called");
        datasource = new Update1CodesDataSource(getActivity());
        datasource.createPreFilledDatabase();
        datasource.open();
//        datasource.createUpdate1Codes();

    }


    private List<Update1Codes> listSorting(final List<Update1Codes> update1Codes, final String sortBy, final boolean ascending) {
        Collections.sort(update1Codes, new Comparator<Update1Codes>() {
            @Override
            public int compare(Update1Codes lhs, Update1Codes rhs) {
                Update1Codes code1 = lhs;
                Update1Codes code2 = rhs;
                if (sortBy.equals("name")) {
                    if (ascending)
                        return code1.getFoodName().compareToIgnoreCase(code2.getFoodName());
                    else
                        return code2.getFoodName().compareToIgnoreCase(code1.getFoodName());
                } else if (sortBy.equals("code")) {
                    if (ascending) {
                        return code1.getUpdate1Code() - code2.getUpdate1Code();
                    } else if (!ascending) {
                        return code2.getUpdate1Code() - code1.getUpdate1Code();
                    }
                }
                return 0;
            }
        });

        return update1Codes;
    }

    private void setCategories() {
        categories = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!categories.contains(values.get(i).getCategorie()))
                categories.add(values.get(i).getCategorie());
        }
    }

    public void adapterPrepare() {

        values = datasource.getAllUpdate1Codes();
        values = listSorting(values, sharedPreferences.getString(sortByPref, "name"), sharedPreferences.getBoolean(sorting, true));
        Log.d("adapterPrepare", "function called");
        adapter = new MyArrayAdapter(getActivity(), values);
        setAdapter(adapter);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void categoriesAdapterPrepare() {
        Log.d("categoriesAdapterPre", "function called");
        setCategories(categories);
        categorieAdapter = new MyCategorieArrayAdapter(getActivity(), categories);
        setCategorieAdapter(categorieAdapter);
        setListAdapter(categorieAdapter);
        setHeaderView("categories");
        ((MainActivity)getActivity()).setOrderingViewVisibility(false);
        ((MainActivity)getActivity()).setSearchViewVisibility(false);
        categorieAdapter.notifyDataSetChanged();
    }

    public void setSorting(String by, boolean asc) {
        editor.putString(sortByPref, by);
        editor.putBoolean(sorting, asc);
        editor.commit();
        adapterPrepare();
        Log.d("setSorting", "searchString: null");
    }

    public void setHeaderView(String which) {

        switch(which){
            case "list":
                addItem(LIST_FRAGMENT);
                break;
            case "filter":
                addItem(FILTER_FRAGMENT);
                break;
            case "header":
                if ((mContainerView != null) && (mContainerView.getChildCount() > 0)) {
                    removeItem(FILTER_FRAGMENT);
                }else{
                    addItem(HEADER_FRAGMENT);
                }
                break;
            case "categories":
                removeItem(1);
                break;
            case "empty":
                removeItem(0);
                break;
            default:
                break;
        }
    }

    private void addItem(int frag){
        newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(frag, mContainerView, false);

        if(frag == LIST_FRAGMENT){
            dataSourcePrepare();
            setSorting(sharedPreferences.getString(sortByPref, "name"), sharedPreferences.getBoolean(sorting, true));
            setCategories();
        }
        if(frag == FILTER_FRAGMENT) {
            searchView = (SearchView) newView.findViewById(R.id.searchview);

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    setHeaderView("header");
                    ((MainActivity) getActivity()).setOrderingViewVisibility(true);
                    ((MainActivity) getActivity()).setSearch(false);
                    return false;
                }
            });

            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.clearFocus();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filter(newText);
                    return true;
                }
            });
        }

        mContainerView.addView(newView, 0);

        if(searchView != null) {
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocus();
            searchView.requestFocusFromTouch();
            if(frag == HEADER_FRAGMENT){
                searchView.clearFocus();
            }
        }
        Log.d("addItem", String.valueOf(mContainerView.getChildCount()));
    }

    private void removeItem(int frag){

        switch(frag){
            case HEADER_FRAGMENT:
                mContainerView.removeView(newView);
                break;
            case FILTER_FRAGMENT:
                if(searchView != null) {
                    searchView.clearFocus();
                }
                if(mContainerView != null) {
                    mContainerView.removeView(newView);
                    ((MainActivity) getActivity()).setSearch(false);
                }
                break;
            case 1:
                mContainerView.removeView(newView);
                if(searchView != null) {
                    searchView.clearFocus();
                }
                if(mContainerView.getChildCount() > 0){
                    newView = (ViewGroup) mContainerView.getChildAt(mContainerView.getChildCount());
                    mContainerView.removeView(newView);
                }
                break;
            case 0:
                mContainerView.removeAllViews();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}