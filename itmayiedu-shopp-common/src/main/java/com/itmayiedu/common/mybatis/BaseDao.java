
package com.itmayiedu.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;


public interface BaseDao {

	    /**
	     * @InsertProvider 注解作用， 自定义sql语句.
	     */
	
	   @InsertProvider(type=BaseProvider.class,method="save")
	   public void save(@Param("oj") Object oj, @Param("table") String table);

	   @InsertProvider(type = BaseProvider.class, method = "update")
	   public void update(@Param("oj") Object oj, @Param("table") String table, @Param("id") Long id);
	
}
