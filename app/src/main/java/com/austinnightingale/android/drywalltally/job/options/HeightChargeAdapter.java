package com.austinnightingale.android.drywalltally.job.options;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.job.dialogs.RemoveChargeListener;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class HeightChargeAdapter extends RecyclerView.Adapter<HeightChargeViewHolder> implements Action1<List<HeightCharge>> {

    List<HeightCharge> charges;
    RemoveChargeListener callback;
    boolean mAllowRemoval;

    public HeightChargeAdapter(Fragment fragment, boolean allowRemoval) {
        charges = new ArrayList<>();
        if (allowRemoval) {
            callback = (RemoveChargeListener) fragment;
        }
        mAllowRemoval = allowRemoval;
    }

    @Override
    public HeightChargeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new HeightChargeViewHolder(view, callback, mAllowRemoval);
    }

    @Override
    public void onBindViewHolder(HeightChargeViewHolder holder, int position) {
        holder.bind(charges.get(position));
    }

    @Override
    public int getItemCount() {
        return charges == null ? 0 : charges.size();
    }

    @Override
    public void call(List<HeightCharge> strings) {
        charges = strings;
        notifyDataSetChanged();
    }
}
