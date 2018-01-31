package com.migangqui.example.entity;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Type(type = "com.vividsolutions.jts.geom.Geometry")
	private Point location;

	// Getters and setters are omitted for brevity
}
