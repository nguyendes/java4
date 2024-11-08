//package org.example.mapper;
//
//import org.example.domain.dto.DanhMucDTO;
//import org.example.domain.dto.SanPhamDTO;
//import org.example.domain.entity.SanPham;
//
//public class SanPhamMapper {
//    public static SanPhamDTO toDTO(SanPham sanPham) {
//        if (sanPham == null) {
//            return null; // Trả về null nếu sanPham là null
//        }
//
//        SanPhamDTO dto = new SanPhamDTO();
//        dto.setId(sanPham.getId());
//        dto.setMaSanPham(sanPham.getMaSanPham());
//        dto.setTenSanPham(sanPham.getTenSanPham());
//        dto.setTrangThai(sanPham.getTrangThai());
//        dto.setNgayTao(sanPham.getNgayTao());
//        dto.setNgaySua(sanPham.getNgaySua());
//
//        // Chuyển đổi danh mục nếu nó không phải là null
//        if (sanPham.getDanhMuc() != null) {
//            DanhMucDTO danhMucDTO = new DanhMucDTO();
//            danhMucDTO.setId(sanPham.getDanhMuc().getId());
//            danhMucDTO.setTenDanhMuc(sanPham.getDanhMuc().getTenDanhMuc());
//            dto.setDanhMuc(danhMucDTO);
//        }
//
//        return dto; // Trả về DTO đã được xây dựng
//    }
//}
