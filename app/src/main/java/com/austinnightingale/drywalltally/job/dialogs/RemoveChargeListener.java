package com.austinnightingale.drywalltally.job.dialogs;

import com.austinnightingale.drywalltally.db.HeightCharge;

public interface RemoveChargeListener {
    void removeChargeWithId(int chargeId);
    void openRemoveChargeDialog(HeightCharge charge);
}
