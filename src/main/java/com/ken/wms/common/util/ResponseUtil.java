package com.ken.wms.common.util;

import org.springframework.stereotype.Component;


@Component
public class ResponseUtil {

    /**
     * 鐢熸垚涓�涓� Response 瀵硅薄
     * @return response 瀵硅薄
     */
    public Response newResponseInstance(){
        Response response = new Response();

        return response;
    }

}
