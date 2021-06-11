package com.ductran.ptit.apptinhcalo.Toolbar;

public class ItemMenu {
    public String tenItem;
    public int icon;

    public ItemMenu(String tenItem, int icon) {
        this.tenItem = tenItem;
        this.icon = icon;
    }

    public String getTenItem() {
        return tenItem;
    }

    public void setTenItem(String tenItem) {
        this.tenItem = tenItem;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
