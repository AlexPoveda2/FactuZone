package com.example.practicafactuzone;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddRecordActivity extends AppCompatActivity {

    private EditText etTitle, etDate, etPrice;
    private Button btnSaveRecord;
    private ImageView ivPreview;

    private FirebaseFirestore firestore;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        etTitle = findViewById(R.id.etTitle);
        etDate = findViewById(R.id.etDate);
        etPrice = findViewById(R.id.etPrice);
        btnSaveRecord = findViewById(R.id.btnSaveRecord);
        ivPreview = findViewById(R.id.ivPreview);

        firestore = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();

        // Selección de fecha
        etDate.setOnClickListener(v -> showDatePicker());

        // Guardar registro
        btnSaveRecord.setOnClickListener(v -> saveRecord());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            etDate.setText(date);
        }, year, month, day).show();
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }

    private void saveRecord() {
        String title = etTitle.getText().toString();
        String date = etDate.getText().toString();
        String price = etPrice.getText().toString();

        if (title.isEmpty() || date.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(this,"fug", Toast.LENGTH_SHORT).show();
        }
    }
}
