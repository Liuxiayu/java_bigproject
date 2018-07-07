package com.ken.wms.common.controller;

import com.ken.wms.common.service.Interface.StockRecordManageService;
import com.ken.wms.common.util.Response;
import com.ken.wms.common.util.ResponseUtil;
import com.ken.wms.domain.StockRecordDTO;
import com.ken.wms.exception.StockRecordManageServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "stockRecordManage")
public class StockRecordManageHandler {

    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private StockRecordManageService stockRecordManageService;

    @RequestMapping(value = "stockOut", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> stockOut(@RequestParam("customerID") Integer customerID,
                                 @RequestParam("goodsID") Integer goodsID,
                                 @RequestParam(value = "repositoryID", required = false) String repositoryIDStr,
                                 @RequestParam("number") long number) throws StockRecordManageServiceException {
       
        Response responseContent = responseUtil.newResponseInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        boolean authorizeCheck = true;
        boolean argumentCheck = true;
        Integer repositoryID = null;

      
        if (repositoryIDStr != null) {
            if (StringUtils.isNumeric(repositoryIDStr)) {
                repositoryID = Integer.valueOf(repositoryIDStr);
            } else {
                argumentCheck = false;
                responseContent.setResponseMsg("request argument error");
            }
        }

     
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String personInCharge = (String) session.getAttribute("userName");
        Object repositoryIDBelong = session.getAttribute("repositoryBelong");

      
        if (!currentUser.hasRole("systemAdmin")) {
            if (repositoryIDBelong.getClass() == Integer.class) {
                repositoryID = (Integer) repositoryIDBelong;
            } else {
                authorizeCheck = false;
                responseContent.setResponseMsg("You are not authorized");
            }
        }

        if (authorizeCheck && argumentCheck) {
            if (stockRecordManageService.stockOutOperation(customerID, goodsID, repositoryID, number, personInCharge))
                result = Response.RESPONSE_RESULT_SUCCESS;
        }

    
        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    @RequestMapping(value = "stockIn", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> stockIn(@RequestParam("supplierID") Integer supplierID,
                                @RequestParam("goodsID") Integer goodsID,
                                @RequestParam(value = "repositoryID", required = false) String repositoryIDStr,
                                @RequestParam("number") long number) throws StockRecordManageServiceException {
       
        Response responseContent = responseUtil.newResponseInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        boolean authorizeCheck = true;
        boolean argumentCheck = true;
        Integer repositoryID = null;

     
        if (repositoryIDStr != null) {
            if (StringUtils.isNumeric(repositoryIDStr)) {
                repositoryID = Integer.valueOf(repositoryIDStr);
            } else {
                argumentCheck = false;
                responseContent.setResponseMsg("request argument error");
            }
        }

    
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String personInCharge = (String) session.getAttribute("userName");
        Object repositoryIDBelong = session.getAttribute("repositoryBelong");

      
        if (!currentUser.hasRole("systemAdmin")) {
            if (repositoryIDBelong.getClass() == Integer.class) {
                repositoryID = (Integer) repositoryIDBelong;
            } else {
                authorizeCheck = false;
                responseContent.setResponseMsg("You are not authorized");
            }
        }

   
        if (authorizeCheck && argumentCheck) {
            if (stockRecordManageService.stockInOperation(supplierID, goodsID, repositoryID, number, personInCharge)) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }

     
        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

   
    @SuppressWarnings({"SingleStatementInBlock", "unchecked"})
    @RequestMapping(value = "searchStockRecord", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getStockRecord(@RequestParam("searchType") String searchType,
                                       @RequestParam("repositoryID") String repositoryIDStr,
                                       @RequestParam("startDate") String startDateStr,
                                       @RequestParam("endDate") String endDateStr,
                                       @RequestParam("limit") int limit,
                                       @RequestParam("offset") int offset) throws ParseException, StockRecordManageServiceException {

        Response responseContent = responseUtil.newResponseInstance();
        List<StockRecordDTO> rows = null;
        long total = 0;

        String regex = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
        boolean startDateFormatCheck = (StringUtils.isEmpty(startDateStr) || startDateStr.matches(regex));
        boolean endDateFormatCheck = (StringUtils.isEmpty(endDateStr) || endDateStr.matches(regex));
        boolean repositoryIDCheck = (StringUtils.isEmpty(repositoryIDStr) || StringUtils.isNumeric(repositoryIDStr));

        if (startDateFormatCheck && endDateFormatCheck && repositoryIDCheck) {
            Integer repositoryID = -1;
            if (StringUtils.isNumeric(repositoryIDStr)) {
                repositoryID = Integer.valueOf(repositoryIDStr);
            }

            Map<String, Object> queryResult = stockRecordManageService.selectStockRecord(repositoryID, startDateStr, endDateStr, searchType, offset, limit);
            if (queryResult != null) {
                rows = (List<StockRecordDTO>) queryResult.get("data");
                total = (long) queryResult.get("total");
            }
        } else
            responseContent.setResponseMsg("Request argument error");

        if (rows == null)
            rows = new ArrayList<>(0);

        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        return responseContent.generateResponse();
    }
}
