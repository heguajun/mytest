package com.demo.dao;

import com.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */

public interface CustomerDao extends JpaRepository<Customer,Long>,
                                     JpaSpecificationExecutor<Customer> {


    //使用 jpql 的方式查询
    //查询全部
    @Query(value = "from  Customer ")
    public List<Customer> findAll();


    //根据条件查询  ?1  代表占位符 1代表参数搜引

    @Query(value = "from  Customer where  cust_Name = ?1")
    public Customer findName(String cust_Name);
   @Query(value = "update Customer set  custName=?1 where custId=?2")
   @Modifying  //修改
    public void updataC(String custName,Long custId);

  //使用sql 进行查询
    //  nativeQuery：使用本地sql查询
    @Query(value = "select  * from cus_customer",nativeQuery = true)
    public  List<Customer> findSql();

    //方法命名规则查询
    public  Customer findByCustName(String name);

    public List<Customer>findByCustLevelBetween(String num1,String num2);



}
