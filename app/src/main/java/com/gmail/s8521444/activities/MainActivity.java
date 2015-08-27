package com.gmail.s8521444.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.gmail.s8521444.R;
import com.gmail.s8521444.fragments.FolderFragment;
import com.gmail.s8521444.fragments.SimpleFragment;
import com.gmail.s8521444.fragments.TabbedFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

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
                fragment = SimpleFragment.newInstance(1);
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
}
