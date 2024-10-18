package com.example.proyectodecomandero;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Gerente extends AppCompatActivity implements View.OnClickListener {
    Button btnAgregar, btnStock;
    EditText etProdGer, etPrecio, etStockMin, etStockActual;
    private static final int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gerente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAgregar = findViewById(R.id.btnAgregar);
        btnStock = findViewById(R.id.btnStock);
        etProdGer = findViewById(R.id.etProdGer);
        etPrecio = findViewById(R.id.etPrecio);
        etStockMin = findViewById(R.id.etStockMin);
        etStockActual = findViewById(R.id.etStockActual);
        btnAgregar.setOnClickListener(this);
        btnStock.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnAgregar){
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == R.id.btnStock){
            Intent intent = new Intent(this, SumarStock.class);
            startActivity(intent);
            finish();
        }
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
    public int StockRestante(int stockMin, int stockActual) {
        int stockMinim = Integer.parseInt(etStockMin.getText().toString());
        int stockActu = Integer.parseInt(etStockActual.getText().toString());
        String mensaje= "El stock actual del producto "+etProdGer.getText()+"es menor que el stock mínimo";
        // Obtener el número de teléfono del Intent
        String numeroTelefono = getIntent().getStringExtra("telefono");
        if (stockMinim > stockActu) {
            // Verifica si el permiso de SMS ha sido otorgado
            if (checkSmsPermission()) {
                sendSms(numeroTelefono,mensaje );
            } else {
                requestSmsPermission();
            }
        }

        return stockActu - stockMinim;
    }

    private boolean checkSmsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestSmsPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes volver a llamar al método para enviar el SMS
                StockRestante(Integer.parseInt(etStockMin.getText().toString()),
                        Integer.parseInt(etStockActual.getText().toString()));
            } else {
                Toast.makeText(this, "Permiso de SMS denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSms(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS enviado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Fallo al enviar SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}