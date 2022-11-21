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

    public static Hang fromString(String input, String attributeSeparator) {
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
        this.thoiDiemXuat = d;
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
        return this.thoiDiemXuat;
    }

    public boolean equals(Hang hangNhap) {
        boolean result = true;
        if (this.getId() != hangNhap.getId()) {
            result = false;
        }
        if (!(this.getTenHang().toLowerCase().trim().equals(hangNhap.getTenHang().toLowerCase().trim()))) {

            result = false;
        }
        if (!(this.getLoaiHang().toLowerCase().trim().equals(hangNhap.getLoaiHang().toLowerCase().trim()))) {
            result = false;
        }
        if (!(this.getKhoCuaHang().toLowerCase().trim().equals(hangNhap.getKhoCuaHang().toLowerCase().trim()))) {
            result = false;
        }
        if (!(this.getTenNhaSanXuat().toLowerCase().trim().equals(hangNhap.getTenNhaSanXuat().toLowerCase().trim()))) {
            result = false;
        }
        if (!(this.getTenNhaBanLe().toLowerCase().trim().equals(hangNhap.getTenNhaBanLe().toLowerCase().trim()))) {
            result = false;
        }
        if (!(this.getThoiDiemNhap().equals(hangNhap.getThoiDiemNhap()))) {
            result = false;
        }
        if (!(this.getThoiDiemXuat().equals(hangNhap.getThoiDiemXuat()))) {
            result = false;
        }
        return result;
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