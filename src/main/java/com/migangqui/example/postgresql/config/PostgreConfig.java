package com.migangqui.example.postgresql.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.exception.GenericJDBCException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class PostgreConfig {

	@Autowired
	private EntityManager entityManager;

	@PostConstruct
	public void checkGeoIndex() {
		log.info("Checking indexes");
		if (existGeoIndex()) {
			log.debug("All indexed created");
		} else {
			createIndexes();
		}
	}

	/* Private methods */
	
	private boolean existGeoIndex() {
		boolean result = false;
		try {
			entityManager.createNativeQuery("select * from pg_indexes where indexname = 'event_gix'").getSingleResult();
			result = true;
		} catch (NoResultException e) {
			log.debug("Controlled error. No index found. Preparing to create.");
		}
		return result;
	}

	private void createIndexes() {
		try {
			entityManager.createNativeQuery("CREATE INDEX event_gix ON event USING GIST(location)").getSingleResult();
		} catch (PersistenceException e) {
			if (existGeoIndex())
				log.debug("Index created");
		}

	}

}
