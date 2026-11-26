package com.example.squad10;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MortarActivity extends AppCompatActivity {

    // {прицел в mils, дальность в м} — внутриигровая таблица Squad (ровная земля)
    private static final double[][] TABLE = {
            {1579,   50}, {1558,  100}, {1538,  150}, {1517,  200}, {1496,  250},
            {1475,  300}, {1453,  350}, {1431,  400}, {1409,  450}, {1387,  500},
            {1364,  550}, {1341,  600}, {1317,  650}, {1292,  700}, {1267,  750},
            {1240,  800}, {1212,  850}, {1183,  900}, {1152,  950}, {1118, 1000},
            {1081, 1050}, {1039, 1100}, { 988, 1150}, { 918, 1200}, { 800, 1250}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortar);

        EditText etDistance = findViewById(R.id.etDistance);
        EditText etHeight = findViewById(R.id.etHeight);
        TextView tvResult = findViewById(R.id.tvResult);

        findViewById(R.id.btnCalc).setOnClickListener(v -> {
            double dist = parse(etDistance.getText().toString());
            double height = parse(etHeight.getText().toString());
            if (Double.isNaN(height)) height = 0; // TODO: поправка на высоту — следующий шаг

            if (Double.isNaN(dist) || dist <= 0) {
                tvResult.setText("Введи дистанцию");
                return;
            }
            double mils = rangeToMils(dist);
            if (Double.isNaN(mils)) {
                tvResult.setText("Цель вне досягаемости (50–1250 м)");
                return;
            }
            tvResult.setText(String.format(Locale.US,
                    "Прицел: %.0f\nДистанция: %.0f м", Math.round(mils), dist));
        });
    }

    private static double parse(String s) {
        try { return Double.parseDouble(s.replace(',', '.').trim()); }
        catch (Exception e) { return Double.NaN; }
    }

    private static double rangeToMils(double range) {
        if (range < TABLE[0][1] || range > TABLE[TABLE.length - 1][1]) return Double.NaN;
        for (int i = 1; i < TABLE.length; i++) {
            double r0 = TABLE[i - 1][1], r1 = TABLE[i][1];
            if (range <= r1) {
                double m0 = TABLE[i - 1][0], m1 = TABLE[i][0];
                return m0 + (m1 - m0) * (range - r0) / (r1 - r0);
            }
        }
        return Double.NaN;
    }
}