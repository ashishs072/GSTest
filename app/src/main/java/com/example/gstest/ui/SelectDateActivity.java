package com.example.gstest.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.gstest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class SelectDateActivity extends Activity implements View.OnClickListener {
    private RadioButton specificDateButton;
    private RadioButton dateRangeButton;
    private TextView specificDate;
    private TextView dateFrom;
    private TextView dateTo;
    private FloatingActionButton saveDateBtn;
    DatePickerDialog datePickerDialog;
    private View dateFromLine, dateToLine, specificLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);
        setupUI();
    }

    private void setupUI() {
        specificDateButton = findViewById(R.id.specific_date);
        dateRangeButton = findViewById(R.id.date_range);
        specificDate = findViewById(R.id.tvSpecificDate);
        dateFrom = findViewById(R.id.tvDateFrom);
        dateTo = findViewById(R.id.tvDateTo);
        dateFromLine = findViewById(R.id.dateFromLine);
        dateToLine = findViewById(R.id.dateToLine);
        specificLine = findViewById(R.id.specificDateLine);
        saveDateBtn = findViewById(R.id.saveDateButton);
        saveDateBtn.setOnClickListener(this);
        dateFrom.setOnClickListener(this);
        dateTo.setOnClickListener(this);
        specificDate.setOnClickListener(this);
        specificDateButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dateRangeButton.setChecked(false);
                    specificDate.setVisibility(View.VISIBLE);
                    specificLine.setVisibility(View.VISIBLE);
                    dateFrom.setVisibility(View.GONE);
                    dateTo.setVisibility(View.GONE);
                    dateFrom.setText("");
                    dateTo.setText("");
                    dateFromLine.setVisibility(View.GONE);
                    dateToLine.setVisibility(View.GONE);
                }
            }
        });
        dateRangeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    specificDateButton.setChecked(false);
                    specificDate.setVisibility(View.GONE);
                    specificDate.setText("");
                    specificLine.setVisibility(View.GONE);
                    dateFrom.setVisibility(View.VISIBLE);
                    dateTo.setVisibility(View.VISIBLE);
                    dateFromLine.setVisibility(View.VISIBLE);
                    dateToLine.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvDateFrom:
                selectDateFromDatePicker("DateFrom");
                break;
            case R.id.tvDateTo:
                selectDateFromDatePicker("DateTo");
                break;
            case R.id.tvSpecificDate:
                selectDateFromDatePicker("SpecificDate");
                break;
            case R.id.saveDateButton:
                goToMainActivity();
                break;
        }
    }

    private void selectDateFromDatePicker(String dateType) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the textview
                        if(dateType.equals("SpecificDate")){
                            specificDate.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else if(dateType.equals("DateFrom")){
                            dateFrom.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {
                            dateTo.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);
                        }


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void goToMainActivity() {
        if(!specificDate.getText().equals("") ){
            mainScreenNavigation();
        } else if (!dateFrom.getText().toString().equals("") && !dateTo.getText().toString().equals("") ){
            mainScreenNavigation();
        } else{
            Toast.makeText(this,"Please select proper date to see the AOPD for specific date or date range",Toast.LENGTH_LONG).show();
        }

    }

    private void mainScreenNavigation(){
        Intent intent = new Intent(this,MainActivity.class);
        if(!specificDate.getText().equals("")){
            intent.putExtra("SpecificDate",specificDate.getText().toString());
        } else {
            intent.putExtra("DateFrom",dateFrom.getText().toString());
            intent.putExtra("DateTo",dateTo.getText().toString());
        }
        startActivity(intent);
    }

}
