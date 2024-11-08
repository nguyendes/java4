package org.example.sys.repository;

import org.example.sys.domain.entity.DanhMuc;

import java.util.List;

public interface DanhMucRepo {
    List<DanhMuc> findAll();
    DanhMuc findById(int id);
    DanhMuc save(DanhMuc danhMuc);
    void delete(DanhMuc danhMuc);
    boolean update(DanhMuc danhMuc);
}
