public class NhaBanLe {
    private String tenNhaBanLe = "";
    private String diaDiem = "";
    private String soDienThoai = "";

    // Constructor
    public NhaBanLe() {
    }

    public NhaBanLe(String tenNhaBanLe, String diaDiem, String soDienThoai) {
        this.tenNhaBanLe = tenNhaBanLe;
        this.diaDiem = diaDiem;
        this.soDienThoai = soDienThoai;
    }

    // Set operations
    public void setTenNhaBanLe(String tenNhaBanLe) {
        this.tenNhaBanLe = tenNhaBanLe;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // get oprations
    public String getTenNhaBanLe() {
        return this.tenNhaBanLe;
    }

    public String getDiaDiem() {
        return this.diaDiem;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public static NhaBanLe taoNhaBanLe(String input, String attributeSeparator) {
        String[] mangCacThuocTinh = input.split(attributeSeparator);
        NhaBanLe newNhaBanLe = new NhaBanLe();
        try {
            newNhaBanLe.setTenNhaBanLe(mangCacThuocTinh[0]);
            newNhaBanLe.setDiaDiem(mangCacThuocTinh[1]);
            newNhaBanLe.setSoDienThoai(mangCacThuocTinh[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newNhaBanLe;
    }

    public boolean equals(NhaBanLe nhaBanLe) {
        if (!(this.getTenNhaBanLe().trim().toLowerCase().equals(nhaBanLe.getTenNhaBanLe().toLowerCase().trim()))) {
            return false;
        }
        if (!(this.getDiaDiem().trim().toLowerCase().equals(nhaBanLe.getDiaDiem().toLowerCase().trim()))) {
            return false;
        }
        if (!(this.getSoDienThoai().trim().toLowerCase().equals(nhaBanLe.getSoDienThoai().toLowerCase().trim()))) {
            return false;
        }
        return true;
    }

    public String toString(String attributeSeparator) {
        return String.format("%s%s%s%s%s", this.tenNhaBanLe, attributeSeparator,
                this.diaDiem, attributeSeparator,
                this.soDienThoai);
    }

}