package cn.peng.studygodpath.frame.shiro;

import cn.peng.studygodpath.frame.shiro.entity.SysPermission;
import cn.peng.studygodpath.frame.shiro.entity.SysRole;
import cn.peng.studygodpath.frame.shiro.entity.User;
import cn.peng.studygodpath.frame.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByName(userName);
        for (SysRole role : user.getRoles()) {
            info.addRole(role.getRole());
            for (SysPermission permission : role.getPermissions()) {
                info.addStringPermission(permission.getName());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByName(userName);
        if (null == user) {
            throw new UnknownAccountException();
        }
        AuthenticationInfo into = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

        return into;
    }
}
