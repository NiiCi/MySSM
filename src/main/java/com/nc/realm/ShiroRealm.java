package com.nc.realm;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nc.entity.Admin;
import com.nc.entity.Pers;
import com.nc.entity.Roles;
import com.nc.service.AdminService;
import com.nc.service.NavService;


@Component
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	NavService navServie;
	@Autowired
	AdminService adminService;
	//��Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		 Set<String> roles = new HashSet<>();
		 Set<String> stringPermissions = new HashSet<>();
		 //ͨ���û�����ѯ��ɫ,����set����
		 List<Roles> roleList = navServie.queryRolesByName(principal.getPrimaryPrincipal().toString());
		 for (Roles role : roleList) {
			roles.add(role.getRoleCode());
		 }
		 List<Pers> perList = navServie.queryAllPers(principal.getPrimaryPrincipal().toString());
		 for (Pers pers : perList) {
			 stringPermissions.add(pers.getPerCode());
		}
		
		 //ͨ���û�����ѯ��ɫȨ��,����set����
	/*	 List<Pers> perList = adminService.queryPersByRoleid(principal.getPrimaryPrincipal().toString());
		for (Pers pers : perList) {
			stringPermissions.add(pers.getPerCode());
		}*/
	/*	 if (principal.getPrimaryPrincipal().toString().equals("����")) {
			roles.add("admin");
			stringPermissions.add("*");
		}*/
		 SimpleAuthorizationInfo sz = new SimpleAuthorizationInfo();
		 sz.setRoles(roles);
		 sz.setStringPermissions(stringPermissions);
		return sz;
	}

	//��֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authen) throws AuthenticationException {
		//��authen������ת��Ϊ�������µ�UsernamePasswordToken
		UsernamePasswordToken up = (UsernamePasswordToken) authen;
		String name = (String) up.getUsername();
		String truepwd = null;
		/*��ȡ����ʱ,AuthenticationToken ��ȡ�����������,
		���������µ�UsernamePasswordToken�����ȡ����*/
		String password = new String(up.getPassword());
		//ͨ���û�����ѯ��ȷ������
		Admin infoList = adminService.queryAdminByName(name);
		//����ѯ�����븳ֵ��truepwd
		
			truepwd = infoList.getAdmin_password();
			System.out.println("truepwd-----: " + truepwd.toString());
		
		System.out.println("�û���-----: "+name+" , "+"����-----: "+password);
	/*	if (name.equals("����")) {
			System.out.println("����Ա��¼");
		}else if (name.equals("����")) {
			System.out.println("��ͨ�û���¼");
		}else{
			throw new UnknownAccountException("�û���������!!!");
		}*/
		
		//��������֤��֤�ɹ�������һ��AuthenticationInfoʵ��,ʧ�����׳��쳣
		/*SimpleAuthenticationInfo sa=new SimpleAuthenticationInfo(name, "asdasd", getName());*/
		SimpleAuthenticationInfo sa = new SimpleAuthenticationInfo(name, truepwd.toString(), ByteSource.Util.bytes(name), getName());
		return sa;
	}
}

