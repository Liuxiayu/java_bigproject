package com.ken.wms.security.service;

import com.ken.wms.dao.RolePermissionMapper;
import com.ken.wms.domain.RolePermissionDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;


public class FilterChainDefinitionMapBuilder {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    private StringBuilder builder = new StringBuilder();

    /**
     * 鑾峰彇鎺堟潈淇℃伅
     * @return 杩斿洖鎺堟潈淇℃伅鍒楄〃
     */
    public LinkedHashMap<String, String> builderFilterChainDefinitionMap(){
        LinkedHashMap<String, String> permissionMap = new LinkedHashMap<>();

        // 鍥哄畾鐨勬潈闄愰厤缃�
        permissionMap.put("/css/**", "anon");
        permissionMap.put("/js/**", "anon");
        permissionMap.put("/fonts/**", "anon");
        permissionMap.put("/media/**", "anon");
        permissionMap.put("/pagecomponent/**", "anon");
        permissionMap.put("/login", "anon, kickOut");
        permissionMap.put("/account/login", "anon");
        permissionMap.put("/account/checkCode/**", "anon");

        // 鍙彉鍖栫殑鏉冮檺閰嶇疆
        LinkedHashMap<String, String> permissions = getPermissionDataFromDB();
        if (permissions != null){
            permissionMap.putAll(permissions);
        }

        // 鍏朵綑鏉冮檺閰嶇疆
        permissionMap.put("/", "authc");

//        permissionMap.forEach((s, s2) -> {System.out.println(s + ":" + s2);});

        return permissionMap;
    }

    /**
     * 鑾峰彇閰嶇疆鍦ㄦ暟鎹簱涓殑 URL 鏉冮檺淇℃伅
     * @return 杩斿洖鎵�鏈変繚瀛樺湪鏁版嵁搴撲腑鐨� URL 淇濆瓨淇℃伅
     */
    private LinkedHashMap<String, String> getPermissionDataFromDB(){
        LinkedHashMap<String, String> permissionData = null;

        List<RolePermissionDO> rolePermissionDOS = rolePermissionMapper.selectAll();
        if (rolePermissionDOS != null){
            permissionData = new LinkedHashMap<>(rolePermissionDOS.size());
            String url;
            String role;
            String permission;
            for (RolePermissionDO rolePermissionDO : rolePermissionDOS){
                url = rolePermissionDO.getUrl();
                role = rolePermissionDO.getRole();

                // 鍒ゆ柇璇� url 鏄惁宸茬粡瀛樺湪
                if (permissionData.containsKey(url)){
                    builder.delete(0, builder.length());
                    builder.append(permissionData.get(url));
                    builder.insert(builder.length() - 1, ",");
                    builder.insert(builder.length() - 1, role);
                }else{
                    builder.delete(0, builder.length());
                    builder.append("authc,kickOut,roles[").append(role).append("]");
                }
                permission = builder.toString();
//                System.out.println(url + ":" + permission);
                permissionData.put(url, permission);
            }
        }

        return permissionData;
    }

//    /**
//     * 鏋勯�犺鑹叉潈闄�
//     * @param role 瑙掕壊
//     * @return 杩斿洖 roles[role name] 鏍煎紡鐨勫瓧绗︿覆
//     */
//    private String permissionStringBuilder(String role){
//        builder.delete(0, builder.length());
//        return builder.append("authc,roles[").append(role).append("]").toString();
//    }
}
