package test;

import entity.Category;
import entity.Product;
import service.CategoryDAO;
import service.ProductDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Demo {
    private static Product product;

    private static Category category;

    public static void main(String[] args) {
        category = new Category("Sino");
        new CategoryDAO().insertCategory(category);
        //System.out.println(new CategoryDAO().getIdByName(category.getName()));

        product = new Product("Iphone", "3500000", new CategoryDAO().getIdByName(category.getName()));
        new ProductDAO().insertProduct(product);

        System.out.println(new ProductDAO().getIdByName(product.getName()));

        int id = new CategoryDAO().getIdByName(category.getName());
        System.out.println(id);
        boolean check = new ProductDAO().updateProduct(new ProductDAO().getIdByName(product.getName()), "ChiPi", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        System.out.println(">>>>>>>>>>>>>>   " + check);


        new ProductDAO().deleteAllProduct();
        new CategoryDAO().deleteAllCategory();
    }
}
