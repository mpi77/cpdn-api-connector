package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.MapPoint;

public class MapPointTest {
	
	private MapPoint m;

	@Before
	public void setUp() throws Exception {
		this.m = new MapPoint(null, null, null, null, null, null, null, null);
	}

	@Test
	public void testMapPoint() {
		this.m = new MapPoint(null, null, null, null, null, null, null, null);
		assertNotNull(this.m);
	}

	@Test
	public void testGetSetId() {
		assertNull(this.m.getId());
		
		this.m.setId(1);
		assertSame(1, this.m.getId().intValue());
	}

	@Test
	public void testGetSetGpsAlt() {
		assertNull(this.m.getGpsAlt());
		
		this.m.setGpsAlt(1.1);
		assertEquals(1.1, this.m.getGpsAlt().doubleValue(), 0);
	}

	@Test
	public void testGetSetGpsLat() {
		assertNull(this.m.getGpsLat());
		
		this.m.setGpsLat(1.1);
		assertEquals(1.1, this.m.getGpsLat().doubleValue(), 0);
	}

	@Test
	public void testGetSetGpsLng() {
		assertNull(this.m.getGpsLng());
		
		this.m.setGpsLng(1.1);
		assertEquals(1.1, this.m.getGpsLng().doubleValue(), 0);
	}

	@Test
	public void testGetSetX() {
		assertNull(this.m.getX());
		
		this.m.setX(1);
		assertSame(1, this.m.getX().intValue());
	}

	@Test
	public void testGetSetY() {
		assertNull(this.m.getY());
		
		this.m.setY(1);
		assertSame(1, this.m.getY().intValue());
	}

	@Test
	public void testGetSetScheme() {
		assertNull(this.m.getScheme());
		
		this.m.setScheme(1);
		assertSame(1, this.m.getScheme().intValue());
	}

	@Test
	public void testGetSetNode() {
		assertNull(this.m.getNode());
		
		this.m.setNode(1);
		assertSame(1, this.m.getNode().intValue());
	}

	@Test
	public void testCreateJsonBody() {
		this.m.setGpsAlt(1.1);
		this.m.setGpsLat(1.1);
		this.m.setGpsLng(1.1);
		this.m.setNode(1);
		this.m.setScheme(1);
		this.m.setX(1);
		this.m.setY(1);
		
		assertEquals("{\"node\":1,\"scheme\":1,\"x\":1,\"y\":1,\"gps\":{\"altitude\":1.1,\"latitude\":1.1,\"longitude\":1.1}}", this.m.createJsonBody());
	}

	@Test
	public void testUpdateJsonBody() {
		this.m.setGpsAlt(1.1);
		this.m.setGpsLat(1.1);
		this.m.setGpsLng(1.1);
		this.m.setNode(1);
		this.m.setScheme(1);
		this.m.setX(1);
		this.m.setY(1);
		
		assertEquals("{\"node\":1,\"scheme\":1,\"x\":1,\"y\":1,\"gps\":{\"altitude\":1.1,\"latitude\":1.1,\"longitude\":1.1}}", this.m.updateJsonBody());
	}

	@Test
	public void testBuildMapPoint() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"gps\":{\"altitude\":\"0\",\"latitude\":\"0\",\"longitude\":\"0\"},\"node\":null,\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"x\":\"111\",\"y\":\"111\"}";
		this.m = MapPoint.buildMapPoint(response);
		
		assertSame(1, this.m.getId().intValue());
		assertEquals(0, this.m.getGpsAlt().doubleValue(), 0);
		assertEquals(0, this.m.getGpsLat().doubleValue(), 0);
		assertEquals(0, this.m.getGpsLng().doubleValue(), 0);
		assertSame(111, this.m.getX().intValue());
		assertSame(111, this.m.getY().intValue());
		assertSame(1, this.m.getScheme().intValue());
		assertNull(this.m.getNode());
	}

	@Test(expected = JSONException.class)
	public void testBuildMapPointEmptyResponse() {
		this.m = MapPoint.buildMapPoint("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildMapPointInvalidResponse() {
		this.m = MapPoint.buildMapPoint("{}");
	}
	
	@Test
	public void testBuildMapPoints() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"gps\":{\"altitude\":\"0\",\"latitude\":\"0\",\"longitude\":\"0\"},\"node\":null,\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"x\":\"111\",\"y\":\"111\"},"
				+ "{\"_meta\":{\"id\":\"2\"},\"gps\":{\"altitude\":\"0\",\"latitude\":\"0\",\"longitude\":\"0\"},\"node\":null,\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"x\":\"111\",\"y\":\"111\"}]}";
		
		List<MapPoint> m = MapPoint.buildMapPoints(response);
		
		assertSame(1, m.get(0).getId().intValue());
		assertEquals(0, m.get(0).getGpsAlt().doubleValue(), 0);
		assertEquals(0, m.get(0).getGpsLat().doubleValue(), 0);
		assertEquals(0, m.get(0).getGpsLng().doubleValue(), 0);
		assertSame(111, m.get(0).getX().intValue());
		assertSame(111, m.get(0).getY().intValue());
		assertSame(1, m.get(0).getScheme().intValue());
		assertNull(m.get(0).getNode());
		
		assertSame(2, m.get(1).getId().intValue());
		assertEquals(0, m.get(1).getGpsAlt().doubleValue(), 0);
		assertEquals(0, m.get(1).getGpsLat().doubleValue(), 0);
		assertEquals(0, m.get(1).getGpsLng().doubleValue(), 0);
		assertSame(111, m.get(1).getX().intValue());
		assertSame(111, m.get(1).getY().intValue());
		assertSame(1, m.get(1).getScheme().intValue());
		assertNull(m.get(1).getNode());
	}

}
