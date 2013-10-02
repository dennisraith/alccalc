
package com.dr.ac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dr.ac.BaseFragment;
import com.dr.ac.R;
import com.dr.ac.adapter.SettingsAdapter;
import com.dr.ac.model.Setting;
import com.dr.ac.model.Setting.SettingsAction;

import java.util.ArrayList;


public class SettingsFragment extends BaseFragment implements OnItemClickListener {

    private static ArrayList<Setting> ListData;

    private ListView                  mListView;
    private SettingsAdapter           mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (SettingsFragment.ListData == null) {
            SettingsFragment.ListData = new ArrayList<Setting>();
            SettingsFragment.ListData.add(new Setting(R.string.setting_clear, SettingsAction.CLEAR_DATA));
        }

        View view = inflater.inflate(R.layout.layout_settings, null);

        this.mListView = (ListView) view.findViewById(R.id.listview);
        this.mAdapter = new SettingsAdapter(this.getActivity(), SettingsFragment.ListData);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        SettingsFragment.ListData.get(arg2).doAction();
    }

}
