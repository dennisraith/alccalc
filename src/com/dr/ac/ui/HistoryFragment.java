
package com.dr.ac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.dr.ac.BaseFragment;
import com.dr.ac.R;
import com.dr.ac.adapter.ResultAdapter;
import com.dr.ac.manager.ConfigManager;
import com.dr.ac.model.ResultModel;

import java.util.ArrayList;


public class HistoryFragment extends BaseFragment {

    private ExpandableListView     mList;
    private ArrayList<ResultModel> mEntries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_result_fragment, null);
        this.mList = (ExpandableListView) view.findViewById(R.id.expView);
        this.setTitle(R.string.fragment_history);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadEntries();

        ResultAdapter adapter = new ResultAdapter(this.getActivity(), this.mEntries);
        this.mList.setAdapter(adapter);
    }

    private void loadEntries() {
        this.mEntries = ConfigManager.getInstance().loadData();
    }

    public void setEntries(ArrayList<ResultModel> entries) {
        this.mEntries = entries;
    }

}
