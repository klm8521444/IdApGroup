package com.gmail.s8521444.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gmail.s8521444.R;
import com.gmail.s8521444.fragments.FolderFragment;
import com.gmail.s8521444.fragments.SearchFragment;
import com.gmail.s8521444.fragments.TabbedFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemLongClickListener {



    public static enum Mode {
        NORMAL, REMOVE, SEARCH, SORT;
    }

    public static Mode mode;

    private DrawerLayout drawerLayout;

    private Toolbar mActionBarToolbar;
    //private SlidingMenu slidingMenu;
    private SearchView mSearchView;
    private TextView tvNoInternet;
    private TextView tvToolbarTitle;
    private ImageButton btnToolbarButton;

    private MenuItem editMenuItem;
    private MenuItem microMenuItem;
    private MenuItem removeMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem sortAbMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar toolbar = getSupportActionBar();
        if (toolbar != null)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                mToolBar,
                R.string.open_drawer,
                R.string.close_drawer) {
        };

        drawerLayout.setDrawerListener(drawerToggle);
        selectItem(0);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_cafe:
                selectItem(0);
                break;
            case R.id.navigation_play:
                selectItem(1);
                break;
            case R.id.navigation_maps:
                selectItem(2);
                break;
        }

        menuItem.setChecked(true);
        drawerLayout.closeDrawers();

        return true;
    }

    private void selectItem(final int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = SearchFragment.newInstance(1);
                break;
            case 1:
                fragment = TabbedFragment.newInstance();
                break;
            case 2:
                fragment = FolderFragment.newInstance(5);
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    /*
    private Toolbar mActionBarToolbar;
    //private SlidingMenu slidingMenu;
    private SearchView mSearchView;
    private TextView tvNoInternet;
    private TextView tvToolbarTitle;
    private ImageButton btnToolbarButton;

    private MenuItem editMenuItem;
    private MenuItem microMenuItem;
    private MenuItem removeMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem sortAbMenuItem;
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        microMenuItem = menu.findItem(R.id.action_micro);
        removeMenuItem = menu.findItem(R.id.action_remove);
        searchMenuItem = menu.findItem(R.id.action_search);
        editMenuItem = menu.findItem(R.id.action_edit);
        sortAbMenuItem = menu.findItem(R.id.action_sort);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, this);
        startMode(Mode.NORMAL);
        return true;
    }

    private void startMode(Mode modeToStart) {
        if (mode == Mode.SORT) {
            //Save order before exit sort mode
        } else if (mode == Mode.REMOVE) {
            //Clear listview cache after exiting form remove mode.

        }
        if (modeToStart == Mode.NORMAL) {
            removeMenuItem.setVisible(false);
            microMenuItem.setVisible(false);
            sortAbMenuItem.setVisible(false);
            searchMenuItem.setVisible(true);
            editMenuItem.setVisible(true);
            mActionBarToolbar.setLogo(null);
            mActionBarToolbar.setTitle(getString(R.string.app_name));
            mActionBarToolbar.setBackgroundResource(R.color.toolbar_orange);

            if (mode != modeToStart) {

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.status_bar_orange));
            }

            mode = modeToStart;
            return;
        } else if (modeToStart == Mode.SORT) {
            removeMenuItem.setVisible(false);
            searchMenuItem.setVisible(false);
            editMenuItem.setVisible(false);
            microMenuItem.setVisible(false);
            sortAbMenuItem.setVisible(true);
            mActionBarToolbar.setTitle(getString(R.string.drag_drop));
            mActionBarToolbar.setBackgroundResource(R.color.price_green);
            mActionBarToolbar.setLogo(null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.status_bar_green));
            }
        } else if (modeToStart == Mode.REMOVE) {

        } else if (modeToStart == Mode.SEARCH) {
            removeMenuItem.setVisible(false);
            searchMenuItem.setVisible(false);
            editMenuItem.setVisible(false);
            microMenuItem.setVisible(true);
        }
        mode = modeToStart;

    }

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleToolBar() {
        return getString(R.string.app_name);
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return true;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onRefresh() {

    }
}
