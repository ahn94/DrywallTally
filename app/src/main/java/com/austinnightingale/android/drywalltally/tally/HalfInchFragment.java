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
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.job.dialogs.TallyInputDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class HalfInchFragment extends Fragment implements HalfInch.View, NumberPickerListener{


    public static HalfInchFragment newInstance() {
        return new HalfInchFragment();
    }


    @Inject
    HalfInch.Presenter halfPresenter;

    @BindView(R.id.regHalfEightTally) TextView regHalfEightText;
    @BindView(R.id.regHalfNineTally) TextView regHalfNineText;
    @BindView(R.id.regHalfTenTally) TextView regHalfTenText;
    @BindView(R.id.regHalfTwelveTally) TextView regHalfTwelveText;
    @BindView(R.id.regHalfFourteenTally) TextView regHalfFourteenText;
    @BindView(R.id.regHalfSixteenTally) TextView regHalfSixteenText;
    @BindView(R.id.stretchHalfTwelveTally) TextView stretchHalfTwelveText;
    @BindView(R.id.stretchHalfFourteenTally) TextView stretchHalfFourteenText;
    @BindView(R.id.stretchHalfSixteenTally) TextView stretchHalfSixteenText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
        halfPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regular_half_inch, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        halfPresenter.setView(this);
        halfPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        halfPresenter.onPause();
        halfPresenter.setView(null);
    }

    @Override
    public int getID() {
        int id;
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(TallyArea.ID)) {
            id = intent.getIntExtra(TallyArea.ID, -1);
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
        halfPresenter.updateJob(column, value);
    }

    /**
     *  8' Regular Half Inch
     */
    @OnClick(R.id.half8Plus)
    public void regHalfEightIncrement() {
        halfPresenter.updateJob(TallyArea.HALF_8, 1);
    }

    @OnClick(R.id.half8Minus)
    public void regHalfEightDecrement() {
        halfPresenter.updateJob(TallyArea.HALF_8, -1);
    }

    @Override
    public void updateRegHalfEight(int tally) {
        regHalfEightText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.half8View)
    public boolean half8LP() {
        showDialog(TallyArea.HALF_8, "8'");
        return true;
    }

    /**
     *  9' Regular Half Inch
     */
    @OnClick(R.id.half9Plus)
    public void regHalfNineAdd() {
        halfPresenter.updateJob(TallyArea.HALF_9, 1);
    }

    @OnClick(R.id.half9Minus)
    public void regHalfNineMinus() {
        halfPresenter.updateJob(TallyArea.HALF_9, -1);
    }

    @Override
    public void updateRegHalfNine(int tally) {
        regHalfNineText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.half9View)
    public boolean half9LP() {
        showDialog(TallyArea.HALF_9, "9'");
        return true;
    }

    /**
     *  10' Regular Half Inch
     */
    @OnClick(R.id.half10Plus)
    public void regHalfTenAdd() {
        halfPresenter.updateJob(TallyArea.HALF_10, 1);
    }

    @OnClick(R.id.half10Minus)
    public void regHalfTenMinus() {
        halfPresenter.updateJob(TallyArea.HALF_10, -1);
    }

    @Override
    public void updateRegHalfTen(int tally) {
        regHalfTenText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.half10View)
    public boolean half10LP() {
        showDialog(TallyArea.HALF_10, "10'");
        return true;
    }

    /**
     *  12' Regular Half Inch
     */
    @OnClick(R.id.half12Plus)
    public void regHalfTwelveAdd() {
        halfPresenter.updateJob(TallyArea.HALF_12, 1);
    }

    @OnClick(R.id.half12Minus)
    public void regHalfTwelveMinus() {
        halfPresenter.updateJob(TallyArea.HALF_12, -1);
    }

    @Override
    public void updateRegHalfTwelve(int tally) {
        regHalfTwelveText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.half12View)
    public boolean half12LP() {
        showDialog(TallyArea.HALF_12, "12'");
        return true;
    }

    /**
     *  14' Regular Half Inch
     */
    @OnClick(R.id.half14Plus)
    public void regHalfFourteenPlus() {
        halfPresenter.updateJob(TallyArea.HALF_14, 1);
    }

    @OnClick(R.id.half14Minus)
    public void regHalfFourteenMinus() {
        halfPresenter.updateJob(TallyArea.HALF_14, -1);
    }

    @Override
    public void updateRegHalfFourteen(int tally) {
        regHalfFourteenText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.half14View)
    public boolean half14LP() {
        showDialog(TallyArea.HALF_14, "14'");
        return true;
    }

    /**
     *
     *  16' Regular Half Inch
     */
    @OnClick(R.id.half16Plus)
    public void reghalfSixteenPlus() {
        halfPresenter.updateJob(TallyArea.HALF_16, 1);
    }

    @OnClick(R.id.half16Minus)
    public void reghalfSixteenMinus() {
        halfPresenter.updateJob(TallyArea.HALF_16, -1);
    }

    @Override
    public void updateRegHalfSixteen(int tally) {
        regHalfSixteenText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.half16View)
    public boolean half16LP() {
        showDialog(TallyArea.HALF_16, "16'");
        return true;
    }

    /**
     *
     *  12' Stretch Half Inch
     */
    @OnClick(R.id.halfStr12Plus)
    public void stretchHalfTwelvePlus(){
        halfPresenter.updateJob(TallyArea.HALF_s12, 1);
    }

    @OnClick(R.id.stretchHalfTwelveMinus)
    public void stretchHalfTwelveMinus(){
        halfPresenter.updateJob(TallyArea.HALF_s12, -1);
    }

    @Override
    public void updateStretchHalfTwelve(int tally) {
        stretchHalfTwelveText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.halfS12View)
    public boolean halfS12LP() {
        showDialog(TallyArea.HALF_s12, "s12'");
        return true;
    }

    /**
     *
     *  14' Stretch Half Inch
     */
    @OnClick(R.id.halfStr14Plus)
    public void stretchHalfFourteenPlus(){
        halfPresenter.updateJob(TallyArea.HALF_s14, 1);
    }

    @OnClick(R.id.halfStr14Minus)
    public void stretchHalfFourteenMinus(){
        halfPresenter.updateJob(TallyArea.HALF_s14, -1);
    }

    @Override
    public void updateStretchHalfFourteen(int tally) {
        stretchHalfFourteenText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.halfS14View)
    public boolean halfS14LP() {
        showDialog(TallyArea.HALF_s14, "s14'");
        return true;
    }

    /**
     *
     *  16' Stretch Half Inch
     */
    @OnClick(R.id.halfStr16Plus)
    public void stretchHalfSixteenPlus(){
        halfPresenter.updateJob(TallyArea.HALF_s16, 1);
    }

    @OnClick(R.id.halfStr16Minus)
    public void stretchHalfSixteenMinus(){
        halfPresenter.updateJob(TallyArea.HALF_s16, -1);
    }

    @Override
    public void updateStretchHalfSixteen(int tally){
        stretchHalfSixteenText.setText(String.valueOf(tally));
    }

    @OnLongClick(R.id.halfS16View)
    public boolean halfS16LP() {
        showDialog(TallyArea.HALF_s16, "s16'");
        return true;
    }

}
