package com.falkenproject.wsskel.dao;

import com.falkenproject.wsskel.web.SkeletonObject;
import org.skife.jdbi.v2.BeanMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.Collection;

public interface SkeletonDAO {

    class SkeletonObjectBeanMapper extends BeanMapper<SkeletonObject>{

        public SkeletonObjectBeanMapper(Class<SkeletonObject> type) {
            super(type);
        }
    }

    @SqlQuery("SELECT Name,Value FROM SkeletonObjects")
    @Mapper(SkeletonObjectBeanMapper.class)
    Collection<SkeletonObject> getObjects();

    @SqlQuery("SELECT Name,Value FROM SkeletonObjects WHERE Name=:name")
    @Mapper(SkeletonObjectBeanMapper.class)
    SkeletonObject getObject(@Bind("name") String name);

    @SqlQuery("INSERT INTO SkeletonObjects (Name,Value) VALUES (:name,:value)")
    void saveObject(@BindBean("object") SkeletonObject skeletonObject);

    @SqlQuery("DELETE FROM SkeletonObjects WHERE Name=:name")
    void deleteObject(String name);
}
