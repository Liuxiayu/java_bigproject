package com.ken.wms.dao;

import com.ken.wms.domain.StockInDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface StockInMapper {

    /**
     * 閫夋嫨鍏ㄩ儴鐨勫叆搴撹褰�
     *
     * @return 杩斿洖鍏ㄩ儴鐨勫叆搴撹褰�
     */
    List<StockInDO> selectAll();

    /**
     * 閫夋嫨鎸囧畾渚涘簲鍟咺D鐩稿叧鐨勫叆搴撹褰�
     *
     * @param supplierID 鎸囧畾鐨勪緵搴斿晢ID
     * @return 杩斿洖鎸囧畾渚涘簲鍟嗙浉鍏崇殑鍏ュ簱璁板綍
     */
    List<StockInDO> selectBySupplierId(Integer supplierID);

    /**
     * 閫夋嫨鎸囧畾璐х墿ID鐩稿叧鐨勫叆搴撹褰�
     *
     * @param goodID 鎸囧畾鐨勮揣鐗㊣D
     * @return 杩斿洖鎸囧畾璐х墿鐩稿叧鐨勫叆搴撹褰�
     */
    List<StockInDO> selectByGoodID(Integer goodID);

    /**
     * 閫夋嫨鎸囧畾浠撳簱ID鐩稿叧鐨勫叆搴撹褰�
     *
     * @param repositoryID 鎸囧畾鐨勪粨搴揑D
     * @return 杩斿洖鎸囧畾浠撳簱鐩稿叧鐨勫叆搴撹褰�
     */
    List<StockInDO> selectByRepositoryID(Integer repositoryID);

    /**
     * 閫夋嫨鎸囧畾浠撳簱ID浠ュ強鎸囧畾鏃ユ湡鑼冨洿鍐呯殑鍏ュ簱璁板綍
     *
     * @param repositoryID 鎸囧畾鐨勪粨搴揑D
     * @param startDate    璁板綍鐨勮捣濮嬫棩鏈�
     * @param endDate      璁板綍鐨勭粨鏉熸棩鏈�
     * @return 杩斿洖鎵�鏈夌鍚堣姹傜殑鍏ュ簱璁板綍
     */
    List<StockInDO> selectByRepositoryIDAndDate(@Param("repositoryID") Integer repositoryID,
                                                @Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);

    /**
     * 閫夋嫨鎸囧畾鍏ュ簱璁板綍鐨処D鐨勫叆搴撹褰�
     *
     * @param id 鍏ュ簱璁板綍ID
     * @return 杩斿洖鎸囧畾ID鐨勫叆搴撹褰�
     */
    StockInDO selectByID(Integer id);

    /**
     * 娣诲姞涓�鏉℃柊鐨勫叆搴撹褰�
     *
     * @param stockInDO 鍏ュ簱璁板綍
     */
    void insert(StockInDO stockInDO);

    /**
     * 鏇存柊鍏ュ簱璁板綍
     *
     * @param stockInDO 鍏ュ簱璁板綍
     */
    void update(StockInDO stockInDO);

    /**
     * 鍒犻櫎鎸囧畾ID鐨勫叆搴撹褰�
     *
     * @param id 鎸囧畾鍒犻櫎鍏ュ簱璁板綍鐨処D
     */
    void deleteByID(Integer id);
}
