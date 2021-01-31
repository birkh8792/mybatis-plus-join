/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mybatisplus.wrapper.interfaces;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * copy {@link com.baomidou.mybatisplus.core.conditions.query.Query}
 */
public interface MySFunctionQuery<Children> extends Serializable {

    /**
     * 设置查询字段
     *
     * @param columns 字段数组
     * @return children
     */
    @SuppressWarnings("unchecked")
    <E> Children select(SFunction<E, ?>... columns);

    /**
     * ignore
     * <p>注意只有内部有 entity 才能使用该方法</p>
     */
    default Children select(Predicate<TableFieldInfo> predicate) {
        return select(null, predicate);
    }

    /**
     * 过滤查询的字段信息(主键除外!)
     * <p>例1: 只要 java 字段名以 "test" 开头的             -> select(i -> i.getProperty().startsWith("test"))</p>
     * <p>例2: 只要 java 字段属性是 CharSequence 类型的     -> select(TableFieldInfo::isCharSequence)</p>
     * <p>例3: 只要 java 字段没有填充策略的                 -> select(i -> i.getFieldFill() == FieldFill.DEFAULT)</p>
     * <p>例4: 要全部字段                                   -> select(i -> true)</p>
     * <p>例5: 只要主键字段                                 -> select(i -> false)</p>
     *
     * @param predicate 过滤方式
     * @return children
     */
    <E>Children select(Class<E> entityClass, Predicate<TableFieldInfo> predicate);

    /**
     * 查询条件 SQL 片段
     */
    String getSqlSelect();
}
