public class NhaSanXuat {
    private String tenNhaSanXuat = "";
    private String diaDiem = "";
    private String soDienThoai = "";

    // Constructor
    public NhaSanXuat() {
    }

    public NhaSanXuat(String tenNhaSanXuat, String diaDiem, String soDienThoai) {
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.diaDiem = diaDiem;
        this.soDienThoai = soDienThoai;
    }

    // Set operations
    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // get oprations
    public String getTenNhaSanXuat() {
        return this.tenNhaSanXuat;
    }

    public String getDiaDiem() {
        return this.diaDiem;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public static NhaSanXuat taoNhaSanXuat(String input, String attributeSeparator) {
        String[] mangCacThuocTinh = input.split(attributeSeparator);
        NhaSanXuat newNhaSanXuat = new NhaSanXuat();
        try {
            newNhaSanXuat.setTenNhaSanXuat(mangCacThuocTinh[0]);
            newNhaSanXuat.setDiaDiem(mangCacThuocTinh[1]);
            newNhaSanXuat.setSoDienThoai(mangCacThuocTinh[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newNhaSanXuat;
    }

    public boolean equals(NhaSanXuat nhaSanXuat) {
        if (!(this.getTenNhaSanXuat().toLowerCase().trim().equals(nhaSanXuat.getTenNhaSanXuat()))) {
            return false;
        }
        if (!(this.getDiaDiem().toLowerCase().trim().equals(nhaSanXuat.getDiaDiem()))) {
            return false;
        }
        if (!(this.getSoDienThoai().toLowerCase().trim().equals(nhaSanXuat.getSoDienThoai()))) {
            return false;
        }
        return true;
    }

    public boolean equalsOne(NhaSanXuat nhaSanXuat) {
        if (this.getTenNhaSanXuat().toLowerCase().trim().equals(nhaSanXuat.getTenNhaSanXuat())) {
            return true;
        }
        if (this.getDiaDiem().toLowerCase().trim().equals(nhaSanXuat.getDiaDiem())) {
            return true;
        }
        if (this.getSoDienThoai().toLowerCase().trim().equals(nhaSanXuat.getSoDienThoai())) {
            return true;
        }
        return false;
    }

    public String toString(String attributeSeparator) {
        return String.format("%s%s%s%s%s", this.tenNhaSanXuat, attributeSeparator,
                this.diaDiem, attributeSeparator,
                this.soDienThoai);
    }
}