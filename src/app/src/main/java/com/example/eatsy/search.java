package com.example.eatsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_search);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            // 获取按钮并设置点击监听器
            Button returnHome = findViewById(R.id.back);
            returnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 创建返回MainActivity的Intent
                    Intent intent = new Intent(search.this, DashboardActivity.class);

                    // 设置FLAG_ACTIVITY_CLEAR_TOP，清除MainActivity之上的所有Activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    // 启动MainActivity
                    startActivity(intent);
                }
            });
        }

}


