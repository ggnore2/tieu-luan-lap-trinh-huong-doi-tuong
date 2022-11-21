import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class BoPhanBaoTriTongThe extends AbstractBoPhanBaoTriTongThe implements BoPhan {
    private String parentPath = "./BoPhanBaoTriTongTheDatabases/";
    private String hangDatabasePath = "./BoPhanBaoTriTongTheDatabases/hang.txt";
    private String nhaBanLeDatabasePath = "./BoPhanBaoTriTongTheDatabases/nhaBanLe.txt";
    private String nhaSanXuatDatabasePath = "./BoPhanBaoTriTongTheDatabases/nhaSanXuat.txt";
    private String attributeSeparator = ",";

    public void taoDatabases() {
        Path parentPath = Paths.get(this.parentPath);
        if (!(Files.exists(parentPath))) {
            try {
                Files.createDirectory(parentPath);
            } catch (Exception e) {
                System.out.printf("error creating directory %s\n", this.parentPath);
            }
        }

        File hangDatabase = new File(this.hangDatabasePath);
        File nhaBanLeDatabase = new File(this.nhaBanLeDatabasePath);
        File nhaSanXuatDatabase = new File(this.nhaSanXuatDatabasePath);

        if (!hangDatabase.exists()) {
            try {
                FileWriter fw = new FileWriter(hangDatabase);
                String content = String.format(
                        "Id%sten hang%sloai hang%skho cua hang%sten nha san xuat%sten nha ban le%sthoi diem nhap%sthoi diem xuat\n",
                        this.attributeSeparator, this.attributeSeparator, this.attributeSeparator,
                        this.attributeSeparator, this.attributeSeparator, this.attributeSeparator,
                        this.attributeSeparator);
                fw.write(content);
                fw.close();
            } catch (IOException e) {
                System.out.printf("Error creating %s\n", this.hangDatabasePath);
            }
        }
        if (!nhaBanLeDatabase.exists()) {
            try {
                FileWriter fw = new FileWriter(nhaBanLeDatabase);
                String content = String.format("ten nha ban le%sdia diem%sso dien thoai\n", this.attributeSeparator,
                        this.attributeSeparator);
                fw.write(content);
                fw.close();
            } catch (IOException e) {
                System.out.printf("Error creating %s\n", this.nhaBanLeDatabasePath);
            }
        }
        if (!nhaSanXuatDatabase.exists()) {
            try {
                FileWriter fw = new FileWriter(nhaSanXuatDatabase);
                String content = String.format("ten nha san xuat%sdia diem%sso dien thoai\n", this.attributeSeparator,
                        this.attributeSeparator);
                fw.write(content);
                fw.close();
            } catch (IOException e) {
                System.out.printf("Error creating to %s\n", this.nhaSanXuatDatabasePath);
            }
        }

    }

    public void nhapHang(Hang hang) {
        File hangDataBase = new File(this.hangDatabasePath);
        try {
            hang.setThoiDiemNhap(new Date());
            FileWriter fw = new FileWriter(hangDataBase, true);
            String content = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s\n", hang.getId(), this.attributeSeparator,
                    hang.getTenHang(), this.attributeSeparator,
                    hang.getLoaiHang(),
                    this.attributeSeparator, hang.getKhoCuaHang(),
                    this.attributeSeparator, hang.getTenNhaSanXuat(), this.attributeSeparator, hang.getTenNhaBanLe(),
                    this.attributeSeparator, hang.getThoiDiemNhap().toString(), this.attributeSeparator,
                    hang.getThoiDiemXuat().toString());
            fw.write(content);
            fw.close();
        } catch (Exception e) {
            System.out.printf("Error writing to %s\n", this.hangDatabasePath);
        }
    }

    public void nhapNhaSanXuat(NhaSanXuat nhaSanXuat) {
        File nhaSanXuatDataBase = new File(this.nhaSanXuatDatabasePath);
        try {
            FileWriter fw = new FileWriter(nhaSanXuatDataBase, true);
            String content = String.format("%s%s%s%s%s\n", nhaSanXuat.getTenNhaSanXuat(), this.attributeSeparator,
                    nhaSanXuat.getDiaDiem(),
                    this.attributeSeparator, nhaSanXuat.getSoDienThoai());
            fw.write(content);
            fw.close();
        } catch (Exception e) {
            System.out.printf("Error writing to %s\n", this.nhaSanXuatDatabasePath);
        }
    }

    public void nhapNhaBanLe(NhaBanLe nhaBanLe) {

        File nhaBanLeDataBase = new File(this.nhaBanLeDatabasePath);
        try {
            FileWriter fw = new FileWriter(nhaBanLeDataBase, true);
            String content = String.format("%s%s%s%s%s\n", nhaBanLe.getTenNhaBanLe(), this.attributeSeparator,
                    nhaBanLe.getDiaDiem(),
                    this.attributeSeparator, nhaBanLe.getSoDienThoai());
            fw.write(content);
            fw.close();

        } catch (Exception e) {
            System.out.printf("Error writing to %s\n", this.nhaBanLeDatabasePath);
        }
    }

    public ArrayList<Hang> timChinhXacHang(Hang hangNhap) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();

        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {
                // "Id%sten hang%sloai hang%skho cua hang%sten nha san xuat%sten nha ban
                // le%sthoi diem nhap\n"
                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);
                if (tempHang.equals(hangNhap)) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timMotPhanChinhXacHang(Hang hangNhap) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.equalsOne(hangNhap)) {
                    hangs.add(hangNhap);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoLoaiHang(String loaiHang) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getLoaiHang().toLowerCase().trim().equals(loaiHang.toLowerCase().trim())) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoLoaiHangReturnString(String loaiHang) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getLoaiHang().toLowerCase().trim().equals(loaiHang.toLowerCase().trim())) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoId(long Id) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getId() == Id) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoIdReturnString(long Id) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getId() == Id) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoTenHang(String tenHang) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getTenHang().toLowerCase().trim().equals(tenHang.toLowerCase().trim())) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoTenHangReturnString(String tenHang) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getTenHang().toLowerCase().trim().equals(tenHang.toLowerCase().trim())) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoKhoCuaHang(String khoCuaHang) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getKhoCuaHang().toLowerCase().trim().equals(khoCuaHang.toLowerCase().trim())) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoKhoCuaHangReturnString(String khoCuaHang) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getKhoCuaHang().toLowerCase().trim().equals(khoCuaHang.toLowerCase().trim())) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoNhaSanXuat(String nhaSanXuat) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getTenNhaSanXuat().toLowerCase().trim().equals(nhaSanXuat.toLowerCase().trim())) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoNhaSanXuatReturnString(String nhaSanXuat) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getTenNhaSanXuat().toLowerCase().trim().equals(nhaSanXuat.toLowerCase().trim())) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoNhaBanLe(String nhaBanLe) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getTenNhaBanLe().toLowerCase().trim().equals(nhaBanLe.toLowerCase().trim())) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoNhaBanLeReturnString(String nhaBanLe) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getTenNhaBanLe().toLowerCase().trim().equals(nhaBanLe.toLowerCase().trim())) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoThoiDiemNhap(Date thoiDiemNhap) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getThoiDiemNhap().equals(thoiDiemNhap)) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoThoiDiemNhapReturnString(Date thoiDiemNhap) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getThoiDiemNhap().equals(thoiDiemNhap)) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<Hang> timHangTheoThoiDiemXuat(Date thoiDiemXuat) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<Hang> hangs = new ArrayList<Hang>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getThoiDiemXuat().equals(thoiDiemXuat)) {
                    hangs.add(tempHang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoThoiDiemXuatReturnString(Date thoiDiemXuat) {
        File hangDatabase = new File(this.hangDatabasePath);
        String hang;
        ArrayList<String> hangs = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((hang = bufferreader.readLine()) != null) {

                Hang tempHang = Hang.taoHang(hang, this.attributeSeparator);

                if (tempHang.getThoiDiemXuat().equals(thoiDiemXuat)) {
                    hangs.add(hang);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return hangs;
    }

    public ArrayList<String> timHangTheoThuocTinhReturnString(ArrayList<String> cacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("Id", 0);
        hm.put("ten hang", 1);
        hm.put("loai hang", 2);
        hm.put("kho cua hang", 3);
        hm.put("ten nha san xuat", 4);
        hm.put("ten nha ban le", 5);
        hm.put("thoi diem nhap", 6);
        hm.put("thoi diem xuat", 7);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (String thuocTinh : cacThuocTinh) {
            if (hm.containsKey(thuocTinh)) {
                indexes.add(hm.get(thuocTinh));
            }
            ;
        }
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        int index = 0;
        for (int i : indexes) {
            if (i == 0) {
                als.add(this.timHangTheoIdReturnString(Long.parseLong(giaTriCacThuocTinh.get(index))));
            } else if (i == 1) {
                als.add(this.timHangTheoTenHangReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 2) {
                als.add(this.timHangTheoLoaiHangReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 3) {
                als.add(this.timHangTheoKhoCuaHangReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 4) {
                als.add(this.timHangTheoNhaSanXuatReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 5) {
                als.add(this.timHangTheoNhaBanLeReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 6) {
                try {
                    als.add(this.timHangTheoThoiDiemNhapReturnString(
                            (new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")).parse(giaTriCacThuocTinh.get(
                                    index)
                                    .trim())));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (i == 7) {
                try {
                    als.add(this.timHangTheoThoiDiemXuatReturnString(
                            (new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")).parse(giaTriCacThuocTinh.get(
                                    index)
                                    .trim())));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            index += 1;
        }
        ;
        ArrayList<String> result = als.get(0);
        for (ArrayList<String> i : als) {
            result.retainAll(i);
        }

        return result;
    }

    public ArrayList<Hang> timHangTheoThuocTinh(ArrayList<String> cacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<String> al = timHangTheoThuocTinhReturnString(cacThuocTinh, giaTriCacThuocTinh);
        ArrayList<Hang> result = new ArrayList<Hang>();
        for (String i : al) {
            result.add(Hang.taoHang(i, this.attributeSeparator));
        }

        return result;
    }

    public ArrayList<NhaBanLe> timChinhXacNhaBanLe(NhaBanLe nhaBanLeNhap) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<NhaBanLe> nhaBanLes = new ArrayList<NhaBanLe>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {
                String[] mangCacThuocTinh = nhaBanLe.split(this.attributeSeparator);

                NhaBanLe tempNhaBanLe = new NhaBanLe();
                tempNhaBanLe.setTenNhaBanLe(mangCacThuocTinh[0]);
                tempNhaBanLe.setDiaDiem(mangCacThuocTinh[1]);
                tempNhaBanLe.setSoDienThoai(mangCacThuocTinh[2]);
                if (tempNhaBanLe.equals(nhaBanLeNhap)) {
                    nhaBanLes.add(nhaBanLeNhap);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<NhaBanLe> timNhaBanLeTheoTenNhaBanLe(String tenNhaBanLe) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<NhaBanLe> nhaBanLes = new ArrayList<NhaBanLe>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {

                NhaBanLe tempBanLe = NhaBanLe.taoNhaBanLe(nhaBanLe, this.attributeSeparator);

                if (tempBanLe.getTenNhaBanLe().toLowerCase().trim().equals(tenNhaBanLe.toLowerCase().trim())) {
                    nhaBanLes.add(tempBanLe);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<String> timNhaBanLeTheoTenNhaBanLeReturnString(String tenNhaBanLe) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<String> nhaBanLes = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {

                NhaBanLe tempBanLe = NhaBanLe.taoNhaBanLe(nhaBanLe, this.attributeSeparator);

                if (tempBanLe.getTenNhaBanLe().toLowerCase().trim().equals(tenNhaBanLe.toLowerCase().trim())) {
                    nhaBanLes.add(nhaBanLe);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<NhaBanLe> timNhaBanLeTheoDiaDiem(String diaDiem) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<NhaBanLe> nhaBanLes = new ArrayList<NhaBanLe>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {

                NhaBanLe tempBanLe = NhaBanLe.taoNhaBanLe(nhaBanLe, this.attributeSeparator);

                if (tempBanLe.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    nhaBanLes.add(tempBanLe);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<String> timNhaBanLeTheoDiaDiemReturnString(String diaDiem) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<String> nhaBanLes = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {

                NhaBanLe tempBanLe = NhaBanLe.taoNhaBanLe(nhaBanLe, this.attributeSeparator);

                if (tempBanLe.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    nhaBanLes.add(nhaBanLe);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<NhaBanLe> timNhaBanLeTheoSoDienThoai(String soDienThoai) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<NhaBanLe> nhaBanLes = new ArrayList<NhaBanLe>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {

                NhaBanLe tempBanLe = NhaBanLe.taoNhaBanLe(nhaBanLe, this.attributeSeparator);

                if (tempBanLe.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    nhaBanLes.add(tempBanLe);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<String> timNhaBanLeTheoSoDienThoaiReturnString(String soDienThoai) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<String> nhaBanLes = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {

                NhaBanLe tempBanLe = NhaBanLe.taoNhaBanLe(nhaBanLe, this.attributeSeparator);

                if (tempBanLe.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    nhaBanLes.add(nhaBanLe);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<String> timNhaBanLeTheoThuocTinhReturnString(ArrayList<String> cacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("ten nha ban le", 0);
        hm.put("dia diem", 1);
        hm.put("so dien thoai", 2);

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (String thuocTinh : cacThuocTinh) {
            if (hm.containsKey(thuocTinh)) {
                indexes.add(hm.get(thuocTinh));
            }
            ;
        }
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        int index = 0;
        for (int i : indexes) {
            if (i == 0) {
                als.add(this
                        .timNhaBanLeTheoTenNhaBanLeReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));
            } else if (i == 1) {
                als.add(this.timNhaBanLeTheoDiaDiemReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 2) {
                als.add(this.timNhaBanLeTheoSoDienThoaiReturnString(giaTriCacThuocTinh.get(
                        index).trim().toLowerCase()));

            }
            index += 1;
        }
        ;
        ArrayList<String> result = als.get(0);
        for (ArrayList<String> i : als) {
            result.retainAll(i);
        }

        return result;
    }

    public ArrayList<NhaBanLe> timNhaBanLeTheoThuocTinh(ArrayList<String> cacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<String> ar = timNhaBanLeTheoThuocTinhReturnString(cacThuocTinh, giaTriCacThuocTinh);
        ArrayList<NhaBanLe> result = new ArrayList<NhaBanLe>();
        for (String i : ar) {
            result.add(NhaBanLe.taoNhaBanLe(i, this.attributeSeparator));
        }
        return result;
    }

    public ArrayList<NhaBanLe> timMotPhanChinhXacNhaBanLe(NhaBanLe nhaBanLeNhap) {
        File hangDatabase = new File(this.nhaBanLeDatabasePath);
        String nhaBanLe;
        ArrayList<NhaBanLe> nhaBanLes = new ArrayList<NhaBanLe>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaBanLe = bufferreader.readLine()) != null) {
                String[] mangCacThuocTinh = nhaBanLe.split(this.attributeSeparator);

                NhaBanLe tempNhaBanLe = new NhaBanLe();
                tempNhaBanLe.setTenNhaBanLe(mangCacThuocTinh[0]);
                tempNhaBanLe.setDiaDiem(mangCacThuocTinh[1]);
                tempNhaBanLe.setSoDienThoai(mangCacThuocTinh[2]);
                if (tempNhaBanLe.equalsOne(nhaBanLeNhap)) {
                    nhaBanLes.add(nhaBanLeNhap);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaBanLes;
    }

    public ArrayList<NhaSanXuat> timChinhXacNhaSanXuat(NhaSanXuat nhaSanXuatNhap) {
        File nhaSanXuatDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<NhaSanXuat> nhaSanXuats = new ArrayList<NhaSanXuat>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(nhaSanXuatDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {
                String[] mangCacThuocTinh = nhaSanXuat.split(this.attributeSeparator);

                NhaSanXuat tempNhaSanXuat = new NhaSanXuat();
                tempNhaSanXuat.setTenNhaSanXuat(mangCacThuocTinh[0]);
                tempNhaSanXuat.setDiaDiem(mangCacThuocTinh[1]);
                tempNhaSanXuat.setSoDienThoai(mangCacThuocTinh[2]);
                if (tempNhaSanXuat.equals(nhaSanXuatNhap)) {
                    nhaSanXuats.add(nhaSanXuatNhap);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<String> timNhaSanXuatTheoThuocTinhReturnString(ArrayList<String> cacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("ten nha ban le", 0);
        hm.put("dia diem", 1);
        hm.put("so dien thoai", 2);

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (String thuocTinh : cacThuocTinh) {
            if (hm.containsKey(thuocTinh)) {
                indexes.add(hm.get(thuocTinh));
            }
            ;
        }
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        int index = 0;
        for (int i : indexes) {
            if (i == 0) {
                als.add(this
                        .timNhaSanXuatTheoTenNhaSanXuatReturnString(
                                giaTriCacThuocTinh.get(index).trim().toLowerCase()));
            } else if (i == 1) {
                als.add(this.timNhaSanXuatTheoDiaDiemReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            } else if (i == 2) {
                als.add(this
                        .timNhaSanXuatTheoSoDienThoaiReturnString(giaTriCacThuocTinh.get(index).trim().toLowerCase()));

            }
            index += 1;
        }
        ;
        ArrayList<String> result = als.get(0);
        for (ArrayList<String> i : als) {
            result.retainAll(i);
        }

        return result;
    }

    public ArrayList<NhaSanXuat> timNhaSanXuatTheoThuocTinh(ArrayList<String> cacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<String> ar = timNhaBanLeTheoThuocTinhReturnString(cacThuocTinh, giaTriCacThuocTinh);
        ArrayList<NhaSanXuat> result = new ArrayList<NhaSanXuat>();
        for (String i : ar) {
            result.add(NhaSanXuat.taoNhaSanXuat(i, this.attributeSeparator));
        }
        return result;
    }

    public ArrayList<NhaSanXuat> timNhaSanXuatTheoTenNhaSanXuat(String tenNhaSanXuat) {
        File hangDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<NhaSanXuat> nhaSanXuats = new ArrayList<NhaSanXuat>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {

                NhaSanXuat tempSanXuat = NhaSanXuat.taoNhaSanXuat(nhaSanXuat, this.attributeSeparator);

                if (tempSanXuat.getTenNhaSanXuat().toLowerCase().trim().equals(tenNhaSanXuat.toLowerCase().trim())) {
                    nhaSanXuats.add(tempSanXuat);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<String> timNhaSanXuatTheoTenNhaSanXuatReturnString(String tenNhaSanXuat) {
        File hangDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<String> nhaSanXuats = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {

                NhaSanXuat tempSanXuat = NhaSanXuat.taoNhaSanXuat(nhaSanXuat, this.attributeSeparator);

                if (tempSanXuat.getTenNhaSanXuat().toLowerCase().trim().equals(tenNhaSanXuat.toLowerCase().trim())) {
                    nhaSanXuats.add(nhaSanXuat);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<NhaSanXuat> timNhaSanXuatTheoDiaDiem(String diaDiem) {
        File hangDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<NhaSanXuat> nhaSanXuats = new ArrayList<NhaSanXuat>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {

                NhaSanXuat tempSanXuat = NhaSanXuat.taoNhaSanXuat(nhaSanXuat, this.attributeSeparator);

                if (tempSanXuat.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    nhaSanXuats.add(tempSanXuat);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<String> timNhaSanXuatTheoDiaDiemReturnString(String diaDiem) {
        File hangDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<String> nhaSanXuats = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {

                NhaSanXuat tempSanXuat = NhaSanXuat.taoNhaSanXuat(nhaSanXuat, this.attributeSeparator);

                if (tempSanXuat.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    nhaSanXuats.add(nhaSanXuat);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<NhaSanXuat> timNhaSanXuatTheoSoDienThoai(String soDienThoai) {
        File hangDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<NhaSanXuat> nhaSanXuats = new ArrayList<NhaSanXuat>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {

                NhaSanXuat tempSanXuat = NhaSanXuat.taoNhaSanXuat(nhaSanXuat, this.attributeSeparator);

                if (tempSanXuat.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    nhaSanXuats.add(tempSanXuat);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<String> timNhaSanXuatTheoSoDienThoaiReturnString(String soDienThoai) {
        File hangDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<String> nhaSanXuats = new ArrayList<String>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(hangDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {

                NhaSanXuat tempSanXuat = NhaSanXuat.taoNhaSanXuat(nhaSanXuat, this.attributeSeparator);

                if (tempSanXuat.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    nhaSanXuats.add(nhaSanXuat);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public ArrayList<NhaSanXuat> timMotPhanChinhXacNhaSanXuat(NhaSanXuat nhaSanXuatNhap) {
        File nhaSanXuatDatabase = new File(this.nhaSanXuatDatabasePath);
        String nhaSanXuat;
        ArrayList<NhaSanXuat> nhaSanXuats = new ArrayList<NhaSanXuat>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(nhaSanXuatDatabase));
            bufferreader.readLine();
            while ((nhaSanXuat = bufferreader.readLine()) != null) {
                String[] mangCacThuocTinh = nhaSanXuat.split(this.attributeSeparator);

                NhaSanXuat tempNhaSanXuat = new NhaSanXuat();
                tempNhaSanXuat.setTenNhaSanXuat(mangCacThuocTinh[0]);
                tempNhaSanXuat.setDiaDiem(mangCacThuocTinh[1]);
                tempNhaSanXuat.setSoDienThoai(mangCacThuocTinh[2]);
                if (tempNhaSanXuat.equalsOne(nhaSanXuatNhap)) {
                    nhaSanXuats.add(nhaSanXuatNhap);
                }
            }
            bufferreader.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return nhaSanXuats;
    }

    public void xoaMotHangTheoTenHang(String tenHang) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getTenHang().toLowerCase().trim().equals(tenHang.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoTenHang(String tenHang) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getTenHang().toLowerCase().trim().equals(tenHang.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoId(long Id) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getId() == Id) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoId(long Id) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getId() == Id) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoLoaiHang(String loaiHang) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getLoaiHang().toLowerCase().trim().equals(loaiHang.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoLoaiHang(String loaiHang) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getLoaiHang().toLowerCase().trim().equals(loaiHang.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoKhoCuaHang(String khoCuaHang) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getKhoCuaHang().toLowerCase().trim().equals(khoCuaHang.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoKhoCuaHang(String khoCuaHang) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getKhoCuaHang().toLowerCase().trim().equals(khoCuaHang.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoTenNhaSanXuat(String tenNhaSanXuat) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getTenNhaSanXuat().toLowerCase().trim().equals(tenNhaSanXuat.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoTenNhaSanXuat(String tenNhaSanXuat) {

        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getTenNhaSanXuat().toLowerCase().trim().equals(tenNhaSanXuat.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoTenNhaBanLe(String tenNhaBanLe) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getTenNhaBanLe().toLowerCase().trim().equals(tenNhaBanLe.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoTenNhaBanLe(String tenNhaBanLe) {

        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getTenNhaBanLe().toLowerCase().trim().equals(tenNhaBanLe.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoThoiDiemNhap(Date thoiDiemNhap) {

        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getThoiDiemNhap().equals(thoiDiemNhap)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoThoiDiemNhap(Date thoiDiemNhap) {

        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getThoiDiemNhap().equals(thoiDiemNhap)) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaMotHangTheoThoiDiemXuat(Date thoiDiemXuat) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getThoiDiemXuat().equals(thoiDiemXuat)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.remove(index);
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaHangTheoThoiDiemXuat(Date thoiDiemXuat) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.getThoiDiemXuat().equals(thoiDiemXuat)) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachHang.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
            writer.close();
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaChinhXacHang(Hang hangNhap) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.equals(hangNhap)) {
                    index = i;
                    break;
                }
            }
            danhSachHang.remove(index);
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaChinhXacNhaSanXuat(NhaSanXuat nhaSanXuatNhap) {
        try {
            ArrayList<String> danhSachNhaSanXuat = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaSanXuatDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaSanXuat.size(); i++) {
                NhaSanXuat tempNhaSanXuat = NhaSanXuat.taoNhaSanXuat(danhSachNhaSanXuat.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.equals(nhaSanXuatNhap)) {
                    index = i;
                    break;
                }
            }
            danhSachNhaSanXuat.remove(index);
            String newDatabase = String.join("\n", danhSachNhaSanXuat);
            FileWriter writer = new FileWriter(new File(this.nhaSanXuatDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaNhaSanXuatTheoTenNhaSanXuat(String tenNhaSanXuat) {
        try {
            ArrayList<String> danhSachNhaSanXuat = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaSanXuatDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachNhaSanXuat.size(); i++) {
                NhaSanXuat tempNhaSanXuat = NhaSanXuat.taoNhaSanXuat(danhSachNhaSanXuat.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getTenNhaSanXuat().toLowerCase().trim().equals(tenNhaSanXuat.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachNhaSanXuat.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachNhaSanXuat);
            FileWriter writer = new FileWriter(new File(this.nhaSanXuatDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaNhaSanXuatTheoDiaDiem(String diaDiem) {
        try {
            ArrayList<String> danhSachNhaSanXuat = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaSanXuatDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachNhaSanXuat.size(); i++) {
                NhaSanXuat tempNhaSanXuat = NhaSanXuat.taoNhaSanXuat(danhSachNhaSanXuat.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachNhaSanXuat.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachNhaSanXuat);
            FileWriter writer = new FileWriter(new File(this.nhaSanXuatDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaNhaSanXuatTheoSoDienThoai(String soDienThoai) {
        try {
            ArrayList<String> danhSachNhaSanXuat = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaSanXuatDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachNhaSanXuat.size(); i++) {
                NhaSanXuat tempNhaSanXuat = NhaSanXuat.taoNhaSanXuat(danhSachNhaSanXuat.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachNhaSanXuat.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachNhaSanXuat);
            FileWriter writer = new FileWriter(new File(this.nhaSanXuatDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void xoaChinhXacNhaBanLe(NhaBanLe nhaBanLeNhap) {
        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.equals(nhaBanLeNhap)) {
                    index = i;
                    break;
                }
            }
            danhSachNhaBanLe.remove(index);
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void xoaMotNhaBanLeTheoTenNhaBanLe(String tenNhaBanLe) {
        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getTenNhaBanLe().toLowerCase().trim().equals(tenNhaBanLe.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachNhaBanLe.remove(index);
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void xoaNhaBanLeTheoTenNhaBanLe(String tenNhaBanLe) {
        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getTenNhaBanLe().toLowerCase().trim().equals(tenNhaBanLe.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachNhaBanLe.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void xoaMotNhaBanLeTheoDiaDiem(String diaDiem) {
        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachNhaBanLe.remove(index);
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void xoaNhaBanLeTheoDiaDiem(String diaDiem) {
        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getDiaDiem().toLowerCase().trim().equals(diaDiem.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachNhaBanLe.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void xoaMotNhaBanLeTheoSoDienThoai(String soDienThoai) {

        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachNhaBanLe.remove(index);
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void xoaNhaBanLeTheoSoDienThoai(String soDienThoai) {

        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.getSoDienThoai().toLowerCase().trim().equals(soDienThoai.toLowerCase().trim())) {
                    indexes.add(i);
                }
            }
            int count = 0;
            for (int i : indexes) {
                danhSachNhaBanLe.remove(i - count);
                count += 1;
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

    public void thayDoiChinhXacHang(Hang hangCu, Hang hangMoi) {
        try {
            ArrayList<String> danhSachHang = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.hangDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachHang.size(); i++) {
                Hang tempHang = Hang.taoHang(danhSachHang.get(i), this.attributeSeparator);
                if (tempHang.equals(hangCu)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachHang.set(index, hangMoi.toString(this.attributeSeparator));
            }
            String newDatabase = String.join("\n", danhSachHang);
            FileWriter writer = new FileWriter(new File(this.hangDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void thayDoiChinhXacNhaSanXuat(NhaSanXuat nhaSanXuatCu, NhaSanXuat nhaSanXuatMoi) {
        try {
            ArrayList<String> danhSachNhaSanXuat = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaSanXuatDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaSanXuat.size(); i++) {
                NhaSanXuat tempNhaSanXuat = NhaSanXuat.taoNhaSanXuat(danhSachNhaSanXuat.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.equals(nhaSanXuatCu)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachNhaSanXuat.set(index, nhaSanXuatMoi.toString(this.attributeSeparator));
            }
            String newDatabase = String.join("\n", danhSachNhaSanXuat);
            FileWriter writer = new FileWriter(new File(this.nhaSanXuatDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    public void thayDoiChinhXacNhaBanLe(NhaBanLe nhaBanLeCu, NhaBanLe nhaBanLeMoi) {
        try {
            ArrayList<String> danhSachNhaBanLe = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(this.nhaBanLeDatabasePath)).split("\n")));
            int index = -1;
            for (int i = 1; i < danhSachNhaBanLe.size(); i++) {
                NhaBanLe tempNhaSanXuat = NhaBanLe.taoNhaBanLe(danhSachNhaBanLe.get(i),
                        this.attributeSeparator);
                if (tempNhaSanXuat.equals(nhaBanLeCu)) {
                    index = i;
                    break;
                }
            }
            if (index > 0) {
                danhSachNhaBanLe.set(index, nhaBanLeMoi.toString());
            }
            String newDatabase = String.join("\n", danhSachNhaBanLe);
            FileWriter writer = new FileWriter(new File(this.nhaBanLeDatabasePath));
            writer.write(newDatabase);
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

    }

}
