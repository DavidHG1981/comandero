package com.example.proyectodecomandero;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Camarero extends AppCompatActivity {
    Spinner spMesaComanda, spProductoComanda;
    EditText etCantidad, etTotalCuenta;
    Button btnAgregarPro;
    ListView lvMesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camarero);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spMesaComanda = findViewById(R.id.spMesaComanda);
        spProductoComanda= findViewById(R.id.spProductoComanda);
        etCantidad = findViewById(R.id.etCantidad);
        etTotalCuenta = findViewById(R.id.etTotalCuenta);
        btnAgregarPro = findViewById(R.id.btnAgregarPro);
        lvMesas = findViewById(R.id.lvMesas);
        btnAgregarPro.setOnClickListener(v -> {
           Toast.makeText(this, "Con este botón se agregarán los productos a la comanda", Toast.LENGTH_SHORT).show();
        });
        ArrayAdapter<CharSequence> aAMesas = ArrayAdapter.createFromResource(this, R.array.mesas, android.R.layout.simple_spinner_item);
        aAMesas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMesaComanda.setAdapter(aAMesas);

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
}