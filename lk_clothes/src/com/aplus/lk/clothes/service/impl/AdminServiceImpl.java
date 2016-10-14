package com.aplus.lk.clothes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Action;
import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.mapper.ActionMapper;
import com.aplus.lk.clothes.mapper.AdminMapper;
import com.aplus.lk.clothes.service.AdminService;
import com.aplus.lk.clothes.utils.StringUtils;
import com.aplus.lk.clothes.utils.UUIDUtils;

@Service(value="adminServiceImpl")
public class AdminServiceImpl implements AdminService, UserDetailsService{

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ActionMapper actionMapper;
	
	@Override
	public void save(Admin admin) {
		admin.setCreateDate(new Date());
		admin.setModifyDate(admin.getCreateDate());
		admin.setId(UUIDUtils.getUUID());
		admin.setIsAccountLocked(false);
		admin.setIsAccountExpired(false);
		admin.setIsCredentialsExpired(false);
		List<String> groupIdList = admin.getGroupIdList();
		admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		adminMapper.save(admin);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("adminId", admin.getId());
		for(String groupId: groupIdList){
			parameterMap.put("groupId", groupId);
			adminMapper.saveAdminGroup(parameterMap);
		}
	}

	@Override
	public void update(Admin admin) {
		admin.setModifyDate(new Date());
		if(admin.getIsAccountEnabled() == null){
			admin.setIsAccountEnabled(false);
		}
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("adminId", admin.getId());
		List<String> persistentGroupIdList = adminMapper.queryGroupIdListByAdminId(admin.getId());
		List<String> groupIdList = admin.getGroupIdList();
		for(String groupId : persistentGroupIdList){
			if(!groupIdList.contains(groupId)){
				parameterMap.put("groupId", groupId);
				adminMapper.deleteAdminGroup(parameterMap);
			}
		}
		for(String groupId : groupIdList){
			if(!persistentGroupIdList.contains(groupId)){
				parameterMap.put("groupId", groupId);
				adminMapper.saveAdminGroup(parameterMap);
			}
		}
		if(StringUtils.isNotEmpty(admin.getPassword())){
			admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		}
		adminMapper.update(admin);
	}

	@Override
	public void delete(String[] ids) {
		adminMapper.deleteAdminGroupByAdminIds(ids);
		adminMapper.delete(ids);
	}

	@Override
	public Pager queryPager(Pager pager, String username, String name) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("username", username);
		parameterMap.put("name", name);
		pager.setDataList(adminMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(adminMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public Admin queryAdminById(String id) {
		return adminMapper.queryAdminById(id);
	}

	@Override
	public Admin queryAdminByUsername(String username) {
		return adminMapper.queryAdminByUsername(username);
	}

	@Override
	public Admin queryAdminWithActionIdById(String id) {
		Admin admin = adminMapper.queryAdminById(id);
		admin.setGroupIdList(adminMapper.queryGroupIdListByAdminId(id));
		return admin;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Admin admin = adminMapper.queryAdminByUsername(username);
		if(admin == null){
			throw new UsernameNotFoundException("管理员["+username+"]不存在");
		}
		admin.setAuthorities(this.getGrantedAuthorities(admin));
		return admin;
	}
	
	@SuppressWarnings("deprecation")
	private List<GrantedAuthority> getGrantedAuthorities(Admin admin) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		List<String> groupIdList = adminMapper.queryGroupIdListByAdminId(admin.getId());
		List<Action> actions = null;
		for(String groupId : groupIdList){
			actions = actionMapper.queryActionsByGroupId(groupId);
			for(Action action : actions){
				if(!grantedAuthorities.contains(action.getRoleName())){
					grantedAuthorities.add(new GrantedAuthorityImpl(action.getRoleName()));
				}
			}
		}
		return grantedAuthorities;
	}

	@Override
	public Admin getLoginAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null || !(principal instanceof Admin)) {
			return null;
		} else {
			return (Admin) principal;
		}
	}

	@Override
	public void updatePasswordAndEmail(Admin admin) {
		if (StringUtils.isNotEmpty(admin.getPassword())) {
			admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		}
		adminMapper.updatePasswordAndEmail(admin);
	}

	@Override
	public void updateLoginIpAndDate(Admin admin) {
		adminMapper.updateLoginIpAndDate(admin);
	}

}
