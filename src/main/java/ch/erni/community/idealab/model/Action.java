package ch.erni.community.idealab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rap
 */
@Entity
@Data
public class Action {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String icon;

}
