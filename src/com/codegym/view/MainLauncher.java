package com.codegym.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {

    public static void menuOption(){
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nChọn chức năng ");
                System.out.print("⭆ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
//                        UserViewLauncher.run();
                        break;
                    case 2:
//                        ProductViewLaucher.run();
                        break;
                    case 3:
//                        OrderViewLauncher.run();
                        break;
                    case 0:
//                        AppUtils.exit();
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        menuOption();
                }

            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }


    public static void mainMenu(){
        System.out.println("CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM");
        System.out.println("Chọn chức năng (để tiếp tục)");
        System.out.println("1.Xem danh sách");
        System.out.println("2.Thêm mới");
        System.out.println("3.Cập nhật");
        System.out.println("4.Xóa theo id");
        System.out.println("5.Sắp xếp");
        System.out.println("6.Tìm sản phẩm có giá đắt nhất");
        System.out.println("7.Đọc từ file");
        System.out.println("8.Ghi vào file");
        System.out.println("9.THoát");

    }
}
