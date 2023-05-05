package com.example.foodit.classes;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

public class PrepStep {
    public String id;
    public SpannableStringBuilder text;

    public PrepStep(String text) {
        id = Helper.id();

        this.text = refineText(text);
    }

    public SpannableStringBuilder refineText(String text)
    {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        return ssb;
    }
}
