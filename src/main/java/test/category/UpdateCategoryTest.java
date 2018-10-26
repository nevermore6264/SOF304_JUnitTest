package test.category;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Category;
import service.CategoryDAO;

public class UpdateCategoryTest {
    private static CategoryDAO categoryDAO;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        categoryDAO = new CategoryDAO();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }


    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /*---------------------------True--------------------------------*/

    // Nhập đầy đủ các trường
    @Test
    public void test1() {
        Category category = new Category("SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"Kingki");
        assertEquals(true, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name 10 ký tự
    @Test
    public void test2() {
        Category category = new Category(1, "SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"Motorolaaa");
        assertEquals(true, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name 3 ký tự
    @Test
    public void test4() {
        Category category = new Category(1, "SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"ICM");
        assertEquals(true, kq);
        categoryDAO.deleteCategory(category.getId());
    }


    /*---------------------------False--------------------------------*/

    // Name 11 ký tự
    @Test
    public void test3() {
        Category category = new Category(1, "SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"Motorolaaaa");
        assertEquals(false, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Để null trường name
    @Test
    void test5() {
        Category category = new Category("SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),null);
        assertEquals(false, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Để rỗng trường name
    @Test
    void test6() {
        Category category = new Category("SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"         ");
        assertEquals(false, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Trùng name
    @Test
    void test7() {
        Category category1 = new Category("Nokia");
        categoryDAO.insertCategory(category1);

        Category category = new Category("SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"Nokia");
        assertEquals(false, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name có 1 ký tự
    @Test
    void test8() {
        Category category = new Category("SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"A");
        assertEquals(false, kq);
        categoryDAO.deleteCategory(category.getId());
    }

    // Name có ký tự đặc biệt
    @Test
    void test9() {
        Category category = new Category("SVM");
        categoryDAO.insertCategory(category);
        boolean kq = categoryDAO.updateCategory(categoryDAO.getIdByName(category.getName()),"%##$@%");
        assertEquals(false, kq);
        categoryDAO.deleteCategory(category.getId());
    }

}
