package com.dati.realm;

import com.dati.pojo.Permission;
import com.dati.pojo.Role;
import com.dati.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class AuthRealm extends AuthorizingRealm {
    @Override
    /**
     * 当进行权限验证时调用此方法解析安全数据得到角色以及权限
     **/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //拿安全数据。
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getRole_name());
            for (Permission per : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(per.getPermission_name());
            }
        }
        return simpleAuthorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
