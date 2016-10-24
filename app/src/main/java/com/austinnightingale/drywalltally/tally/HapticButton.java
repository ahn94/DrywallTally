package com.austinnightingale.drywalltally.tally;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.austinnightingale.drywalltally.R;

public class HapticButton extends AppCompatImageButton {

    Vibrator vib;

    public HapticButton(Context context) {
        super(context);
    }

    public HapticButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HapticButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setSupportBackgroundTintList(getResources().getColorStateList(R.color.tallyButton));
        if (!isInEditMode()) {
            vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            vib.vibrate(30);
        }
        return super.onTouchEvent(event);
    }



}
