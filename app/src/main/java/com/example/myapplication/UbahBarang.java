package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class UbahBarang extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText edkdBarang, edNamaBarang, edStok;
    Button ubahData, batal;
    Integer id_barang = 0;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahbarang);

        Bundle data = getIntent().getExtras();
        String json = data.getString("dataJson");
        
        buildData(json);

        edkdBarang = (EditText) findViewById(R.id.etKdBarang);
        edkdBarang.setEnabled(true);
        edNamaBarang = (EditText) findViewById(R.id.etNamaBarang);
        edStok = (EditText) findViewById(R.id.etStokBarang);
        
        ubahData = (Button) findViewById(R.id.btnUbah);
        ubahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesUbahdata();
            }
        });
        batal = (Button) findViewById(R.id.btnBatal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mydb = new DataBaseHelper(this);
    }

    private void prosesUbahdata() {
        Integer idBarang = id_barang;
        String kode_barang = edkdBarang.getText().toString();
        String nama_barang = edNamaBarang.getText().toString();
        String stok = edStok.getText().toString();

        Integer stokConvert = Integer.valueOf(stok);

        Boolean result = mydb.updateData(idBarang, kode_barang, nama_barang, stokConvert);
        if(result == true) {
            Toast.makeText(this, "Data Berhasil di Perbarui", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Gagal di Perbarui", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            edkdBarang.setText(jsonObject.getString("kode_barang"));
            edNamaBarang.setText(jsonObject.getString("nama_barang"));
            edStok.setText(jsonObject.getInt("stok"));
            id_barang = jsonObject.getInt("id_barang");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
