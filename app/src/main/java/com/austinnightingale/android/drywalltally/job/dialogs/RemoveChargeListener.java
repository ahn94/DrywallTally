package com.austinnightingale.android.drywalltally.job.dialogs;

import com.austinnightingale.android.drywalltally.db.HeightCharge;

public interface RemoveChargeListener {
    void removeChargeWithId(int chargeId);
    void openRemoveChargeDialog(HeightCharge charge);
}
