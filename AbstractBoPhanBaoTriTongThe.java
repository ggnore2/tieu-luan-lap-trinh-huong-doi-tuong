import java.util.ArrayList;

public abstract class AbstractBoPhanBaoTriTongThe {
    public abstract void nhapNhaSanXuat(NhaSanXuat nhaSanXuatNhap);

    public abstract void thayDoiChinhXacNhaSanXuat(NhaSanXuat nhaSanXuatCu, NhaSanXuat nhaSanXuatMoi);

    public abstract ArrayList<NhaSanXuat> timNhaSanXuatTheoThuocTinh(ArrayList<String> thuocTinhs,
            ArrayList<String> giaTris);

    public abstract void xoaChinhXacNhaSanXuat(NhaSanXuat nhaSanXuatNhap);

    public abstract void nhapNhaBanLe(NhaBanLe nhaBanLeNhap);

    public abstract void thayDoiChinhXacNhaBanLe(NhaBanLe nhaBanLeCu, NhaBanLe nhaBanLeMoi);

    public abstract ArrayList<NhaBanLe> timNhaBanLeTheoThuocTinh(ArrayList<String> thuocTinhs,
            ArrayList<String> giaTris);

    public abstract void xoaChinhXacNhaBanLe(NhaBanLe nhaBanLe);

    public abstract void nhapHang(Hang hangNhap);

    public abstract void thayDoiChinhXacHang(Hang hangCu, Hang hangMoi);

    public abstract ArrayList<Hang> timHangTheoThuocTinh(ArrayList<String> thuocTinhs,
            ArrayList<String> giaTris);

    public abstract void xoaChinhXacHang(Hang hangNhap);

}
