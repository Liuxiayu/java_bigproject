package com.ken.wms.common.util;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;


public class Response {

    public static final String RESPONSE_RESULT_SUCCESS = "success";
    public static final String RESPONSE_RESULT_ERROR = "error";

    // response 涓彲鑳戒娇鐢ㄧ殑鍊�
    private static final String RESPONSE_RESULT = "result";
    private static final String RESPONSE_MSG = "msg";
    private static final String RESPONSE_DATA = "data";
    private static final String RESPONSE_TOTAL = "total";

    // 瀛樻斁鍝嶅簲涓殑淇℃伅
    private Map<String,Object> responseContent;

    // Constructor
    Response() {
        this.responseContent = new HashedMap(10);
    }

    /**
     * 璁剧疆 response 鐨勭姸鎬�
     * @param result response 鐨勭姸鎬侊紝鍊间负 success 鎴� error
     */
    public void setResponseResult(String result){
        this.responseContent.put(Response.RESPONSE_RESULT,result);
    }

    /**
     * 璁剧疆 response 鐨勯檮鍔犱俊鎭�
     * @param msg response  鐨勯檮鍔犱俊鎭�
     */
    public void setResponseMsg(String msg){
        this.responseContent.put(Response.RESPONSE_MSG,msg);
    }

    /**
     * 璁剧疆 response 涓惡甯︾殑鏁版嵁
     * @param data response 涓惡甯︾殑鏁版嵁
     */
    public void setResponseData(Object data){
        this.responseContent.put(Response.RESPONSE_DATA,data);
    }

    /**
     * 璁剧疆 response 涓惡甯︽暟鎹殑鏁伴噺锛屼笌 RESPONSE_DATA 閰嶅悎浣跨敤
     * @param total 鎼哄甫鏁版嵁鐨勬暟閲�
     */
    public void setResponseTotal(long total){
        this.responseContent.put(Response.RESPONSE_TOTAL,total);
    }

    /**
     * 璁剧疆 response 鑷畾涔変俊鎭�
     * @param key 鑷畾涔変俊鎭殑 key
     * @param value 鑷畾涔変俊鎭殑鍊�
     */
    public void setCustomerInfo(String key, Object value){
        this.responseContent.put(key,value);
    }

    /**
     * 鐢熸垚 response
     * @return 浠ｈ〃 response 鐨勪竴涓� Map 瀵硅薄
     */
    public Map<String, Object> generateResponse(){
        return this.responseContent;
    }
}
