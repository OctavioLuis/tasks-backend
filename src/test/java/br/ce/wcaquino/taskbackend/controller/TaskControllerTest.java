package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDevSalvarTareaSemDescipcion() {
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		 controller = new TaskController();
		try {
			controller.save(todo);
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}

	@Test
	public void naoDevSalvarTareaSemData() {
		Task todo = new Task();
		todo.setTask("Descipcion");
		 controller = new TaskController();
		try {
			controller.save(todo);
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}

	@Test
	public void naoDevSalvarTareaSemPass() {
		Task todo = new Task();
		todo.setTask("Descipcion");
		todo.setDueDate(LocalDate.of(2010, 01, 01));
		 controller = new TaskController();
		try {
			controller.save(todo);
		} catch (ValidationException e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}

	
}
