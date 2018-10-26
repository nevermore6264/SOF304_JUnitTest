package test.product;

import static org.junit.jupiter.api.Assertions.*;

import entity.Category;
import org.junit.jupiter.api.*;

import entity.Product;
import service.CategoryDAO;
import service.ProductDAO;

public class InsertProductTest {
    private static ProductDAO productDAO;
    private Category category;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        productDAO = new ProductDAO();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {

    }

    @BeforeEach
    void setUp() throws Exception {
        category = new Category("Sino");
        new CategoryDAO().insertCategory(category);
    }

    @AfterEach
    void tearDown() throws Exception {
        new CategoryDAO().deleteAllCategory();
    }

    /*---------------------------True--------------------------------*/

    // Nhập đầy đủ các trường
    @Test
    public void test1() {
        Product product = new Product("Iphone", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(true, check);
        productDAO.deleteProduct(product.getId());
    }

    // Name 50 ký tự
    @Test
    public void test2() {
        Product product = new Product("Tuonglaicuabanphuthuocnhieudieunhungchuyeulavaoban", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(true, check);
        productDAO.deleteProduct(product.getId());
    }

    // Name 3 ký tự
    @Test
    public void test3() {
        Product product = new Product("ZXC", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(true, check);
        productDAO.deleteProduct(product.getId());
    }

    //Giá = 1000000000
    @Test
    public void test10() {
        Product product = new Product("CVXS", "1000000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(true, check);
        productDAO.deleteProduct(product.getId());
    }

    //Giá = 100000
    @Test
    public void test12() {
        Product product = new Product("Hello", "100000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(true, check);
        productDAO.deleteProduct(product.getId());
    }


    /*---------------------------False--------------------------------*/

    // Để null trường name
    @Test
    void test5() {
        Product product = new Product(null, "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Name >50 ký tự
    @Test
    public void test4() {
        Product product = new Product("Tuonglaicuabanphuthuocnhieudieunhungchuyeulavaobanmathoi", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Để rỗng trường name
    @Test
    void test6() {
        Product product = new Product("        ", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Trùng name
    @Test
    void test7() {
        Product product1 = new Product("Phone", "35000000", category.getId());
        productDAO.insertProduct(product1);

        Product product = new Product("Phone", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product1.getId());
    }

    // Name có 1 ký tự
    @Test
    void test8() {
        Product product = new Product("A", "35000000", category.getId());

        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Name có ký tự đặc biệt
    @Test
    void test9() {
        Product product = new Product("!#@*^$@$", "35000000", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Null giá
    @Test
    void test14() {
        Product product = new Product("ChiKi", null, category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Null giá và name
    @Test
    void test15() {
        Product product = new Product(null, null, category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Giá > 1000000000
    @Test
    void test16() {
        Product product = new Product("ChiKi", "1000000001", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Giá < 100000
    @Test
    void test17() {
        Product product = new Product("ChiKi", "99999", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }


    // Giá chứa ký tự đặc biệt
    @Test
    void test18() {
        Product product = new Product("ChiKi", "!@#$%^", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }

    // Giá là chữ
    @Test
    void test19() {
        Product product = new Product("ChiKi", "abc", category.getId());
        boolean check = productDAO.insertProduct(product);
        assertEquals(false, check);
        productDAO.deleteProduct(product.getId());
    }
}
