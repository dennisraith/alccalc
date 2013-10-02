
package com.dr.ac;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dr.ac.constants.ACConsts;
import com.dr.ac.ui.InputFragment;
import com.dr.ac.ui.NavigationFragment;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout       mDrawerLayout;
    private NavigationFragment mNavigationFragment;
    private DrawerToggler      mToggle;
    private View               mMenu;
    private BaseFragment       mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.layout_main);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(false);

        this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        this.mMenu = this.findViewById(R.id.drawer_menu);
        this.mNavigationFragment = new NavigationFragment();
        this.getSupportFragmentManager().beginTransaction()
                .replace(this.mMenu.getId(), this.mNavigationFragment).commit();
        this.navigateTo(new InputFragment());
        this.mToggle = new DrawerToggler(this, this.mDrawerLayout,
                android.R.color.transparent, R.string.app_name,
                R.string.app_name);
        this.mDrawerLayout.setDrawerListener(this.mToggle);
        this.mDrawerLayout.closeDrawer(this.mMenu);
        boolean firstStartup = this.getSharedPreferences(ACConsts.PREFS_NAME, 0).getBoolean(ACConsts.PREF_STARTUP, true);
        Log.d(this.getClass().getName(), firstStartup + "");
        this.handleFirstStartup(firstStartup);
    }

    private void handleFirstStartup(boolean firstStartup) {
        if (firstStartup) {
            this.mDrawerLayout.openDrawer(this.mMenu);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    MainActivity.this.mDrawerLayout.closeDrawer(MainActivity.this.mMenu);
                }
            }, 1500);

        }

        this.getSharedPreferences(ACConsts.PREFS_NAME, 0).edit().putBoolean(ACConsts.PREF_STARTUP, false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public BaseFragment navigateTo(BaseFragment fragment) {
        return this.navigateTo(fragment, false);
    }

    public BaseFragment navigateTo(BaseFragment fragment, boolean addToBackStack) {

        this.hideKeyboard();
        if (this.mCurrentFragment == fragment) {
            return this.mCurrentFragment;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        this.mCurrentFragment = fragment;
        this.toggleMenu(true);
        return fragment;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title) {
        this.getSupportActionBar().setTitle(title);
    }

    @Override
    public void setTitle(int title) {
        this.getSupportActionBar().setTitle(title);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void toggleMenu(boolean forceClose) {
        if (this.mDrawerLayout.isDrawerVisible(this.mMenu)) {
            this.mDrawerLayout.closeDrawer(this.mMenu);
        } else if (!forceClose) {
            this.mDrawerLayout.openDrawer(this.mMenu);
        }
    }

    @Override
    public void onBackPressed() {
        if (this.getSupportFragmentManager().getBackStackEntryCount() > 1) {
            this.getSupportFragmentManager().popBackStack();
        } else {
            this.toggleMenu(false);
        }
    }

    class DrawerToggler extends ActionBarDrawerToggle {

        public DrawerToggler(Activity activity, DrawerLayout drawerLayout,
                int drawerImageRes, int openDrawerContentDescRes,
                int closeDrawerContentDescRes) {
            super(activity, drawerLayout, drawerImageRes,
                    openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            MainActivity.this.setTitle("");

        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            MainActivity.this.setTitle(MainActivity.this.mCurrentFragment.getTitle());

        }
    }

}
