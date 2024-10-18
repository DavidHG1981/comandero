package com.example.proyectodecomandero;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Telefono extends AppCompatActivity implements View.OnClickListener {
    EditText etTelefono, etTfnoGuardado;
    Button btnModificarTfno, btnEnviarTfno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telefono);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etTelefono = findViewById(R.id.etTelefono);
        etTfnoGuardado = findViewById(R.id.etTfnoGuardado);
        btnModificarTfno = findViewById(R.id.btnModificarTfno);
        btnEnviarTfno = findViewById(R.id.btnEnviarTfno);
        btnModificarTfno.setOnClickListener(this);
        btnEnviarTfno.setOnClickListener(this);
        etTelefono.setEnabled(false);


        etTfnoGuardado.setText(etTelefono.getText());

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

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnModificarTfno){
            etTelefono.setEnabled(true);

        }
        if(view.getId() == R.id.btnEnviarTfno) {
            etTfnoGuardado.setText(etTelefono.getText());
            Intent i = new Intent(this, Gerente.class);
            i.putExtra("telefono", etTelefono.getText().toString());
            startActivity(i);
            finish();


        }

    }
}