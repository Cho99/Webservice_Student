package com.example.webservicestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UpdateSinhVienActivity extends AppCompatActivity {

    int id = 0;
    String url = "http://192.168.1.3/android/update.php";

    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnUpdate, btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);
        


        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");
        Toast.makeText(this, sinhVien.getHoTen(), Toast.LENGTH_SHORT).show();

        AnhXa();
        id = sinhVien.getId();
        edtHoTen.setText(sinhVien.getHoTen());
        edtNamSinh.setText(sinhVien.getNamSinh() + "");
        edtDiaChi.setText(sinhVien.getDiaChi());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edtHoTen.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                if (hoten.matches("") || namsinh.equals("") || diachi.length() == 0) {
                    Toast.makeText(UpdateSinhVienActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    UpdateSinhVien(url);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void UpdateSinhVien(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(UpdateSinhVienActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateSinhVienActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(UpdateSinhVienActivity.this, "Cập nhâth thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("idSV", String.valueOf(id));
                params.put("hotenSV", edtHoTen.getText().toString().trim());
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());
                return params;
            }
        };
         requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtHoTen = (EditText) findViewById(R.id.editTextUpdateName);
        edtNamSinh = (EditText) findViewById(R.id.editTextUpdateNamSinh);
        edtDiaChi = (EditText) findViewById(R.id.editTextUpdateDiaChi);

        btnHuy = (Button) findViewById(R.id.buttonHuy);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
    }
}
