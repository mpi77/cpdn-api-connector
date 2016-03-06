package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.Object;

public class ObjectTest {

	private Object o;
	
	@Before
	public void setUp() throws Exception {
		this.o = new Object(null, null, null);
	}

	@Test
	public void testObject() {
		this.o = new Object(null, null, null);
		assertNotNull(this.o);
	}

	@Test
	public void testGetSetId() {
		assertNull(this.o.getId());
		
		this.o.setId(1);
		assertSame(1, this.o.getId().intValue());
	}

	@Test
	public void testGetSetName() {
		assertNull(this.o.getName());
		
		this.o.setName("NAME");
		assertEquals("NAME", this.o.getName());
	}

	@Test
	public void testGetSetScheme() {
		assertNull(this.o.getScheme());
		
		this.o.setScheme(1);
		assertSame(1, this.o.getScheme().intValue());
	}

	@Test
	public void testCreateJsonBody() {
		this.o.setName("NAME");
		this.o.setScheme(1);
		
		assertEquals("{\"scheme\":1,\"name\":\"NAME\"}", this.o.createJsonBody());
	}

	@Test
	public void testUpdateJsonBody() {
		this.o.setName("NAME");
		this.o.setScheme(1);
		
		assertEquals("{\"scheme\":1,\"name\":\"NAME\"}", this.o.updateJsonBody());
	}

	@Test
	public void testBuildObject() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"name\":\"n1\",\"scheme\":{\"_meta\":{\"id\":\"1\"}}}";
		this.o = Object.buildObject(response);
		
		assertSame(1, this.o.getId().intValue());
		assertEquals("n1", this.o.getName());
		assertSame(1, this.o.getScheme().intValue());
	}
	
	@Test(expected = JSONException.class)
	public void testBuildObjectEmptyResponse() {
		this.o = Object.buildObject("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildObjectInvalidResponse() {
		this.o = Object.buildObject("{}");
	}

	@Test
	public void testBuildObjects() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"name\":\"n1\",\"scheme\":{\"_meta\":{\"id\":\"1\"}}},"
				+ "{\"_meta\":{\"id\":\"2\"},\"name\":\"n2\",\"scheme\":{\"_meta\":{\"id\":\"2\"}}}]}";
		
		List<Object> o = Object.buildObjects(response);
		
		assertSame(1, o.get(0).getId().intValue());
		assertEquals("n1", o.get(0).getName());
		assertSame(1, o.get(0).getScheme().intValue());
		
		assertSame(2, o.get(1).getId().intValue());
		assertEquals("n2", o.get(1).getName());
		assertSame(2, o.get(1).getScheme().intValue());
	}

}
