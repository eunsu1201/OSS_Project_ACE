package com.example.application_ace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PuzzleSelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_select);

        Button btnBalance = findViewById(R.id.btnBalancePuzzle);
        Button btnIncome = findViewById(R.id.btnIncomePuzzle);

        btnBalance.setOnClickListener(v -> {
            Intent intent = new Intent(PuzzleSelectActivity.this, PuzzleBalanceActivity.class);
            startActivity(intent);
        });

        btnIncome.setOnClickListener(v -> {
            Intent intent = new Intent(PuzzleSelectActivity.this, PuzzleIncomeActivity.class);
            startActivity(intent);
        });
    }
}
