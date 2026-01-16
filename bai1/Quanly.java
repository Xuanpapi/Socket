package bai1;

import java.util.*;

public class Quanly {
    List<User> ds_user = new ArrayList<>();// danh sach user
    Scanner nhap = new Scanner(System.in);

    // them user
    public void them_user(User user) {
        ds_user.add(user);
    }

    public void kiem_tra(String tai_khoan) throws Exception {
        for (User user : ds_user) {
            if (tai_khoan.equalsIgnoreCase(user.get_tk())) {
                throw new Exception("Tai khoan da ton tai !");
            }
        }
    }

    public String them_user() throws Exception {
        System.out.println("Nhap vao ten tai khoan: ");
        String user = nhap.nextLine();
        for (User u : ds_user) {
            if (u.get_tk().equalsIgnoreCase(user)) {
                throw new Exception("Loi , tai khoan da ton tai !");
            }
        }
        System.out.println("Nhap vao mat khau: ");
        String mat_khau = nhap.nextLine();
        User u = new User(user, mat_khau);
        ds_user.add(u);
        return "Them user thanh cong !";

    }

    public String tim_tai_khoan(String tai_khoan) throws Exception {
        if (ds_user.isEmpty()) {
            throw new Exception("Loi danh sach rong !");
        }
        for (User user : ds_user) {
            if (tai_khoan.equalsIgnoreCase(user.get_tk())) {

                return "Tai khoan: " + tai_khoan + "    |   Mat khau: " + user.get_mk();
            }
        }
        return "Khong tim thay tai khoan ";
    }

    public String dang_nhap(String tai_khoan, String mat_khau) throws Exception {
        if (ds_user.isEmpty()) {
            throw new Exception("Loi danh sach rong !");
        }
        boolean kiem_tra = false;
        for (User u : ds_user) {
            if (u.get_tk().equalsIgnoreCase(tai_khoan)) {
                if (u.get_mk().equalsIgnoreCase(mat_khau)) {
                    kiem_tra = true;
                }
            }
        }
        if (kiem_tra) {
            return "Dang nhap thanh cong ";
        }
        return "Thong tin tai khoan mat khau khong chinh xac !";
    }

}
