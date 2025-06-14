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

public class PuzzleBalanceActivity extends AppCompatActivity {

    private TextView tvResultMark;
    private Button btnFinish;
    private final Handler handler = new Handler();

    private final HashMap<Integer, String> correctAnswers = new HashMap<>();
    private final HashMap<Integer, String> definitions = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_balance);

        tvResultMark = findViewById(R.id.tvResultMark);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(PuzzleBalanceActivity.this, MainActivity.class);
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

        View dropAsset = findViewById(R.id.dropAsset);
        View dropLiability = findViewById(R.id.dropLiability);
        View dropEquity = findViewById(R.id.dropEquity);

        setDropTarget((TextView) dropAsset);
        setDropTarget((TextView) dropLiability);
        setDropTarget((TextView) dropEquity);

        correctAnswers.put(R.id.item1, "자산"); // 현금
        correctAnswers.put(R.id.item2, "자산"); // 외상매출금
        correctAnswers.put(R.id.item3, "부채"); // 외상매입금
        correctAnswers.put(R.id.item4, "자본"); // 자본금
        correctAnswers.put(R.id.item5, "자산"); // 토지
        correctAnswers.put(R.id.item6, "부채"); // 장기차입금
        correctAnswers.put(R.id.item7, "자본"); // 이익잉여금
        correctAnswers.put(R.id.item8, "자산"); // 건물
        correctAnswers.put(R.id.item9, "자산"); // 재고자산
        correctAnswers.put(R.id.item10, "부채"); // 미지급금
        correctAnswers.put(R.id.item11, "자본"); // 자본잉여금
        correctAnswers.put(R.id.item12, "자산"); // 비품
        correctAnswers.put(R.id.item13, "부채"); // 예수금
        correctAnswers.put(R.id.item14, "자산"); // 단기금융상품
        correctAnswers.put(R.id.item15, "부채"); // 선수금

        definitions.put(R.id.item1, "현금: 기업이 보유한 유동 자산입니다.\n→ 자산입니다.");
        definitions.put(R.id.item2, "외상매출금: 외상으로 판매한 금액을 받을 권리입니다.\n→ 자산입니다.");
        definitions.put(R.id.item3, "외상매입금: 외상으로 구입한 물품에 대한 채무입니다.\n→ 부채입니다.");
        definitions.put(R.id.item4, "자본금: 창업자가 출자한 금액입니다.\n→ 자본입니다.");
        definitions.put(R.id.item5, "토지: 기업이 소유한 토지입니다.\n→ 자산입니다.");
        definitions.put(R.id.item6, "장기차입금: 장기간에 걸쳐 상환할 부채입니다.\n→ 부채입니다.");
        definitions.put(R.id.item7, "이익잉여금: 누적된 이익의 잔액입니다.\n→ 자본입니다.");
        definitions.put(R.id.item8, "건물: 기업이 보유한 건물 자산입니다.\n→ 자산입니다.");
        definitions.put(R.id.item9, "재고자산: 판매 목적으로 보유 중인 자산입니다.\n→ 자산입니다.");
        definitions.put(R.id.item10, "미지급금: 지급하지 않은 부채입니다.\n→ 부채입니다.");
        definitions.put(R.id.item11, "자본잉여금: 자본금 외에 유입된 자본입니다.\n→ 자본입니다.");
        definitions.put(R.id.item12, "비품: 사무용 자산입니다.\n→ 자산입니다.");
        definitions.put(R.id.item13, "예수금: 타인의 자금을 일시적으로 보관한 금액입니다.\n→ 부채입니다.");
        definitions.put(R.id.item14, "단기금융상품: 만기 1년 이내 금융 투자 자산입니다.\n→ 자산입니다.");
        definitions.put(R.id.item15, "선수금: 선지급받은 금액입니다.\n→ 부채입니다.");
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