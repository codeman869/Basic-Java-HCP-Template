package com.codydeckard.tasks.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codydeckard.tasks.model.Task;

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
	public Response /*Task*/ getTaskById(@PathParam("id") String id) {
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
	public Task createNewTask(Task task) {
		
		Task.newTask(task.getTitle(), task.getDescription(), "Default User");
			
		return task;
		
	}
}
