package com.androidtutorialshub.bottomsheets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class NestedScrollViewActivity extends AppCompatActivity {

    BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);

        NestedScrollView ba=(NestedScrollView) findViewById(R.id.back);
        //ba.setscroll
        View bottomSheet = findViewById(R.id.bottom_sheet);

        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setPeekHeight(800);
        behavior.setHideable(true);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
            }
            @Override
            public void onSlide(@NonNull View view, float v) {
            }
        });

        Button button = (Button)findViewById(R.id.button_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){

                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else{

                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);*/
            }
        });
    }
}
