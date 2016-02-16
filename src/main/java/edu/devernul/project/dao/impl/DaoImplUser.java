package edu.devernul.project.dao.impl;

import edu.devernul.project.dao.DaoUser;
import edu.devernul.project.dao.EnumUser;
import edu.devernul.project.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

public class DaoImplUser implements DaoUser {

    @Autowired
    private SessionFactory sessionFactory;

    public User getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        return p;
    }

    public synchronized List<User> findAll() {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from User s order by s.userId");
        return q.list();
    }

    public synchronized User create(User entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    public User update(User entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
        return entity;
    }

    public User getByName(String name) {
        Session s = this.sessionFactory.getCurrentSession();
        Query q;
        q = s.createQuery("from User s where s.name=:name");
        q.setParameter("name", name);
        return (User)q.list().get(0);
    }

    public void delete(User entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    public void initAction(){

        for(EnumUser userEl:EnumUser.values()){
            User user = new User();
            user.setName(userEl.getNameUser());
            user.setEmail(userEl.geteMail());
            create(user);
        }

    }
}
