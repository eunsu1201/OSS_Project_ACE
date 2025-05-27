package com.example.application_ace;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnPrev, btnNextOrFinish;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        // 버튼과 뷰페이저 연결
        viewPager = findViewById(R.id.viewPager);
        btnPrev = findViewById(R.id.btnPrev);
        btnNextOrFinish = findViewById(R.id.btnNextOrFinish);

        // 단원 정보 가져오기
        String lessonTitle = getIntent().getStringExtra("lessonTitle");
        List<CardItem> cards = getCardsForLesson(lessonTitle);

        // 어댑터 설정
        cardAdapter = new CardAdapter(cards);
        viewPager.setAdapter(cardAdapter);

        // 버튼 클릭 이벤트
        btnPrev.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            if (current > 0) {
                viewPager.setCurrentItem(current - 1);
            } else {
                // 여기! this → CardViewActivity.this 로 변경
                Toast.makeText(CardViewActivity.this, "첫 번째 페이지입니다!", Toast.LENGTH_SHORT).show();
            }
        });



        btnNextOrFinish.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            int last = cardAdapter.getItemCount() - 1;
            if (current < last) {
                viewPager.setCurrentItem(current + 1);
            } else {
                finish(); // 마지막 페이지면 종료
            }
        });

        // 페이지가 바뀔 때 버튼 상태 업데이트
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateButtons(position);
            }
        });

        // 처음 상태에서 버튼 설정
        updateButtons(0);
    }

    // 버튼 상태 업데이트 메서드
    private void updateButtons(int position) {
        int last = cardAdapter.getItemCount() - 1;

        if (position == last) {
            btnNextOrFinish.setText("종료");
            btnNextOrFinish.setTextColor(Color.WHITE);
            btnNextOrFinish.setBackgroundColor(Color.parseColor("#D32F2F")); // 빨간색
        } else {
            btnNextOrFinish.setText("▶");
            btnNextOrFinish.setTextColor(Color.BLACK);
            btnNextOrFinish.setBackgroundColor(Color.parseColor("#DDDDDD"));
        }
    }

    // 단원별 카드 내용 매핑
    private List<CardItem> getCardsForLesson(String lessonTitle) {
        HashMap<String, List<CardItem>> lessonMap = new HashMap<>();

        lessonMap.put("1. 부기와 회계", Arrays.asList(
                new CardItem("부기와 회계",
                        "⦁ 회계는 기업의 이해관계자들이 합리적인 의사결정을 할 수 있도록 유용한 재무적 정보를 제공하는 일련의 과정 또는 체계입니다.\n\n" +
                                "⦁ 회계는 기업 활동의 결과로 발생하는 사건들을 장부에 기록하고 그 기록들을 모아 주주나 채권자들에게 유용한 정보를 제공하는 역할을 합니다."
                ),
                new CardItem("부기의 개념과 단식부기",
                        "⦁ 부기란? 거래 내용을 정리하여 장부에 기입하는 것으로 ‘장부기록’의 준말\n\n" +
                                "- 부기의 종류\n" +
                                "  (1) 단식부기 : 현금의 수입과 지출이 있을 때만 장부에 기록하는 형식으로 재산에 대한 자세한 정보를 알기 어렵고 오류 검증하기에도 어렵기 때문에 대부분의 기업에서는 사용하지 않는 방식입니다."

                ),
                new CardItem("복식부기와 분개",
                        "  (2) 복식부기 : 회사 재산에 영향을 미치는 모든 거래를 파악하여 원인과 결과를 차변(왼쪽)과 대변(오른쪽)으로 분리하여 이중으로 기록하는 방식입니다.\n\n" +
                                "  * 차변과 대변으로 나누어 작성하는 것을 '분개'라고 합니다."
                ),
                new CardItem("분개의 예시",
                        "⦁ 자본금 납입 (회사 설립 시)\n" +
                                "- 상황) 창업자가 현금 10,000,000원을 출자하여 회사를 설립하였다.\n" +
                                "- 분개)\n" +
                                "   (차변) 현금 10,000,000" +
                                "                                   \t\t\t\t(대변) 자본금 10,000,000\n\n"
                ),
                new CardItem("분개의 예시",
                        "⦁ 상품 판매\n" +
                                "- 상황) 원가가 3,000,000인 상품을 5,000,000원에 현금으로 판매하였다.\n" +
                                "- 분개)\n" +
                                "   (차변) 현금 5,000,000\n" +
                                "           매출원가 3,000,000\n" +
                                "                         (대변) 매출 5,000,000\n" +
                                "                                   상품 3,000,000"
                ),
                new CardItem("분개의 활용과 주의사항",
                        "⦁ 분개는 어떻게 쓰일까?\n" +
                                "- 분개 내용은 기말(연말)에 총계정원장으로 옮겨져 계정별 잔액을 파악할 수 있습니다.\n" +
                                "- 이렇게 정리된 데이터를 바탕으로 재무제표가 만들어지는 것입니다.\n\n" +
                                "⦁ 분개 시 주의사항\n" +
                                "- 항상 차변과 대변의 금액이 같아야 합니다. 하나의 거래가 두 개 이상의 계정에 영향을 주기 때문입니다.\n" +
                                "  이 균형이 깨지면 기말결산 시에 잔액이 맞지 않아 오류가 발생하게 됩니다."
                )


        ));

        lessonMap.put("2. 재무제표", Arrays.asList(
                new CardItem("재무제표",
                        "⦁ 재무제표 (Financial Statements)\n" +
                                "- 기업이 기말 결산 시, 회계 기간동안 기업의 재무 상태와 경영 성과 등을\n" +
                                "  주주, 채권자, 정부 등에게 제공하기 위해 작성한 회계 보고서입니다."
                ),

                new CardItem("재무제표의 주요 종류",
                        "⦁ 주재무제표*의 종류\n" +
                                "(1) 재무상태표 : 기말 시점에서 회사의 재무상태 (자산, 부채, 자본)*을 보고\n" +
                                "(2) 손익계산서 : 일정기간 동안의 회사의 경영성과 (수익, 비용)*을 보고\n\n" +
                                "* 재무제표는 총 5가지가 있지만, 재무제표의 주를 이루는 2가지만 소개하였습니다.\n" +
                                "* 자산, 부채, 자본은 4단원을 참고해주세요!\n" +
                                "* 수익, 비용은 5단원을 참고해주세요!"
                ),
                new CardItem("재무상태표와 손익계산서의 관계",
                        "⦁ 재무상태표와 손익계산서의 관계\n\n" +
                                "  재무상태표의 기초 자본 + 손익계산서의 당기순이익 = 재무상태표의 기말 자본"
                )

        ));

        lessonMap.put("3. 회계의 순환과정", Arrays.asList(
                new CardItem("회계의 순환과정이란?",
                        "⦁ 모든 거래들을 식별하고 장부에 기록한 후, 기말에 이러한 기록을 바탕으로 재무제표를 작성하는 것을 회계의 순환과정이라고 합니다."
                ),

                new CardItem("거래",
                        "⦁ 거래\n" +
                                "(1) 일상생활에서의 거래\n" +
                                "- 일반적으로 재화나 서비스를 대상으로 거래당사자간에 이루어진 경제적 합의를 말합니다.\n" +
                                "  ex) 임대차 계약, 종업원 채용계약\n" +
                                "(2) 회계상 거래\n" +
                                "- 거래가 회사의 재무상태에 영향을 미치며 금액으로 측정할 수 있다면 회계상의 거래라고 봅니다.\n" +
                                "- 회계의 순환과정에서는 회계상 거래만을 거래로 식별합니다.\n" +
                                "  ex) 화재, 분실, 도난, 훼손"
                ),

                new CardItem("거래",
                        "(3) 일상&회계의 거래\n" +
                                "- 일상생활에서의 거래와 회계상의 거래는 명확하게 구분짓긴 어려우며\n" +
                                "  예시는 아래 그림과 같습니다."
                )
        ));

        lessonMap.put("4. 자산, 부채, 자본", Arrays.asList(
                new CardItem("자산과 부채 그리고 자본",
                        "⦁ 기업의 회계기간(1년) 동안에 발생한 자산, 부채, 자본을 의미하며, 이는 기말에 재무상태표에 반영되는 중요한 정보입니다."
                ),
                new CardItem("자산",
                        "⦁ 자산\n" +
                                "- 기업이 소유하고 있는 형태가 있거나(유형) 형태가 없는(무형) 가치물을 의미합니다.\n"+
                                "- 1년이내에 현금으로 전환이 가능한지 여부에 따라 유동/비유동 자산으로 분류됩니다."
                ),

                new CardItem("유동자산",
                        "⦁ 유동자산\n" +
                                "(1) 당좌자산\n" +
                                "- 판매과정을 거치지 않고 1년 이내에 현금으로 바꿀 수 있는 자산입니다.\n" +
                                "  ex) 당좌예금, 선급금, 단기대여금, 매출채권 등"
                ),

                new CardItem("유동자산",
                        "(2) 재고자산\n" +
                                "- 주된 영업활동을 통해 판매할 목적으로 보유하고 있는 자산입니다.\n" +
                                "  ex) 상품, 제품, 재공품, 원재료 등"
                ),

                new CardItem("비유동자산",
                        "⦁ 비유동자산\n" +
                                "(1) 투자자산\n" +
                                "- 기업의 이익을 위하여 투자 목적으로 보통 장기적인 기간동안 보유하고 있는 자산입니다.\n" +
                                "  ex) 장기금융상품, 장기대여금, 투자부동산 등"
                ),

                new CardItem("비유동자산",
                        "(2) 유형자산\n" +
                                "- 영업활동에 사용할 목적으로 보유하고 있는 자산입니다.\n" +
                                "  ex) 토지, 건물, 비품, 차량운반구, 기계장치 등"
                ),

                new CardItem("비유동자산",

                        "(3) 무형자산\n" +
                        "- 영업활동에 사용할 목적으로 보유하는 물리적인 실체가 없는 자산입니다.\n" +
                        "- 기업이 통제 가능하며, 미래 경제적 효익이 있는 자산입니다.\n" +
                        "  ex) 영업권, 산업재산권, 개발비, 소프트웨어 등"
                ),
                new CardItem("부채",
                        "⦁ 부채\n" +
                                "- 과거의 이익으로 인해 발생한 빚과 같은 개념이며, 상환기간(갚아야 하는 기한)에 따라 유동부채와 비유동부채로 나눌 수 있습니다.\n"
                ),
                new CardItem("유동부채",

                        "⦁ 유동부채\n" +
                                "- 1년 이내로 상환기한이 다가온 부채를 의미합니다.\n" +
                                "  ex) 외상매입금, 매입채무, 선수금, 미지급금 등"
                ),
                new CardItem("비유동부채",
                        "⦁ 비유동부채\n" +
                                "- 유동부채와 반대의 개념으로, 1년 이후로 상환기간이 남은 부채를 의미합니다.\n"+
                                "  ex) 임대보증금, 장기차입금, 장기외상매입금, 장기미지급금 등"
                ),
                new CardItem("자본",

                        "⦁ 자본\n" +
                                "- (자산-부채)의 잔액이며, 기업의 순자산을 의미합니다.\n" +
                                "  ex) 자본금, 자본잉여금 등"
                )
        ));

        lessonMap.put("5. 수익, 비용", Arrays.asList(
                new CardItem("수익과 비용",
                        "⦁ 기업의 회계기간(1년) 동안의 수익과 비용을 의미하며, 이는 기말에 손익계산서에 반영되는 중요한 정보입니다."
                ),
                new CardItem("수익",
                        "⦁ 수익\n" +
                                "(1) 매출액\n" +
                                "- 상품 또는 제품의 판매대가로 기업의 주영업활동으로 인한 수익을 의미합니다.\n" +
                                "- 매출액 = 총매출액 - (매출할인+매출환입+매출에누리)"
                ),

                new CardItem("수익",
                        "(2) 영업외수익\n" +
                                "- 주된 영업활동 외의 활동으로 벌어들인 수익의 총합입니다.\n" +
                                "  ex) 이자수익, 배당금수익, 임대료, 유형자산처분이익 등"
                ),

                new CardItem("비용",
                        "⦁ 비용\n" +
                                "(1) 매출원가\n" +
                                "- 판매된 자산의 원가를 의미하며 매출액에 대응되는 비용입니다.\n" +
                                "- 매출원가 = 기초재고원가 + 당기순매입액 - 기말재고액"
                ),

                new CardItem("비용",
                        "(2) 판매비와 관리비\n" +
                                "- 상품과 서비스의 판매 또는 영업활동에 드는 비용입니다.\n" +
                                "  ex) 급여, 복리후생비, 통신비, 세금과공과, 감가상각비, 소모품비 등"
                ),

                new CardItem("비용",
                        "(3) 영업외비용\n" +
                                "- 영업활동 외의 활동에서 발생하는 비용입니다.\n" +
                                "- 대부분 자산으로 인식하지 않고 발생한 기간에 비용으로 인식합니다."
                )
        ));

        lessonMap.put("6. 결산의 이해", Arrays.asList(
                new CardItem("회계의 결산",
                        "⦁ 결산(Closing)이란?\n"+
                                "  - 장부를 마감하는 절차로서 기록한 장부를 정리하여 회계기간 말 기업의 재무상태와 경영성과를 파악하는 것을 말합니다.\n"+
                                "  - 이를 통해 기업의 이해관계자들은 전표나 총계정원장만으로는 알 수 없는 자세한 정보를 파악할 수 있습니다."
                ),
                new CardItem("결산의 절차",
                        "⦁ 결산은 아래와 같은 절차를 통해 진행됩니다.\n" +
                                "(1) 매출액\n" +
                                "- 상품 또는 제품의 판매대가로 기업의 주영업활동으로 인한 수익을 의미합니다.\n" +
                                "- 매출액 = 총매출액 - (매출할인+매출환입+매출에누리)"
                )
        ));

        lessonMap.put("7. 시산표", Arrays.asList(
                new CardItem("시산표",
                        "⦁ 시산표(Trial Balance, T/B)?\n"+
                                "  - 모든 계정의 차변금액과 대변금액을 한곳에 모아 단순 집계한 표입니다.\n"+
                                "  - 시산표의 자기검증기능 : 모든 계정의 차변금액 합계액과 대변금액 합계액은 반드시 일치해야 합니다."
                ),
                new CardItem("시산표의 종류",
                        "⦁ 시산표의 종류\n" +
                                "(1) 합계시산표\n" +
                                "- 각 계정의 차변의 합계를 시산표 차변에, 대변의 합계를 시산표 대변에 기입하는 것 입니다.\n\n" +
                                "(2) 잔액 시산표\n"+
                                "- 각 계정의 차변의 잔액을 시산표 차변에, 대변의 잔액을 시산표 대변에 기입하는 것 입니다."
                )
        ));

        lessonMap.put("8. 결산수정분개", Arrays.asList(
                new CardItem("결산수정분개",
                        "⦁ 결산수정분개의 목적\n"+
                                " (1) 발생주의\n"+
                                "  - 재무제표는 발생주의에 따라 작성해야 합니다. \n 하지만 기업에서는 편의를 위해 현금주의로 기록하기 때문에 결산 시에 발생주의에 맞게 전환할 필요가 있습니다."
                ),
                new CardItem("결산수정분개",
                        "⦁ 결산수정분개의 목적\n" +
                                " (2) 기말 자산, 부채 평가\n" +
                                "  - 기말 현재 자산과 부채를 적정하게 평가하기 위해 결산수정분개를 해야 합니다.\n" +
                                "  - 예를 들어, 자산의 가치하락으로 기업의 가치를 감소시키는 경우 장부 금액을 실제 금액에 맞게 적절하게 수정해야 합니다."
                )
        ));

        lessonMap.put("9. 장부 마감", Arrays.asList(
                new CardItem("마감",
                        "⦁ 마감이란?\n"+
                                "  - 이번 회계연도를 종료하고 다음 회계연도를 계속 기록할 수 있도록 준비하는 절차입니다.\n\n"+
                                "  - 수익과 비용이 집계되는 손익계산서 관련 계정을 먼저 마감하여 당기순이익을 이익잉여금으로 대체한 후, 재무상태표 관련 계정을 마감하는 절차를 거칩니다."
                ),
                new CardItem("손익계정의 마감",
                         "- 현재 회계기간의 수익과 비용이 총계정원장에 남아있으면 다음 회계연도의 수익과 비용에 영향을 미치므로, 수익과 비용을 0으로 만드는 역분개 작업을 통해 수익과 비용을 소멸시켜야 합니다.\n\n" +
                                "- 이때 총계정원장에 임시계정을 만들어 수익과 비용을 한 계정에 모으고 그 차액을 이익잉여금(수익)으로 대체 합니다."
                ),
                new CardItem("재무상태표계정의 마감",

                        "  - 누적된 재무상태를 보여주는 표이기 때문에 손익계산서와 달리 현재 회계기간이 끝나도 잔액을 소멸시키지 않고 이월 시킵니다.\n\n"+
                                "  - 따라서 결산 이후의 이익잉여금에는 손익계정의 마감 시 당기순이익이 반영되어 있습니다."
                )
        ));

        return lessonMap.getOrDefault(lessonTitle,
                Arrays.asList(new CardItem("내용 없음", "선택한 단원에 대한 학습 내용이 없습니다.")));
    }
}

