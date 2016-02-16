package edu.devernul.project.dao.impl;

import edu.devernul.project.dao.DaoStatus;
import edu.devernul.project.dao.EnumStatus;
import edu.devernul.project.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

public class DaoImplStatus implements DaoStatus {

    @Autowired
    private SessionFactory sessionFactory;

    public Status getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Status p = (Status) session.load(Status.class, new Integer(id));
        return p;
    }

    public synchronized List<Status> findAll() {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Status s order by s.statusId");
        return q.list();
    }

    public synchronized Status create(Status entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    public Status update(Status entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
        return entity;
    }

    public Status getByName(String name) {
        Session s = this.sessionFactory.getCurrentSession();
        Query q;
        q = s.createQuery("from Status s where s.name=:name");
        q.setParameter("name", name);
        return (Status)q.list().get(0);
    }

    public void delete(Status entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    public void initAction(){
        for(EnumStatus statusEl:EnumStatus.values()){
            create(new Status(statusEl.getTitle()));
        }
    }
}
