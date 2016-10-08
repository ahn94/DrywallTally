package com.austinnightingale.drywalltally.job.summary.tallysummary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.db.TallyArea;
import com.austinnightingale.drywalltally.job.Report;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by Austin on 9/26/2016.
 */

public class TallyTotalAdapter extends RecyclerView.Adapter<TallyViewHolder> implements Action1<TallyArea> {

    private static final int HALF = 1;
    private static final int HALF_S = 2;
    private static final int FIVE = 3;
    private static final int FIVE_S = 4;
    private static final int CEIL = 5;
    private static final int FIRE = 6;
    private static final int MOLD = 7;
    private static final int HALF_TOTAL = 8;

    List<Integer> types;
    List<Integer[]> data;
    private boolean showEmtpyCards;

    public TallyTotalAdapter(boolean showEmtpyCards) {
        this.showEmtpyCards = showEmtpyCards;
        types = new ArrayList<>();
        data = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public TallyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case HALF:
                View vHalf = inflater.inflate(R.layout.card_half_total, parent, false);
                return new HalfViewHolder(vHalf);
            case HALF_S:
                View vHalfS = inflater.inflate(R.layout.card_half_s_totals, parent, false);
                return new HalfStretchViewHolder(vHalfS);
            case FIVE:
                View vFive = inflater.inflate(R.layout.card_five_totals, parent, false);
                return new FiveViewHolder(vFive);
            case FIVE_S:
                View vFiveS = inflater.inflate(R.layout.card_five_s_total, parent, false);
                return new FiveSViewHolder(vFiveS);
            case CEIL:
                View vCeil = inflater.inflate(R.layout.card_ceiling_totals, parent, false);
                return new CeilViewHolder(vCeil);
            case FIRE:
                View vFire = inflater.inflate(R.layout.card_fire_totals, parent, false);
                return new FireViewHolder(vFire);
            case MOLD:
                View vMold = inflater.inflate(R.layout.card_mold, parent, false);
                return new MoldViewHolder(vMold);
            case HALF_TOTAL:
                View vLiteTotals = inflater.inflate(R.layout.card_ceiling_plus_half_total, parent, false);
                return new LiteTotalViewHolder(vLiteTotals);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(TallyViewHolder holder, int position) {
        int type = types.get(position);
        switch (type) {
            case HALF:
                ((HalfViewHolder) holder).bind(data.get(position));
                break;
            case HALF_S:
                ((HalfStretchViewHolder) holder).bind(data.get(position));
                break;
            case FIVE:
                ((FiveViewHolder) holder).bind(data.get(position));
                break;
            case FIVE_S:
                ((FiveSViewHolder) holder).bind(data.get(position));
                break;
            case CEIL:
                ((CeilViewHolder) holder).bind(data.get(position));
                break;
            case FIRE:
                ((FireViewHolder) holder).bind(data.get(position));
                break;
            case MOLD:
                ((MoldViewHolder) holder).bind(data.get(position));
                break;
            case HALF_TOTAL:
                ((LiteTotalViewHolder) holder).bind(data.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void call(TallyArea tallyArea) {
        data.clear();
        types.clear();

        Integer[] half = Report.getHalfTallies(tallyArea);
        Integer[] halfStretch = Report.getHalfStretchTallies(tallyArea);
        Integer[] five = Report.getFiveETallies(tallyArea);
        Integer[] fiveS = Report.getFiveEStretchTallies(tallyArea);
        Integer[] ceiling = Report.getCeilTallies(tallyArea);
        Integer[] fire = Report.getFireTallies(tallyArea);
        Integer[] mold = Report.getMoldTallies(tallyArea);

        if (showEmtpyCards || Report.getTallySum(half) > 0) {
            types.add(HALF);
            data.add(half);
        }

        if (showEmtpyCards || Report.getTallySum(halfStretch) > 0) {
            types.add(HALF_S);
            data.add(halfStretch);
        }

        if (showEmtpyCards || Report.getTallySum(five) > 0) {
            types.add(FIVE);
            data.add(five);
        }

        if (showEmtpyCards || Report.getTallySum(fiveS) > 0) {
            types.add(FIVE_S);
            data.add(fiveS);
        }

        if (showEmtpyCards || Report.getTallySum(ceiling) > 0) {
            types.add(CEIL);
            data.add(ceiling);
        }

        if (showEmtpyCards || Report.getTallySum(fire) > 0) {
            types.add(FIRE);
            data.add(fire);
        }

        if (showEmtpyCards || Report.getTallySum(mold) > 0) {
            types.add(MOLD);
            data.add(mold);
        }

        Integer[] halfTotal = new Integer[half.length];
        for (int n = 0; n < half.length; n++) {
            halfTotal[n] = half[n] + ceiling[n];
        }

        if (Report.getTallySum(ceiling) > 0 && + Report.getTallySum(half) > 0) {
            types.add(HALF_TOTAL);
            data.add(halfTotal);
        }

        notifyDataSetChanged();
    }
}
