package com.example.application_ace;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class ConceptActivity extends AppCompatActivity {

    private LinearLayout lessonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept);

        lessonContainer = findViewById(R.id.lessonContainer);

        List<String> lessons = Arrays.asList(
                "1. 부기와 회계", "2. 재무제표", "3. 회계의 순환과정", "4. 자산, 부채, 자본",
                "5. 수익, 비용", "6. 결산의 이해", "7. 시산표", "8. 결산수정분개", "9. 장부 마감"
        );

        LayoutInflater inflater = LayoutInflater.from(this);

        for (String lesson : lessons) {
            View cardView = inflater.inflate(R.layout.lesson_item, lessonContainer, false);
            TextView titleView = cardView.findViewById(R.id.tvLessonTitle);
            titleView.setText(lesson);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(ConceptActivity.this, CardViewActivity.class);
                intent.putExtra("lessonTitle", lesson);
                startActivity(intent);
            });

            lessonContainer.addView(cardView);
        }
    }
}


