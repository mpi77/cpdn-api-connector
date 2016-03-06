package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.Permission;

public class PermissionTest {

	private Permission p;
	
	@Before
	public void setUp() throws Exception {
		this.p = new Permission(null, null, null, null, null, null);
	}

	@Test
	public void testPermission() {
		this.p = new Permission(null, null, null, null, null, null);
		assertNotNull(this.p);
	}

	@Test
	public void testGetSetId() {
		assertNull(this.p.getId());
		
		this.p.setId(1);
		assertSame(1, this.p.getId().intValue());
	}

	@Test
	public void testGetSetSchemeId() {
		assertNull(this.p.getSchemeId());
		
		this.p.setSchemeId(1);
		assertSame(1, this.p.getSchemeId().intValue());
	}

	@Test
	public void testGetSetProfileId() {
		assertNull(this.p.getProfileId());
		
		this.p.setProfileId(1);
		assertSame(1, this.p.getProfileId().intValue());
	}

	@Test
	public void testGetSetMode() {
		assertNull(this.p.getMode());
		
		this.p.setMode(Permission.MODE_READ_WRITE_EXECUTE);
		assertEquals(Permission.MODE_READ_WRITE_EXECUTE, this.p.getMode());
	}

	@Test
	public void testGetSetTsFrom() {
		assertNull(this.p.getTsFrom());
		
		this.p.setTsFrom("2016-01-01 00:00:00");
		assertEquals("2016-01-01 00:00:00", this.p.getTsFrom());
	}

	@Test
	public void testGetSetTsTo() {
		assertNull(this.p.getTsTo());
		
		this.p.setTsTo("2017-01-01 00:00:00");
		assertEquals("2017-01-01 00:00:00", this.p.getTsTo());
	}

	@Test
	public void testCreateJsonBody() {
		this.p.setMode(Permission.MODE_READ);
		this.p.setProfileId(1);
		this.p.setSchemeId(1);
		this.p.setTsFrom("2016-01-01 00:00:00");
		this.p.setTsTo("2017-01-01 00:00:00");
		
		assertEquals("{\"mode\":\"r\",\"tsFrom\":\"2016-01-01 00:00:00\",\"scheme\":1,\"tsTo\":\"2017-01-01 00:00:00\",\"user\":1}", this.p.createJsonBody());
	}

	@Test
	public void testUpdateJsonBody() {
		this.p.setMode(Permission.MODE_READ);
		this.p.setProfileId(1);
		this.p.setSchemeId(1);
		this.p.setTsFrom("2016-01-01 00:00:00");
		this.p.setTsTo("2017-01-01 00:00:00");
		
		assertEquals("{\"mode\":\"r\",\"tsFrom\":\"2016-01-01 00:00:00\",\"scheme\":1,\"tsTo\":\"2017-01-01 00:00:00\",\"user\":1}", this.p.updateJsonBody());
	}

	@Test
	public void testBuildPermission() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"mode\":\"rwx\",\"tsFrom\":\"2016-01-01 00:00:00\",\"tsTo\":\"2017-01-01 00:00:00\",\"user\":{\"_meta\":{\"id\":\"1\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}}}";
		this.p = Permission.buildPermission(response);
		
		assertSame(1, this.p.getId().intValue());
		assertSame(1, this.p.getSchemeId().intValue());
		assertSame(1, this.p.getProfileId().intValue());
		assertEquals(Permission.MODE_READ_WRITE_EXECUTE, this.p.getMode());
		assertEquals("2016-01-01 00:00:00", this.p.getTsFrom());
		assertEquals("2017-01-01 00:00:00", this.p.getTsTo());
	}
	
	@Test(expected = JSONException.class)
	public void testBuildPermissionEmptyResponse() {
		this.p = Permission.buildPermission("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildPermissionInvalidResponse() {
		this.p = Permission.buildPermission("{}");
	}

	@Test
	public void testBuildPermissions() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"mode\":\"rwx\",\"tsFrom\":\"2016-01-01 00:00:00\",\"tsTo\":\"2017-01-01 00:00:00\",\"user\":{\"_meta\":{\"id\":\"1\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}}},"
				+ "{\"_meta\":{\"id\":\"2\"},\"mode\":\"rwx\",\"tsFrom\":\"2016-01-01 00:00:00\",\"tsTo\":\"2017-01-01 00:00:00\",\"user\":{\"_meta\":{\"id\":\"2\"}},\"scheme\":{\"_meta\":{\"id\":\"2\"}}}]}";
		
		List<Permission> p = Permission.buildPermissions(response);
		
		assertSame(1, p.get(0).getId().intValue());
		assertSame(1, p.get(0).getSchemeId().intValue());
		assertSame(1, p.get(0).getProfileId().intValue());
		assertEquals(Permission.MODE_READ_WRITE_EXECUTE, p.get(0).getMode());
		assertEquals("2016-01-01 00:00:00", p.get(0).getTsFrom());
		assertEquals("2017-01-01 00:00:00", p.get(0).getTsTo());
		
		assertSame(2, p.get(1).getId().intValue());
		assertSame(2, p.get(1).getSchemeId().intValue());
		assertSame(2, p.get(1).getProfileId().intValue());
		assertEquals(Permission.MODE_READ_WRITE_EXECUTE, p.get(1).getMode());
		assertEquals("2016-01-01 00:00:00", p.get(1).getTsFrom());
		assertEquals("2017-01-01 00:00:00", p.get(1).getTsTo());
	}

}
