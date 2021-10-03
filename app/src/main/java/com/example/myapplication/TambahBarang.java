package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TambahBarang extends AppCompatActivity {

    EditText namaBarang, kdBarang, stokBarang;
    Button tambahBarang;
    DataBaseHelper myDb;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahbarang);

        kdBarang = (EditText) findViewById(R.id.edKdBarang);
        namaBarang = (EditText) findViewById(R.id.edNamaBarang);
        stokBarang = (EditText) findViewById(R.id.edStokBarang);
        tambahBarang = (Button) findViewById(R.id.btnSimpan);
        tambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionSimpan();
            }
        });
        myDb = new DataBaseHelper(this);
    }

    private void actionSimpan() {
        String barang = kdBarang.getText().toString();
        String namabrg = namaBarang.getText().toString();
        String stok = stokBarang.getText().toString();

        Integer stokConvert = Integer.valueOf(stok);

        Boolean result = myDb.insertData(barang, namabrg, stokConvert);
        if (result == true) {
            Toast.makeText(this, "Data Berhasil di Simpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Gagal di Simpan", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}
