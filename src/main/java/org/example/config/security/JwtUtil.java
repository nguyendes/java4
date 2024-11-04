//package org.example.config.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//
//import java.util.Date;
//
//public class JwtUtil {
//    private static final String SECRET_KEY = "your_secret_key"; // Khóa bí mật
//    private static final long EXPIRATION_TIME = 86400000; // 1 ngày
//
//    public static String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//    public static String validateToken(String token) {
//        try {
//            Claims claims = Jwts.parser()
//                    .setSigningKey(SECRET_KEY)
//                    .parseClaimsJws(token)
//                    .getBody();
//            return claims.getSubject();
//        } catch (SignatureException e) {
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
//
