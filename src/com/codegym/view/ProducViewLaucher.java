package com.codegym.view;

import com.codegym.utils.AppUtils;

import java.util.*;
public class ProducViewLaucher {
    static Scanner scanner = new Scanner(System.in);
    static ProductView productView = new ProductView();
    public static void run(){
        int choice =-1;
        do {
            menuAccessories();
            try {
                System.out.println("Chọn chức năng");
                System.out.print(" ⭆ ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                }catch (Exception e){
                    System.out.println("Nhập số nguyên");
                }
                switch (choice){
                    case 1:
                        productView.displayProduct(InputOption.SHOW);
                        break;
                    case 2:
                        productView.addProduct();
                        break;
                    case 3:
                        productView.updateProduct();
                        break;
                    case 4:
                        productView.remove();
                        break;
                    case 5:
                        productView.sortByPriceByASC();
                        break;
                    case 9:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            }catch (Exception e){
                System.out.println("Nhập sai! Vui lòng nhập lạiiii");
                e.printStackTrace();
            }
        }while (true);
    }


    public static void menuAccessories() {
        System.out.println();
        System.out.println("\tஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ MENU ACCESORIE ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ");
        System.out.println("\tஐ                    CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM                 ஐ");
        System.out.println("\tஐ                     1. Xem danh sách sản phẩm                    ஐ");
        System.out.println("\tஐ                     2. Thêm mới sản phẩm                         ஐ");
        System.out.println("\tஐ                     3. Sửa thông sản phẩm theo ID                ஐ");
        System.out.println("\tஐ                     4. Xóa sản phẩm theo ID                      ஐ");
        System.out.println("\tஐ                     5. Sắp xếp giá tăng dần                      ஐ");
        System.out.println("\tஐ                     6. Tìm sản phẩm có giá đắt nhất              ஐ");
        System.out.println("\tஐ                     7. Đọc vào file                              ஐ");
        System.out.println("\tஐ                     8. Ghi vào file                              ஐ");
        System.out.println("\tஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ");
        System.out.println("\t⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛");
        System.out.println("\t⊛                    Nhấn '8' để quay lại MAIN MENU                 ⊛ ");
        System.out.println("\t⊛                    Nhấn '0' để thoát chương trình                 ⊛ ");
        System.out.println("\t⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ⊛ ");
    }
}
