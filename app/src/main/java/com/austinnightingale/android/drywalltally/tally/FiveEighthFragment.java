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

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.dialogs.TallyInputDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class FiveEighthFragment extends Fragment implements FiveEigths.View, NumberPickerListener{

    public static FiveEighthFragment newInstance() {
        return new FiveEighthFragment();
    }

    @Inject FiveEigths.Presenter fiveEigthsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
        fiveEigthsPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five_eighths, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fiveEigthsPresenter.setView(this);
        fiveEigthsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        fiveEigthsPresenter.onPause();
        fiveEigthsPresenter.setView(null);
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
    public void setNegativeError() {

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

    @Override
    public void setPickerResults(String column, int value) {
        fiveEigthsPresenter.updateJob(column, value);
    }

    /**
     *  Bind views
     */
    @BindView(R.id.fiveEighthEightTally) TextView fiveEighthsEightText;
    @BindView(R.id.fiveEighthNineTally) TextView fiveEighthsNineText;
    @BindView(R.id.fiveEighthTenTally) TextView fiveEighthsTenText;
    @BindView(R.id.fiveEighthTwelveTally) TextView fiveEighthsTwelveText;
    @BindView(R.id.fiveEighthFourteenTally) TextView fiveEighthsFourteenText;
    @BindView(R.id.stretchFiveEighthTwelveTally) TextView stretchFiveEighthsTwelveText;

    /**
     *
     *  8' Regular Five Eighth Inch
     */
    @OnClick(R.id.fiveEighthEightPlus)
    public void fiveEighthsEightPlus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_8, 1);
    }

    @OnClick(R.id.fiveEighthEightMinus)
    public void fiveEighthsEightMinus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_8, -1);
    }

    @Override
    public void updateFiveEighthEightText(String tally) {
        fiveEighthsEightText.setText(tally);
    }

    @OnLongClick(R.id.fiveEighths8View)
    public boolean fiveEighth8LP() {
        showDialog(Job.FiveE_8, "8'");
        return true;
    }

    /**
     *
     *  9' Regular Five Eighth-Inch
     */
    @OnClick(R.id.fiveEighthNinePlus)
    public void fiveEighthsNinePlus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_9, 1);
    }

    @OnClick(R.id.fiveEighthNineMinus)
    public void fiveEighthsNineMinus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_9, -1);
    }

    @Override
    public void updateFiveEighthNineText(String tally) {
        fiveEighthsNineText.setText(tally);
    }

    @OnLongClick(R.id.fiveEighths9View)
    public boolean fiveEighth9LP() {
        showDialog(Job.FiveE_9, "9'");
        return true;
    }

    /**
     *
     *  10' Regular Five-Eighth Inch
     */
    @OnClick(R.id.fiveEighthTenPlus)
    public void fiveEighthsTenPlus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_10, 1);
    }

    @OnClick(R.id.fiveEighthTenMinus)
    public void fiveEighthsTenMinus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_10, -1);
    }
    @Override
    public void updateFiveEighthTenText(String tally) {
        fiveEighthsTenText.setText(tally);
    }

    @OnLongClick(R.id.fiveEighths10View)
    public boolean fiveEighth10LP() {
        showDialog(Job.FiveE_10, "10'");
        return true;
    }

    /**
     *
     *  12' Regular Five-Eighth Inch
     */
    @OnClick(R.id.fiveEighthTwelvePlus)
    public void fiveEighthsTwelvePlus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_12, 1);
    }

    @OnClick(R.id.fiveEighthTwelveMinus)
    public void fiveEighthsTwelveMinus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_12, -1);
    }
    @Override
    public void updateFiveEighthTwelveText(String tally) {
        fiveEighthsTwelveText.setText(tally);
    }

    @OnLongClick(R.id.fiveEighths12View)
    public boolean fiveEighth12LP() {
        showDialog(Job.FiveE_12, "12'");
        return true;
    }

    /**
     *
     *  14' Regular Five-Eighth Inch
     */
    @OnClick(R.id.fiveEighthFourteenPlus)
    public void fiveEighthsFourteenPlus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_14, 1);
    }

    @OnClick(R.id.fiveEighthFourteenMinus)
    public void fiveEighthsFourteenMinus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_14, -1);
    }
    @Override
    public void updateFiveEighthFourteenText(String tally) {
        fiveEighthsFourteenText.setText(tally);
    }

    @OnLongClick(R.id.fiveEighths14View)
    public boolean fiveEighth14LP() {
        showDialog(Job.FiveE_14, "14'");
        return true;
    }

    /**
     *
     *  12' Stretch Five-Eighth Inch
     */
    @OnClick(R.id.stretchFiveEighthTwelvePlus)
    public void stretchFiveEighthTwelvePlus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_s12, 1);
    }

    @OnClick(R.id.stretchFiveEighthTwelveMinus)
    public void stretchFiveEighthTwelveMinus() {
        fiveEigthsPresenter.updateJob(Job.FiveE_s12, -1);
    }
    @Override
    public void updateStretchFiveEighthTwelveText(String tally) {
        stretchFiveEighthsTwelveText.setText(tally);
    }

    @OnLongClick(R.id.fiveEighthsS12View)
    public boolean fiveEighthS12LP() {
        showDialog(Job.FiveE_s12, "s12'");
        return true;
    }

}
