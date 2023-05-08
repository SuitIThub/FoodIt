package com.example.foodit.classes.objects;

import android.text.SpannableStringBuilder;

import com.example.foodit.classes.Helper;

import org.json.JSONException;
import org.json.JSONObject;

public class PrepStep {
    public static class Save {
        public final String id;
        public final String text;

        public Save(PrepStep step) {
            id = step.id;
            text = step.text.toString();
        }
    }

    public String id;
    public SpannableStringBuilder text;

    public PrepStep(String text) {
        id = Helper.id();

        this.text = refineText(text);
    }

    public PrepStep(Save save){
        id = save.id;
        text = refineText(save.text);
    }

    public SpannableStringBuilder refineText(String text)
    {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        return ssb;
    }
}
