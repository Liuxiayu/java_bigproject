package com.ken.wms.common.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ken.wms.common.service.Interface.CustomerManageService;
import com.ken.wms.common.util.EJConvertor;
import com.ken.wms.common.util.FileUtil;
import com.ken.wms.dao.CustomerMapper;
import com.ken.wms.dao.StockOutMapper;
import com.ken.wms.domain.Customer;
import com.ken.wms.domain.StockOutDO;
import com.ken.wms.exception.CustomerManageServiceException;
import com.ken.wms.util.aop.UserOperation;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerManageServiceImpl implements CustomerManageService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private StockOutMapper stockOutMapper;
    @Autowired
    private EJConvertor ejConvertor;

   
    @Override
    public Map<String, Object> selectById(Integer customerId) throws CustomerManageServiceException {
       
        Map<String, Object> resultSet = new HashMap<>();
        List<Customer> customers = new ArrayList<>();
        long total = 0;

        Customer customer;
        try {
            customer = customerMapper.selectById(customerId);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
            throw new CustomerManageServiceException(e);
        }

        if (customer != null) {
            customers.add(customer);
            total = 1;
        }

        resultSet.put("data", customers);
        resultSet.put("total", total);
        return resultSet;
    }

   
    @Override
    public Map<String, Object> selectByName(int offset, int limit, String customerName) throws CustomerManageServiceException {
        
        Map<String, Object> resultSet = new HashMap<>();
        List<Customer> customers;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;


        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                customers = customerMapper.selectApproximateByName(customerName);
                if (customers != null) {
                    PageInfo<Customer> pageInfo = new PageInfo<>(customers);
                    total = pageInfo.getTotal();
                } else
                    customers = new ArrayList<>();
            } else {
                customers = customerMapper.selectApproximateByName(customerName);
                if (customers != null)
                    total = customers.size();
                else
                    customers = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            throw new CustomerManageServiceException(e);
        }

        resultSet.put("data", customers);
        resultSet.put("total", total);
        return resultSet;
    }


    @Override
    public Map<String, Object> selectByName(String customerName) throws CustomerManageServiceException {
        return selectByName(-1, -1, customerName);
    }

    @Override
    public Map<String, Object> selectAll(int offset, int limit) throws CustomerManageServiceException {
       
        Map<String, Object> resultSet = new HashMap<>();
        List<Customer> customers;
        long total = 0;
        boolean isPagination = true;

        
        if (offset < 0 || limit < 0)
            isPagination = false;

        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                customers = customerMapper.selectAll();
                if (customers != null) {
                    PageInfo<Customer> pageInfo = new PageInfo<>(customers);
                    total = pageInfo.getTotal();
                } else
                    customers = new ArrayList<>();
            } else {
                customers = customerMapper.selectAll();
                if (customers != null)
                    total = customers.size();
                else
                    customers = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            throw new CustomerManageServiceException(e);
        }

        resultSet.put("data", customers);
        resultSet.put("total", total);
        return resultSet;
    }

   
    @Override
    public Map<String, Object> selectAll() throws CustomerManageServiceException {
        return selectAll(-1, -1);
    }

    private boolean customerCheck(Customer customer) {
        return customer.getName() != null && customer.getPersonInCharge() != null && customer.getTel() != null
                && customer.getEmail() != null && customer.getAddress() != null;
    }

  
    @UserOperation(value = "添加客户信息")
    @Override
    public boolean addCustomer(Customer customer) throws CustomerManageServiceException {

        // 插入新的记录
        if (customer != null) {
            // 验证
            if (customerCheck(customer)) {
                try {
                    if (null == customerMapper.selectByName(customer.getName())) {
                        customerMapper.insert(customer);
                        return true;
                    }
                } catch (PersistenceException e) {
                    throw new CustomerManageServiceException(e);
                }
            }
        }
        return false;
    }

   
    @UserOperation(value = "修改客户信息")
    @Override
    public boolean updateCustomer(Customer customer) throws CustomerManageServiceException {

       
        if (customer != null) {
          
            if (customerCheck(customer)) {
                try {
                   
                    Customer customerFromDB = customerMapper.selectByName(customer.getName());
                    if (customerFromDB == null || customerFromDB.getId().equals(customer.getId())) {
                        customerMapper.update(customer);
                        return true;
                    }
                } catch (PersistenceException e) {
                    throw new CustomerManageServiceException(e);
                }
            }
        }
        return false;
    }

   
    @UserOperation(value = "删除客户信息")
    @Override
    public boolean deleteCustomer(Integer customerId) throws CustomerManageServiceException {

        try {
          
            List<StockOutDO> records = stockOutMapper.selectByCustomerId(customerId);
            if (records != null && records.size() > 0) {
                return false;
            } else {
          
                customerMapper.deleteById(customerId);
                return true;

            }
        } catch (PersistenceException e) {
            throw new CustomerManageServiceException(e);
        }
    }

    
    @UserOperation(value = "导入客户信息")
    @Override
    public Map<String, Object> importCustomer(MultipartFile file) throws CustomerManageServiceException {
     
        Map<String, Object> result = new HashMap<>();
        int total = 0;
        int available = 0;

      
        try {
            List<Customer> customers = ejConvertor.excelReader(Customer.class, FileUtil.convertMultipartFileToFile(file));
            if (customers != null) {
                total = customers.size();

             
                List<Customer> availableList = new ArrayList<>();
                for (Customer customer : customers) {
                    if (customerCheck(customer)) {
                        if (customerMapper.selectByName(customer.getName()) == null)
                            availableList.add(customer);
                    }
                }

                available = availableList.size();
                if (available > 0) {
                    customerMapper.insertBatch(availableList);
                }
            }
        } catch (PersistenceException | IOException e) {
            throw new CustomerManageServiceException(e);
        }

        result.put("total", total);
        result.put("available", available);
        return result;
    }

   
    @UserOperation(value = "导出客户信息")
    @Override
    public File exportCustomer(List<Customer> customers) {
        if (customers == null)
            return null;

        return ejConvertor.excelWriter(Customer.class, customers);
    }

}
