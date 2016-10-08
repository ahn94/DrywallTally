package com.austinnightingale.drywalltally.db;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class JobInfo {
    public abstract Job job();
    public abstract List<String> heightCharges();
    public abstract List<TallyArea> tallyAreas();

    public static TypeAdapter<JobInfo> typeAdapter(Gson gson) {
        return new AutoValue_JobInfo.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_JobInfo.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder job(Job job);
        public abstract Builder heightCharges(List<String> chargeList);
        public abstract Builder tallyAreas(List<TallyArea> tallyAreas);
        public abstract JobInfo build();
    }
}
