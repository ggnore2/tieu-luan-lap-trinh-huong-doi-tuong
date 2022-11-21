import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainClass {
    public static ArrayList<String> taoArrayListStringTuCacString(String... inputs) {
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(inputs));
        return result;
    }

    public static void main(String[] args) {
        BoPhanMuaBan boPhanMuaBan = new BoPhanMuaBan();
        BoPhanBaoTriTongThe boPhanBaoTriTongThe = new BoPhanBaoTriTongThe();
        BoPhanVanChuyen boPhanVanChuyen = new BoPhanVanChuyen();

        Kho s = new Kho("Kho A", 0, 1000);
        Kho a = new Kho("Kho A", 0, 9999);
        // boPhanVanChuyen.nhapKho(s);

        // boPhanVanChuyen.nhapKho(a);
        ArrayList<String> thuocTinhs = taoArrayListStringTuCacString("ten kho", "so luong hang trong kho",
                "gioi han cua kho");
        ArrayList<String> giaTris = taoArrayListStringTuCacString("Kho A", "0", "9999");
        try {
            boPhanMuaBan.mua();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}