package CurdTest;

import com.demo.dao.RoleDao;
import com.demo.dao.UserDao;
import com.demo.entity.Role;
import com.demo.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")

public class ManyToMany {

//自动注入
    @Autowired
    UserDao userDao ;
    @Autowired
    RoleDao roleDao ;

@Test
@Transactional  //开启事务
@Rollback(false) //不回滚
    public  void testAdd(){
    //创建两个用户  3个角色
    Users users1= new Users();
    users1.setUsername("张三1");


    Role role=new Role();
    role.setRoleName("角色1");

    //建立关联关系
    users1.getRoles().add(role);
    role.getUsers().add(users1);
    //保存
    roleDao.save(role);
    userDao.save(users1);

    }
    @Test
    @Transactional  //开启事务
    @Rollback(false) //不回滚
    public  void  testDelete(){
    //删除 ID为6得 数据
        userDao.delete(6);
    }

}
