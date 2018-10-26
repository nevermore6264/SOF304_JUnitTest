package test.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import entity.Category;
import entity.Product;
import org.junit.jupiter.api.*;
import service.CategoryDAO;
import service.ProductDAO;

public class DeleteProductTest {
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
        category = new Category("Chichi");
        new CategoryDAO().insertCategory(category);
    }

    @AfterEach
    void tearDown() throws Exception {
        new CategoryDAO().deleteAllCategory();
    }

    /*---------------------------True--------------------------------*/

    // Xoá thành công
    @Test
    public void test1() {
        Product product = new Product("Iphone", "35000000", category.getId());
        productDAO.insertProduct(product);
        boolean kq = productDAO.deleteProduct(product.getId());
        assertEquals(true, kq);
    }


    // Xoa thành công toàn bộ
    @Test
    public void test2() {
        Product product = new Product("Iphone", "35000000", category.getId());
        productDAO.insertProduct(product);
        Product product1 = new Product("Iphone", "35000000", category.getId());
        productDAO.insertProduct(product1);

        boolean kq = productDAO.deleteAllProduct();
        assertEquals(true, kq);
    }

    /*---------------------------False--------------------------------*/

    //Product không tồn tại trong dtb
    @Test
    public void test3() {
        Product product = new Product("Iphone", "35000000", category.getId());
        productDAO.insertProduct(product);

        boolean kq = productDAO.deleteProduct(123);
        assertEquals(false, kq);
    }
}

