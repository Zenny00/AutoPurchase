package com.example.autopurchase;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;

public class PurchaseActivity extends Activity {
    //Private data members

    //Model
    private Auto mAuto;

    //Not private, this data will be passed to the loan activity
    String loanReport;
    String monthlyPayment;

    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        //Get references to view
        carPriceET = (EditText) findViewById(R.id.editText1);
        downPayET = (EditText) findViewById(R.id.editText2);
        loanTermRG = (RadioGroup) findViewById(R.id.radioGroup1);

        start = (Button) findViewById(R.id.button1);
        start.setOnClickListener(startListener);

        //Create an auto object
        mAuto = new Auto();
    }

    //Collect data from the view
    private void collectAutoInputData()
    {
        mAuto.setDownPayment((double) Integer.valueOf(downPayET.getText().toString()));
        mAuto.setPrice( (double) Integer.valueOf(carPriceET.getText().toString()) );

        Integer radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton) findViewById(radioId);
        mAuto.setLoanTerm(term.getText().toString());
    }

    //Build report
    private void buildLoanReport()
    {
        Resources res = PurchaseActivity.this.getResources();

        monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f", mAuto.monthlyPayment());
        loanReport = res.getString(R.string.report_line6) + String.format("%10.02f", mAuto.getPrice());
        loanReport += res.getString(R.string.report_line7) + String.format("%10.02f", mAuto.getDownPayment());
        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.taxAmount());
        loanReport += res.getString(R.string.report_line10) + String.format("%18.02f", mAuto.totalCost());
        loanReport += res.getString(R.string.report_line11) + String.format("%12.02f", mAuto.borrowedAmount());
        loanReport += res.getString(R.string.report_line12) + String.format("12.02f", mAuto.interestAmount());

        loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + mAuto.getLoanTerm() + " years.";
        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);
    }

    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            collectAutoInputData();
            buildLoanReport();

            Intent launchReport = new Intent(com.example.autopurchase.PurchaseActivity.this, com.example.autopurchase.LoanSummaryActivity.class);

            launchReport.putExtra("LoanReport", loanReport);
            launchReport.putExtra("MonthlyPayment", monthlyPayment);

            startActivity(launchReport);
        }
    };
}