package org.jit.sose.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jit.sose.config.JWTConfig;
import org.jit.sose.constant.JWTConstant;
import org.jit.sose.entity.query.JWTQuery;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * json web token 生成工具类
 * 
 * @author: dylan
 * @date: 2019-09-10 22:56:49
 */
@Slf4j
public class JWTUtil {

	/**
	 * 创建token
	 * 
	 * @param query 参数封装类
	 * @return token
	 */
	public static String createToken(JWTQuery query) {
		// 设置私有字段
		// 方法二
		HashMap<String, Object> claimsMap = new HashMap<>();
		
		claimsMap.put("userId", query.getUserId()); // 用户id
//		claimsMap.put("userName", query.getUserName());
		claimsMap.put("role", query.getRole()); // 用户角色
		claimsMap.put("menuNameList", query.getMenuNameList()); // 用户权限：可以访问的菜单

		// 方法三
//		JSONObject strj = new JSONObject();
//		String payload = strj.toJSONString();
//		System.out.println("payload:"+payload);

		// 当前时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		log.info("token生成时间：" + DateFormatUtil.formatSecond(now));

		// 签名算法
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//		SecretKey secretKey = generalKey();
		// 密钥
//		System.out.println(generalKey());

		JwtBuilder builder = Jwts.builder()
		// 添加负载
//				.claim("hello", "world") // 方式一、私有字段
//				.setId(jti) // 编号
				.setClaims(claimsMap) // 方式二、私有字段集合
//				.setSubject(payload) // 主题,
//				.setPayload(payload) // 有效载荷和主题二选一
				.setIssuer(JWTConfig.ISS) // 签发人
				.setIssuedAt(now) // 签发时间，设置当前时间，确保每次生成的token的负载都不一样
				.signWith(signatureAlgorithm, generalKey()); // 签名算法以及密匙（头部）

		// 声明有效时间
		long expMillis;
		if (query.getExpiration() != null && query.getExpiration() >= 0) {
			// 自定义过期时间
			expMillis = nowMillis + query.getExpiration();
		} else {
			// 设置为默认时间,2小时
			expMillis = nowMillis + JWTConstant.EXPIRATION;
		}
		Date expDate = new Date(expMillis);
		builder.setExpiration(expDate); // 过期时间
		log.info("token失效时间：" + DateFormatUtil.formatSecond(expDate));

		// 完成生成
		return builder.compact();
	}

	/**
	 * 解析JWT字符串
	 * 
	 * @param token
	 * @return
	 */
	public static Claims parseJWT(String token) {
//		SecretKey secretKey = generalKey();
		return Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();
	}

	/**
	 * 判断token是否过期
	 * 
	 * @param token
	 * @return 未过期:false;过期:true
	 */
	public static boolean isFailure(String token) {
		Boolean b;
		try {
			// 解析token,正常解析未过期
			Claims claims = parseJWT(token);
			log.info("token有效时间：" + DateFormatUtil.formatSecond(claims.getExpiration()));
			b = false;
		} catch (Exception e) {
			// 解析出错，token过期
			b = true;
		}
		return b;
	}

	/**
	 * 如果需要刷新token，返回新的token<br>
	 * 如果不需要则返回null
	 * 
	 * @param token 旧token
	 * @return null/新token
	 */
	public static String refreshToken(String token) {
		Claims claims = parseJWT(token);
		// token创建时间
		Date createTime = claims.getIssuedAt();
		// 需要刷新token的时间= 创建时间+刷新时间
		Date refreshTime = new Date(createTime.getTime() + JWTConstant.RRFRESH_TIME);
		// token过期时间
		Date expiration = getExpiration(claims);
		// 当前时间
		Date nowDate = new Date();

		log.info("token需要刷新的时间：" + DateFormatUtil.formatSecond(refreshTime));
		// 需要刷新token的时间 在旧过期时间或当前之后,不需要刷新token
		if (refreshTime.after(nowDate) || refreshTime.after(expiration)) {
			return null;
		}
		log.info("刷新token");

		// 创建新的token
		HashMap<String, Object> claimsMap = new HashMap<>();

		claimsMap.put("userId", getUserId(claims));
		claimsMap.put("userName", getUsername(claims));
		claimsMap.put("role", getUserRole(claims));
		claimsMap.put("menuNameList", getMenuNameList(claims));

		// 当前时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		log.info("新token生成时间：" + DateFormatUtil.formatSecond(now));

		// 签名算法
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		JwtBuilder builder = Jwts.builder().setClaims(claimsMap) // 方式二、私有字段集合
				.setIssuer(JWTConfig.ISS) // 签发人
				.setIssuedAt(now) // 签发时间，设置当前时间，确保每次生成的token的负载都不一样
				.setExpiration(getExpiration(claims))// 过期时间
				.signWith(signatureAlgorithm, generalKey()); // 签名算法以及密匙（头部）

		log.info("新token失效时间：" + DateFormatUtil.formatSecond(getExpiration(claims)));

		// 完成生成
		return builder.compact();
	}

	/**
	 * 获取过期时间
	 * 
	 * @param token
	 * @return
	 */
	public static Date getExpiration(Claims claims) {
		return claims.getExpiration();
	}

	/**
	 * 获取主题
	 * 
	 * @param token
	 * @return
	 */
	public static String getSubject(Claims claims) {
		return (String) claims.getSubject();
	}

	/**
	 * 从token中获取用户id
	 * 
	 * @param token
	 * @return 用户id
	 */
	public static Integer getUserId(Claims claims) {
		return (Integer) claims.get("userId");
	}

	/**
	 * 从token中获取用户名
	 * 
	 * @param token
	 * @return 用户名
	 */
	public static String getUsername(Claims claims) {
		return (String) claims.get("userName");
	}

	/**
	 * 从token中获取用户角色
	 * 
	 * @param token
	 * @return 用户角色
	 */
	public static String getUserRole(Claims claims) {
		return (String) claims.get("role");
	}

	/**
	 * 从token中获取用户角色
	 * 
	 * @param token
	 * @return 用户角色
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getMenuNameList(Claims claims) {
		return (List<String>) claims.get("menuNameList");
	}

	/**
	 * 加密秘钥
	 * 
	 * @return 加密后的秘钥
	 */
	public static String generalKey() {
//		byte[] encodedKey = Base64.decode(SECRET);
//		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

		String key = MD5Util.md5(JWTConfig.SECRET, "dylan");
		return key;
	}

}
