package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.entity.Customer;
import com.sun.deploy.panel.PathEditor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import javax.swing.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //在执行某些方法之前加载当前配置
public class SpecTest {
   @Autowired
    CustomerDao customerDao;
   //  精确匹配查询,单条件查询
 @Test
    public  void Specfind(){
        Specification<Customer> spec= new Specification<Customer>() {
            //  用来封装自定义条件
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
             //1：获取比较的属性
                Path<Object> custName=root.get("CustName");
                //2: 构建查询条件
                Predicate predicate =criteriaBuilder.equal(custName,"tom");
                   //返惠构建好的条件
                return predicate;
            }
        };
        //调用 查询方法
        Customer customer =customerDao.findOne(spec);
        System.out.println(customer);
    }
    //多条件拼装
    @Test
    public  void testSPec1(){
     Specification<Customer> specification=new Specification<Customer>() {
         public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            //第一  获取比较的属性
             Path<Object> custName=root.get("custName");
             Path<Object>  custIndustry=root.get("custIndustry");
             Path<Object>  custLevel =root.get("custLevel");
            //构造查询条件

             Predicate p1= criteriaBuilder.equal(custName,"tom");
             Predicate p2= criteriaBuilder.equal(custIndustry,"1");
             Predicate p3  =criteriaBuilder.equal(custLevel,"1");

             //将多个条件组合起来
             Predicate and =criteriaBuilder.and(p1,p2);
             Predicate  or = criteriaBuilder.or(and,p3); //custName =? and  xx=？  or xx=?
             return or;

         }
     };
        Customer list=customerDao.findOne(specification);
        // List<Customer> list=customerDao.findAll(specification);
        System.out.println(list);
    }
    //通过条件实现模糊查询
    @Test
    public void testLike(){
      Specification<Customer> spec= new Specification<Customer>() {
          public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
             //custname like "x"
              //获取查询的属性  custName
              Path<Object> custName= root.get("custName");
              //拼接查询方式
              Predicate like= criteriaBuilder.like(custName.as(String.class),"%t%");

              return like;
          }
      };
      //查询
        List<Customer> list =customerDao.findAll(spec);
        System.out.println(list);
        //添加排序
        //倒叙 大到小 Sort.Direction.DESC
        //升序 小到大   Sort.Direction.ASC
        //获取被排序的字段

        Sort sort= new Sort(Sort.Direction.DESC,"custId");
         //查询
        List<Customer> list1 =customerDao.findAll(spec,sort);
        System.out.println(list1);
    }

   @Test
    public  void  tsstPage(){
       Specification spec= null;
     //创建 PageRequest 对象
        /**
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *      第一个参数：当前查询的页数（从0开始）
         *      第二个参数：每页查询的数量
         */
        PageRequest pageRequest =new PageRequest(0,2);
        //分页查询
        Page<Customer> page=customerDao.findAll(null,pageRequest);
        //获取到数据集合列表的总和
        System.out.println(page.getContent());
        //获取总条数
        System.out.println(page.getTotalElements());
        // 获取总页数
        System.out.println(page.getTotalPages());
    }



}
