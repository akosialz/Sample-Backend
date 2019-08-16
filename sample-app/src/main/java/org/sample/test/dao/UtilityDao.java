package org.sample.test.dao;

import org.sample.test.model.Sample;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor=Exception.class)
public interface UtilityDao extends CrudRepository<Sample, String> {

	@Modifying
	@Query(nativeQuery=true, value="SET CURRENT SCHEMA :schema")
	public void setSchema(@Param("schema") String schema);
}
