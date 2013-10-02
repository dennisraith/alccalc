
package com.dr.ac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dr.ac.BaseFragment;
import com.dr.ac.MainActivity;
import com.dr.ac.R;
import com.dr.ac.adapter.MenuAdapter;


public class NavigationFragment extends BaseFragment implements OnItemClickListener {

    private ListView     mListView;
    private View         mBanner;
    private MainActivity mActivity;

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
        this.getMainActivity().navigateTo(MenuAdapter.getInstance().getItem(position).getTargetFragment());

    }

    public ListView getListView() {
        return this.mListView;
    }

    public View getBanner() {
        return this.mBanner;
    }

}
