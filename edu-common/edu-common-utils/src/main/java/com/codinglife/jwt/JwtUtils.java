package com.codinglife.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: JWT跨越身份验证工具类
 * JWT token的格式：header.payload.signature 头部.负载.签名
 * header的格式（算法、token的类型）,默认：{"alg": "HS512","typ": "JWT"} alg-签名算法 typ-令牌类型
 * payload的格式 设置：（用户信息、创建时间、生成时间）
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @author: CodingLifeVV
 * @date: 2022.04.24
 */

public class JwtUtils {

    /**
     * 主题
     */
    public static final String SUBJECT = "edu-user";

    /**
     * 过期时间，毫秒，一天
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    /**
     * 密钥
     */
    public static final String APP_SECRET = "secretkeycodinglifevv";

    /**
     * 创建 token 信息
     * @param id
     * @return
     */
    public static String createToken(String id, String username){

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(SUBJECT)
                .setIssuedAt(new Date())  // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)    // 自定义参数声明值
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 验证 token 是否存在且有效
     * @param jwtToken
     * @return
     */
    public static boolean validateToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 验证 token 是否存在且有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken))
            return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }

}
