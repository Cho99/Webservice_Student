package com.example.webservicestudent;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private int Id;
    private String HoTen;
    private int NamSinh;
    private String DiaChi;

    public SinhVien(int id, String hoTen, int namSinh, String diaChi) {
        Id = id;
        HoTen = hoTen;
        NamSinh = namSinh;
        DiaChi = diaChi;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
