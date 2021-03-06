package com.codegym.services;
import com.codegym.model.Product;
import com.codegym.utils.CSVUtils;
import java.util.*;




public class ProductService implements IProductService{


    public final static String PATH ="data/products.csv";

    private static ProductService instance;
    private ProductService(){
    }

    public static ProductService getInstance(){
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }




    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH);
        for (String record : records){
            products.add(Product.parse(record));
        }
        return products;
    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = findAll();
        products.add(newProduct);
        CSVUtils.writeFile(PATH,products);
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                String title = newProduct.getTitle();

                if (title != null && !title.isEmpty())
                    product.setTitle(newProduct.getTitle());

                Integer quantity = newProduct.getQuantity();
                if (quantity != null)
                    product.setQuantity(quantity);

                Double price = newProduct.getPrice();
                if (price != null)
                    product.setPrice(price);

                String description = newProduct.getDescription();
                if (description != null && !description.isEmpty())
                    product.setDescription(description);

                CSVUtils.writeFile(PATH,products);
                break;
            }
        }
    }

    @Override
    public void deleteById(int id) {
        List<Product> products = findAll();
        for (int i = 0 ; i < products.size(); i++){
            if(id == products.get(i).getId()){
                products.remove(products.get(i));
            }
        }
        CSVUtils.writeFile(PATH,products);
    }

    @Override
    public Product findById(int id) {
        List<Product> products = findAll();
        for (Product product : products){
            if(id == product.getId()){
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean existsById(int id) {
        return findById(id) !=null;
    }

    @Override
    public List<Product> findAllOrderByPriceASC() {
        List<Product> products = findAll();
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o1.getPrice() - o2.getPrice();
                if (result > 0){
                    return 1;
                }else if(result < 0){
                    return -1;
                }
                return 0;
            }
        });
        return products;
    }



}
