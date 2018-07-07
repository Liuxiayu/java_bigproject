package com.ken.wms.dao;

import com.ken.wms.domain.StockOutDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface StockOutMapper {

    /**
     * 閫夋嫨鍏ㄩ儴鐨勫嚭搴撹褰�
     *
     * @return 杩斿洖鎵�鏈夌殑鍑哄簱璁板綍
     */
    List<StockOutDO> selectAll();

    /**
     * 閫夋嫨鎸囧畾瀹㈡埛ID鐩稿叧鐨勫嚭搴撹褰�
     *
     * @param customerId 鎸囧畾鐨勫鎴稩D
     * @return 杩斿洖鎸囧畾瀹㈡埛鐩稿叧鐨勫嚭搴撹褰�
     */
    List<StockOutDO> selectByCustomerId(Integer customerId);

    /**
     * 閫夋嫨鎸囧畾璐х墿ID鐩稿叧鐨勫嚭搴撹褰�
     *
     * @param goodId 鎸囧畾鐨勮揣鐗㊣D
     * @return 杩斿洖鎸囧畾璐х墿ID鐩稿叧鐨勫嚭搴撹褰�
     */
    List<StockOutDO> selectByGoodId(Integer goodId);

    /**
     * 閫夋嫨鎸囧畾浠撳簱ID鍏宠仈鐨勫嚭搴撹褰�
     *
     * @param repositoryID 鎸囧畾鐨勪粨搴揑D
     * @return 杩斿洖鎸囧畾浠撳簱ID鐩稿叧鐨勫嚭搴撹褰�
     */
    List<StockOutDO> selectByRepositoryID(Integer repositoryID);

    /**
     * 閫夋嫨鎸囧畾浠撳簱ID浠ュ強鎸囧畾鏃ユ湡鑼冨洿鍐呯殑鍑哄簱璁板綍
     *
     * @param repositoryID 鎸囧畾鐨勪粨搴揑D
     * @param startDate    璁板綍璧峰鏃ユ湡
     * @param endDate      璁板綍缁撴潫鏃ユ湡
     * @return 杩斿洖鎵�鏈夌鍚堟寚瀹氳姹傜殑鍑哄簱璁板綍
     */
    List<StockOutDO> selectByRepositoryIDAndDate(@Param("repositoryID") Integer repositoryID,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);

    /**
     * 閫夋嫨鎸囧畾ID鐨勫嚭搴撹褰�
     *
     * @param id 鎸囧畾鐨勫嚭搴撹褰旾D
     * @return 杩斿洖鎸囧畾ID鐨勫嚭搴撹褰�
     */
    StockOutDO selectById(Integer id);

    /**
     * 鎻掑叆涓�鏉℃柊鐨勫嚭搴撹褰�
     *
     * @param stockOutDO 鍑哄簱璁板綍
     */
    void insert(StockOutDO stockOutDO);

    /**
     * 鏇存柊鍑哄簱璁板綍
     *
     * @param stockOutDO 鍑哄簱璁板綍
     */
    void update(StockOutDO stockOutDO);

    /**
     * 鍒犻櫎鎸囧畾ID鐨勫嚭搴撹褰�
     *
     * @param id 鎸囧畾鐨勫嚭搴撹褰旾D
     */
    void deleteById(Integer id);
}
