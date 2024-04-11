//package com.blog;
//
//import com.blog.auth.ERole;
//import com.blog.auth.Role;
//import com.blog.auth.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StartupDataLoader implements CommandLineRunner {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
////        Java 11以下的版本不能使用isEmpty()方法。
////        作为替代方案，使用 ! isPresent() 来检查Optional对象是否"不"包含值
//        if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
//            roleRepository.save(new Role(ERole.ROLE_USER));
//        }
//
//        // 检查并创建其他角色...
//    }
//}
