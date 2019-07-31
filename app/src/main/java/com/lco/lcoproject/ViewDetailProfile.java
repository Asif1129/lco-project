package com.lco.lcoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class ViewDetailProfile extends AppCompatActivity {
HTextView hTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_profile);
        hTextView = (HTextView) findViewById(R.id.text);
        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/font-name.ttf"));
// be sure to set custom typeface before setting the animate type, otherwise the font may not be updated.
        hTextView.setAnimateType(HTextViewType.LINE);
        hTextView.animateText("new simple string"); //
    }
}
