package com.norbiupdate1.pokesmile.norbiupdate1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends FragmentActivity
        implements FilterFragment.OnFragmentInteractionListener, NavigationDrawerFragment.NavigationDrawerCallbacks,
        HeaderFragment.OnFragmentInteractionListener {

    private final static String MYPREFERENCES = "MyPrefs";
    private final static String SORTBYPREF = "SortBy";
    private final static String SORTING = "Sorting";
    private static View orderingView;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.Fragment objFragment;
    private Menu1Fragment menu1Fragment;
    private boolean isSearch = false;
    private View filterView;
    private LinearLayout containerMain;
    private DrawerLayout.LayoutParams containerMainParams;
    private boolean inCategories = false;
    private boolean inCategorieList = false;

    private static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        checkSharedPreferences();

        fragmentManager = getSupportFragmentManager();

        setContentView(R.layout.activity_main);
        containerMain = new LinearLayout(this);
        containerMain = (LinearLayout) findViewById(R.id.container_main);
        containerMainParams = (DrawerLayout.LayoutParams) containerMain.getLayoutParams();
        orderingView = findViewById(R.id.action_ordering);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (orderingView == null) orderingView = findViewById(R.id.action_ordering);
        if (filterView == null) filterView = findViewById(R.id.action_search);
        objFragment = null;

        switch (position) {
            case 0:
                menu1Fragment = new Menu1Fragment();
                if (orderingView != null) setOrderingViewVisibility(true);
                if (filterView != null) filterView.setVisibility(View.VISIBLE);
                if (containerMainParams != null) {
                }
                fragmentManager.beginTransaction().replace(R.id.container_main, menu1Fragment).commit();
                break;
            case 1:
                objFragment = new Menu2Fragment();
                menu1Fragment.setHeaderView("empty");
                if (filterView != null) filterView.setVisibility(View.INVISIBLE);
                if (containerMainParams != null) {
                    setContainerParams(0);
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.container_main, objFragment)
                        .commit();
                break;
            case 2:
                objFragment = new Menu3Fragment();
                menu1Fragment.setHeaderView("empty");
                if (filterView != null) filterView.setVisibility(View.INVISIBLE);
                if (containerMainParams != null) {
                    setContainerParams(0);
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.container_main, objFragment)
                        .commit();
                break;
            case 3:
                System.exit(1);
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public void onBackPressed() {
        if (isSearch) {
            setOrderingViewVisibility(true);
            menu1Fragment.setHeaderView("header");
            menu1Fragment.filter(null);
            isSearch = false;
        } else if (inCategories) {
            menu1Fragment = null;
            menu1Fragment = new Menu1Fragment();
            if (orderingView != null) setOrderingViewVisibility(true);
            if (filterView != null) filterView.setVisibility(View.VISIBLE);
            if (containerMainParams != null) {
            }
            fragmentManager.beginTransaction().replace(R.id.container_main, menu1Fragment).commit();
            inCategories = false;
        }else if(inCategorieList){
            menu1Fragment.categoriesAdapterPrepare();
            fragmentManager.beginTransaction().replace(R.id.container_main, menu1Fragment).commit();
            inCategories = true;
            inCategorieList = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_categories) {
            menu1Fragment.categoriesAdapterPrepare();
            fragmentManager.beginTransaction().replace(R.id.container_main, menu1Fragment).commit();
            inCategories = true;
            return true;
        }
        if (id == R.id.action_order_by_code) {
            menu1Fragment.setSorting("code", true, null, true);
            return true;
        }
        if (id == R.id.action_order_by_name) {
            menu1Fragment.setSorting("name", true, null, true);
            return true;
        }
        if (id == R.id.action_ordering) {
            checkSharedPreferences();
            boolean asc = sharedPreferences.getBoolean(SORTING, true);
            String by = sharedPreferences.getString(SORTBYPREF, "name");
            if (inCategories) {
                menu1Fragment.setSorting(by, !asc, null, false);
            } else if (inCategorieList) {
                menu1Fragment.setSorting(by, !asc, menu1Fragment.getSelectedCategorie(), true);
            } else {
                menu1Fragment.setSorting(by, !asc, null, true);
            }
            return true;
        }
        if (id == R.id.action_search) {

            if (isSearch == false) {
                menu1Fragment.setHeaderView("filter");
                setOrderingViewVisibility(false);
                isSearch = true;
            } else if (isSearch == true) {
                setOrderingViewVisibility(true);
                menu1Fragment.setHeaderView("header");
                menu1Fragment.filter(null);
                isSearch = false;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setSearch(boolean isSearch) {
        this.isSearch = isSearch;
    }

    private void checkSharedPreferences() {
        Log.d("checkSharedPreferences", "function called, SORTBYPREF = " + sharedPreferences.getString(SORTBYPREF, null));
        if (sharedPreferences.getString(SORTBYPREF, null) == null) {
            editor.putString(SORTBYPREF, "name");
            editor.putBoolean(SORTING, true);
            editor.commit();
        }
    }

    public void setContainerParams(int topMargin) {
        containerMainParams.topMargin = dpToPx(topMargin);
        containerMain.setLayoutParams(containerMainParams);

    }

    public void setOrderingViewVisibility(boolean onOff) {
        if (orderingView == null) orderingView = findViewById(R.id.action_ordering);
        if (onOff) orderingView.setVisibility(View.VISIBLE);
        else orderingView.setVisibility(View.GONE);
    }

    public void setSearchViewVisibility(boolean onOff) {
        if (filterView == null) filterView = findViewById(R.id.action_search);
        if (onOff) filterView.setVisibility(View.VISIBLE);
        else filterView.setVisibility(View.GONE);
    }

    public boolean isInCategories() {
        return inCategories;
    }

    public void setInCategories(boolean inCategories) {
        this.inCategories = inCategories;
    }

    public int getScreenSizeX(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int getScreenSizeY() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public boolean isInCategorieList() {
        return inCategorieList;
    }

    public void setInCategorieList(boolean inCategorieList) {
        this.inCategorieList = inCategorieList;
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}