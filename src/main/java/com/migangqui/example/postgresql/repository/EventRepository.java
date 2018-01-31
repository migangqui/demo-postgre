package com.migangqui.example.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.migangqui.example.postgresql.entity.Event;
import com.vividsolutions.jts.geom.Polygon;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Query(value = "select e from Event e where within(e.location, ?1) = true")
	List<Event> findByLocationWithIn(Polygon polygon);
	
}
