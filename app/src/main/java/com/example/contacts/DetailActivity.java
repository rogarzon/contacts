package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.contacts.MainActivity.DAY_KEY;
import static com.example.contacts.MainActivity.DESC_KEY;
import static com.example.contacts.MainActivity.EMAIL_KEY;
import static com.example.contacts.MainActivity.MONTH_KEY;
import static com.example.contacts.MainActivity.NAME_KEY;
import static com.example.contacts.MainActivity.PHONE_KEY;
import static com.example.contacts.MainActivity.YEAR_KEY;

public class DetailActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvDescription;
    private TextView tvBirthday;
    private Button btEdit;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescription = findViewById(R.id.tvDescription);
        tvBirthday = findViewById(R.id.tvBirthday);
        btEdit = findViewById(R.id.btEdit);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            loadData(extras.getString(NAME_KEY, ""),
                    extras.getString(PHONE_KEY, ""),
                    extras.getString(EMAIL_KEY, ""),
                    extras.getString(DESC_KEY, ""),
                    extras.getInt(YEAR_KEY, year),
                    extras.getInt(MONTH_KEY, month),
                    extras.getInt(DAY_KEY, day));
        }
    }

    private void loadData(String name, String phone, String email, String description,
                          int year, int month, int day){
        tvName.setText(name);
        tvPhone.setText(phone);
        tvEmail.setText(email);
        tvDescription.setText(description);
        tvBirthday.setText(String.format("%d/%d/%d", day, month, year));
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    protected void onStart() {
        super.onStart();
        btEdit.setOnClickListener(v -> {
            Intent i  = new Intent(DetailActivity.this, MainActivity.class);
            i.putExtra(NAME_KEY, tvName.getText().toString());
            i.putExtra(PHONE_KEY, tvPhone.getText().toString());
            i.putExtra(EMAIL_KEY, tvEmail.getText().toString());
            i.putExtra(DESC_KEY, tvDescription.getText().toString());
            i.putExtra(YEAR_KEY, year);
            i.putExtra(MONTH_KEY, month);
            i.putExtra(DAY_KEY, day);
            startActivity(i);
            finish();
        });
    }
}