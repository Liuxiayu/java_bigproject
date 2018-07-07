package com.ken.wms.domain;


public class RolePermissionDO {

    /**
     * URL 鐨勮鑹茶鑹叉潈闄愪俊鎭悕绉�
     */
    private String name;

    /**
     * URL 鐨勮鑹茶鑹叉潈闄愪俊鎭搴旂殑 URL
     */
    private String url;

    /**
     * URL 鐨勮鑹茶鑹叉潈闄愪俊鎭搴旂殑瑙掕壊
     */
    private String role;


    /**
     * 瀵瑰簲鐨� getter & setter
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "RolePermissionDO{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
