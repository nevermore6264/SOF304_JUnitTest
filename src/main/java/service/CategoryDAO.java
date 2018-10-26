package service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connect.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Category;

public class CategoryDAO {

    public boolean checkCategory(String name) {
        Session session = HibernateUtil.getSession(Category.class);
        try {
            session.beginTransaction();
            String hql = "from Category where name = '" + name + "'";
            org.hibernate.Query query = session.createQuery(hql);
            ArrayList<Category> categories = (ArrayList<Category>) query.list();
            session.getTransaction().commit();
            if (categories.size() >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExistID(int id) {
        Session session = HibernateUtil.getSession(Category.class);
        try {
            session.beginTransaction();
            String hql = "from Category where id = " + id + "";
            Query query = session.createQuery(hql);
            ArrayList<Category> categories = (ArrayList<Category>) query.list();
            session.getTransaction().commit();
            if (categories.size() >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertCategory(Category category) {
        Session session = HibernateUtil.getSession(Category.class);
        try {
            Pattern pt = Pattern.compile("[a-zA-Z0-9]{3,10}");
            Matcher mt = pt.matcher(category.getName());
            if (!mt.matches()) {
                return false;
            }
            if (checkCategory(category.getName())) {
                return false;
            }
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(int id, String name) {
        if (name == null) {
            return false;
        }
        Pattern pt = Pattern.compile("[a-zA-Z0-9]{3,10}");
        Matcher mt = pt.matcher(name);
        if (!mt.matches() || name.trim().equals("")) {
            return false;
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Category category = (Category) session.get(Category.class, id);
                if (checkCategory(name)) {
                    return false;
                } else {
                    category.setName(name);
                    category.setId(id);
                    session.update(category);
                    tx.commit();
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean deleteCategory(int id) {

        Session session = HibernateUtil.getSession(Category.class);
        if (isExistID(id) && new ProductDAO().isExistCategoryID(id)) {
            try {
                session.beginTransaction();
                String hql = "delete from Category where id = :id";
                session.createQuery(hql).setInteger("id", id).executeUpdate();
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteAllCategory() {
        Session session = HibernateUtil.getSession(Category.class);
        try {
            new ProductDAO().deleteAllProduct();
            session.beginTransaction();
            String hql = "delete from Category";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIdByName(String name) {
        Session session = HibernateUtil.getSession(Category.class);
        try {
            session.beginTransaction();
            String hql = "from Category where name = '" + name + "'";
            Query query = session.createQuery(hql);
            ArrayList<Category> categories = (ArrayList<Category>) query.list();
            session.getTransaction().commit();
            if (categories.size() == 1) {
                return categories.get(0).getId();

            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }


}
