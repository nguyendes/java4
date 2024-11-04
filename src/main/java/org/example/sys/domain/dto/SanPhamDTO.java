package org.example.sys.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SanPhamDTO {
    private int id;
    private String maSanPham;
    private String tenSanPham;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String tenDanhMuc;
}
