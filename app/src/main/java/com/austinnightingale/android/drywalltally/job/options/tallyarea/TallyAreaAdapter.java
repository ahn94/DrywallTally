package com.austinnightingale.android.drywalltally.job.options.tallyarea;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.tally.TallyActivity;

import java.util.List;

import rx.functions.Action1;


public class TallyAreaAdapter extends RecyclerView.Adapter<TallyAreaViewHolder> implements Action1<List<TallyArea>>, TallyCallback{

    private static final String TAG = "adapterTally";
    private List<TallyArea> mTallyAreas;
    private Context context;

    public TallyAreaAdapter(Context context) {
        this.context = context;
    }


    @Override
    public TallyAreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new TallyAreaViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(TallyAreaViewHolder holder, int position) {
        holder.bind(mTallyAreas.get(position));
    }

    @Override
    public int getItemCount() {
        return mTallyAreas == null ? 0 : mTallyAreas.size();
    }

    @Override
    public void call(List<TallyArea> tallyAreas) {
        mTallyAreas = tallyAreas;
        notifyDataSetChanged();
    }

    @Override
    public void openTallyForArea(int id) {
        Log.d(TAG, "openTallyForArea: clicked");
        Intent intent = TallyActivity.newInstance(id);
        intent.setClass(context, TallyActivity.class);
        context.startActivity(intent);
    }
}
