package com.leyes.app.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.redis.IRedisCache;
import com.leyes.app.redis.RedisObject;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.util.UuidUtil;
import com.leyes.app.web.pojo.AuthRedisCache;
import com.leyes.app.web.pojo.TokenCache;
import com.leyes.app.web.pojo.UserSession;

/**
 * 当前上下文信息类
* @TypeName: SessionContextUtils 
* @Description: 
* @author：Jingpeng 
* @date 2016年7月14日 下午1:32:40 
*
 */
@Component
public class SessionContextUtils {
    
	private static final String userIdHeader="UserId_";
	@Autowired private HttpServletRequest request; 
	@Autowired private HttpServletResponse response; 
	/**
	 * 获取当前上下文请求
	 * 
	 * @Title: getContextRequest
	 * @Description: 
	 * @return
	 * @return HttpServletRequest
	 * @throws
	 */
	public   HttpServletRequest getContextRequest() {
		return request;
	}

	/**
	 * 获取当前上下文响应
	 * 
	 * @Title: getContextResponse
	 * @Description: 
	 * @return
	 * @return HttpServletResponse
	 * @throws
	 */
	public   HttpServletResponse getContextResponse() {
		return response;
	}

	/**
	 * 获取当前上下文Token
	 * 
	 * @Title: getContextToken
	 * @Description: 
	 * @return
	 * @return String
	 * @throws
	 */
	public String getContextToken() {
		HttpServletRequest req = getContextRequest();
		String token = req.getHeader("token");
		return token;
	}
	/**
	 * 获取客户端类型 Android|| ios
	* @Title: getClient 
	* @Description: 
	* @return
	* @return String    
	* @throws
	 */
	public String getClient(){
		HttpServletRequest req = getContextRequest();
		String client = req.getHeader("client");
		return client;
	}
	/**
	 * 请求来自哪个渠道 默认leyes
	* @Title: getChannel 
	* @Description: 
	* @return
	* @return String    
	* @throws
	 */
	public String getChannel(){
		HttpServletRequest req = getContextRequest();
		String client = req.getHeader("channel");
		return client;
	}
	/**
	 * 获取客户端的版本
	* @Title: getVersionCode 
	* @Description: 
	* @return
	* @return int    
	* @throws
	 */
	public int getVersionCode(){
		HttpServletRequest req = getContextRequest();
		String versionCode = req.getHeader("versionCode");
		if(StringUtils.isNotEmpty(versionCode))
			return Integer.parseInt(versionCode);
		else return 0;
	}
	/**
	 * 获取社区Id
	* @Title: getCommunityId 
	* @Description: 
	* @return
	* @return String    
	* @throws
	 */
	public String getCommunityId(){
		HttpServletRequest req = getContextRequest();
		String communityId = req.getHeader("communityId");
		return communityId;
	}
	
	/**
	 * 根据UserId查询Session信息
	 * @param userId
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public UserSession getSessionByUserId(String userId){
	    IRedisCache<RedisObject> authRedisCache = getRedisCache();
	    TokenCache token= authRedisCache.get(userIdHeader+userId, TokenCache.class);
	    return authRedisCache.get(token.getToken(), UserSession.class);
	}*/
	/**
	 * 用UserId存储Session
	 * @param userId
	 * @param session
	 */
	/*@SuppressWarnings("unchecked")
	public void saveSessionWithUserId(String userId,UserSession session){
	    IRedisCache<RedisObject> authRedisCache = getRedisCache();
	    TokenCache token= authRedisCache.get(userIdHeader+userId, TokenCache.class);
	    authRedisCache.setNoExpire(token.getToken(), session);
	}*/
	/**
	 * 获取当前用户认证信息
	* @Title: getContextCurrentUser 
	* @Description: 
	* @param clazz 泛型类型
	* @return
	* @return T    
	* @throws 
	 */ 
	@SuppressWarnings("unchecked")
	public UserSession getContextCurrentUser() { 
		String token = getContextToken(); 
		IRedisCache<RedisObject> authRedisCache = getRedisCache();
		return authRedisCache.get(token, UserSession.class);
	}

	/**
	 * 保存当前登录会话信息永不过期 
	* @Title: loginContextCurrentUser 
	* @Description: 
	* @return void    
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public void saveContextCurrentUserNoExpire(String token, UserSession session){
		IRedisCache<RedisObject> authRedisCache = getRedisCache();
		//TODO
		RedisAuthType auth = session.getRedisAuthTypeEnum();
		String userId="";
		if(auth.equals(RedisAuthType.employee)){
			userId=session.getEmployeeId();
			TokenCache tokenCache = authRedisCache.get(userIdHeader+userId, TokenCache.class);
			  if(tokenCache!=null){
				authRedisCache.Remove(tokenCache.getToken()); 
			}
			  
		}else{
			userId=session.getUserId();
		}
		
		authRedisCache.setNoExpire(token, session);
		authRedisCache.setNoExpire(userIdHeader+userId, new TokenCache(token));
		 
	}
	/**
	 * 移除当前用户登录信息
	 */
	@SuppressWarnings("unchecked")
	public void removeContextCurrentUser(){
		String token=getContextToken();
		IRedisCache<RedisObject> authRedisCache = getRedisCache();
		UserSession session= authRedisCache.get(token, UserSession.class);
		authRedisCache.Remove(userIdHeader+session.getUserId());
		authRedisCache.Remove(token); 
	}
	
	
	
	/**
	 * 获取RedisCache实现
	* @Title: getRedisCache 
	* @Description: 
	* @param clazz
	* @return
	* @return T    
	* @throws
	 */ 
	public static AuthRedisCache getRedisCache(){
		return SpringContextUtils.getBean(AuthRedisCache.class); 
	}
	/**
	 * 
	* @Title: 生成新的Token
	* @Description: 生成新的Token
	* @return Token的值
	* @return String    
	* @throws
	 */
	public static String getNewToken(){
		String token=UuidUtil.getUUIDString();
		return token;
	}
}
