import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class GiaoDich {
    private String tenHang;
    private String loaiHang;
    private String nguoiMua;
    private String nguoiBan;
    private int soLuong;
    private Date thoiDiemGiaoDich;

    public GiaoDich() {

    };

    public GiaoDich(String tenHang, String loaiHang, String nguoiMua, String nguoiBan, int soLuong,
            Date thoiDiemGiaoDich) {
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.nguoiMua = nguoiMua;
        this.nguoiBan = nguoiBan;
        this.soLuong = soLuong;
        this.thoiDiemGiaoDich = thoiDiemGiaoDich;
    }

    public String getTenHang() {
        return this.tenHang;
    }

    public String getLoaiHang() {
        return this.loaiHang;
    }

    public int getSoLuong() {
        return this.soLuong;
    }

    public String getNguoiMua() {
        return this.nguoiMua;
    }

    public String getNguoiBan() {
        return this.nguoiBan;
    }

    public Date getThoiDiemGiaoDich() {
        return this.thoiDiemGiaoDich;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNguoiMua(String nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

    public void setNguoiBan(String nguoiBan) {
        this.nguoiBan = nguoiBan;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public void setThoiDiemGiaoDich(Date thoiDiemGiaoDich) {
        this.thoiDiemGiaoDich = thoiDiemGiaoDich;
    }

    public static GiaoDich fromString(String input, String attributeSeparator) {
        GiaoDich giaoDich = new GiaoDich();
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList(input.split(attributeSeparator)));
        giaoDich.setTenHang(arr.get(0));
        giaoDich.setLoaiHang(arr.get(1));
        giaoDich.setSoLuong(Integer.parseInt(arr.get(2)));
        giaoDich.setNguoiMua(arr.get(3));
        giaoDich.setNguoiBan(arr.get(4));
        try {
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date date = parser.parse(arr.get(5));
            giaoDich.setThoiDiemGiaoDich(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return giaoDich;
    }

    public String toString(String attributeSeparator) {
        return String.format("%s%s%s%s%s%s%s%s%s%s%s", this.tenHang, attributeSeparator,
                this.loaiHang, attributeSeparator, String.valueOf(this.soLuong), attributeSeparator,
                this.nguoiMua, attributeSeparator, this.nguoiBan, attributeSeparator, this.thoiDiemGiaoDich.toString());
    }

    public boolean equals(GiaoDich giaoDichNhap) {
        boolean result = true;
        if (!giaoDichNhap.getTenHang().toLowerCase().trim().equals(this.getTenHang().toLowerCase().trim())) {
            result = false;
        }
        if (!giaoDichNhap.getLoaiHang().toLowerCase().trim().equals(this.getLoaiHang().toLowerCase().trim())) {
            result = false;
        }
        if (giaoDichNhap.getSoLuong() != this.getSoLuong()) {
            result = false;
        }
        if (!giaoDichNhap.getThoiDiemGiaoDich().equals(this.getThoiDiemGiaoDich())) {
            result = false;

        }
        if (!giaoDichNhap.getNguoiMua().toLowerCase().trim().equals(this.getNguoiMua().toLowerCase().trim())) {
            result = false;
        }
        if (!giaoDichNhap.getNguoiBan().toLowerCase().trim().equals(this.getNguoiBan().toLowerCase().trim())) {
            result = false;
        }
        return result;
    }
}
