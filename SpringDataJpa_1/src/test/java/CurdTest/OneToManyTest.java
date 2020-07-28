package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.dao.LinkManDao;
import com.demo.entity.Customer;
import com.demo.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")

public class OneToManyTest {
   @Autowired
   private  CustomerDao customerDao;
   @Autowired
   private  LinkManDao linkManDao;

    @Test
        @Transactional //开启事务
        @Rollback(false)  // 设置回滚
        public  void  testAdd(){
     //  第一个创建客户信息
            Customer c = new Customer();
            c.setCustName("TBD云集中心");
            c.setCustLevel("VIP客户");
            c.setCustSource("网络");
            c.setCustIndustry("商业办公");
            c.setCustAddress("昌平区北七家镇");
            c.setCustPhone("010-84389340");

            //创建联系人
            LinkMan l =new LinkMan();
            l.setLkmName("TBD联系人");
            l.setLkmGender("male");
            l.setLkmMobile("13811111111");
            l.setLkmPhone("010-34785348");
            l.setLkmEmail("98354834@qq.com");
            l.setLkmPosition("老师");
            l.setLkmMemo("还行吧");

            //实现添加
            //在客户实体类中包涵了一个联系人

            c.getLinkmans().add(l);
            l.setCustomer(c);

           //调用方法 实现添加
           //知道主从顺序

            customerDao.save(c);
            linkManDao.save(l);
        }
    //  级联删除  直接删除
    @Test
    @Transactional // 开启事务
    @Rollback(false) // 设置不回滚
    public void testDelete(){
       // linkManDao.delete(4L);
        customerDao.delete(96L);
    }
    //级联添加
    @Test
    @Transactional // 开启事务
    @Rollback(false) // 设置不回滚
    public void  testAdd1(){

        //  第一个创建 客户信息
        Customer  c  =  new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        // 创建联系人
        LinkMan  l =  new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("male");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");
        // 实现添加
        // 在客户实体类中包涵了一个联系人
        l.setCustomer(c);
        c.getLinkmans().add(l);
        // 调用方法 实现 添加
        customerDao.save(c);

    }





}

