package org.sample.test.dao;

import org.sample.test.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDao extends JpaRepository<Sample, String>,
	JpaSpecificationExecutor<Sample> {
}
