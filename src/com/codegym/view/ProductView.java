package com.codegym.view;

import com.codegym.model.Product;
import com.codegym.services.*;
import com.codegym.utils.AppUtils;

import java.sql.PreparedStatement;
import java.util.*;

public class ProductView {
    private final Scanner scanner = new Scanner(System.in);

    private final IProductService productService;

    public ProductView() {
        productService = ProductService.getInstance();
    }

    public void addProduct() {
        do {
            long id = System.currentTimeMillis() / 1000;
            String title = inputTitle(InputOption.ADD);
            Double price = inputPrice(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            String description = inputDescription();
            Product product = new Product(id, title, price, quantity, description);
            productService.add(product);
            System.out.println("Bạn đã thêm phụ kiện thành công");

        } while (AppUtils.isRestry(InputOption.ADD));
    }

    public void displayProduct(InputOption option) {
        System.out.println("----------------------------------------------------------DANH SÁCH SẢN PHẨM----------------------------------------------------------------");
        System.out.printf("%-15s %-35s %-25s %-25s %-20s\n", "Id", "Tên Sản phẩm", "Giá tiền", "Số lượng", "Mô tả");
        for (Product product : productService.findAll()) {
            System.out.printf("%-15s %-35s %-25s %-25s %-20s\n",
                    product.getId(),
                    product.getTitle(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getDescription()
            );
        }

        if (option == InputOption.SHOW) {
            AppUtils.isRestry(InputOption.SHOW);
        }
    }

    public void updateProduct() {
        boolean isRetry;
        displayProduct(InputOption.UPDATE);
        do {
            long id = inputId(InputOption.UPDATE);
            System.out.println("┌ - - SỬA - - -  ┐");
            System.out.println("| 1.Sửa tên sản phẩm |");
            System.out.println("| 2.Sửa số lượng |");
            System.out.println("| 3.Sửa giá bánh |");
            System.out.println("| 4.Quay lại MENU|");
            System.out.println("└ - - - - - - -  ┘");
            System.out.println("Chọn chức năng: ");
            int option = AppUtils.retryChoose(1, 4);
            Product newProduct = new Product();
            newProduct.setId(id);
            switch (option) {
                case 1:
                    String title = inputTitle(InputOption.UPDATE);
                    newProduct.setTitle(title);
                    productService.update(newProduct);
                    System.out.println("Tên sản phẩm đã cập nhật thành công");
                    break;
                case 2:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("Số lượng sản phẩm đã cập nhật thành công");
                    break;
                case 3:
                    double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("Bạn đã sửa sản phẩm thành công");

                    break;
            }
            isRetry = option != 4 && AppUtils.isRestry(InputOption.UPDATE);
        }
        while (isRetry);
    }

    public void remove() {
        displayProduct(InputOption.DELETE);
        int id;
        while (!productService.existsById(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy sản phẩm cần xóa!");
            System.out.println("Nhấn 'y' để tìm lại Id muốn xóa  \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng vui lòng chọn lại");
                    break;
            }
        }
        System.out.println("❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("❄     1. Nhấn 1 để xoá       ❄");
        System.out.println("❄     2. Nhấn 2 để quay lại  ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            productService.deleteById(id);
            System.out.println("Xóa sản phẩm thành công");
            AppUtils.isRestry(InputOption.DELETE);
        }
    }

    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập id");
                break;
            case UPDATE:
                System.out.println("Nhập Id bạn muốn sửa:");
                break;
            case DELETE:
                System.out.println("Nhập Id bạn muốn xóa:");
                break;
        }

        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = productService.existsById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("ID đã tồn tại");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy Id vui lòng nhập lại");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    private String inputTitle(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập tên muốn sửa");
                break;
        }
        System.out.print("⭆ ");
        return scanner.nextLine();
    }

    private int inputQuantity(InputOption option) {

        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng muốn sửa");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0)
                System.out.println("Số lượng phải lớn hơn 0");
        } while (quantity <= 0);
        return quantity;
    }

    private double inputPrice(InputOption option) {

        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDoubtle();
            if (price <= 0)
                System.out.println("Giá phải lớn hơn 0");
        } while (price <= 0);
        return price;
    }

    private String inputDescription() {
        System.out.println("Mô tả sản phẩm!");
        System.out.print("⭆ ");
        return scanner.nextLine();
    }

    public void displayProductSort(InputOption option, List<Product> products) {
        System.out.println("-----------------------------------------DANH SÁCH BÁNH-------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s \n", "Id", "Tên Sản phẩm", "Giá tiền", "Số lượng", "Mô tả");
        for (Product product : products) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s\n",
                    product.getId(),
                    product.getTitle(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getDescription()
            );
        }
        if (option == InputOption.SHOW) {
            AppUtils.isRestry(InputOption.SHOW);
        }
    }
    public void sortByPriceByASC(){
        displayProductSort(InputOption.SHOW, productService.findAllOrderByPriceASC());
    }







}



