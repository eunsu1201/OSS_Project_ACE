package com.example.application_ace;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class PuzzleIncomeActivity extends AppCompatActivity {

    private TextView tvResultMark;
    private Button btnFinish;
    private final Handler handler = new Handler();

    private final HashMap<Integer, String> correctAnswers = new HashMap<>();
    private final HashMap<Integer, String> definitions = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_income);

        tvResultMark = findViewById(R.id.tvResultMark);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(PuzzleIncomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        for (int i = 1; i <= 15; i++) {
            int resId = getResources().getIdentifier("item" + i, "id", getPackageName());
            View item = findViewById(resId);
            if (item != null) {
                setDrag(item);
            }
        }

        View dropRevenue = findViewById(R.id.dropRevenue);
        View dropExpense = findViewById(R.id.dropExpense);

        setDropTarget((TextView) dropRevenue);
        setDropTarget((TextView) dropExpense);

        // 정답 설정
        correctAnswers.put(R.id.item1, "비용"); // 소모품비
        correctAnswers.put(R.id.item2, "수익"); // 이자수익
        correctAnswers.put(R.id.item3, "비용"); // 급여
        correctAnswers.put(R.id.item4, "수익"); // 수수료수익
        correctAnswers.put(R.id.item5, "수익"); // 매출
        correctAnswers.put(R.id.item6, "비용"); // 매출원가
        correctAnswers.put(R.id.item7, "비용"); // 광고선전비
        correctAnswers.put(R.id.item8, "비용"); // 감가상각비
        correctAnswers.put(R.id.item9, "수익"); // 임대료수익
        correctAnswers.put(R.id.item10, "비용"); // 복리후생비
        correctAnswers.put(R.id.item11, "비용"); // 법인세비용
        correctAnswers.put(R.id.item12, "수익"); // 기타수익
        correctAnswers.put(R.id.item13, "비용"); // 기타비용
        correctAnswers.put(R.id.item14, "비용"); // 지급이자
        correctAnswers.put(R.id.item15, "비용"); // 수선비

        definitions.put(R.id.item1, "소모품비: 소모품 사용으로 인한 비용입니다.\n→ 비용입니다.");
        definitions.put(R.id.item2, "이자수익: 예금 등에서 발생한 수익입니다.\n→ 수익입니다.");
        definitions.put(R.id.item3, "급여: 직원에게 지급하는 급여입니다.\n→ 비용입니다.");
        definitions.put(R.id.item4, "수수료수익: 서비스 제공으로 발생한 수익입니다.\n→ 수익입니다.");
        definitions.put(R.id.item5, "매출: 상품 판매를 통해 발생한 수익입니다.\n→ 수익입니다.");
        definitions.put(R.id.item6, "매출원가: 판매된 상품의 원가입니다.\n→ 비용입니다.");
        definitions.put(R.id.item7, "광고선전비: 광고 활동에 지출한 비용입니다.\n→ 비용입니다.");
        definitions.put(R.id.item8, "감가상각비: 자산 가치 감소를 반영한 비용입니다.\n→ 비용입니다.");
        definitions.put(R.id.item9, "임대료수익: 부동산 임대 수익입니다.\n→ 수익입니다.");
        definitions.put(R.id.item10, "복리후생비: 복지에 지출한 비용입니다.\n→ 비용입니다.");
        definitions.put(R.id.item11, "법인세비용: 세금으로 지출한 금액입니다.\n→ 비용입니다.");
        definitions.put(R.id.item12, "기타수익: 기타 다양한 수익입니다.\n→ 수익입니다.");
        definitions.put(R.id.item13, "기타비용: 기타 다양한 비용입니다.\n→ 비용입니다.");
        definitions.put(R.id.item14, "지급이자: 차입금에 대한 이자 비용입니다.\n→ 비용입니다.");
        definitions.put(R.id.item15, "수선비: 수리와 유지에 사용된 비용입니다.\n→ 비용입니다.");
    }

    private void setDrag(View view) {
        view.setOnLongClickListener(v -> {
            v.startDragAndDrop(null, new View.DragShadowBuilder(v), v, 0);
            return true;
        });
    }

    private void setDropTarget(TextView target) {
        target.setOnDragListener((v, event) -> {
            View dragged = (View) event.getLocalState();

            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    String expected = correctAnswers.get(dragged.getId());
                    String targetLabel = ((TextView) v).getText().toString();

                    if (expected.equals(targetLabel)) {
                        dragged.setVisibility(View.GONE);
                        showResultMark("O", 0xFF4CAF50);
                    } else {
                        showResultMark("X", 0xFFF44336);
                        dragged.setTranslationX(0);
                        dragged.setTranslationY(0);

                        handler.postDelayed(() -> {
                            ToastUtil.showTooltip(this, definitions.get(dragged.getId()), dragged);
                            handler.postDelayed(ToastUtil::hideTooltip, 1500);
                        }, 1000);
                    }
                    return true;
            }
            return true;
        });
    }

    private void showResultMark(String mark, int color) {
        tvResultMark.setText(mark);
        tvResultMark.setTextColor(color);
        tvResultMark.setVisibility(View.VISIBLE);
        handler.postDelayed(() -> tvResultMark.setVisibility(View.GONE), 1000);
    }
}