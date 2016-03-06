package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.Node;

public class NodeTest {
	
	private Node n;

	@Before
	public void setUp() throws Exception {
		this.n = new Node();
	}

	@Test
	public void testNode() {
		this.n = new Node();
		assertNotNull(this.n);
		
		this.n = new Node(null, null, null, null, null);
		assertNotNull(this.n);
	}

	@Test
	public void testGetSetId() {
		assertSame(0, this.n.getId().intValue());
		
		this.n.setId(1);
		assertSame(1, this.n.getId().intValue());
	}

	@Test
	public void testGetSetScheme() {
		assertSame(0, this.n.getScheme().intValue());
		
		this.n.setScheme(1);
		assertSame(1, this.n.getScheme().intValue());
		
		this.n.setScheme(null);
		assertNull(this.n.getScheme());
	}

	@Test
	public void testGetSetMapPoint() {
		assertSame(0, this.n.getMapPoint().intValue());
		
		this.n.setMapPoint(1);
		assertSame(1, this.n.getMapPoint().intValue());
		
		this.n.setMapPoint(null);
		assertNull(this.n.getMapPoint());
	}

	@Test
	public void testGetSetCalc() {
		HashMap<String, Double> calc = new HashMap<String, Double>();
		this.n.setCalc(calc);
		assertSame(calc, this.n.getCalc());
	}

	@Test
	public void testGetSetSpec() {
		HashMap<String, Object> spec = new HashMap<String, Object>();
		this.n.setSpec(spec);
		assertSame(spec, this.n.getSpec());
	}

	@Test
	public void testCreateJsonBody() {
		this.n.setId(1);
		this.n.setScheme(1);
		this.n.setMapPoint(1);
		
		Map<String, Double> calc = new HashMap<String, Double>(); 
		calc.put("load.active", 0.0);
		calc.put("load.reactive", 0.0);
		calc.put("voltage.dropKv", 0.0);
		calc.put("voltage.dropProc", 0.0);
		calc.put("voltage.phase", 0.0);
		calc.put("voltage.value", 0.0);
		this.n.setCalc(calc);
		
		Map<String, Object> spec = new HashMap<String, Object>(); 
		spec.put("type", Node.TYPE_POWER);
		spec.put("label", "lbl");
		spec.put("cosFi", 0.0);
		spec.put("mi", 0.0);
		spec.put("lambda.max", 0.0);
		spec.put("lambda.min", 0.0);
		spec.put("power.active", 0.0);
		spec.put("power.installed", 0.0);
		spec.put("power.rated", 0.0);
		spec.put("power.reactive", 0.0);
		spec.put("reactance.longitudinal", 0.0);
		spec.put("reactance.transverse", 0.0);
		spec.put("voltage.level", 0.0);
		spec.put("voltage.phase", 0.0);
		spec.put("voltage.rated", 0.0);
		spec.put("voltage.value", 0.0);
		this.n.setSpec(spec);
		
		String expected = "{\"mapPoint\":1,\"scheme\":1,\"calc\":{\"load\":{\"reactive\":0,\"active\":0},\"voltage\":{\"phase\":0,\"dropProc\":0,\"dropKv\":0,\"value\":0}}"
				+ ",\"spec\":{\"lambda\":{\"min\":0,\"max\":0},\"reactance\":{\"transverse\":0,\"longitudinal\":0},\"label\":\"lbl\",\"power\":{\"rated\":0,\"installed\":0,"
				+ "\"reactive\":0,\"active\":0},\"type\":\"power\",\"mi\":0,\"cosFi\":0,\"voltage\":{\"phase\":0,\"rated\":0,\"level\":0,\"value\":0}}}";
		
		assertEquals(expected, this.n.createJsonBody());
	}

	@Test
	public void testGetJsonCalcBody() {
		Map<String, Double> calc = new HashMap<String, Double>(); 
		calc.put("load.active", 0.0);
		calc.put("load.reactive", 0.0);
		calc.put("voltage.dropKv", 0.0);
		calc.put("voltage.dropProc", 0.0);
		calc.put("voltage.phase", 0.0);
		calc.put("voltage.value", 0.0);
		
		this.n.setCalc(calc);
		assertEquals("{\"load\":{\"reactive\":0,\"active\":0},\"voltage\":{\"phase\":0,\"dropProc\":0,\"dropKv\":0,\"value\":0}}", this.n.getJsonCalcBody().toString());
	}	

	@Test
	public void testGetJsonSpecBody() {
		Map<String, Object> spec = new HashMap<String, Object>(); 
		spec.put("type", Node.TYPE_POWER);
		spec.put("label", "lbl");
		spec.put("cosFi", 0.0);
		spec.put("mi", 0.0);
		spec.put("lambda.max", 0.0);
		spec.put("lambda.min", 0.0);
		spec.put("power.active", 0.0);
		spec.put("power.installed", 0.0);
		spec.put("power.rated", 0.0);
		spec.put("power.reactive", 0.0);
		spec.put("reactance.longitudinal", 0.0);
		spec.put("reactance.transverse", 0.0);
		spec.put("voltage.level", 0.0);
		spec.put("voltage.phase", 0.0);
		spec.put("voltage.rated", 0.0);
		spec.put("voltage.value", 0.0);
		
		this.n.setSpec(spec);
		assertEquals("{\"lambda\":{\"min\":0,\"max\":0},\"reactance\":{\"transverse\":0,\"longitudinal\":0},\"label\":\"lbl\",\"power\":{\"rated\":0,\"installed\":0,\"reactive\":0,\"active\":0},\"type\":\"power\",\"mi\":0,\"cosFi\":0,\"voltage\":{\"phase\":0,\"rated\":0,\"level\":0,\"value\":0}}", this.n.getJsonSpecBody().toString());
	}

	@Test
	public void testGetJsonMapPointBody() {
		this.n.setMapPoint(1);
		assertEquals("{\"mapPoint\":1}", this.n.getJsonMapPointBody().toString());
	}

	@Test
	public void testGetJsonSchemeBody() {
		this.n.setScheme(1);
		assertEquals("{\"scheme\":1}", this.n.getJsonSchemeBody().toString());
	}

	@Test
	public void testBuildNode() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"calc\":{\"load\":{\"active\":\"0\",\"reactive\":\"0\"},\"voltage\":{\"dropKv\":\"0\",\"dropProc\":\"0\",\"phase\":\"0\",\"value\":\"0\"}},"
				+ "\"spec\":{\"type\":\"power\",\"label\":null,\"cosFi\":\"0\",\"mi\":\"0\",\"lambda\":{\"max\":\"0\",\"min\":\"0\"},\"power\":{\"active\":\"0\""
				+ ",\"installed\":\"0\",\"rated\":\"0\",\"reactive\":\"0\"},\"reactance\":{\"longitudinal\":\"0\",\"transverse\":\"0\"},\"voltage\":{\"level\":\"0\""
				+ ",\"phase\":\"0\",\"rated\":\"0\",\"value\":\"0\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"mapPoint\":{\"_meta\":{\"id\":\"1\"}}}";
		this.n = Node.buildNode(response);
		
		Map<String, Double> calc = new HashMap<String, Double>(); 
		calc.put("load.active", 0.0);
		calc.put("load.reactive", 0.0);
		calc.put("voltage.dropKv", 0.0);
		calc.put("voltage.dropProc", 0.0);
		calc.put("voltage.phase", 0.0);
		calc.put("voltage.value", 0.0);
		
		Map<String, Object> spec = new HashMap<String, Object>(); 
		spec.put("type", Node.TYPE_POWER);
		spec.put("label", null);
		spec.put("cosFi", 0.0);
		spec.put("mi", 0.0);
		spec.put("lambda.max", 0.0);
		spec.put("lambda.min", 0.0);
		spec.put("power.active", 0.0);
		spec.put("power.installed", 0.0);
		spec.put("power.rated", 0.0);
		spec.put("power.reactive", 0.0);
		spec.put("reactance.longitudinal", 0.0);
		spec.put("reactance.transverse", 0.0);
		spec.put("voltage.level", 0.0);
		spec.put("voltage.phase", 0.0);
		spec.put("voltage.rated", 0.0);
		spec.put("voltage.value", 0.0);
		
		assertEquals(1, this.n.getId().intValue());
		assertEquals(1, this.n.getMapPoint().intValue());
		assertEquals(1, this.n.getScheme().intValue());
		assertEquals(calc, this.n.getCalc());
		assertEquals(spec, this.n.getSpec());
	}
	
	@Test(expected = JSONException.class)
	public void testBuildNodeEmptyResponse() {
		this.n = Node.buildNode("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildNodeInvalidResponse() {
		this.n = Node.buildNode("{}");
	}

	@Test
	public void testBuildNodes() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"calc\":{\"load\":{\"active\":\"0\",\"reactive\":\"0\"},\"voltage\":{\"dropKv\":\"0\",\"dropProc\":\"0\",\"phase\":\"0\",\"value\":\"0\"}},"
				+ "\"spec\":{\"type\":\"power\",\"label\":null,\"cosFi\":\"0\",\"mi\":\"0\",\"lambda\":{\"max\":\"0\",\"min\":\"0\"},\"power\":{\"active\":\"0\""
				+ ",\"installed\":\"0\",\"rated\":\"0\",\"reactive\":\"0\"},\"reactance\":{\"longitudinal\":\"0\",\"transverse\":\"0\"},\"voltage\":{\"level\":\"0\""
				+ ",\"phase\":\"0\",\"rated\":\"0\",\"value\":\"0\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"mapPoint\":{\"_meta\":{\"id\":\"1\"}}},"
				+ "{\"_meta\":{\"id\":\"2\"},\"calc\":{\"load\":{\"active\":\"0\",\"reactive\":\"0\"},\"voltage\":{\"dropKv\":\"0\",\"dropProc\":\"0\",\"phase\":\"0\",\"value\":\"0\"}},"
				+ "\"spec\":{\"type\":\"power\",\"label\":null,\"cosFi\":\"0\",\"mi\":\"0\",\"lambda\":{\"max\":\"0\",\"min\":\"0\"},\"power\":{\"active\":\"0\""
				+ ",\"installed\":\"0\",\"rated\":\"0\",\"reactive\":\"0\"},\"reactance\":{\"longitudinal\":\"0\",\"transverse\":\"0\"},\"voltage\":{\"level\":\"0\""
				+ ",\"phase\":\"0\",\"rated\":\"0\",\"value\":\"0\"}},\"scheme\":{\"_meta\":{\"id\":\"1\"}},\"mapPoint\":{\"_meta\":{\"id\":\"1\"}}}]}";
		List<Node> n = Node.buildNodes(response);
		
		assertEquals(1, n.get(0).getId().intValue());
		assertEquals(1, n.get(0).getMapPoint().intValue());
		assertEquals(1, n.get(0).getScheme().intValue());
		
		assertEquals(2, n.get(1).getId().intValue());
		assertEquals(1, n.get(1).getMapPoint().intValue());
		assertEquals(1, n.get(1).getScheme().intValue());
	}

	@Test
	public void testBuildCalcMap() {
		Map<String, Double> expected = new HashMap<String, Double>(); 
		expected.put("load.active", 0.0);
		expected.put("load.reactive", 0.0);
		expected.put("voltage.dropKv", 0.0);
		expected.put("voltage.dropProc", 0.0);
		expected.put("voltage.phase", 0.0);
		expected.put("voltage.value", 0.0);
		
		assertEquals(expected, Node.buildCalcMap(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
	}

	@Test
	public void testBuildSpecMap() {
		Map<String, Object> expected = new HashMap<String, Object>(); 
		expected.put("type", Node.TYPE_POWER);
		expected.put("label", "lbl");
		expected.put("cosFi", 0.0);
		expected.put("mi", 0.0);
		expected.put("lambda.max", 0.0);
		expected.put("lambda.min", 0.0);
		expected.put("power.active", 0.0);
		expected.put("power.installed", 0.0);
		expected.put("power.rated", 0.0);
		expected.put("power.reactive", 0.0);
		expected.put("reactance.longitudinal", 0.0);
		expected.put("reactance.transverse", 0.0);
		expected.put("voltage.level", 0.0);
		expected.put("voltage.phase", 0.0);
		expected.put("voltage.rated", 0.0);
		expected.put("voltage.value", 0.0);
		
		assertEquals(expected, Node.buildSpecMap(Node.TYPE_POWER, "lbl", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
	}

}
