package com.example.xuantuandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuantuandroid.R;
import com.example.xuantuandroid.adapters.SliderAdapter;

public class onBoardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    Context context;

    Button btn;

    TextView[] dots;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide thanh trang thai
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // setContentView(R.layout.activity_on_boarding);

        //hide thanh cong cu
        getSupportActionBar().hide();

        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

      //  addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
        btn = findViewById(R.id.get_started_btn);
        // Goi Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(onBoardingActivity.this, RegistrationActivity.class));

                finish();
            }
        });

    }
/*
    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i = 0 ; i < dots.length; i++){
            dots[i] = new Text();
            dots[i].setText(Html.fromHtml("&#8226"));

        }
    }*/

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0){
                btn.setVisibility(View.INVISIBLE);
            }else if ( position == 1){
                btn.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(onBoardingActivity.this,R.anim.slide_amination);
                btn.setAnimation(animation);
                btn.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}