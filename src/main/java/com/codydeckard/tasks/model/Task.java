package com.codydeckard.tasks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

import com.codydeckard.tasks.db.Database;

@Entity
@Table(name = "TASKS")
@NamedQueries({ @NamedQuery(name = "Task.findAll", query = "SELECT p FROM Task p"),
		@NamedQuery(name = "Task.findById", query = "SELECT p FROM Task p WHERE p.guid = :guid") })
public class Task extends BaseObject implements Serializable {

	// @Id @Column(name="ID") @GeneratedValue(strategy=GenerationType.AUTO)
	// private int id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "title", length = 20)
	private String title;

	@Column(name = "description", length = 100)
	private String description;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Task findTaskById(String guid) {

		Map properties = new HashMap();
		Task task = null;
		try {

			EntityManager em = Database.getEntityManager();
			
			Query q = em.createNamedQuery("Task.findById");
			
			q.setParameter("guid", guid);
			
			List<Task> tasks = q.getResultList();
			
			if(!tasks.isEmpty()) {
				task = tasks.get(0);
			}
			
			return task;
				
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static Task newTask(String title, String description, String user) {

		Task newTask = new Task();

		newTask.title = title;
		newTask.description = description;
		Date now = new Date();
		newTask.setCreatedAt(now);
		newTask.setLastModifiedAt(now);
		newTask.setCreatedBy(user);
		newTask.setLastModifiedBy(user);

		EntityManager em = Database.getEntityManager();

		
		try {

			em.getTransaction().begin();
		
			em.persist(newTask);

			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return newTask;
		

	}

	public String getTitle() {
		return this.title;
	}
	@SuppressWarnings("unchecked")
	public static List<Task> getTasks() {
		
		EntityManager em = Database.getEntityManager();


		try {
		
			Query q = em.createNamedQuery("Task.findAll");
	
			
			return q.getResultList();
	} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String toString() {
		return "Task - ID: " + this.getGUID() + " Title: " + title + " Desc: " + description + " Created by: "
				+ this.getCreatedBy();

	}
}
