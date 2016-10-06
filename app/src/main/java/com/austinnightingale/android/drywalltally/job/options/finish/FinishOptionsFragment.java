package com.austinnightingale.android.drywalltally.job.options.finish;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.dialogs.ChoiceDialog;
import com.austinnightingale.android.drywalltally.job.dialogs.InputListener;
import com.austinnightingale.android.drywalltally.job.dialogs.RemoveChargeDialog;
import com.austinnightingale.android.drywalltally.job.dialogs.RemoveChargeListener;
import com.austinnightingale.android.drywalltally.tally.BaseJobFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;


public class FinishOptionsFragment extends BaseJobFragment
        implements InputListener, HeightChargeListener, RemoveChargeListener {


    @BindView(R.id.value_wall_finish)
    TextView textWallFinish;
    @BindView(R.id.value_ceiling_finish)
    TextView textCeilingFinish;
    @BindView(R.id.rv_height_charges)
    RecyclerView heightRecycle;


    HeightChargeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_options, container, false);
        ButterKnife.bind(this, view);
        adapter = new HeightChargeAdapter(this, true);
        heightRecycle.setAdapter(adapter);
        heightRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = new CompositeSubscription();
        subscription.add(
                dao.obsJobWithId(getID()).subscribe(this::refreshView)
        );
        subscription.add(
                dao.obsHeightChargeListFromJobId(getID()).subscribe(adapter)
        );
    }

    private void refreshView(Job job) {
        textCeilingFinish.setText(String.valueOf(job.ceilFinish()));
        textWallFinish.setText(String.valueOf(job.wallFinish()));
    }

    private void showDialog(String column, String title, int resource) {
        Bundle arguments = new Bundle();
        arguments.putString(Job.COLUMN_NAME, column);
        arguments.putString(ChoiceDialog.TITLE, title);
        arguments.putInt(ChoiceDialog.RES, resource);

        ChoiceDialog choiceDialog = new ChoiceDialog();
        choiceDialog.setArguments(arguments);
        choiceDialog.setTargetFragment(this, 0);

        choiceDialog.show(getFragmentManager(), "number picker");
    }

    @Override
    public void removeChargeWithId(int chargeId) {
        dao.deleteHeightCharge(chargeId);
    }

    @Override
    public void openRemoveChargeDialog(HeightCharge charge) {
        RemoveChargeDialog removeChargeDialog = RemoveChargeDialog.newInstance(
                charge.chargeId(),
                charge.heightCharge(),
                this
        );
        removeChargeDialog.show(getFragmentManager(), "remove charge dialog");
    }

    @Override
    public void addCharge(String heightCharge) {
        HeightCharge charge = HeightCharge.builder()
                .setHeightCharge(heightCharge)
                .setJobId(getID())
                .build();
        dao.insertHeightCharge(charge);
    }


    private void showPicker(HeightChargeDialog heightChargeDialog) {
        heightChargeDialog.show(getFragmentManager(), "height charge picker");
    }


    @Override
    public void setInputResults(ContentValues values) {
        updateJob(values);
    }

    @OnClick(R.id.view_wall_finish)
    public void wallFinishClick() {
        showDialog(Job.WALL_FINISH, "Wall Finish", R.array.wall_finishes);
    }

    @OnClick(R.id.view_ceiling_finish)
    public void ceilFinishClick() {
        showDialog(Job.CEIL_FINISH, "Ceiling Finish", R.array.ceiling_finishes);
    }

    @OnClick(R.id.view_height_upcharge)
    public void heightChargeClick() {
        showPicker(HeightChargeDialog.newInstance(this));
    }
}
