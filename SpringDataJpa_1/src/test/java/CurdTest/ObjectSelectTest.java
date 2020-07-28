package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.dao.LinkManDao;
import com.demo.entity.Customer;
import com.demo.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ObjectSelectTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

@Test
@Transactional  // 解决   no Session  规避在一个事务中操作


    public  void testFindById(){
    //根据 客户ID 获取所有关联数据()
        Customer customer =customerDao.findOne(97L);
        //能够获取到 当前对象得联系人 则说明当前得查询正确得
        Set<LinkMan> set = customer.getLinkmans();
        for (LinkMan li : set) {
            System.out.println(li);
        }
        // 根据联系ID 获取当前联系人的客户
        // LinkMan  linkMan =  linkManDao.findOne(6L);
        //  Customer customer1 =  linkMan.getCustomer();
        // System.out.println(customer1);

    }

}
