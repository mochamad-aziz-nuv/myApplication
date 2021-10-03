package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    LinearLayout ln1;
    Button btnTrans, btnbrg;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ln1 = (LinearLayout) findViewById(R.id.linear1);

        btnTrans = (Button) findViewById(R.id.transaksi);
        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrans = new Intent(MainActivity.this, TransaksiActivity.class);
                startActivity(intentTrans);
            }
        });
        btnbrg = (Button) findViewById(R.id.barang);
        btnbrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadBarang();
            }
        });
        myDb = new DataBaseHelper(this);
    }

    private void loadBarang () {
        Cursor res = myDb.getAllData();
        String dataJson = "";
        StringBuffer sbf = new StringBuffer();
        if(res != null && res.getCount()>0) {
            while (res.moveToNext()) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("idBarang", String.valueOf(res.getString(0)));
                    jsonObject.put("kdBarang", res.getString(1));
                    jsonObject.put("namaBarang", res.getString(2));
                    jsonObject.put("stok", String.valueOf(res.getInt(3)));

                    dataJson = jsonObject.toString();
                }catch (Exception e){

                }
            }
            Intent intent = new Intent(MainActivity.this, BarangActivity.class);
            intent.putExtra("dataJson", dataJson);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Peringatan!!!")
                    .setMessage("Anda Belum Memiliki Barang Silahkan Menambah Barang Terlebih Dahulu")
                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentNull = new Intent(MainActivity.this, TambahBarang.class);
                            startActivity(intentNull);
                        }
                    })
                    .show();
        }

    }


}