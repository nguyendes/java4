package org.example.sys.repository;

import org.example.sys.domain.dto.SanPhamDTO;

import java.util.List;

public interface SPDMRepo {
    List<SanPhamDTO> findAll();
}
