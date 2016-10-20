package com.austinnightingale.drywalltally.job.options;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.job.dialogs.InputDialog;
import com.austinnightingale.drywalltally.job.dialogs.InputListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;



public class ExtraOptionsFragment extends Fragment implements InputListener {

    @BindView(R.id.value_course_screws)
    TextView textScrews;
    @BindView(R.id.value_dsa20)
    TextView textDsa20;
    @BindView(R.id.value_shims)
    TextView textShims;
    @BindView(R.id.value_lite_blue)
    TextView textLiteBlue;
    @BindView(R.id.value_magnum_hp)
    TextView textMagnumHp;
    @BindView(R.id.value_hot_45)
    TextView textHot45;
    @BindView(R.id.value_hot_90)
    TextView textHot90;
    @BindView(R.id.value_hot_20)
    TextView textHot20;
    @BindView(R.id.value_hot_5)
    TextView textHot5;
    @BindView(R.id.value_levelcoat)
    TextView textLevelCoat;
    @BindView(R.id.value_ultraflex_325)
    TextView textUltraflex325;
    @BindView(R.id.value_ultraflex_450)
    TextView textUltraflex450;
    @BindView(R.id.value_nocoat_8ft_90)
    TextView textNoCoat8ft90;
    @BindView(R.id.value_nocoat_10ft_90)
    TextView textNoCoat10ft90;
    @BindView(R.id.value_nocoat_8ft_bull)
    TextView textNoCoat8ftBull;
    @BindView(R.id.value_nocoat_10ft_bull)
    TextView textNoCoat10ftBull;
    @BindView(R.id.value_xcrack)
    TextView textXCrack;
    


    private Subscription subscription;
    @Inject
    DAO dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_extras_options, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

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

    private void refreshView(Job job) {
        textScrews.setText(String.valueOf(job.CScrews()));
        textDsa20.setText(String.valueOf(job.dsa20()));
        textShims.setText(String.valueOf(job.shims()));
        textLiteBlue.setText(String.valueOf(job.liteBlue()));
        textMagnumHp.setText(String.valueOf(job.magnumHp()));
        textHot45.setText(String.valueOf(job.hot45()));
        textHot90.setText(String.valueOf(job.hot90()));
        textHot20.setText(String.valueOf(job.hot20()));
        textHot5.setText(String.valueOf(job.hot5()));
        textLevelCoat.setText(String.valueOf(job.levelcoat()));
        textUltraflex325.setText(String.valueOf(job.ultraflex325()));
        textUltraflex450.setText(String.valueOf(job.ultraflex450()));
        textNoCoat8ft90.setText(String.valueOf(job.nocoat8ft90()));
        textNoCoat10ft90.setText(String.valueOf(job.nocoat10ft90()));
        textNoCoat8ftBull.setText(String.valueOf(job.nocoat8ftBull()));
        textNoCoat10ftBull.setText(String.valueOf(job.nocoat10ftBull()));
        textXCrack.setText(String.valueOf(job.xcrack()));
    }


    private void showDialog(String column, String title) {
        InputDialog numberInputDialog = InputDialog.newInstance(
                column,
                title,
                InputType.TYPE_CLASS_NUMBER,
                false,
                this
        );
        numberInputDialog.show(getFragmentManager(), "number picker");
    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = dao.obsJobWithId(getID())
                .subscribe(new Subscriber<Job>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("jobinfo", e.toString());
                    }

                    @Override
                    public void onNext(Job job) {
                        refreshView(job);
                    }
                });
    }

    @Override
    public void setInputResults(ContentValues values) {
        updateJob(values);
    }

    public void updateJob(ContentValues values) {
        dao.updateJob(getID(), values);
    }
    
    @OnClick(R.id.view_course_screws)
    public void screwsClicked() {
        showDialog(Job.SCREWS, getString(R.string.job_extra_course_screws));
    }
    
    @OnClick(R.id.view_dsa20)
    public void dsa20Clicked() {
        showDialog(Job.DSA20, getString(R.string.job_extra_dsa20));
    }

    @OnClick(R.id.view_shims)
    public void shimsClicked() {
        showDialog(Job.SHIMS, getString(R.string.job_extra_shims));
    }
    
    @OnClick(R.id.view_lite_blue)
    public void liteBlueClicked() {
        showDialog(Job.LITE_BLUE, getString(R.string.job_extra_lite_blue));
    }
    
    @OnClick(R.id.view_magnum_hp)
    public void magnumHpClicked() {
        showDialog(Job.MAGNUM_HP, getString(R.string.job_extra__magnum_ap));
    }
    
    @OnClick(R.id.view_hot_45) 
    public void hot45Clicked() {
        showDialog(Job.HOT_45, getString(R.string.job_extra_hot_45));
    }

    @OnClick(R.id.view_hot_90)
    public void hot90Clicked() {
        showDialog(Job.HOT_90, getString(R.string.job_extra_hot_90));
    }

    @OnClick(R.id.view_hot_20)
    public void hot20Clicked() {
        showDialog(Job.HOT_20, getString(R.string.job_extra_hot_20));
    }

    @OnClick(R.id.view_hot_5)
    public void hot5Clicked() {
        showDialog(Job.HOT_5, getString(R.string.job_extra_hot_5));
    }

    @OnClick(R.id.view_levelcoat)
    public void levelcoatClicked() {
        showDialog(Job.LEVELCOAT, getString(R.string.job_extra__5g_levelcoat));
    }
    
    @OnClick(R.id.view_nocoat_8ft_90)
    public void noCoat8ft90Clicked() {
        showDialog(Job.NOCOAT_8_90, getString(R.string.job_extra_8_nocoat_90));
    }

    @OnClick(R.id.view_nocoat_10ft_90)
    public void noCoat10ft90Clicked() {
        showDialog(Job.NOCOAT_10_90, getString(R.string.job_extra_10_nocoat_90));
    }

    @OnClick(R.id.view_nocoat_8ft_bull)
    public void noCoat8ftBullClicked() {
        showDialog(Job.NOCOAT_8_BULLNOSE, getString(R.string.job_extra_8_nocoat_bull));
    }

    @OnClick(R.id.view_nocoat_10ft_bull)
    public void noCoat10ftBullClicked() {
        showDialog(Job.NOCOAT_10_BULLNOSE, getString(R.string.job_extra_10_nocoat_bull));
    }

    @OnClick(R.id.view_xcrack)
    public void xcrackClicked() {
        showDialog(Job.XCRACK, getString(R.string.job_extra_xcrack));
    }

    @OnClick(R.id.view_ultraflex_325)
    public void ultraFlex325Clicked() {
        showDialog(Job.ULTRAFLEX_325, getString(R.string.job_extra_ultraflex_325));
    }

    @OnClick(R.id.view_ultraflex_450)
    public void ultraFlex450Clicked() {
        showDialog(Job.ULTRAFLEX_450, getString(R.string.job_extra_ultraflex_450));
    }
}
