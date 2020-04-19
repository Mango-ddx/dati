package com.dati.realm;

import com.dati.Utils.Md5Utils;
import com.dati.pojo.Permission;
import com.dati.pojo.Role;
import com.dati.pojo.User;
import com.dati.service.UserClassService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private UserClassService classService;
    @Override
    public void setName(String name) {
        super.setName("loginRealm");
    }

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
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = classService.queryUser(usernamePasswordToken.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        }
//因为数据库里面的密码被加密过,所以用同样规则的加密对用户输入的密码进行加密再进行认证。
        String md5Password = Md5Utils.toMd5Password(String.valueOf(usernamePasswordToken.getPassword()), user.getAccount());
        if  (!md5Password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        //访问一下数据。（延迟加载在访问的时候才会进行查询（不用就不查询，访问（调用）才查询）。）
        List<Role> roles = user.getRoles();
        if (!roles.isEmpty()) {
            List<Permission> permissions = roles.get(0).getPermissions();
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, String.valueOf(usernamePasswordToken.getPassword()), this.getName());
        return simpleAuthenticationInfo;
    }
}
