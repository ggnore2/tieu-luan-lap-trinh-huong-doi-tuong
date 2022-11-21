import java.util.ArrayList;

public abstract class AbstractBoPhanVanChuyen {
    public abstract void nhapKho(Kho khoNhap);

    public abstract ArrayList<Kho> timKhoTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris);

    public abstract void xoaChinhXacKho(Kho khoNhap);

    public abstract void thayDoiChinhXacKho(Kho khoCu, Kho khoMoi);
}
