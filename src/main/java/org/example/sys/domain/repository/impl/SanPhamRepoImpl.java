package org.example.sys.domain.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.sys.domain.repository.SPDMRepo;
import org.example.sys.domain.repository.SanPhamRepo;
import org.example.sys.domain.dto.SanPhamDTO;
import org.example.sys.domain.entity.SanPham;
import org.example.config.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SanPhamRepoImpl implements SanPhamRepo, SPDMRepo {

    @Override
    public List<SanPhamDTO> findAll() {
        List<SanPhamDTO> sanPhamDTOS = new ArrayList<>();
        String hql = "SELECT new org.example.sys.domain.dto.SanPhamDTO(sp.id, sp.maSanPham, sp.tenSanPham, sp.trangThai, sp.ngayTao, sp.ngaySua, dm.tenDanhMuc) " +
                "FROM SanPham sp " +
                "JOIN sp.danhMuc dm";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
        return sanPhamDTOS =session.createQuery(hql, SanPhamDTO.class).getResultList();}
        catch(HibernateException e){
            log.error(e.getMessage());
        }return sanPhamDTOS;
    }

    @Override
    public SanPham findById(int id) {
        SanPham sanPham = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            sanPham= (SanPham) session.get(SanPham.class, id);
        }catch (HibernateException e){
            log.error(e.getMessage());
        }
        return sanPham;
    }

    @Override
    public SanPham save(SanPham sanPham) {
        Transaction tx = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.save(sanPham);
            tx.commit();
        }catch (HibernateException e){
            log.error(e.getMessage());
            if(tx!=null){
                tx.rollback();
            }
        }
        return sanPham;
    }

    @Override
    public boolean update(SanPham sanPham) {
        Transaction tx = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.update(sanPham);
            tx.commit();
        }catch (HibernateException e){
            log.error(e.getMessage());
            if(tx!=null){
                tx.rollback();
            }
        }
        return true;
    }

    @Override
    public void delete(SanPham sanPham) {
        Transaction tx = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            tx= session.beginTransaction();
            session.delete(sanPham);
            tx.commit();
        }catch (HibernateException e){
            log.error(e.getMessage());
            if(tx!=null){
                tx.rollback();
            }
        }

    }
}
