package bai1;

public class User {
    private String tai_khoan;
    private String mat_khau;

    public User(String tai_khoan, String mat_khau) {
        this.tai_khoan = tai_khoan;
        this.mat_khau = mat_khau;
    }

    public String toString() {
        return "Tai khoan: " + tai_khoan + "    |   Mat khau: " + mat_khau;
    }

    public String get_tk() {
        return tai_khoan;
    }

    public String get_mk() {
        return mat_khau;
    }

}
