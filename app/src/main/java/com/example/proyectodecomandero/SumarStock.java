package com.example.proyectodecomandero;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SumarStock extends AppCompatActivity {
    Spinner spProducto;
    EditText etStock, etStockSuma;
    Button btnSumaStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sumar_stock);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spProducto = findViewById(R.id.spProducto);
        etStock = findViewById(R.id.etStock);
        etStockSuma = findViewById(R.id.etStockSuma);
        btnSumaStock = findViewById(R.id.btnSumaStock);
        btnSumaStock.setOnClickListener(this::onClick);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.back) {
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btnSumaStock) {
            Toast.makeText(this, "Pondre un método para sumar stock", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}