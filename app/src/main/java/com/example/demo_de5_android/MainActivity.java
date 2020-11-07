package com.example.demo_de5_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_name, edt_phone, edt_address, edt_order, edt_receive;
    private Button btn_order, btn_receive, btn_buy;
    private Date dateOrder, dateReceive;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edt_name = findViewById(R.id.edtName);
        edt_phone = findViewById(R.id.edtPhone);
        edt_address = findViewById(R.id.edtAddress);
        edt_order = findViewById(R.id.edtOrder);
        edt_receive = findViewById(R.id.edtReceive);
        btn_order = findViewById(R.id.btnOrder);
        btn_receive = findViewById(R.id.btnReceive);
        btn_order.setOnClickListener(this);
        btn_receive.setOnClickListener(this);
        btn_buy = findViewById(R.id.btnBuy);
        btn_buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOrder:
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                try {
                                    month++;
                                    dateOrder = dateFormat.parse(day + "/" + month + "/" + year);
                                    edt_order.setText(day + "/" + month + "/" + year);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                break;
            case R.id.btnReceive:
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                try {
                                    month++;
                                    dateReceive = dateFormat.parse(day + "/" + month + "/" + year);
                                    edt_receive.setText(day + "/" + month + "/" + year);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                break;
            case R.id.btnBuy:
                long hieu = dateReceive.getTime() - dateOrder.getTime();
                int day = (int) TimeUnit.DAYS.convert(hieu, TimeUnit.MILLISECONDS);
                Toast.makeText(this, day+"", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}