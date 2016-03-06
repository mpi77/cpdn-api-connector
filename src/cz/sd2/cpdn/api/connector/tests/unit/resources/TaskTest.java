package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.Task;

public class TaskTest {

	private Task t;
	
	@Before
	public void setUp() throws Exception {
		this.t = new Task(null, null, null, null, null, null, null, null);
	}

	@Test
	public void testTask() {
		this.t = new Task(null, null, null, null, null, null, null, null);
		assertNotNull(this.t);
	}

	@Test
	public void testGetSetId() {
		assertNull(this.t.getId());
		
		this.t.setId(1);
		assertSame(1, this.t.getId().intValue());
	}

	@Test
	public void testGetSetExecutorId() {
		assertNull(this.t.getExecutorId());
		
		this.t.setExecutorId("exe");
		assertEquals("exe", this.t.getExecutorId());
	}

	@Test
	public void testGetSetSchemeId() {
		assertNull(this.t.getSchemeId());
		
		this.t.setSchemeId(1);
		assertSame(1, this.t.getSchemeId().intValue());
	}

	@Test
	public void testGetSetProfileId() {
		assertNull(this.t.getProfileId());
		
		this.t.setProfileId(1);
		assertSame(1, this.t.getProfileId().intValue());
	}

	@Test
	public void testGetSetStatus() {
		assertNull(this.t.getStatus());
		
		this.t.setStatus("new");
		assertEquals("new", this.t.getStatus());
	}

	@Test
	public void testGetSetPriority() {
		assertNull(this.t.getPriority());
		
		this.t.setPriority(1);
		assertSame(1, this.t.getPriority().intValue());
	}

	@Test
	public void testGetSetCommand() {
		assertNull(this.t.getCommand());
		
		this.t.setCommand("{solve:problem}");
		assertEquals("{solve:problem}", this.t.getCommand());
	}

	@Test
	public void testGetSetResult() {
		assertNull(this.t.getResult());
		
		this.t.setResult("{status:ok}");
		assertEquals("{status:ok}", this.t.getResult());
	}

	@Test
	public void testCreateJsonBody() {
		this.t.setCommand("{}");
		this.t.setExecutorId("exe");
		this.t.setPriority(1);
		this.t.setProfileId(1);
		this.t.setResult("{}");
		this.t.setSchemeId(1);
		this.t.setStatus("new");
		
		assertEquals("{\"result\":\"{}\",\"scheme\":1,\"executor\":\"exe\",\"priority\":1,\"user\":1,\"command\":\"{}\",\"status\":\"new\"}", this.t.createJsonBody());
	}

	@Test
	public void testUpdateJsonBody() {
		this.t.setCommand("{}");
		this.t.setExecutorId("exe");
		this.t.setPriority(1);
		this.t.setProfileId(1);
		this.t.setResult("{}");
		this.t.setSchemeId(1);
		this.t.setStatus("new");
		
		assertEquals("{\"result\":\"{}\",\"scheme\":1,\"executor\":\"exe\",\"priority\":1,\"user\":1,\"command\":\"{}\",\"status\":\"new\"}", this.t.updateJsonBody());
	}

	@Test
	public void testBuildTask() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"executor\":{\"_meta\":{\"id\":\"e1\"}},\"user\":{\"_meta\":{\"id\":\"1\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"status\":\"new\",\"priority\":\"1\",\"command\":null,\"result\":null}";
		this.t = Task.buildTask(response);
		
		assertSame(1, this.t.getId());
		assertNull(this.t.getCommand());
		assertEquals("e1", this.t.getExecutorId());
		assertSame(1, this.t.getPriority());
		assertSame(1, this.t.getProfileId());
		assertNull(this.t.getResult());
		assertSame(1, this.t.getSchemeId());
		assertEquals("new", this.t.getStatus());
	}
	
	@Test(expected = JSONException.class)
	public void testBuildTaskEmptyResponse() {
		this.t = Task.buildTask("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildTaskInvalidResponse() {
		this.t = Task.buildTask("{}");
	}

	@Test
	public void testBuildTasks() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"executor\":{\"_meta\":{\"id\":\"e1\"}},\"user\":{\"_meta\":{\"id\":\"1\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"status\":\"new\",\"priority\":\"1\",\"command\":null,\"result\":null},"
				+ "{\"_meta\":{\"id\":\"2\"},\"executor\":{\"_meta\":{\"id\":\"e2\"}},\"user\":{\"_meta\":{\"id\":\"2\"}},\"scheme\":{\"_meta\":{\"id\":\"2\"}},\"status\":\"new\",\"priority\":\"1\",\"command\":null,\"result\":null}]}";
		
		List<Task> t = Task.buildTasks(response);
		
		assertSame(1, t.get(0).getId());
		assertNull(t.get(0).getCommand());
		assertEquals("e1", t.get(0).getExecutorId());
		assertSame(1, t.get(0).getPriority());
		assertSame(1, t.get(0).getProfileId());
		assertNull(t.get(0).getResult());
		assertSame(1, t.get(0).getSchemeId());
		assertEquals("new", t.get(0).getStatus());
		
		assertSame(2, t.get(1).getId());
		assertNull(t.get(1).getCommand());
		assertEquals("e2", t.get(1).getExecutorId());
		assertSame(1, t.get(1).getPriority());
		assertSame(2, t.get(1).getProfileId());
		assertNull(t.get(1).getResult());
		assertSame(2, t.get(1).getSchemeId());
		assertEquals("new", t.get(1).getStatus());
	}

}
