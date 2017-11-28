package com.codydeckard.tasks.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.ws.WebServiceContext;

import com.codydeckard.tasks.model.Task;
import com.codydeckard.tasks.services.UserManager;

@Path("/tasks")
@Produces({ MediaType.APPLICATION_JSON })
public class TaskService {
	
	@GET
	@Path("/")
	public List<Task> getTasks() {
		
		List<Task> tasks = Task.getTasks();
		
		return tasks;

	}
	
	@GET
	@Path("/{id}")
	public Response getTaskById(@PathParam("id") String id) {
		Task task = null;
		
		task = Task.findTaskById(id);
		
		if(task == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for GUID: " + id).build();
		} else {
			return Response.ok(task, MediaType.APPLICATION_JSON).build();
		}
		
	}
	
	@POST
	@Path("/new")
	public Task createNewTask(@Context SecurityContext secContext, Task task) {
		
		String user = secContext.getUserPrincipal().getName();		
		
		UserManager.setCurrentUser(user);

		Task newTask = Task.newTask(task.getTitle(), task.getDescription(), user);
			
		return newTask;
		
	}
	
	@PATCH
	@Path("/{id}")
	public Response updateTask(@PathParam("id") String id, @Context SecurityContext secContext, Task task) {
		String username = secContext.getUserPrincipal().getName();
		
		UserManager.setCurrentUser(username);

		Task modified = Task.update(id, task);
		
			
		if(modified == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for GUID: " + id).build();
		}

		return Response.ok(modified, MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeTask(@PathParam("id") String id) {
		
		Task task = Task.removeTask(id);
		
		if(task == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for GUID: " + id).build();
		}
		
		return Response.ok().build();

	}
	
}
