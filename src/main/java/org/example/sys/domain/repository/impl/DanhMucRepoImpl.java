package org.example.sys.domain.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.sys.domain.entity.DanhMuc;
import org.example.config.utils.HibernateUtil;
import org.example.sys.domain.repository.DanhMucRepo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DanhMucRepoImpl implements DanhMucRepo {

    @Override
    public List<DanhMuc> findAll() {
        List<DanhMuc> danhMucs = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            danhMucs = session.createQuery("from DanhMuc", DanhMuc.class).list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return danhMucs;
    }

    @Override
    public DanhMuc findById(int id) {
        DanhMuc danhMuc = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            danhMuc = session.get(DanhMuc.class, id);
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return danhMuc;
    }

    @Override
    public DanhMuc save(DanhMuc danhMuc) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(danhMuc);
            tx.commit();
            return danhMuc;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public void delete(DanhMuc danhMuc) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(danhMuc);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean update(DanhMuc danhMuc) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(danhMuc);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            log.error(e.getMessage());
            if (tx != null) tx.rollback();
        }
        return false;
    }
}
