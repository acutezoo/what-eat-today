package com.pighome.eat.base.dao;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 批量操作接口
 */
@RegisterMapper
public interface BatchMapper<T> extends UpdateBatchByPrimaryKeySelectiveMapper<T>, InsertBatchIgnoreMapper<T> {

}
