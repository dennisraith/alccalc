package com.dr.ac.ui;


import com.dr.ac.BaseFragment;
import com.dr.ac.R;
import com.dr.ac.model.MenuAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NavigationFragment extends BaseFragment implements OnItemClickListener {

	private ListView mListView;
	private View mBanner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.setTitle(R.string.fragment_navigation);
		
		View view = inflater.inflate(R.layout.layout_navigation, null);
		this.mListView = (ListView) view.findViewById(R.id.listview);
		this.mListView.setAdapter(MenuAdapter.getInstance());
		this.mListView.setOnItemClickListener(this);
		this.mBanner = view.findViewById(R.id.banner);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}

	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		MenuAdapter.getInstance().getItem(position).getTargetFragment();
		
		
	}
	
	public ListView getListView(){
		return this.mListView;
	}
	public View getBanner(){
		return this.mBanner;
	}
	
	public void animate(){
		this.mListView.startAnimation(new MenuAnimation(getActivity(), null));
	}
	
	
	class MenuAnimation extends TranslateAnimation implements AnimationListener{

		public MenuAnimation(Context context, AttributeSet attrs) {
			super(0,0,-800,0);
			this.setDuration(250);
			this.setInterpolator(new AccelerateDecelerateInterpolator());
			this.setFillAfter(true);
			this.setAnimationListener(this);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation animation) {
			getListView().setVisibility(View.VISIBLE);
		}
	}
}
