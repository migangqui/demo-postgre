package com.migangqui.example.postgresql.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.migangqui.example.postgresql.entity.Event;
import com.migangqui.example.postgresql.repository.EventRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.util.GeometricShapeFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitComponent {

	@Autowired
	private EventRepository eventRepository;
	
	private GeometryFactory geometryFactory = new GeometryFactory();
	
	

//	@PostConstruct
	public void init() {
		eventRepository.deleteAll();

		log.info("Total object in db {}", eventRepository.count());
		Event event;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 250_000; i++) {

			log.info("Registering event A and B {}", i+1);

			event = new Event();
			event.setName("eventA"+i);
			event.setLocation(geometryFactory.createPoint(new Coordinate(-5.994724, 37.397159)));

			eventRepository.save(event);

			event = new Event();
			event.setName("eventB"+i);
			event.setLocation(geometryFactory.createPoint(new Coordinate(-4.773935, 37.882503)));

			eventRepository.save(event);

		}

		for (int i = 0; i < 123; i++) {

			log.info("Registering event C {}", i+1);

			event = new Event();
			event.setName("eventC"+i);
			event.setLocation(geometryFactory.createPoint(new Coordinate(2.325399, 48.823206)));

			eventRepository.save(event);

		}

		log.info("Time in insert data in DB -> {}", System.currentTimeMillis() - startTime);

		GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
	    shapeFactory.setNumPoints(32);
	    shapeFactory.setCentre(new Coordinate(2.320237, 48.821198));
	    shapeFactory.setSize(1);
	    shapeFactory.createCircle();

	    startTime = System.currentTimeMillis();
	    List<Event> events = eventRepository.findByLocationWithIn(shapeFactory.createCircle());
		log.info("Time in find {} object betweewn {} -> {}", events.size(), eventRepository.count(), System.currentTimeMillis() - startTime);

		System.exit(0);
	}
	
}
