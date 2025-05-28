package com.example.pricesscraper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pricesscraper.common.ErrorCode;
import com.example.pricesscraper.common.WebScraper;
import com.example.pricesscraper.exception.BusinessException;
import com.example.pricesscraper.mapper.ItemsMapper;
import com.example.pricesscraper.domain.Items;
import com.example.pricesscraper.service.ItemsService;
import com.example.pricesscraper.utils.ThrowUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* @author legion
* @description 针对表【items】的数据库操作Service实现
* @createDate 2025-05-12 20:26:08
*/
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items>
    implements ItemsService{
    @Resource
    private WebScraper webScraper;

    @Override
    public List<Items> SearchProduct(String key) throws IOException {
        if(key == null){
            throw new BusinessException(ErrorCode.PARAM_IS_WRONG,"请输入关键词！");
        }
        List<Items> products = webScraper.ScrapeProductData(key);

        for(Items product : products){
            boolean add = baseMapper.insert(product) != 0;
            ThrowUtils.ThrowIf(!add,ErrorCode.OPERATION_ERROR);
        }
        return products;
    }

}




