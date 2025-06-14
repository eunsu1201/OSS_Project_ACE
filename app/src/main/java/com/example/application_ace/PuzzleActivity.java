package com.example.application_ace;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PuzzleActivity extends AppCompatActivity {

    TextView item1, item2, item3;
    TextView dropAsset, dropLiability, dropEquity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        // 드래그 항목
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);

        // 드롭 대상
        dropAsset = findViewById(R.id.dropAsset);
        dropLiability = findViewById(R.id.dropLiability);
        dropEquity = findViewById(R.id.dropEquity);

        // 드래그 시작 이벤트 등록
        setDragListener(item1);
        setDragListener(item2);
        setDragListener(item3);

        // 드롭 영역에 리스너 등록
        setDropTarget(dropAsset);
        setDropTarget(dropLiability);
        setDropTarget(dropEquity);
    }

    private void setDragListener(TextView view) {
        view.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item(view.getText());
            ClipData dragData = new ClipData(
                    view.getText(),
                    new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                    item);

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            v.startDragAndDrop(dragData, shadowBuilder, v, 0);
            return true;
        });
    }

    private void setDropTarget(TextView target) {
        target.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(0xFFE0E0E0);
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(0xFFEEEEEE);
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    ((TextView) v).setText(item.getText());
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(0xFFEEEEEE);
                    return true;

                default:
                    return false;
            }
        });
    }
}
