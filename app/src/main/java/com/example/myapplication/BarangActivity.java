package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.adapter.BarangAdapter;
import com.example.myapplication.model.BarangView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BarangActivity extends AppCompatActivity {
    ArrayList<BarangView> barangList;
    ListView lvBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        Bundle data = getIntent().getExtras();
        String json = data.getString("dataJson");

        lvBarang = (ListView) findViewById(R.id.lvBarang);
        buildDataBarang(json);
        /*if(json != null){
            buildDataBarang(json);
            adapter = new BarangAdapter(barangList, BarangActivity.this);
            lvBarang.setAdapter(adapter);
        } else {
            Toast.makeText(this, "ada datanya", Toast.LENGTH_SHORT).show();
        }*/
        Button tmbhBarang = (Button) findViewById(R.id.btnTambah);
        tmbhBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BarangActivity.this, TambahBarang.class);
                startActivity(intent);
            }
        });
    }

    private void buildDataBarang(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (barangList == null) {
                barangList = new ArrayList<BarangView>();
            } else {
                barangList.clear();
            }
                BarangView barangView = new BarangView();

                barangView.setIdBarang(jsonObject.getInt("idBarang"));
                barangView.setKdBarang(jsonObject.getString("kdBarang"));
                barangView.setNamaBarang(jsonObject.getString("namaBarang"));
                barangView.setStok(jsonObject.getString("stok"));
                barangList.add(barangView);

                BarangAdapter adapter = new BarangAdapter(barangList, this);
                lvBarang.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}