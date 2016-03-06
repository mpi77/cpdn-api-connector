package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.Scheme;

public class SchemeTest {

	private Scheme s;
	
	@Before
	public void setUp() throws Exception {
		this.s = new Scheme(null, null, null, null, false);
	}

	@Test
	public void testScheme() {
		this.s = new Scheme(null, null, null, null, false);
		assertNotNull(this.s);
	}

	@Test
	public void testGetSetId() {
		assertNull(this.s.getId());
		
		this.s.setId(1);
		assertSame(1, this.s.getId().intValue());
	}

	@Test
	public void testGetSetName() {
		assertNull(this.s.getName());
		
		this.s.setName("NAME");
		assertEquals("NAME", this.s.getName());
	}

	@Test
	public void testGetSetDescription() {
		assertNull(this.s.getDescription());
		
		this.s.setDescription("DESCRIPTION");
		assertEquals("DESCRIPTION", this.s.getDescription());
	}

	@Test
	public void testGetSetVersion() {
		assertNull(this.s.getVersion());
		
		this.s.setVersion(1);
		assertSame(1, this.s.getVersion());
	}

	@Test
	public void testIsLock() {
		assertFalse(this.s.isLock());
		
		this.s.setLock(true);
		assertTrue(this.s.isLock());
		
		this.s.setLock(false);
		assertFalse(this.s.isLock());
	}

	@Test
	public void testCreateJsonBody() {
		this.s.setId(1);
		this.s.setName("NAME");
		this.s.setDescription("");
		this.s.setVersion(1);
		this.s.setLock(true);
		
		assertEquals("{\"name\":\"NAME\",\"description\":\"\",\"lock\":\"1\",\"version\":1}", this.s.createJsonBody());
	}

	@Test
	public void testUpdateJsonBody() {
		this.s.setName("NAME");
		this.s.setDescription("");
		this.s.setVersion(1);
		this.s.setLock(true);
		
		assertEquals("{\"name\":\"NAME\",\"description\":\"\",\"lock\":\"1\",\"version\":1}", this.s.updateJsonBody());
	}
	
	@Test
	public void testBuildScheme() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"name\":\"n1\",\"description\":\"d1\",\"lock\":\"1\",\"version\":\"1\"}";
		this.s = Scheme.buildScheme(response);
		
		assertSame(1, this.s.getId().intValue());
		assertEquals("n1", this.s.getName());
		assertEquals("d1", this.s.getDescription());
		assertSame(1, this.s.getVersion());
		assertTrue(this.s.isLock());
	}
	
	@Test(expected = JSONException.class)
	public void testBuildSchemeEmptyResponse() {
		this.s = Scheme.buildScheme("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildSchemeInvalidResponse() {
		this.s = Scheme.buildScheme("{}");
	}
	
	@Test
	public void testBuildSchemes() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"name\":\"n1\",\"description\":\"d1\",\"lock\":\"1\",\"version\":\"1\"},"
				+ "{\"_meta\":{\"id\":\"2\"},\"name\":\"n2\",\"description\":\"d2\",\"lock\":\"1\",\"version\":\"1\"}]}";
		
		List<Scheme> s = Scheme.buildSchemes(response);
		
		assertSame(1, s.get(0).getId().intValue());
		assertEquals("n1", s.get(0).getName());
		assertEquals("d1", s.get(0).getDescription());
		assertSame(1, s.get(0).getVersion());
		assertTrue(s.get(0).isLock());
		
		assertSame(2, s.get(1).getId().intValue());
		assertEquals("n2", s.get(1).getName());
		assertEquals("d2", s.get(1).getDescription());
		assertSame(1, s.get(1).getVersion());
		assertTrue(s.get(1).isLock());
	}

}
