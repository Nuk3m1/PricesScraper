


package com.example.pricesscraper.controller;


import com.example.pricesscraper.common.BaseResponse;
import com.example.pricesscraper.common.ErrorCode;
import com.example.pricesscraper.common.WebScraper;
import com.example.pricesscraper.domain.Items;
import com.example.pricesscraper.exception.BusinessException;
import com.example.pricesscraper.service.ItemsService;
import com.example.pricesscraper.utils.ResultUtils;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ItemController {
    @Resource
    private ItemsService itemsService;

    @GetMapping("/search")
    public BaseResponse<List<Items>> SearchProducts(String key) throws IOException {
        if(key == null){
            throw new BusinessException(ErrorCode.PARAM_IS_WRONG,"请输入关键词！");
        }
        List<Items> products = itemsService.SearchProduct(key);


        return ResultUtils.success(products);

    }


}
