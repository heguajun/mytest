package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.HTMLDocument;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //在执行某些方法之前加载当前配置
public class CURDTest {
    @Autowired
    private CustomerDao customerDao;

//保存
    @Test
     public  void  testSave(){
         Customer c= new Customer();
         c.setCustName("张泉蛋");
         customerDao.save(c);
     }
     //修改
    @Test
     public  void  testUpdata(){
        Customer customer =customerDao.findOne(4L);
        //修改对象的信息
         customer.setCustAddress("惠州xxx");
         customer.setCustLevel("12");
         //修改
         customerDao.save(customer);
     }
     //删除
    @Test
    public void testDelete(){
        customerDao.delete(2L);
    }
    //查询全部
    @Test
    public  void  testAll(){
        List<Customer>list=customerDao.findAll();
        System.out.println(list);
    }
    @Test
    public  void testOne(){
        Customer customer = customerDao.findName("张全蛋");
        System.out.println(customer);
    }
    @Test
     @Transactional //做修改的操作
    public void testUpdate(){
        customerDao.updataC("asdasad",3L);
    }

   @Test
    public  void findSQL(){
      List<Customer> list=  customerDao.findSql();
       System.out.println(list);
   }

   //测试方法规则查询
    @Test
    public  void  testffgz(){
        Customer customer=customerDao.findByCustName("we");
        System.out.println(customer);
    }

    @Test
    public void  testSpecifications (){
        //使用匿名内部类 创建一个Specifications
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("custName").as(String.class),"tom% ");
            }
        };

        //调用查询
       List <Customer> customer=  customerDao.findAll(spec);
        System.out.println(customer);
    }


}
