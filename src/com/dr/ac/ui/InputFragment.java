
package com.dr.ac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dr.ac.BaseFragment;
import com.dr.ac.MainActivity;
import com.dr.ac.R;
import com.dr.ac.constants.ACConsts;
import com.dr.ac.manager.ConfigManager;
import com.dr.ac.model.ResultModel;
import com.dr.ac.widget.NumberDataRow;
import com.dr.ac.widget.TextDataRow;

import org.json.JSONException;

import java.io.IOException;


public class InputFragment extends BaseFragment implements OnClickListener {

    private TextDataRow   mDescr;
    private NumberDataRow mStWrz;
    private NumberDataRow mRstExtr;
    private Button        mCalc;
    private ToggleButton  mSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.setTitle(R.string.fragment_input);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_input_fragment, null);
        this.mDescr = (TextDataRow) view.findViewById(R.id.description);
        this.mStWrz = (NumberDataRow) view.findViewById(R.id.stWuerze);
        this.mRstExtr = (NumberDataRow) view.findViewById(R.id.rstExtr);
        this.mCalc = (Button) view.findViewById(R.id.calc);
        this.mSave = (ToggleButton) view.findViewById(R.id.toggleSave);

        this.mDescr.init(this.getString(R.string.description));
        this.mStWrz.init(this.getString(R.string.stWuerze));
        this.mRstExtr.init(this.getString(R.string.rstExtrakt));
        this.mCalc.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            String descr = savedInstanceState.getString(ACConsts.SIS_DESCR);
            String stwrz = savedInstanceState.getString(ACConsts.SIS_STWRZ);
            String rstext = savedInstanceState.getString(ACConsts.SIS_RSTEXT);
            this.mDescr.setInput(descr);
            this.mStWrz.setInput(stwrz);
            this.mRstExtr.setInput(rstext);
        } else {
            this.mDescr.setInput(null);
            this.mStWrz.setInput(null);
            this.mRstExtr.setInput(null);
        }
    }

    @Override
    public void onClick(View v) {
        ConfigManager.getInstance().setDoSave(this.mSave.isChecked());

        ResultModel result = ACConsts.calc(this.mStWrz.getInput(),
                this.mRstExtr.getInput());

        if (result == null) {
            Toast.makeText(this.getActivity(), R.string.parserError,
                    Toast.LENGTH_SHORT).show();
        } else {
            result.setDescription(this.mDescr.getInput());
            if (ConfigManager.getInstance().doSave()) {
                this.saveResult(result);
            }
            ResultFragment fragment = (ResultFragment) ((MainActivity) this
                    .getActivity()).navigateTo(new ResultFragment());
            fragment.setModel(result);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ACConsts.SIS_DESCR, this.mDescr.getInput());
        outState.putString(ACConsts.SIS_STWRZ, this.mStWrz.getInput());
        outState.putString(ACConsts.SIS_RSTEXT, this.mRstExtr.getInput());
        super.onSaveInstanceState(outState);
    }

    private void saveResult(ResultModel result) {
        try {
            ConfigManager.getInstance().saveData(result);
        } catch (JSONException e) {
            Toast.makeText(this.getActivity(), "Could not parse file",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this.getActivity(), "Could not write file",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}
