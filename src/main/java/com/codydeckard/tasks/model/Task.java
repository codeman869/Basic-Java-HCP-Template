package com.codydeckard.tasks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

import com.codydeckard.tasks.db.Database;

@Entity
@Table(name="TASKS")
@NamedQuery(name="Task.findAll", query="SELECT p FROM Task p")
public class Task extends BaseObject implements Serializable { 

	//@Id @Column(name="ID") @GeneratedValue(strategy=GenerationType.AUTO)
	//private int id;
	
	@Column(name="title", length=20)
	private String title;
	
	@Column(name="description", length=100)
	private String description;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
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
		return "Task - ID: " + this.getGUID() + " Title: " + title + " Desc: " + description + " Created by: " + this.getCreatedBy();

	}
}
