package com.austinnightingale.android.drywalltally.tally;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.dialogs.TallyInputDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by Austin on 9/24/2016.
 */

public class FireFragment extends Fragment implements Fire.View, NumberPickerListener{

    public static FireFragment newInstance() {
        return new FireFragment();
    }

    @Inject
    Fire.Presenter firePrenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
        firePrenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        firePrenter.setView(this);
        firePrenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        firePrenter.onPause();
        firePrenter.setView(null);
    }

    @Override
    public int getID() {
        int id;
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(Job.ID)) {
            id = intent.getIntExtra(Job.ID, -1);
        } else {
            throw new IllegalArgumentException("no job Id in Intent");
        }
        return id;
    }

    @Override
    public void setPickerResults(String column, int value) {
        firePrenter.updateJob(column, value);
    }

    @Override
    public void setNegativeError() {
        Toast.makeText(this.getContext(), "Value can't be a negative number", Toast.LENGTH_SHORT).show();
    }


    private void showDialog(String column, String title) {
        TallyInputDialog inputDialog = TallyInputDialog.newInstance(
                column,
                "Set " + title
                , InputType.TYPE_CLASS_NUMBER,
                true,
                this
        );
        inputDialog.show(getActivity().getSupportFragmentManager(), "number picker");
    }

    @BindView(R.id.mold8Tally)
    TextView mold8Text;
    @BindView(R.id.mold12Tally)
    TextView mold12Text;
    @BindView(R.id.fire8Tally)
    TextView fire8Text;
    @BindView(R.id.fire9Tally)
    TextView fire9Text;
    @BindView(R.id.fire10Tally)
    TextView fire10Text;
    @BindView(R.id.fire12Tally)
    TextView fire12Text;
    @BindView(R.id.fire14Tally)
    TextView fire14Text;
    @BindView(R.id.fire16Tally)
    TextView fire16Text;

    /**
     *
     *  8' Fire
     */
    @OnClick(R.id.fire8Plus)
    public void fire8Plus() {
        firePrenter.updateJob(Job.FIRE_8, 1);
    }
    @OnClick(R.id.fire8Minus)
    public void fire8Minus() {
        firePrenter.updateJob(Job.FIRE_8, -1);
    }
    @Override
    public void updateFire8Text(String tally) {
        fire8Text.setText(tally);
    }
    @OnLongClick(R.id.fire8View)
    public boolean fire8LP() {
        showDialog(Job.FIRE_8, "8'");
        return true;
    }

    /**
     *
     *  9' Fire
     */
    @OnClick(R.id.fire9Plus)
    public void fire9Plus() {
        firePrenter.updateJob(Job.FIRE_9, 1);
    }
    @OnClick(R.id.fire9Minus)
    public void fire9Minus() {
        firePrenter.updateJob(Job.FIRE_9, -1);
    }
    @Override
    public void updateFire9Text(String tally) {
        fire9Text.setText(tally);
    }
    @OnLongClick(R.id.fire9View)
    public boolean fire9LP() {
        showDialog(Job.FIRE_9, "9'");
        return true;
    }

    /**
     *
     *  10' Fire
     */
    @OnClick(R.id.fire10Plus)
    public void fire10Plus() {
        firePrenter.updateJob(Job.FIRE_10, 1);
    }
    @OnClick(R.id.fire10Minus)
    public void fire10Minus() {
        firePrenter.updateJob(Job.FIRE_10, -1);
    }
    @Override
    public void updateFire10Text(String tally) {
        fire10Text.setText(tally);
    }
    @OnLongClick(R.id.fire10View)
    public boolean fire10LP() {
        showDialog(Job.FIRE_10, "10'");
        return true;
    }

    /**
     *
     *  12' Fire
     */
    @OnClick(R.id.fire12Plus)
    public void fire12Plus() {
        firePrenter.updateJob(Job.FIRE_12, 1);
    }
    @OnClick(R.id.fire12Minus)
    public void fire12Minus() {
        firePrenter.updateJob(Job.FIRE_12, -1);
    }
    @Override
    public void updateFire12Text(String tally) {
        fire12Text.setText(tally);
    }
    @OnLongClick(R.id.fire12View)
    public boolean fire12LP() {
        showDialog(Job.FIRE_12, "12'");
        return true;
    }

    /**
     *
     *  14' Fire
     */
    @OnClick(R.id.fire14Plus)
    public void fire14Plus() {
        firePrenter.updateJob(Job.FIRE_14, 1);
    }
    @OnClick(R.id.fire14Minus)
    public void fire14Minus() {
        firePrenter.updateJob(Job.FIRE_14, -1);
    }
    @Override
    public void updateFire14Text(String tally) {
        fire14Text.setText(tally);
    }
    @OnLongClick(R.id.fire14View)
    public boolean fire14LP() {
        showDialog(Job.FIRE_14, "14'");
        return true;
    }

    /**
     *
     *  16' Fire
     */
    @OnClick(R.id.fire16Plus)
    public void fire16Plus() {
        firePrenter.updateJob(Job.FIRE_16, 1);
    }
    @OnClick(R.id.fire16Minus)
    public void fire16Minus() {
        firePrenter.updateJob(Job.FIRE_16, -1);
    }
    @Override
    public void updateFire16Text(String tally) {
        fire16Text.setText(tally);
    }
    @OnLongClick(R.id.fire16View)
    public boolean fire16LP() {
        showDialog(Job.FIRE_16, "16'");
        return true;
    }
    

    /**
     *
     *  8' Mold
     */
    @OnClick(R.id.mold8Plus)
    public void mold8Plus() {
        firePrenter.updateJob(Job.MOLD_8, 1);
    }

    @OnClick(R.id.mold8Minus)
    public void mold8Minus() {
        firePrenter.updateJob(Job.MOLD_8, -1);
    }

    @Override
    public void updateMold8Text(String tally) {
        mold8Text.setText(tally);
    }

    @OnLongClick(R.id.mold8View)
    public boolean mold8LP() {
        showDialog(Job.MOLD_8, "m8'");
        return true;
    }

    /**
     *
     *  12' Mold
     */
    @OnClick(R.id.mold12Plus)
    public void mold12Plus() {
        firePrenter.updateJob(Job.MOLD_12, 1);
    }

    @OnClick(R.id.mold12Minus)
    public void mold12Minus() {
        firePrenter.updateJob(Job.MOLD_12, -1);
    }
    @Override
    public void updateMold12Text(String tally) {
        mold12Text.setText(tally);
    }

    @OnLongClick(R.id.mold12View)
    public boolean mold12LP() {
        showDialog(Job.MOLD_12, "m12'");
        return true;
    }
}
