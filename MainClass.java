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

        Kho s = new Kho("Kho A", 5, 19999);
        Kho a = new Kho("Kho A", 5, 20000);

        boPhanMuaBan.ban();

    }

}