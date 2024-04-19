package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class Addpage extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpage);

        // Reference to the ConstraintLayout and NestedScrollView
        ConstraintLayout constraintLayout = findViewById(R.id.Constriant_add);
        NestedScrollView nestedScrollView = findViewById(R.id.Scroll);

        // Set an onTouchListener on the ConstraintLayout
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Check if the touch event is outside the NestedScrollView
                    Rect rect = new Rect();
                    nestedScrollView.getGlobalVisibleRect(rect);
                    if (!rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        finish(); // Go back to the previous Activity
                        return true; // Event is handled
                    }
                }
                return false; // Event is not handled, pass it to the NestedScrollView or other child views
            }
        });

        CardView donate_card = findViewById(R.id.donateCard);
        donate_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        CardView exchange_card = findViewById(R.id.exchangeCard);
        exchange_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        CardView wanted_card = findViewById(R.id.wantedCard);
        wanted_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}