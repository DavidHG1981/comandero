package com.example.proyectodecomandero;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView fecha_hora;
    Button btnGerente, btnCamarero, btnTelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnGerente = findViewById(R.id.btnGerente);
        btnCamarero = findViewById(R.id.btnCamarero);
        btnTelefono = findViewById(R.id.btnTelefono);
        btnGerente.setOnClickListener(this);
        btnCamarero.setOnClickListener(this);
        btnTelefono.setOnClickListener(this);
        fecha_hora = findViewById(R.id.fecha_hora);
        // Obtener fecha y hora actual
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);

        // Mostrar la fecha y hora en el TextView
        fecha_hora.setText(formattedDateTime);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnGerente) {
            Intent i = new Intent(MainActivity.this, Gerente.class);
            startActivity(i);
        }
        if (view.getId() == R.id.btnCamarero) {
            Intent i = new Intent(MainActivity.this, Camarero.class);
            startActivity(i);


        }
        if(view.getId() == R.id.btnTelefono){
            Intent i = new Intent(MainActivity.this, Telefono.class);
            startActivity(i);

        }
    }

}