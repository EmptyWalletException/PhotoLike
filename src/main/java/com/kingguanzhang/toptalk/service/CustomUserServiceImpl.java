package com.kingguanzhang.toptalk.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.manager.entity.Role;
import com.kingguanzhang.toptalk.manager.repositories.RoleRepository;
import com.kingguanzhang.toptalk.repository.UserRepository;

/**
 * 用于security加载用户角色权限的service;
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (null == username || "".equals(username.trim())){
            throw new RuntimeException("未能获取到账号");
        }

        //通过传入的登录账号获取数据库中查出后封装的用户(就是页面上的登录名和密码)所在的实体类;
        User user = new User();
        user.setAccount(username);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");//注意id因为是Long类型所以会有默认值0,这里要忽略掉id的匹配;
        Example<User> example = Example.of(user,exampleMatcher);;
        Optional<User> userOptional = userRepository.findOne(example);//查出用户;
        if (false == userOptional.isPresent()) { //判断,当isPresent()返回false时表示optional里的value是null;
            throw new UsernameNotFoundException("用户不存在");
        }

        long id = userOptional.get().getId();

        //然后通过用户id查出账号所拥有的角色;
        List<Role> roleList = roleRepository.findRoleByUserId(id);


        //新建一个ArrayList<SimpleGrantedAuthority>用于装载角色信息;
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把遍历出来的角色添加到authorities 就万事大吉。
        for(Role role:roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        //最后返回security能解析的信息,原始密码不加密,但security要求必须加密,所以需要取出来后加密;//构造器依次传入(登录账号,密码,角色列表);注意这里密码需要提前加密,否则报错:id = null;
        //String password = new BCryptPasswordEncoder().encode(userOptional.get().getPassword());//加密密码
        //return new org.springframework.security.core.userdetails.User(userOptional.get().getAccount(),password, authorities);

        //此处形参密码是在数据库中储存的加密后的密码,直接取出即可;
        return new org.springframework.security.core.userdetails.User(userOptional.get().getAccount(),
                userOptional.get().getPassword(), authorities);
    }
}
