package com.pighome.eat.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 批量新增
 */
@RegisterMapper
public interface InsertBatchIgnoreMapper<T> {

    /**
     * 批量新增 insertIgnore
     *
     * @param list
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = BatchExampleProvider.class, method = "dynamicSQL")
    void batchInsertIgnore(List<T> list);

}
