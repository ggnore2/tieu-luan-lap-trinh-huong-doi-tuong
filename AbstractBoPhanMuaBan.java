import java.util.ArrayList;

public abstract class AbstractBoPhanMuaBan {
    public abstract void nhapGiaoDich(GiaoDich giaoDich);

    public abstract void thayDoiChinhXacGiaoDich(GiaoDich giaoDichCu, GiaoDich giaoDichMoi);

    public abstract ArrayList<GiaoDich> timGiaoDichTheoThuocTinh(ArrayList<String> thuocTinhs,
            ArrayList<String> giaTris);

    public abstract void xoaChinhXacGiaoDich(GiaoDich giaoDichNhap);

    public abstract void nhapGia(Gia gia);

    public abstract void thayDoiChinhXacGia(Gia giaCu, Gia giaMoi);

    public abstract ArrayList<Gia> timGiaTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris);

    public abstract void xoaChinhXacGia(Gia giaNhap);
}
