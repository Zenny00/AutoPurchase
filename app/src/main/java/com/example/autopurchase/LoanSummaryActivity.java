package com.example.autopurchase;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoanSummaryActivity extends Activity {
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loansummary_layout);

        TextView monthlyPayET = (TextView) findViewById(R.id.textView2);
        TextView loanReportET = (TextView) findViewById(R.id.textView3);

        //Setup action listener
        finish = (Button) findViewById(R.id.button1);
        finish.setOnClickListener(finishListener);

        //Get passed data
        Intent intent = getIntent();

        String report;
        report = intent.getStringExtra("LoanReport");

        String monthlyPay;
        monthlyPay = intent.getStringExtra("MonthlyPayment");
        monthlyPayET.setText(monthlyPay);
        loanReportET.setText(report);
    }

    private View.OnClickListener finishListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}