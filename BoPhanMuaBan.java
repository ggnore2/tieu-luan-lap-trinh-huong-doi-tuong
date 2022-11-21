import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class BoPhanMuaBan extends AbstractBoPhanMuaBan implements BoPhan {
    private String parentPath = "./BoPhanMuaBanDatabases/";
    private String giaDatabasePath = "./BoPhanMuaBanDatabases/gia.txt";
    private String giaoDichDatabasePath = "./BoPhanMuaBanDatabases/giaoDich.txt";
    private String attributeSeparator = ",";
    private String tenCongTy = "cong ty";

    public String showGia() {
        String gias = "";
        try {
            gias = Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public String showGiaoDich() {
        String giaoDichs = "";
        try {
            giaoDichs = Files.readString(Path.of(this.giaoDichDatabasePath), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return giaoDichs;

    }

    public void ban() {
        String tenHang;
        String loaiHang;
        int soLuong;
        String tenNhaBanLe;
        Scanner sc = new Scanner(System.in);

        BoPhanBaoTriTongThe boPhanBaoTriTongThe = new BoPhanBaoTriTongThe();
        System.out.println("Ten hang : \n");
        tenHang = sc.nextLine();

        System.out.println("Loai hang : \n");
        loaiHang = sc.nextLine();

        System.out.println("So luong : \n");
        soLuong = Integer.valueOf(sc.nextLine());

        System.out.println("Ten nha ban le : \n");
        tenNhaBanLe = sc.nextLine();

        sc.close();

        if (boPhanBaoTriTongThe.timNhaBanLeTheoTenNhaBanLe(tenNhaBanLe).size() == 0) {
            System.out.println("Nha ban le khong hop le\n");
            return;
        }
        ;
        ArrayList<String> thuocTinhs = new ArrayList<String>(Arrays.asList("ten hang", "loai hang"));
        ArrayList<String> giaTris = new ArrayList<String>(Arrays.asList(tenHang, loaiHang));
        ArrayList<Hang> hangs = boPhanBaoTriTongThe.timHangTheoThuocTinh(thuocTinhs, giaTris);

        if (hangs.size() < soLuong) {
            System.out.println("Khong du hang de ban\n");
            return;
        }
        ;
        ArrayList<Hang> hangsTemp = new ArrayList<Hang>();
        hangsTemp.addAll(hangs);
        int index = 0;
        for (Hang hang : hangs) {
            hangsTemp.get(index).setKhoCuaHang("dang ban");
            boPhanBaoTriTongThe.thayDoiChinhXacHang(hang, hangsTemp.get(index));
        }
        GiaoDich giaoDich = new GiaoDich(tenHang, loaiHang, tenNhaBanLe, "", soLuong, new Date());
        this.nhapGiaoDich(giaoDich);

    }

    public void mua() {
        String tenHang;
        String loaiHang;
        int soLuong;
        String tenNhaSanXuat;
        Scanner sc = new Scanner(System.in);

        BoPhanBaoTriTongThe boPhanBaoTriTongThe = new BoPhanBaoTriTongThe();
        System.out.println("Ten hang :");
        tenHang = sc.nextLine();

        System.out.println("Loai hang :");
        loaiHang = sc.nextLine();

        System.out.println("So luong :");
        soLuong = Integer.valueOf(sc.nextLine());

        System.out.println("Ten nha san xuat :");
        tenNhaSanXuat = sc.nextLine();

        sc.close();

        if (boPhanBaoTriTongThe.timNhaSanXuatTheoTenNhaSanXuat(tenNhaSanXuat).size() == 0) {
            System.out.println("Nha san xuat khong hop le\n");
            return;
        }

        BoPhanMuaBan boPhanMuaBan = new BoPhanMuaBan();

        boPhanMuaBan.nhapGiaoDich(new GiaoDich(tenHang, loaiHang, this.tenCongTy, tenNhaSanXuat, soLuong, new Date()));
        BoPhanVanChuyen boPhanVanChuyen = new BoPhanVanChuyen();
        boPhanVanChuyen.vanChuyenGiaoDichMua();
    }

    public void taoDatabases() {
        Path parentPath = Paths.get(this.parentPath);
        if (!(Files.exists(parentPath))) {
            try {
                Files.createDirectory(parentPath);
            } catch (Exception e) {
                System.out.printf("error creating directory %s\n", this.parentPath);
            }
        }

        File giaDatabase = new File(this.giaDatabasePath);

        if (!giaDatabase.exists()) {
            try {
                FileWriter fw = new FileWriter(giaDatabase);
                String content = String.format(
                        "ten hang%sloai hang%sgia\n",
                        this.attributeSeparator, this.attributeSeparator);
                fw.write(content);
                fw.close();
            } catch (IOException e) {
                System.out.printf("Error creating %s\n", this.giaDatabasePath);
            }
        }

        File giaoDichDatabase = new File(this.giaoDichDatabasePath);

        if (!giaoDichDatabase.exists()) {
            try {
                FileWriter fw = new FileWriter(giaoDichDatabase);

                String content = String.format(
                        "ten hang%sloai hang%sso luong%snguoi mua%snguoi ban%sthoi diem giao dich\n",
                        this.attributeSeparator, this.attributeSeparator, this.attributeSeparator,
                        this.attributeSeparator, this.attributeSeparator);
                fw.write(content);
                fw.close();
            } catch (Exception e) {
                System.out.printf("Error creating %s\n", this.giaoDichDatabasePath);
            }
        }

    }

    public void nhapGiaoDich(GiaoDich giaoDichNhap) {
        try {
            FileWriter writer = new FileWriter(new File(this.giaoDichDatabasePath), true);
            writer.write(giaoDichNhap.toString(this.attributeSeparator) + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };

    public void nhapGia(Gia gia) {
        try {
            FileWriter writer = new FileWriter(new File(this.giaDatabasePath), true);
            writer.write(gia.toString(this.attributeSeparator) + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };

    public ArrayList<Gia> timGiaTheoChinhXacTenHangVaLoaiHangVaGia(Gia gia) {
        ArrayList<Gia> gias = new ArrayList<Gia>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {

                System.out.println(giasDoc.get(i));

                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);

                if (tempGia.equals(gia)) {
                    gias.add(tempGia);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<Gia> timGiaTheoTenHang(String tenHang) {
        ArrayList<Gia> gias = new ArrayList<Gia>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.getTenHang().toLowerCase().trim().equals(tenHang.toLowerCase().trim())) {
                    gias.add(tempGia);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<String> timGiaTheoTenHangReturnString(String tenHang) {
        ArrayList<String> gias = new ArrayList<String>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.getTenHang().toLowerCase().trim().equals(tenHang.toLowerCase().trim())) {
                    gias.add(giasDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<Gia> timGiaTheoLoaiHang(String loaiHang) {
        ArrayList<Gia> gias = new ArrayList<Gia>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.getLoaiHang().toLowerCase().trim().equals(loaiHang.toLowerCase().trim())) {
                    gias.add(tempGia);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<String> timGiaTheoLoaiHangReturnString(String loaiHang) {
        ArrayList<String> gias = new ArrayList<String>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.getLoaiHang().toLowerCase().trim().equals(loaiHang.toLowerCase().trim())) {
                    gias.add(giasDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<Gia> timTenHangVaLoaiHangTheoGia(double gia) {
        ArrayList<Gia> gias = new ArrayList<Gia>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.getGia() == gia) {
                    gias.add(tempGia);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<String> timTenHangVaLoaiHangTheoGiaReturnString(double gia) {
        ArrayList<String> gias = new ArrayList<String>();
        try {
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.getGia() == gia) {
                    gias.add(giasDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gias;
    }

    public ArrayList<String> timGiaTheoThuocTinhReturnString(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        ArrayList<String> result = new ArrayList<String>();
        try {
            int i = 0;
            for (String thuocTinh : thuocTinhs) {
                if (thuocTinh.toLowerCase().trim() == "ten hang") {
                    als.add(timGiaTheoTenHangReturnString(giaTris.get(i).toLowerCase().trim()));
                } else if (thuocTinh.toLowerCase().trim() == "loai hang") {
                    als.add(timGiaTheoLoaiHangReturnString(giaTris.get(i).toLowerCase().trim()));
                } else if (thuocTinh.toLowerCase().trim() == "gia") {
                    als.add(timTenHangVaLoaiHangTheoGiaReturnString(Double.valueOf(giaTris.get(i))));
                }
                i += 1;
            }
            result.addAll(als.get(0));
            for (ArrayList<String> al : als) {
                result.retainAll(al);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public ArrayList<Gia> timGiaTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        ArrayList<Gia> result = new ArrayList<Gia>();
        for (String i : timGiaTheoThuocTinhReturnString(thuocTinhs, giaTris)) {
            result.add(Gia.fromString(i, this.attributeSeparator));
        }
        ;
        return result;
    }

    public void thayDoiChinhXacGia(Gia giaCu, Gia giaMoi) {
        try {
            int index = -1;
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.equals(giaCu)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                giasDoc.set(index, giaMoi.toString(this.attributeSeparator));
            }
            String content = String.join("\n", giasDoc);
            Files.writeString(Path.of(this.giaDatabasePath), content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void xoaChinhXacGia(Gia giaCu) {
        try {
            int index = -1;
            ArrayList<String> giasDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaDatabasePath), StandardCharsets.UTF_8).split("\n")));
            for (int i = 1; i < giasDoc.size(); i++) {
                Gia tempGia = Gia.fromString(giasDoc.get(i), this.attributeSeparator);
                if (tempGia.equals(giaCu)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                giasDoc.remove(index);
            }
            String content = String.join("\n", giasDoc) + "\n\n";

            Files.writeString(Path.of(this.giaDatabasePath), content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GiaoDich timChinhXacGiaoDich(GiaoDich giaoDichNhap) {
        GiaoDich result = new GiaoDich();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);

                if (tempGiaoDich.equals(giaoDichNhap)) {
                    result = tempGiaoDich;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String timChinhXacGiaoDichReturnString(GiaoDich giaoDichNhap) {
        GiaoDich result = new GiaoDich();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.equals(giaoDichNhap)) {
                    result = tempGiaoDich;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result.toString(this.attributeSeparator);
    }

    public ArrayList<GiaoDich> timGiaoDichTheoTenHang(String tenHang) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getTenHang().equals(tenHang)) {
                    result.add(tempGiaoDich);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoTenHangReturnString(String tenHang) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getTenHang().equals(tenHang)) {
                    result.add(giaoDichsDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<GiaoDich> timGiaoDichTheoLoaiHang(String loaiHang) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getLoaiHang().equals(loaiHang)) {
                    result.add(tempGiaoDich);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoLoaiHangReturnString(String loaiHang) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getLoaiHang().equals(loaiHang)) {
                    result.add(giaoDichsDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<GiaoDich> timGiaoDichTheoSoLuong(int soLuong) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getSoLuong() == soLuong) {
                    result.add(tempGiaoDich);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoSoLuongReturnString(int soLuong) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getSoLuong() == soLuong) {
                    result.add(giaoDichsDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<GiaoDich> timGiaoDichTheoNguoiMua(String nguoiMua) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getNguoiMua().equals(nguoiMua)) {
                    result.add(tempGiaoDich);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoNguoiMuaReturnString(String nguoiMua) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getNguoiMua().equals(nguoiMua)) {
                    result.add(giaoDichsDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<GiaoDich> timGiaoDichTheoNguoiBan(String nguoiBan) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getNguoiBan().equals(nguoiBan)) {
                    result.add(tempGiaoDich);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoNguoiBanReturnString(String nguoiBan) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getNguoiBan().equals(nguoiBan)) {
                    result.add(giaoDichsDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<GiaoDich> timGiaoDichTheoThoiDiemGiaoDich(Date thoiDiemGiaoDich) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getThoiDiemGiaoDich().equals(thoiDiemGiaoDich)) {
                    result.add(tempGiaoDich);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoThoiDiemGiaoDichReturnString(Date thoiDiemGiaoDich) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.getThoiDiemGiaoDich().equals(thoiDiemGiaoDich)) {
                    result.add(giaoDichsDoc.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<GiaoDich> timGiaoDichTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        ArrayList<GiaoDich> result = new ArrayList<GiaoDich>();
        ArrayList<String> temp = timGiaoDichTheoThuocTinhReturnString(thuocTinhs, giaTris);
        for (String i : temp) {
            result.add(GiaoDich.fromString(i, this.attributeSeparator));
        }
        return result;
    }

    public ArrayList<String> timGiaoDichTheoThuocTinhReturnString(ArrayList<String> thuocTinhs,
            ArrayList<String> giaTris) {
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        ArrayList<String> result = new ArrayList<String>();
        try {
            int index = 0;
            for (String thuocTinh : thuocTinhs) {
                if (thuocTinh.toLowerCase().trim() == "ten hang") {
                    als.add(this.timGiaoDichTheoTenHangReturnString(giaTris.get(index)));
                } else if (thuocTinh.toLowerCase().trim() == "loai hang") {
                    als.add(this.timGiaoDichTheoLoaiHangReturnString(giaTris.get(index)));
                } else if (thuocTinh.toLowerCase().trim() == "so luong") {
                    als.add(this.timGiaoDichTheoSoLuongReturnString(Integer.valueOf(giaTris.get(index))));
                } else if (thuocTinh.toLowerCase().trim() == "nguoi mua") {
                    als.add(this.timGiaoDichTheoNguoiMuaReturnString(giaTris.get(index)));
                } else if (thuocTinh.toLowerCase().trim() == "nguoi ban") {
                    als.add(this.timGiaoDichTheoNguoiBanReturnString(giaTris.get(index)));
                } else if (thuocTinh.toLowerCase().trim() == "thoi diem giao dich") {
                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                    als.add(this.timGiaoDichTheoThoiDiemGiaoDichReturnString(parser.parse(giaTris.get(index))));
                }
                index += 1;
            }
            result = als.get(0);
            for (ArrayList<String> i : als) {
                result.retainAll(i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void thayDoiChinhXacGiaoDich(GiaoDich giaoDichCu, GiaoDich giaoDichMoi) {
        try {
            int index = -1;
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.equals(giaoDichCu)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                giaoDichsDoc.set(index, giaoDichMoi.toString(this.attributeSeparator));
            }
            Files.writeString(Path.of(this.giaoDichDatabasePath), String.join("\n", giaoDichsDoc));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void xoaChinhXacGiaoDich(GiaoDich giaoDichNhap) {
        try {
            int index = -1;
            ArrayList<String> giaoDichsDoc = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.giaoDichDatabasePath)).split("\n")));
            for (int i = 1; i < giaoDichsDoc.size(); i++) {
                GiaoDich tempGiaoDich = GiaoDich.fromString(giaoDichsDoc.get(i), this.attributeSeparator);
                if (tempGiaoDich.equals(giaoDichNhap)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                giaoDichsDoc.remove(index);
            }
            Files.writeString(Path.of(this.giaoDichDatabasePath), String.join("\n", giaoDichsDoc));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
