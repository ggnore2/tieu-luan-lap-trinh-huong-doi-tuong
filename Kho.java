import java.util.ArrayList;
import java.util.Arrays;

public class Kho {
    private String tenKho;
    private int soLuongHangTrongKho;
    private int gioiHanCuaKho;

    public Kho() {

    }

    public Kho(String tenKho, int soLuongHangTrongKho, int gioiHanCuaKho) {
        this.tenKho = tenKho;
        this.soLuongHangTrongKho = soLuongHangTrongKho;
        this.gioiHanCuaKho = gioiHanCuaKho;
    }

    public String getTenKho() {
        return this.tenKho;
    }

    public int getGioiHanCuaKho() {
        return this.gioiHanCuaKho;
    }

    public int getSoLuongHangTrongKho() {
        return this.soLuongHangTrongKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public void setSoLuongHangTrongKho(int soLuongHangTrongKho) {
        this.soLuongHangTrongKho = soLuongHangTrongKho;
    }

    public void setGioiHanCuaKho(int gioiHanCuaKho) {
        this.gioiHanCuaKho = gioiHanCuaKho;
    }

    public boolean equals(Kho khoNhap) {
        boolean result = true;
        if (!this.getTenKho().toLowerCase().trim().equals(khoNhap.getTenKho().toLowerCase().trim())) {
            result = false;
        }
        if (this.getSoLuongHangTrongKho() != khoNhap.getSoLuongHangTrongKho()) {
            result = false;
        }
        if (this.getGioiHanCuaKho() != khoNhap.getGioiHanCuaKho()) {
            result = false;
        }
        return result;
    }

    public static Kho fromString(String input, String attributeSeparator) {
        ArrayList<String> al = new ArrayList<String>(Arrays.asList(input.split(attributeSeparator)));
        Kho khoMoi = new Kho(al.get(0), Integer.valueOf(al.get(1)), Integer.valueOf(al.get(2)));
        return khoMoi;
    }

    public String toString(String attributeSeparator) {
        return String.format("%s%s%s%s%s", this.tenKho, attributeSeparator,
                Integer.toString(this.soLuongHangTrongKho), attributeSeparator,
                Integer.toString(this.gioiHanCuaKho));
    }

}
