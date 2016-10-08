package com.austinnightingale.drywalltally.job.summary.jobsummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.db.TallyArea;
import com.austinnightingale.drywalltally.job.Utils;
import com.austinnightingale.drywalltally.job.options.finish.HeightChargeAdapter;
import com.austinnightingale.drywalltally.tally.BaseJobFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;


public class JobSummaryFragment extends BaseJobFragment {

    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.text_comments)
    TextView textComment;
    @BindView(R.id.text_comment_title)
    TextView textCommentTitle;
    @BindView(R.id.divider_header)
    View divider;
    @BindView(R.id.text_wall_finish)
    TextView textWallFinish;
    @BindView(R.id.text_ceiling_finish)
    TextView textCeilingFinish;
    @BindView(R.id.text_ceiling_square)
    TextView textCeilingSqare;
    @BindView(R.id.text_total_square)
    TextView textTotalSquare;

    @BindView(R.id.card_height)
    CardView cardView;

    @BindView(R.id.rv_height_charges)
    RecyclerView heightRecycle;
    HeightChargeAdapter adapterHeight;

    @BindView(R.id.rv_extras)
    RecyclerView extrasRecycle;
    @BindView(R.id.rv_options)
    RecyclerView optionsRecycle;
    JobSummaryAdapter adapterOption;
    JobSummaryAdapter adapterExtras;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_options_totals, container, false);
        ButterKnife.bind(this, view);


        adapterOption = new JobSummaryAdapter(1);
        adapterExtras = new JobSummaryAdapter(2);

        optionsRecycle.setAdapter(adapterExtras);
        optionsRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        extrasRecycle.setAdapter(adapterOption);
        extrasRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterHeight = new HeightChargeAdapter(this, false);
        heightRecycle.setAdapter(adapterHeight);
        heightRecycle.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getContext())
                        .build()
        );
        heightRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterHeight.notifyDataSetChanged();
        adapterHeight.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapterHeight.getItemCount() == 0) {
                    cardView.setVisibility(View.GONE);
                } else {
                    cardView.setVisibility(View.VISIBLE);
                }
            }
        });
        adapterHeight.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Observable<Job> jobObs = dao.obsJobWithId(getID());

        subscription.add(jobObs.subscribe(adapterOption));
        subscription.add(jobObs.subscribe(adapterExtras));
        subscription.add(jobObs.subscribe(this::refreshView));

        subscription.add(
                dao.obsHeightChargeListFromJobId(getID()).subscribe(adapterHeight)
        );

        subscription.add(dao.obsTallyListByJobId(getID()).subscribe(this::refreshView));
    }

    public void refreshView(Job job) {
        textName.setText(job.jobName());
        String address = job.address();
        if (address.length() > 0) {
            textAddress.setVisibility(View.VISIBLE);
            textAddress.setText(address);
        } else {
            textAddress.setVisibility(View.GONE);
        }
        String comment = job.comment();
        if (comment.length() > 0) {
            textComment.setVisibility(View.VISIBLE);
            textComment.setText(comment);
            textCommentTitle.setVisibility(View.VISIBLE);
        } else {
            textComment.setVisibility(View.GONE);
            textCommentTitle.setVisibility(View.GONE);
        }

        if (textComment.getVisibility() == View.GONE && textAddress.getVisibility() == View.GONE) {
            divider.setVisibility(View.GONE);
        } else {
            divider.setVisibility(View.VISIBLE);
        }

        textWallFinish.setText(getString(R.string.format_wall_finish, job.wallFinish()));
        textCeilingFinish.setText(getString(R.string.format_ceil_finish, job.ceilFinish()));
    }

    private void refreshView(List<TallyArea> tallyAreaList) {
        textCeilingSqare.setText(getString(R.string.format_ceil_square, Utils.jobCeilingFtString(tallyAreaList)));
        textTotalSquare.setText(getString(R.string.format_total_square, Utils.jobTotalFtString(tallyAreaList)));
    }
}
