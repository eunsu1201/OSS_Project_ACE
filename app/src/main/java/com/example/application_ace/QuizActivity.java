package com.example.application_ace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuizNumber, tvQuestion, tvExplanation;
    private Button btnO, btnX, btnPrev, btnNext, btnCheck, btnFinish;
    private CardView cardExplanation;

    private List<QuizItem> quizList;
    private int currentIndex = 0;
    private boolean showExplanation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // 뷰 연결
        tvQuizNumber = findViewById(R.id.tvQuizNumber);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvExplanation = findViewById(R.id.tvExplanation);
        cardExplanation = findViewById(R.id.cardExplanation);

        btnO = findViewById(R.id.btnO);
        btnX = findViewById(R.id.btnX);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnCheck = findViewById(R.id.btnCheck);
        btnFinish = findViewById(R.id.btnFinish);

        quizList = getQuizList();
        updateQuiz();

        btnO.setOnClickListener(v -> checkAnswer(true));
        btnX.setOnClickListener(v -> checkAnswer(false));

        btnCheck.setOnClickListener(v -> {
            showExplanation = true;
            updateQuiz();
        });

        btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(QuizActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < quizList.size() - 1) {
                currentIndex++;
                showExplanation = false;
                updateQuiz();
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                showExplanation = false;
                updateQuiz();
            }
        });
    }

    private void updateQuiz() {
        QuizItem item = quizList.get(currentIndex);
        tvQuizNumber.setText("Quiz " + (currentIndex + 1) + ".");
        tvQuestion.setText(item.getQuestion());

        if (showExplanation && item.getExplanation() != null) {
            cardExplanation.setVisibility(View.VISIBLE);
            tvExplanation.setText("해설: " + item.getExplanation());
        } else {
            cardExplanation.setVisibility(View.GONE);
        }

        // 버튼 가시성 제어
        if (currentIndex == quizList.size() - 1) {
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    private void checkAnswer(boolean userChoice) {
        boolean correct = quizList.get(currentIndex).getAnswer();
        if (userChoice == correct) {
            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "오답입니다!", Toast.LENGTH_SHORT).show();
        }
    }

    private List<QuizItem> getQuizList() {
        return Arrays.asList(
                new QuizItem("- 창업자가 현금 5,000,000원을 출자하여 회사를 설립한 경우, 차변은 자본금 5,000,000으로 기록한다.", false,
                        "차변은 현금, 대변이 자본금임. → 자산의 증가 / 자본의 증가"),
                new QuizItem("- 상품을 3,000,000원에 매입하고 현금으로 지급하였다면, 차변은 상품, 대변은 현금이다.", true,
                        "→ 자산의 증가 / 자산의 감소"),
                new QuizItem("- 상품을 4,000,000원에 외상으로 판매했다면, 차변은 매출, 대변은 외상매출금이다.", false,
                        "차변은 외상매출금, 대변은 매출임. → 자산의 증가 / 수익의 발생"),
                new QuizItem("- 상품을 2,000,000원에 판매하고, 원가는 1,200,000원이었다면, 차변에는 매출원가 1,200,000원이 포함된다.", true,
                        "→ 비용의 발생 / 수익의 발생"),
                new QuizItem("- 단식부기는 기업의 모든 거래를 차변과 대변으로 기록하는 방식이다.", false,
                        "단식부기는 현금의 수입과 지출만 기록함."),
                new QuizItem("- 재무제표는 기말 결산 시 작성되며, 기업의 재무 상태와 경영 성과를 나타낸다.", true,
                        null),
                new QuizItem("- 재무상태표의 기말 자본은 기초 자본에 이익잉여금을 더한 값과 같다.", false,
                        "기말 자본 = 기초 자본 + 당기순이익임.")
        );
    }
}

