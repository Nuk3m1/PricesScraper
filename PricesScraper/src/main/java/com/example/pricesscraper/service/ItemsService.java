package com.example.pricesscraper.service;

import com.example.pricesscraper.domain.Items;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

/**
* @author legion
* @description 针对表【items】的数据库操作Service
* @createDate 2025-05-12 20:26:08
*/
public interface ItemsService extends IService<Items> {
    List<Items> SearchProduct(String key) throws IOException;



}
