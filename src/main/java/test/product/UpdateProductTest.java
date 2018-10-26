package test.product;

import static org.junit.jupiter.api.Assertions.*;

import entity.Category;
import org.junit.jupiter.api.*;

import entity.Product;
import service.CategoryDAO;
import service.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class UpdateProductTest {
    private static ProductDAO productDAO;
    private static CategoryDAO categoryDAO;
    private Category category;
    private Product product;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {

    }

    @BeforeEach
    void setUp() throws Exception {
        category = new Category("Sino");
        categoryDAO.insertCategory(category);
        product = new Product("Iphone", "3500000", new CategoryDAO().getIdByName(category.getName()));
        productDAO.insertProduct(product);
    }

    @AfterEach
    void tearDown() throws Exception {
        categoryDAO.deleteAllCategory();
        productDAO.deleteAllProduct();
    }

    /*---------------------------True--------------------------------*/

    // Nhập đầy đủ các trường
    @Test
    public void test1() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "ChiPi", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }

    // Name 50 ký tự
    @Test
    public void test2() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "Tuonglaicuabanphuthuocnhieudieunhungchuyeulavaoban", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }

    // Name 49 ký tự
    @Test
    public void test3() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "Tuonglaicuabanphuthuocnhieudieunhungchuyeulavaoba", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }

    //Giá = 1000000000
    @Test
    public void test10() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), "1000000000", new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }

    //Giá = 999999999
    @Test
    public void test11() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), "999999999", new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }

    //Giá = 100000
    @Test
    public void test12() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), "100000", new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }

    //Giá = 100001
    @Test
    public void test13() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), "100001", new CategoryDAO().getIdByName(category.getName()));
        assertEquals(true, check);
    }



    /*---------------------------False--------------------------------*/

    // Để null trường name
    @Test
    void test5() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), null, product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Name >50 ký tự
    @Test
    public void test4() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "Tuonglaicuabanphuthuocnhieudieunhungchuyeulavaobanmathoi", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Để rỗng trường name
    @Test
    void test6() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "         ", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Trùng name
    @Test
    void test7() {
        Product product1 = new Product("Phone", "35000000", category.getId());
        productDAO.insertProduct(product1);

        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "Phone", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Name có 1 ký tự
    @Test
    void test8() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "A", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Name có ký tự đặc biệt
    @Test
    void test9() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), "$!!%@^@^#$", product.getPrice(), new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Null giá
    @Test
    void test14() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), null, new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Null giá và name
    @Test
    void test15() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), null, null, new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Giá > 1000000000
    @Test
    void test16() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), "1000000001", new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

    // Giá < 100000
    @Test
    void test17() {
        boolean check = productDAO.updateProduct(new ProductDAO().getIdByName(product.getName()), product.getName(), "99999", new CategoryDAO().getIdByName(category.getName()));
        assertEquals(false, check);
    }

}
