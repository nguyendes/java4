package org.example.sys.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "san_pham")
@Data
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_san_pham_danh_muc"))
    private DanhMuc danhMuc;
}
