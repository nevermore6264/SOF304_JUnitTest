package test.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import entity.Category;
import org.junit.jupiter.api.*;
import service.CategoryDAO;

public class DeleteCategoryTest {
    private static CategoryDAO categoryDAO;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        categoryDAO = new CategoryDAO();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {

    }

    @BeforeEach
    void setUp() throws Exception {

    }

    @AfterEach
    void tearDown() throws Exception {

    }

    /*---------------------------True--------------------------------*/

    // Xoá thành công
    @Test
    public void test1() {
        Category category = new Category("Chichi");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.deleteCategory(category.getId());
        assertEquals(true, kq);
    }


    // Xoa thành công toàn bộ
    @Test
    public void test2() {
        Category category = new Category("Chichi");
        categoryDAO.insertCategory(category);
        Category category1 = new Category("Kichi");
        categoryDAO.insertCategory(category1);

        boolean kq = categoryDAO.deleteAllCategory();
        assertEquals(true, kq);
    }

    /*---------------------------False--------------------------------*/

    //Category không tồn tại trong dtb
    @Test
    public void test3() {
        Category category = new Category("Chichi");
        categoryDAO.insertCategory(category);

        boolean kq = categoryDAO.deleteCategory(3);
        assertEquals(false, kq);
    }
}

