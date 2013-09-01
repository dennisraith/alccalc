package com.dr.ac;
import android.view.animation.Animation.AnimationListener;
import com.dr.ac.ui.InputFragment;
import com.dr.ac.ui.NavigationFragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private NavigationFragment mNavigationFragment;
	private DrawerToggler mToggle;
	private View mMenu;
	private BaseFragment mCurrentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_main);
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setHomeButtonEnabled(false);

		this.mDrawerLayout = (DrawerLayout) this
				.findViewById(R.id.drawer_layout);
		this.mMenu = this.findViewById(R.id.drawer_menu);
		this.mNavigationFragment = new NavigationFragment();
		this.getSupportFragmentManager().beginTransaction()
				.replace(mMenu.getId(), mNavigationFragment).commit();
		this.navigateTo(new InputFragment());
		mToggle = new DrawerToggler(this, this.mDrawerLayout,
				android.R.color.transparent, R.string.app_name,
				R.string.app_name);
		this.mDrawerLayout.setDrawerListener(mToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public BaseFragment navigateTo(BaseFragment fragment) {
		return this.navigateTo(fragment, false);
	}

	public BaseFragment navigateTo(BaseFragment fragment, boolean addToBackStack) {

		this.hideKeyboard();
		if (mCurrentFragment == fragment) {
			this.toggleMenu();
			return mCurrentFragment;
		}
		FragmentTransaction transaction = this.getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.content_frame, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
		this.mCurrentFragment = fragment;
		return fragment;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void setTitle(String title) {
		this.getSupportActionBar().setTitle(title);
	}

	public void setTitle(int title) {
		this.getSupportActionBar().setTitle(title);
	}

	public void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = this.getCurrentFocus();
		if (view != null) {
			imm.hideSoftInputFromWindow(
					this.getCurrentFocus().getWindowToken(), 0);
		}
	}

	public void toggleMenu() {
		if (this.mDrawerLayout.isDrawerVisible(this.mMenu)) {
			this.mDrawerLayout.closeDrawer(this.mMenu);
		} else {
			this.mDrawerLayout.openDrawer(this.mMenu);
		}
	}

	@Override
	public void onBackPressed() {
		// if (this.getSupportFragmentManager().getBackStackEntryCount() > 1) {
		if (false) {
			this.getSupportFragmentManager().popBackStack();
		} else {
			this.toggleMenu();
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
			mNavigationFragment.getListView().setVisibility(View.GONE);
			mNavigationFragment.animate();
			
		}
		@Override
		public void onDrawerClosed(View drawerView) {
			super.onDrawerClosed(drawerView);
			mNavigationFragment.getListView().setVisibility(View.GONE);
		}
	}
	
//	class MenuAnimation extends TranslateAnimation implements AnimationListener{
//
//		public MenuAnimation(Context context, AttributeSet attrs) {
//			super(-2000,1,1,1);
//			this.setDuration(500);
//			this.setInterpolator(new AccelerateDecelerateInterpolator());
//			this.setFillAfter(true);
//			this.setAnimationListener(this);
//		}
//
//		@Override
//		public void onAnimationEnd(Animation animation) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onAnimationRepeat(Animation animation) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onAnimationStart(Animation animation) {
//			mNavigationFragment.getListView().setVisibility(View.VISIBLE);
//		}
//	}
	
}
