package com.example.webservicestudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtHoten, txtNamSinh, txtDiaChi;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtHoten = (TextView) view.findViewById(R.id.textviewHoTen);
            holder.txtNamSinh = (TextView) view.findViewById(R.id.textviewNamSinh);
            holder.txtDiaChi = (TextView) view.findViewById(R.id.textviewDiaChi);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageEit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageDelete);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final SinhVien sinhVien = sinhVienList.get(i);
        holder.txtHoten.setText(sinhVien.getHoTen());
        holder.txtNamSinh.setText("Năm Sinh: "+ sinhVien.getNamSinh());
        holder.txtDiaChi.setText(sinhVien.getDiaChi());

        // Bắt sự kiện xóa && sửa dữ liệu

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateSinhVienActivity.class);
                intent.putExtra("dataSinhVien", sinhVien);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               XacNhanXoa(sinhVien.getHoTen(), sinhVien.getId());
            }
        });
        return view;
    }
    private void XacNhanXoa(String ten, final int id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên " + ten + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.Delete(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }
}
