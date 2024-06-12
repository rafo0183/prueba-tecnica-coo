package cl.coopeuch.pruebatecnica.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.coopeuch.pruebatecnica.dao.TaskDAO;
import cl.coopeuch.pruebatecnica.dto.TaskDTO;
import cl.coopeuch.pruebatecnica.model.Task;

class TaskServiceImplTest {
	@InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskDAO taskDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Nested
    @DisplayName("get() Method")
    class getMethods {
    	@Test
    	@DisplayName("Method get() [Success]")
        public void testGetSuccess() {
            Long taskId = 1L;
            Task task = new Task();

            when(taskDAO.get(taskId)).thenReturn(task);

            TaskDTO result = taskService.get(taskId);
            assertNotNull(result);
        }
    	
    	@Test
    	@DisplayName("Method get() [Fail to get task due id is null]")
        public void testGetTaskWhenIdIsNull() {
            Long taskId = null;
            TaskDTO result = taskService.get(taskId);
            assertNull(result);
        }

        @Test
        @DisplayName("Method get() [Fail to get task due task doesn't found]")
        public void testGetTaskWhenTaskNotFound() {
            Long taskId = 1L;
            when(taskDAO.get(taskId)).thenReturn(null);

            TaskDTO result = taskService.get(taskId);
            assertNull(result);
        }
    }
    
    @Nested
    @DisplayName("getAll() Method")
    class getAllMethods {
    	@Test
    	@DisplayName("Method get() [Success]")
        public void testGetAllTasks() {	
            List<Task> taskList = new ArrayList<>();
            Task task1 = mock(Task.class);
            Task task2 = mock(Task.class);
            taskList.add(task1);
            taskList.add(task2);

            List<TaskDTO> taskDTOList = new ArrayList<>();
            TaskDTO taskDTO1 = mock(TaskDTO.class);
            TaskDTO taskDTO2 = mock(TaskDTO.class);
            taskDTOList.add(taskDTO1);
            taskDTOList.add(taskDTO2);

            when(taskDAO.getAll()).thenReturn(taskList);
            when(task1.toDTO()).thenReturn(taskDTO1);
            when(task2.toDTO()).thenReturn(taskDTO2);

            List<TaskDTO> result = taskService.getAll();
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(taskDTO1));
            assertTrue(result.contains(taskDTO2));
        }
    }
    
    @Nested
    @DisplayName("add() Method")
    class addMethods {
    	@Test
        @DisplayName("Method add() [Success]")
        public void testAddSuccess() {
            // Arrange
            TaskDTO taskRequest = new TaskDTO();
            taskRequest.setName("Any task name");
            taskRequest.setDescription("Any task description");
            taskRequest.setValid(true);

            Task task = new Task();
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setValid(taskRequest.getValid());

            when(taskDAO.insert(any(Task.class))).thenReturn(task);

            // Act
            boolean result = taskService.add(taskRequest);

            // Asserts
            assertTrue(result);
            verify(taskDAO, times(1)).insert(any(Task.class));
        }
        
        @Test
        @DisplayName("Method add() [Fail to add task due to insert returning null]")
        public void testAddFailByInsertion() {
            // Arrange
            TaskDTO taskRequest = new TaskDTO();
            taskRequest.setName("Any task name");
            taskRequest.setDescription("Any task description");
            taskRequest.setValid(true);

            when(taskDAO.insert(any(Task.class))).thenReturn(null);

            // Act
            boolean result = taskService.add(taskRequest);

            // Assert
            assertFalse(result);
            verify(taskDAO, times(1)).insert(any(Task.class));
        }
    }
    
    
    @Nested
    @DisplayName("replace() Method")
    class replaceMethods {
    	@Test
    	@DisplayName("Method replace() [Success]")
        public void testReplaceSuccess() {
    		// Arrange
            TaskDTO taskRequest = new TaskDTO();
            taskRequest.setId(1L);
            taskRequest.setName("Test Task");
            taskRequest.setDescription("Test Description");
            taskRequest.setValid(true);

            //Act
            Task task = new Task();
            task.setId(1L);

            when(taskDAO.get(1L)).thenReturn(task);

            boolean result = taskService.replace(taskRequest);

            // Assert
            assertTrue(result);
            verify(taskDAO).update(task);
            assertEquals("Test Task", task.getName());
            assertEquals("Test Description", task.getDescription());
            assertTrue(task.getValid());
        }
    	
    	@Test
    	@DisplayName("Method replace() [Fail replace task due id task not assigned]")
        public void testReplaceIdNotAssigned() {
            TaskDTO taskRequest = new TaskDTO();

            boolean result = taskService.replace(taskRequest);

            assertFalse(result);
        }

        @Test
        @DisplayName("Method replace() [Fail replace task due task not found]")
        public void testReplaceTaskNotFound() {
            TaskDTO taskRequest = new TaskDTO();
            taskRequest.setId(1L);

            when(taskDAO.get(1L)).thenReturn(null);

            boolean result = taskService.replace(taskRequest);

            assertFalse(result);
        }

        @Test
        @DisplayName("Method replace() [Fail replace task due has a Runtime Exception]")
        public void testReplaceException() {
            TaskDTO taskRequest = new TaskDTO();
            taskRequest.setId(1L);

            when(taskDAO.get(1L)).thenThrow(new RuntimeException("Database error"));

            boolean result = taskService.replace(taskRequest);

            assertFalse(result);
        }
    }

    
    @Nested
    @DisplayName("update() Method")
    class updateMethods {
    	@Test
    	@DisplayName("Method update() [Success]")
	    public void testUpdateSuccess() {
	        Long taskId = 1L;
	        Map<String, Object> taskParams = new HashMap<>();
	        taskParams.put("name", "Updated Task");
	        taskParams.put("description", "Updated Description");
	        taskParams.put("valid", false);

	        Task task = new Task();
	        task.setId(taskId);

	        when(taskDAO.get(taskId)).thenReturn(task);
	        when(taskDAO.insert(task)).thenReturn(task);

	        boolean result = taskService.update(taskId, taskParams);

	        assertTrue(result);
	        assertEquals("Updated Task", task.getName());
	        assertEquals("Updated Description", task.getDescription());
	        assertFalse(task.getValid());
	    }

	    @Test
	    @DisplayName("Method update() [Fail update task due task no found ]")
	    public void testUpdateTaskNotFound() {
	        Long taskId = 1L;
	        Map<String, Object> taskParams = new HashMap<>();

	        when(taskDAO.get(taskId)).thenReturn(null);

	        boolean result = taskService.update(taskId, taskParams);

	        assertFalse(result);
	    }

	    @Test
	    @DisplayName("Method update() [Fail update task due has an Exception]")
	    public void testUpdateException() {
	        Long taskId = 1L;
	        Map<String, Object> taskParams = new HashMap<>();
	        taskParams.put("name", "Updated Task");

	        Task task = new Task();
	        task.setId(taskId);

	        when(taskDAO.get(taskId)).thenReturn(task);
	        doThrow(new RuntimeException("Database error")).when(taskDAO).insert(task);

	        boolean result = taskService.update(taskId, taskParams);

	        assertFalse(result);
	    }
    }

}
