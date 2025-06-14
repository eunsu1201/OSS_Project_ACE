package com.example.application_ace;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;

public class ToastUtil {
    private static PopupWindow currentPopup;

    public static void showTooltip(Context context, String message, View anchorView) {
        if (currentPopup != null && currentPopup.isShowing()) {
            currentPopup.dismiss();
        }

        // 카드 형태 팝업 구성
        CardView cardView = new CardView(context);
        cardView.setCardElevation(6f);
        cardView.setRadius(16f);

        TextView textView = new TextView(context);
        textView.setText(message);
        textView.setTextSize(16);
        textView.setTextColor(0xFF000000);
        textView.setPadding(40, 40, 40, 40);

        cardView.addView(textView);

        currentPopup = new PopupWindow(cardView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        currentPopup.setOutsideTouchable(false);
        currentPopup.setFocusable(false);

        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        currentPopup.showAtLocation(anchorView, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, location[1] - 180);
    }

    public static void hideTooltip() {
        if (currentPopup != null && currentPopup.isShowing()) {
            currentPopup.dismiss();
        }
    }
}

