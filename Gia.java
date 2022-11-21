import java.util.ArrayList;
import java.util.Arrays;

public class Gia {
    private String tenHang;
    private String loaiHang;
    private double gia;

    public Gia(String tenHang, String loaiHang, double gia) {
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.gia = gia;
    }

    public Gia() {

    };

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getGia() {
        return this.gia;
    }

    public String getLoaiHang() {
        return this.loaiHang;
    }

    public String getTenHang() {
        return this.tenHang;
    }

    public String toString(String attributeSeparator) {
        return String.format("%s%s%s%s%s", this.tenHang, attributeSeparator, this.loaiHang, attributeSeparator,
                Double.toString(this.gia));
    }

    public static Gia fromString(String input, String attributeSeparator) {
        ArrayList<String> attributes = new ArrayList<String>(Arrays.asList(input.split(attributeSeparator)));
        Gia gia = new Gia();
        gia.setTenHang(attributes.get(0));
        gia.setLoaiHang(attributes.get(1));
        gia.setGia(Double.valueOf(attributes.get(2)));
        return gia;
    }

    public boolean equals(Gia giaNhap) {
        boolean result = true;
        if (!giaNhap.getTenHang().toLowerCase().trim().equals(this.getTenHang().toLowerCase().trim())) {
            result = false;
        }
        if (!giaNhap.getLoaiHang().toLowerCase().trim().equals(this.getLoaiHang().toLowerCase().trim())) {
            result = false;
        }
        if (giaNhap.getGia() != this.getGia()) {
            result = false;
        }
        return result;
    }
}
