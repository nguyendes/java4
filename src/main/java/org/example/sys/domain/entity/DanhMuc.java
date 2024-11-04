package org.example.sys.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "danh_muc")
@Data
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_danh_muc")
    private String maDanhMuc;

    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name= "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

}
