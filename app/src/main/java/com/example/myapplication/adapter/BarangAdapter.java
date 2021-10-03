package com.example.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBaseHelper;
import com.example.myapplication.R;
import com.example.myapplication.UbahBarang;
import com.example.myapplication.model.BarangView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BarangAdapter extends BaseAdapter {
    private List<BarangView> barangList;
    private Context context;
    DataBaseHelper myDb;
    BarangView barangView;

    public BarangAdapter(ArrayList<BarangView> barangViewList, Context context) {
        this.barangList = barangViewList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return barangList.size();
    }

    @Override
    public Object getItem(int position) {
        return barangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return barangList.get(position).getIdBarang();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemLayout;
        barangView = barangList.get(position);
        myDb = new DataBaseHelper(context);

        itemLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.barang_row, parent, false);
        TextView namaBarang = (TextView) itemLayout.findViewById(R.id.headerRow);
        TextView kdBarang = (TextView) itemLayout.findViewById(R.id.kdBarangRow);
        TextView stokBarang = (TextView) itemLayout.findViewById(R.id.stokRow);
        Button btnEdit = (Button) itemLayout.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahdata();
            }
        });
        Button btnHapus = (Button) itemLayout.findViewById(R.id.btnHapus);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Hapus Data")
                        .setMessage("Apakah Yakin Ingin Menghapus Data Ini ?")
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hapusdata();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
            }
        });

        namaBarang.setText(barangView.getNamaBarang());
        kdBarang.setText(barangView.getKdBarang());
        stokBarang.setText("Stok : "+barangView.getStok());
        return itemLayout;
    }

    private void hapusdata() {
        String id = barangView.getIdBarang().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(context, result+" Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    private void ubahdata() {
        String dataJson = "";
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id_barang", barangView.getIdBarang());
            jsonObject.put("kode_barang", barangView.getKdBarang());
            jsonObject.put("nama_barang", barangView.getNamaBarang());
            jsonObject.put("stok", barangView.getStok());

            dataJson = jsonObject.toString();
        } catch (Exception e) {

        }
        Intent intent = new Intent(context, UbahBarang.class);
        intent.putExtra("dataJson", dataJson);
        context.startActivity(intent);
    }
}
