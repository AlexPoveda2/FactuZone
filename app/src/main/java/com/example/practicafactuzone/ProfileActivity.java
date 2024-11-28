package com.example.practicafactuzone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InvoiceAdapter adapter;
    private List<Invoice> invoiceList;
    private FirebaseFirestore db;
    private Button btnLogout;
    private Button btnAddInvoice;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.recyclerViewInvoices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        invoiceList = new ArrayList<>();
        adapter = new InvoiceAdapter(invoiceList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        loadInvoices();

        btnAddInvoice = findViewById(R.id.btnAddInvoice);
        btnLogout = findViewById(R.id.btnLogout);

        btnAddInvoice.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AddRecordActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            auth.signOut(); // Sign out the user
            Toast.makeText(ProfileActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            // Navigate back to login screen
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear the activity stack
            startActivity(intent);
            finish(); // Finish the current activity
        });
    }

    private void loadInvoices() {
        db.collection("invoices")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String date = document.getString("date");
                            String amount = document.getString("amount");
                            invoiceList.add(new Invoice(date, amount));
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ProfileActivity.this, "Error al cargar las facturas: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
