import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BoPhanVanChuyen extends AbstractBoPhanVanChuyen implements BoPhan {
    public BoPhanVanChuyen() {

    }

    private String tenCongTy = "cong ty";
    private String parentPath = "./BoPhanVanChuyen";
    private String khoPath = "./BoPhanVanChuyen/kho.txt";
    private String attributeSeparator = ",";

    public void taoDatabases() {
        if (!Files.exists(Path.of(this.parentPath))) {
            try {
                Files.createDirectories(Path.of(this.parentPath));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Path.of(this.khoPath))) {
            String content = String.format("ten kho%sso luong hang trong kho%sgioi han cua kho\n",
                    this.attributeSeparator, this.attributeSeparator);
            try {
                Files.writeString(Path.of(this.khoPath), content, StandardOpenOption.CREATE);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void vanChuyenGiaoDichMua() {
        BoPhanBaoTriTongThe boPhanBaoTriTongThe = new BoPhanBaoTriTongThe();

        BoPhanMuaBan boPhanMuaBan = new BoPhanMuaBan();
        ArrayList<GiaoDich> giaoDichDeXoas = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichs = new ArrayList<String>(Arrays.asList(boPhanMuaBan.showGiaoDich().split("\n")));
            if (giaoDichs.size() < 1) {
                return;
            }
            for (int i = 1; i < giaoDichs.size(); i++) {
                GiaoDich giaoDich = GiaoDich.fromString(giaoDichs.get(i), this.attributeSeparator);
                if (giaoDich.getNguoiMua().toLowerCase().trim().equals(this.tenCongTy)) {
                    int n = giaoDich.getSoLuong();
                    ArrayList<Kho> khos = this.timKhoTheoSoLuongDuCuaKho(n);
                    if (khos.size() < 1) {
                        System.out.println("khong co kho");
                        continue;
                    }

                    Kho kho = khos.get(0);
                    Kho khoMoi = Kho.fromString(kho.toString(this.attributeSeparator), this.attributeSeparator);
                    String tenKho = kho.getTenKho();

                    for (int j = 0; j < n; j++) {
                        Hang hang = new Hang(giaoDich.getTenHang(), giaoDich.getLoaiHang(), tenKho,
                                giaoDich.getNguoiBan(), "");
                        boPhanBaoTriTongThe.nhapHang(hang);
                    }

                    int soLuongCu = kho.getSoLuongHangTrongKho();
                    khoMoi.setSoLuongHangTrongKho(soLuongCu + n);
                    BoPhanVanChuyen boPhanVanChuyen = new BoPhanVanChuyen();
                    boPhanVanChuyen.thayDoiChinhXacKho(kho, khoMoi);
                    giaoDichDeXoas.add(giaoDich);
                }
            }
            for (GiaoDich giaoDichDeXoa : giaoDichDeXoas) {
                boPhanMuaBan.xoaChinhXacGiaoDich(giaoDichDeXoa);
                boPhanMuaBan.nhapGiaoDichDaHoanThanh(giaoDichDeXoa);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void vanChuyenGiaoDichBan() {
        BoPhanBaoTriTongThe boPhanBaoTriTongThe = new BoPhanBaoTriTongThe();

        BoPhanMuaBan boPhanMuaBan = new BoPhanMuaBan();

        ArrayList<String> giaoDichs = new ArrayList<String>(Arrays.asList(boPhanMuaBan.showGiaoDich().split("\n")));

        ArrayList<GiaoDich> giaoDichDeXoas = new ArrayList<GiaoDich>();

        for (int i = 1; i < giaoDichs.size(); i++) {
            GiaoDich giaoDichTemp = GiaoDich.fromString(giaoDichs.get(i), this.attributeSeparator);
            if (!giaoDichTemp.getNguoiMua().equals(this.tenCongTy)
                    && (giaoDichTemp.getNguoiBan().equals(this.tenCongTy))) {
                ArrayList<String> thuocTinhs = new ArrayList<String>(Arrays.asList("kho cua hang"));

                ArrayList<String> giaTris = new ArrayList<String>(Arrays.asList("dang ban"));

                ArrayList<Hang> hangs = boPhanBaoTriTongThe.timHangTheoThuocTinh(thuocTinhs, giaTris);

                ArrayList<Integer> indexes = new ArrayList<Integer>();
                for (int j = 0; j < hangs.size(); j++) {
                    if (hangs.get(j).getKhoCuaHang().equals("da ban")) {
                        indexes.add(j);
                    }
                }
                int count = 0;

                for (int index : indexes) {
                    hangs.remove(index - count);
                    count += 1;
                }

                ArrayList<Hang> hangsTemp = new ArrayList<Hang>();
                for (Hang hang : hangs) {
                    hangsTemp.add(Hang.fromString(hang.toString(this.attributeSeparator), this.attributeSeparator));
                }
                int index = 0;
                for (Hang hang : hangs) {
                    hangsTemp.get(index).setKhoCuaHang("da ban");
                    boPhanBaoTriTongThe.thayDoiChinhXacHang(hang, hangsTemp.get(index));
                }
                giaoDichDeXoas.add(giaoDichTemp);

            }
        }
        for (GiaoDich giaoDichDeXoa : giaoDichDeXoas) {
            boPhanMuaBan.xoaChinhXacGiaoDich(giaoDichDeXoa);
            boPhanMuaBan.nhapGiaoDichDaHoanThanh(giaoDichDeXoa);
        }
    }

    public Kho timChinhXacKho(Kho kho) {
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.equals(kho)) {
                    return tempKho;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Kho();
    }

    public ArrayList<Kho> timKhoTheoTenKho(String tenKho) {
        ArrayList<Kho> khos = new ArrayList<Kho>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getTenKho().equals(tenKho)) {
                    khos.add(tempKho);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<String> timKhoTheoTenKhoReturnString(String tenKho) {
        ArrayList<String> khos = new ArrayList<String>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getTenKho().equals(tenKho)) {
                    khos.add(al.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<Kho> timKhoTheoSoLuongHangTrongKho(int soLuongHangTrongKho) {
        ArrayList<Kho> khos = new ArrayList<Kho>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getSoLuongHangTrongKho() == soLuongHangTrongKho) {
                    khos.add(tempKho);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<String> timKhoTheoSoLuongHangTrongKhoReturnString(int soLuongHangTrongKho) {
        ArrayList<String> khos = new ArrayList<String>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getSoLuongHangTrongKho() == soLuongHangTrongKho) {
                    khos.add(al.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<Kho> timKhoTheoSoLuongDuCuaKho(int soLuongDuCuaKho) {
        ArrayList<Kho> khos = new ArrayList<Kho>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getGioiHanCuaKho() - tempKho.getSoLuongHangTrongKho() >= soLuongDuCuaKho) {
                    khos.add(tempKho);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<String> timKhoTheoSoLuongDuCuaKhoReturnString(int soLuongDuCuaKho) {
        ArrayList<String> khos = new ArrayList<String>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getGioiHanCuaKho() - tempKho.getSoLuongHangTrongKho() >= soLuongDuCuaKho) {
                    khos.add(al.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<Kho> timKhoTheoGioiHanCuaKho(int gioiHanCuaKho) {
        ArrayList<Kho> khos = new ArrayList<Kho>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.getGioiHanCuaKho() == gioiHanCuaKho) {
                    khos.add(tempKho);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<String> timKhoTheoGioiHanCuaKhoReturnString(int gioiHanCuaKho) {
        ArrayList<String> khos = new ArrayList<String>();
        try {
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), this.attributeSeparator);
                if (tempKho.getGioiHanCuaKho() == gioiHanCuaKho) {
                    khos.add(al.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return khos;
    }

    public ArrayList<String> timKhoTheoThuocTinhReturnString(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        ArrayList<String> kho = new ArrayList<String>();
        try {
            int index = 0;
            for (String thuocTinh : thuocTinhs) {
                if (thuocTinh.toLowerCase().trim().equals("ten kho")) {
                    als.add(this.timKhoTheoTenKhoReturnString(giaTris.get(index)));
                } else if (thuocTinh.toLowerCase().trim().equals("so luong hang trong kho")) {
                    als.add(this.timKhoTheoSoLuongHangTrongKhoReturnString(Integer.valueOf(giaTris.get(index))));
                } else if (thuocTinh.toLowerCase().trim().equals("gioi han cua kho")) {
                    als.add(this.timKhoTheoGioiHanCuaKhoReturnString(Integer.valueOf(giaTris.get(index))));
                }
                index += 1;
            }
            kho.addAll(als.get(0));
            for (ArrayList<String> i : als) {
                kho.retainAll(i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kho;

    }

    public ArrayList<Kho> timKhoTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        ArrayList<String> al = this.timKhoTheoThuocTinhReturnString(thuocTinhs, giaTris);
        ArrayList<Kho> result = new ArrayList<Kho>();
        for (String i : al) {
            result.add(Kho.fromString(i, this.attributeSeparator));
        }

        return result;

    }

    public void nhapKho(Kho khoNhap) {
        try {
            Files.writeString(Path.of(this.khoPath), khoNhap.toString(this.attributeSeparator) + "\n",
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void thayDoiChinhXacKho(Kho khoCu, Kho khoMoi) {
        try {
            int index = -1;
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), this.attributeSeparator);
                if (tempKho.equals(khoCu)) {
                    index = i;
                    break;
                }
            }

            if (index > 0) {
                al.set(index, khoMoi.toString(this.attributeSeparator));
            }
            Files.writeString(Path.of(this.khoPath), String.join("\n", al));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void xoaChinhXacKho(Kho khoNhap) {
        try {
            int index = -1;
            ArrayList<String> al = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.khoPath)).split("\n")));
            for (int i = 1; i < al.size(); i++) {
                Kho tempKho = Kho.fromString(al.get(i), attributeSeparator);
                if (tempKho.equals(khoNhap)) {
                    index = i;
                    break;
                }
            }

            if (index > 0) {
                al.remove(index);
            }
            Files.writeString(Path.of(this.khoPath), String.join("\n", al));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
