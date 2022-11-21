import java.lang.System;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hang {
    private long Id;
    private String tenHang = "";
    private String loaiHang = "";
    private String khoCuaHang = "";
    private String tenNhaSanXuat = "";
    private String tenNhaBanLe = "";
    private Date thoiDiemNhap;
    private Date thoiDiemXuat;

    // Constructor
    public Hang() {
        this.Id = System.currentTimeMillis();
    }

    public Hang(String tenHang, String khoCuaHang) {
        this.Id = System.currentTimeMillis();
        this.tenHang = tenHang;
        this.khoCuaHang = khoCuaHang;

    }

    public Hang(String tenHang, String khoCuaHang, String tenNhaSanXuat) {
        this.Id = System.currentTimeMillis();
        this.tenHang = tenHang;
        this.khoCuaHang = khoCuaHang;
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public Hang(String tenHang, String loaiHang, String khoCuaHang, String tenNhaSanXuat) {
        this.Id = System.currentTimeMillis();
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.khoCuaHang = khoCuaHang;
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public Hang(String tenHang, String loaiHang, String khoCuaHang, String tenNhaSanXuat, String tenNhaBanLe) {
        this.Id = System.currentTimeMillis();
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.khoCuaHang = khoCuaHang;
        this.tenNhaBanLe = tenNhaBanLe;
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public Hang(long Id, String tenHang, String loaiHang, String khoCuaHang, String tenNhaSanXuat, String tenNhaBanLe) {
        this.Id = Id;
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.khoCuaHang = khoCuaHang;
        this.tenNhaBanLe = tenNhaBanLe;
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public static Hang taoHang(String input, String attributeSeparator) {
        String[] mangCacThuocTinh = input.split(attributeSeparator);
        Hang newHang = new Hang();
        try {
            newHang.setId(Long.valueOf(mangCacThuocTinh[0]));
            newHang.setTenHang(mangCacThuocTinh[1]);
            newHang.setLoaiHang(mangCacThuocTinh[2]);
            newHang.setKhoCuaHang(mangCacThuocTinh[3]);
            newHang.setTenNhaSanXuat(mangCacThuocTinh[4]);
            newHang.setTenNhaBanLe(mangCacThuocTinh[5]);
            newHang.setThoiDiemNhap((new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")).parse(mangCacThuocTinh[6]));
            newHang.setThoiDiemXuat((new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")).parse(mangCacThuocTinh[7]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newHang;
    }

    // Set operations
    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public void setKhoCuaHang(String khoCuaHang) {
        this.khoCuaHang = khoCuaHang;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public void setTenNhaBanLe(String tenNhaBanLe) {
        this.tenNhaBanLe = tenNhaBanLe;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public void setThoiDiemNhap(Date d) {
        this.thoiDiemNhap = d;
    }

    public void setThoiDiemXuat(Date d) {
        this.thoiDiemNhap = d;
    }

    // get oprations
    public String getTenHang() {
        return this.tenHang;
    }

    public String getLoaiHang() {
        return this.loaiHang;
    }

    public String getKhoCuaHang() {
        return this.khoCuaHang;
    }

    public String getTenNhaSanXuat() {
        return this.tenNhaSanXuat;
    }

    public String getTenNhaBanLe() {
        return this.tenNhaBanLe;
    }

    public long getId() {
        return this.Id;
    }

    public Date getThoiDiemNhap() {
        return this.thoiDiemNhap;
    }

    public Date getThoiDiemXuat() {
        return this.thoiDiemNhap;
    }

    public boolean equals(Hang h) {
        if (this.Id != h.Id) {
            return false;
        }
        if (!(this.tenHang.toLowerCase().trim().equals(h.tenHang))) {

            return false;
        }
        if (!(this.loaiHang.toLowerCase().trim().equals(h.loaiHang))) {
            return false;
        }
        if (!(this.khoCuaHang.toLowerCase().trim().equals(h.khoCuaHang))) {
            return false;
        }
        if (!(this.tenNhaSanXuat.toLowerCase().trim().equals(h.tenNhaSanXuat))) {
            return false;
        }
        if (!(this.tenNhaBanLe.toLowerCase().trim().equals(h.tenNhaBanLe))) {
            return false;
        }
        if (!(this.thoiDiemNhap.equals(h.thoiDiemNhap))) {
            return false;
        }
        if (!(this.thoiDiemXuat.equals(h.thoiDiemXuat))) {
            return false;
        }
        return true;
    }

    public boolean equalsOne(Hang h) {
        if (this.Id == h.Id) {
            return true;
        }
        if (this.tenHang.equals(h.tenHang)) {
            return true;
        }
        if (this.loaiHang.equals(h.loaiHang)) {
            return true;
        }
        if (this.khoCuaHang.equals(h.khoCuaHang)) {
            return true;
        }
        if (this.tenNhaSanXuat.equals(h.tenNhaSanXuat)) {
            return true;
        }
        if (this.tenNhaBanLe.equals(h.tenNhaBanLe)) {
            return true;
        }
        if (this.thoiDiemNhap.equals(h.thoiDiemNhap)) {
            return true;
        }
        if (this.thoiDiemNhap.equals(h.thoiDiemXuat)) {
            return true;
        }
        return false;
    }

    public String toString(String attributeSeparator) {
        return String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", this.Id, attributeSeparator,
                this.tenHang, attributeSeparator,
                this.loaiHang, attributeSeparator,
                this.khoCuaHang, attributeSeparator,
                this.tenNhaSanXuat, attributeSeparator,
                this.tenNhaBanLe, attributeSeparator,
                this.thoiDiemNhap.toString(), attributeSeparator,
                this.thoiDiemXuat.toString());
    }
}