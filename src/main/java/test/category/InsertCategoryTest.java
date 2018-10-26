package test.category;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Category;
import service.CategoryDAO;

public class InsertCategoryTest {
    private static CategoryDAO categoryDAO;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        categoryDAO = new CategoryDAO();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }
    
    /*---------------------------True--------------------------------*/

    // Nhập đầy đủ các trường
    @Test
    public void test1() {
        Category category = new Category("Samsung");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(true, check);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name 10 ký tự
    @Test
    public void test2() {
        Category category = new Category(1, "Dienthoaij");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(true, check);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name 3 ký tự
    @Test
    public void test3() {
        Category category = new Category(1, "App");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(true, check);
        categoryDAO.deleteCategory(category.getId());
    }

    /*---------------------------False--------------------------------*/

    // Name 11 ký tự
    @Test
    public void test4() {
        Category category = new Category(1, "Dienthoaijj");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(false, check);
        categoryDAO.deleteCategory(category.getId());
    }

    // Để null trường name
    @Test
    void test5() {
        Category category = new Category(null);
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(false, check);
        categoryDAO.deleteCategory(category.getId());
    }

    // Để rỗng trường name
    @Test
    void test6() {
        Category category = new Category("         ");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(false, check);
        categoryDAO.deleteCategory(category.getId());
    }

    // Trùng name
    @Test
    void test7() {
        Category category1 = new Category("Nokia");
        categoryDAO.insertCategory(category1);

        Category category = new Category("Nokia");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(false, check);
        categoryDAO.deleteCategory(category1.getId());
    }

    // Name có 1 ký tự
    @Test
    void test8() {
        Category category = new Category("A");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(false, check);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name có ký tự đặc biệt
    @Test
    void test9() {
        Category category = new Category("%##$@%");
        boolean check = categoryDAO.insertCategory(category);
        assertEquals(false, check);
        categoryDAO.deleteCategory(category.getId());
    }

}
