package service;


import connect.HibernateUtil;
import entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import entity.Product;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDAO {

    public boolean checkProduct(String name) {
        Session session = HibernateUtil.getSession(Product.class);
        try {
            session.beginTransaction();
            String hql = "from Product where name = '" + name + "'";
            org.hibernate.Query query = session.createQuery(hql);
            ArrayList<Product> products = (ArrayList<Product>) query.list();
            session.getTransaction().commit();
            if (products.size() >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertProduct(Product product) {
        Session session = HibernateUtil.getSession(Product.class);
        if (new CategoryDAO().isExistID(product.getCategory())) {
            try {
                Pattern pt = Pattern.compile("[a-zA-Z0-9\\s]{3,50}");
                Matcher mt = pt.matcher(product.getName());
                if (!mt.matches() || checkProduct(product.getName()) || product.getName().trim().equals("")) {
                    return false;
                }
                Pattern pt1 = Pattern.compile("[0-9]{5,20}");
                Matcher mt1 = pt1.matcher(product.getPrice());
                if (!mt1.matches()) {
                    return false;
                } else {
                    if (Integer.parseInt(product.getPrice()) > 1000000000 || Integer.parseInt(product.getPrice()) < 100000) {
                        return false;
                    }
                }
                session.beginTransaction();
                session.save(product);
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

    public boolean updateProduct(int id, String name, String price, int category) {
        if (name == null || price == null) {
            return false;
        }
        Pattern pt = Pattern.compile("[a-zA-Z0-9]{3,50}");
        Matcher mt = pt.matcher(name);

        Pattern pt1 = Pattern.compile("[0-9]{1,10}");
        Matcher mt1 = pt1.matcher(price);

        if (!mt.matches() && mt1.matches()) {
            return false;
        } else {
            if (Integer.parseInt(price) > 1000000000 || Integer.parseInt(price) < 100000) {
                return false;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx;
            try {
                tx = session.beginTransaction();

                Product product = (Product) session.get(Product.class, id);
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);

                session.update(product);
                tx.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean deleteProduct(int id) {
        Session session = HibernateUtil.getSession(Product.class);
        if (isExistID(id)) {
            try {
                session.beginTransaction();
                String hql = "delete from Product where id = :id";
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

    private boolean isExistID(int id) {
        Session session = HibernateUtil.getSession(Product.class);
        try {
            session.beginTransaction();
            String hql = "from Product where id = " + id + "";
            Query query = session.createQuery(hql);
            ArrayList<Product> products = (ArrayList<Product>) query.list();
            session.getTransaction().commit();
            if (products.size() >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteAllProduct() {
        Session session = HibernateUtil.getSession(Product.class);
        try {
            session.beginTransaction();
            String hql = "delete from Product";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isExistCategoryID(int category) {
        Session session = HibernateUtil.getSession(Product.class);
        try {
            session.beginTransaction();
            String hql = "from Product where category = " + category + "";
            Query query = session.createQuery(hql);
            ArrayList<Product> products = (ArrayList<Product>) query.list();
            session.getTransaction().commit();
            if (products.size() == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public int getIdByName(String name) {
        Session session = HibernateUtil.getSession(Product.class);
        try {
            session.beginTransaction();
            String hql = "from Product where name = '" + name + "'";
            Query query = session.createQuery(hql);
            ArrayList<Product> products = (ArrayList<Product>) query.list();
            session.getTransaction().commit();
            if (products.size() == 1) {
                return products.get(0).getId();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

}
