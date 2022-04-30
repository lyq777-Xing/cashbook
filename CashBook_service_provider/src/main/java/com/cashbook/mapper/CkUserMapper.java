package com.cashbook.mapper;

import com.cashbook.entity.CkUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-04-30
 */
@Mapper
public interface CkUserMapper extends BaseMapper<CkUser> {

}
