package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String DESC_KEY = "description";
    public static final String YEAR_KEY = "year";
    public static final String MONTH_KEY = "month";
    public static final String DAY_KEY = "day";
    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etDescription;
    private DatePicker dpBirthday;
    private Button btNext;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDescription = findViewById(R.id.etDescription);
        dpBirthday = findViewById(R.id.dpBirthday);
        btNext = findViewById(R.id.btNext);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle extras = intent.getExtras();

            if (extras != null) {
                loadData(extras.getString(NAME_KEY, ""),
                        extras.getString(PHONE_KEY, ""),
                        extras.getString(EMAIL_KEY, ""),
                        extras.getString(DESC_KEY, ""),
                        extras.getInt(YEAR_KEY, year),
                        extras.getInt(MONTH_KEY, month),
                        extras.getInt(DAY_KEY, day));
            }
        }
    }

    private void loadData(String name, String phone, String email, String description,
                          int year, int month, int day) {
        etName.setText(name);
        etPhone.setText(phone);
        etEmail.setText(email);
        etDescription.setText(description);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    protected void onStart() {
        super.onStart();
        btNext.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra(NAME_KEY, etName.getText().toString());
            i.putExtra(PHONE_KEY, etPhone.getText().toString());
            i.putExtra(EMAIL_KEY, etEmail.getText().toString());
            i.putExtra(DESC_KEY, etDescription.getText().toString());
            i.putExtra(YEAR_KEY, year);
            i.putExtra(MONTH_KEY, month);
            i.putExtra(DAY_KEY, day);
            startActivity(i);
            finish();
        });

        dpBirthday.init(year, month, day, (view, year, monthOfYear, dayOfMonth) -> {
            MainActivity.this.year = year;
            MainActivity.this.month = monthOfYear;
            MainActivity.this.day = dayOfMonth;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        btNext.setOnClickListener(null);
    }
}