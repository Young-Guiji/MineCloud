package com.springboot.cloud.mallgoods.manager;

import com.springboot.cloud.common.core.annotation.MqProducerStore;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.mallgoods.entity.po.MallProduct;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import com.springboot.cloud.mallgoods.mapper.MallProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class ProductManager {

    @Autowired
    private MallProductMapper mallProductMapper;

    /**
     * Save product.
     *
     * @param mqMessageData the mq message data
     * @param product       the product
     * @param addFlag       the add flag
     */
    @MqProducerStore
    public void saveProduct(final MqMessageData mqMessageData, final MallProduct product, boolean addFlag) {
        log.info("保存商品信息. mqMessageData={}, product={}", mqMessageData, product);
        if (addFlag) {
            mallProductMapper.insert(product);
        } else {
            int result = mallProductMapper.updateById(product);
            if (result < 1) {
                throw new ServiceException(SystemErrorType.GOOD10021022, product.getId());
            }
        }

    }

    /**
     * Delete product.
     *
     * @param mqMessageData the mq message data
     * @param productId     the product id
     */
    @MqProducerStore
    public void deleteProduct(final MqMessageData mqMessageData, final Long productId) {
        log.info("删除商品信息. mqMessageData={}, productId={}", mqMessageData, productId);
        int result = mallProductMapper.deleteById(productId);
        if (result < 1) {
            throw new ServiceException(SystemErrorType.GOOD10021023, productId);
        }
    }

}
