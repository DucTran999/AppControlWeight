package com.ductran.ptit.apptinhcalo.model;

public class Thucpham {

    public int id;
    public String tenThucpham;
    public String protein;
    public String calories;
    public String hinhAnhthucpham;

    public Thucpham(int id, String tenThucpham, String protein, String calories, String hinhAnhthucpham) {
        this.id = id;
        this.tenThucpham = tenThucpham;
        this.protein = protein;
        this.calories = calories;
        this.hinhAnhthucpham = hinhAnhthucpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThucpham() {
        return tenThucpham;
    }

    public void setTenThucpham(String tenThucpham) {
        this.tenThucpham = tenThucpham;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getHinhAnhthucpham() {
        return hinhAnhthucpham;
    }

    public void setHinhAnhthucpham(String hinhAnhthucpham) {
        this.hinhAnhthucpham = hinhAnhthucpham;
    }
}
