package com.ken.wms.common.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ken.wms.common.service.Interface.GoodsManageService;
import com.ken.wms.common.util.EJConvertor;
import com.ken.wms.common.util.FileUtil;
import com.ken.wms.dao.GoodsMapper;
import com.ken.wms.dao.StockInMapper;
import com.ken.wms.dao.StockOutMapper;
import com.ken.wms.dao.StorageMapper;
import com.ken.wms.domain.Goods;
import com.ken.wms.domain.StockInDO;
import com.ken.wms.domain.StockOutDO;
import com.ken.wms.domain.Storage;
import com.ken.wms.exception.GoodsManageServiceException;
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
public class GoodsManageServiceImpl implements GoodsManageService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockInMapper stockInMapper;
    @Autowired
    private StockOutMapper stockOutMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private EJConvertor ejConvertor;

   
    @Override
    public Map<String, Object> selectById(Integer goodsId) throws GoodsManageServiceException {
      
        Map<String, Object> resultSet = new HashMap<>();
        List<Goods> goodsList = new ArrayList<>();
        long total = 0;

     
        Goods goods;
        try {
            goods = goodsMapper.selectById(goodsId);
        } catch (PersistenceException e) {
            throw new GoodsManageServiceException(e);
        }

        if (goods != null) {
            goodsList.add(goods);
            total = 1;
        }

        resultSet.put("data", goodsList);
        resultSet.put("total", total);
        return resultSet;
    }

   
    @Override
    public Map<String, Object> selectByName(int offset, int limit, String goodsName) throws GoodsManageServiceException {
       
        Map<String, Object> resultSet = new HashMap<>();
        List<Goods> goodsList;
        long total = 0;
        boolean isPagination = true;

       
        if (offset < 0 || limit < 0)
            isPagination = false;

       
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                goodsList = goodsMapper.selectApproximateByName(goodsName);
                if (goodsList != null) {
                    PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
                    total = pageInfo.getTotal();
                } else
                    goodsList = new ArrayList<>();
            } else {
                goodsList = goodsMapper.selectApproximateByName(goodsName);
                if (goodsList != null)
                    total = goodsList.size();
                else
                    goodsList = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            throw new GoodsManageServiceException(e);
        }

        resultSet.put("data", goodsList);
        resultSet.put("total", total);
        return resultSet;
    }

  
    @Override
    public Map<String, Object> selectByName(String goodsName) throws GoodsManageServiceException {
        return selectByName(-1, -1, goodsName);
    }

    
    @Override
    public Map<String, Object> selectAll(int offset, int limit) throws GoodsManageServiceException {
        
        Map<String, Object> resultSet = new HashMap<>();
        List<Goods> goodsList;
        long total = 0;
        boolean isPagination = true;

       
        if (offset < 0 || limit < 0)
            isPagination = false;

        
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                goodsList = goodsMapper.selectAll();
                if (goodsList != null) {
                    PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
                    total = pageInfo.getTotal();
                } else
                    goodsList = new ArrayList<>();
            } else {
                goodsList = goodsMapper.selectAll();
                if (goodsList != null)
                    total = goodsList.size();
                else
                    goodsList = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            throw new GoodsManageServiceException(e);
        }

        resultSet.put("data", goodsList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public Map<String, Object> selectAll() throws GoodsManageServiceException {
        return selectAll(-1, -1);
    }

   
    private boolean goodsCheck(Goods goods) {
        if (goods != null) {
            if (goods.getName() != null && goods.getValue() != null) {
                return true;
            }
        }
        return false;
    }

  
    @UserOperation(value = "添加货物信息")
    @Override
    public boolean addGoods(Goods goods) throws GoodsManageServiceException {

        try {
         
            if (goods != null) {
             
                if (goodsCheck(goods)) {
                    goodsMapper.insert(goods);
                    return true;
                }
            }
            return false;
        } catch (PersistenceException e) {
            throw new GoodsManageServiceException(e);
        }
    }

   
    @UserOperation(value = "修改货物信息")
    @Override
    public boolean updateGoods(Goods goods) throws GoodsManageServiceException {

        try {
    
            if (goods != null) {
             
                if (goodsCheck(goods)) {
                    goodsMapper.update(goods);
                    return true;
                }
            }
            return false;
        } catch (PersistenceException e) {
            throw new GoodsManageServiceException(e);
        }
    }

   
    @UserOperation(value = "删除货物信息")
    @Override
    public boolean deleteGoods(Integer goodsId) throws GoodsManageServiceException {

        try {
          
            List<StockInDO> stockInDORecord = stockInMapper.selectByGoodID(goodsId);
            if (stockInDORecord != null && !stockInDORecord.isEmpty())
                return false;

        
            List<StockOutDO> stockOutDORecord = stockOutMapper.selectByGoodId(goodsId);
            if (stockOutDORecord != null && !stockOutDORecord.isEmpty())
                return false;

            
            List<Storage> storageRecord = storageMapper.selectByGoodsIDAndRepositoryID(goodsId, null);
            if (storageRecord != null && !storageRecord.isEmpty())
                return false;

           
            goodsMapper.deleteById(goodsId);
            return true;
        } catch (PersistenceException e) {
            throw new GoodsManageServiceException(e);
        }
    }

   
    @UserOperation(value = "导入货物信息")
    @Override
    public Map<String, Object> importGoods(MultipartFile file) throws GoodsManageServiceException {
       
        Map<String, Object> resultSet = new HashMap<>();
        int total = 0;
        int available = 0;

    
        try {
            List<Goods> goodsList = ejConvertor.excelReader(Goods.class, FileUtil.convertMultipartFileToFile(file));
            if (goodsList != null) {
                total = goodsList.size();

             
                List<Goods> availableList = new ArrayList<>();
                for (Goods goods : goodsList) {
                    if (goodsCheck(goods)) {
                        availableList.add(goods);
                    }
                }
           
                available = availableList.size();
                if (available > 0) {
                    goodsMapper.insertBatch(availableList);
                }
            }
        } catch (PersistenceException | IOException e) {
            throw new GoodsManageServiceException(e);
        }

        resultSet.put("total", total);
        resultSet.put("available", available);
        return resultSet;
    }

    
    @UserOperation(value = "导出货物信息")
    @Override
    public File exportGoods(List<Goods> goods) {
        if (goods == null)
            return null;

        return ejConvertor.excelWriter(Goods.class, goods);
    }

}
