package com.cashbookcloud.cat.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cashbookcloud.cat.service.entity.Cat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatMapper extends BaseMapper<Cat> {
}
