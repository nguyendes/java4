package org.example.sys.domain.repository;

import org.example.sys.domain.entity.SanPham;

public interface SanPhamRepo {
    SanPham findById(int id);
    SanPham save(SanPham sanPham);
    boolean update(SanPham sanPham);
    void delete(SanPham sanPham);
}
