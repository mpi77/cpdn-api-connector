package cz.sd2.cpdn.api.connector.tests.unit.resources;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.sd2.cpdn.api.connector.resources.Section;

public class SectionTest {

	private Section s;
	
	@Before
	public void setUp() throws Exception {
		this.s = new Section();
	}

	@Test
	public void testSection() {
		assertNotNull(this.s);
		
		this.s = new Section(null, null, null, null, null);
		assertNotNull(this.s);
	}

	@Test
	public void testGetSetId() {
		assertSame(0, this.s.getId().intValue());
		
		this.s.setId(1);
		assertSame(1, this.s.getId().intValue());
	}

	@Test
	public void testGetSetScheme() {
		assertSame(0, this.s.getScheme().intValue());
		
		this.s.setScheme(1);
		assertSame(1, this.s.getScheme().intValue());
		
		this.s.setScheme(null);
		assertNull(this.s.getScheme());
	}

	@Test
	public void testGetSetCalc() {
		HashMap<String, Double> calc = new HashMap<String, Double>();
		this.s.setCalc(calc);
		assertSame(calc, this.s.getCalc());
	}

	@Test
	public void testGetSetNodes() {
		HashMap<String, Integer> nodes = new HashMap<String, Integer>();
		this.s.setNodes(nodes);
		assertSame(nodes, this.s.getNodes());
	}

	@Test
	public void testGetSetSpec() {
		HashMap<String, Object> spec = new HashMap<String, Object>();
		this.s.setSpec(spec);
		assertSame(spec, this.s.getSpec());
	}

	@Test
	public void testCreateJsonBody() {
		this.s.setScheme(1);
		
		Map<String, Double> calc = new HashMap<String, Double>(); 
		calc.put("current.dst.phase", 0.0);
		calc.put("current.dst.value", 0.0);
		calc.put("current.dst.ratio", 0.0);
		calc.put("current.src.phase", 0.0);
		calc.put("current.src.value", 0.0);
		calc.put("current.src.ratio", 0.0);
		calc.put("losses.active", 0.0);
		calc.put("losses.reactive", 0.0);
		calc.put("power.dst.active", 0.0);
		calc.put("power.dst.reactive", 0.0);
		calc.put("power.src.active", 0.0);
		calc.put("power.src.reactive", 0.0);
		this.s.setCalc(calc);
		
		Map<String, Object> spec = new HashMap<String, Object>(); 
		spec.put("type", Section.TYPE_LINE);
		spec.put("label", "lbl");
		spec.put("conductance", 0.0);
		spec.put("status", Section.STATUS_OFF);
		spec.put("susceptance", 0.0);
		spec.put("current.max", 0.0);
		spec.put("current.noLoad", 0.0);
		spec.put("reactance.ratio", 0.0);
		spec.put("reactance.value", 0.0);
		spec.put("resistance.ratio", 0.0);
		spec.put("resistance.value", 0.0);
		spec.put("losses.noLoad", 0.0);
		spec.put("losses.short.ab", 0.0);
		spec.put("losses.short.ac", 0.0);
		spec.put("losses.short.bc", 0.0);
		spec.put("power.rated.ab", 0.0);
		spec.put("power.rated.ac", 0.0);
		spec.put("power.rated.bc", 0.0);
		spec.put("voltage.pri.actual", 0.0);
		spec.put("voltage.pri.rated", 0.0);
		spec.put("voltage.sec.actual", 0.0);
		spec.put("voltage.sec.rated", 0.0);
		spec.put("voltage.trc.actual", 0.0);
		spec.put("voltage.trc.rated", 0.0);
		spec.put("voltage.short.ab", 0.0);
		spec.put("voltage.short.ac", 0.0);
		spec.put("voltage.short.bc", 0.0);
		this.s.setSpec(spec);
		
		Map<String, Integer> nodes = new HashMap<String, Integer>(); 
		nodes.put("dst", 1);
		nodes.put("src", 2);
		nodes.put("trc", 3);
		this.s.setNodes(nodes);
		
		String expected = "{\"nodes\":{\"dst\":1,\"trc\":3,\"src\":2},\"scheme\":1,\"calc\":{\"current\":{\"dst\":{\"phase\":0,\"value\":0,\"ratio\":0},\"src\":{\"phase\":0,\"value\":0,\"ratio\":0}},"
				+ "\"power\":{\"dst\":{\"reactive\":0,\"active\":0},\"src\":{\"reactive\":0,\"active\":0}},\"losses\":{\"reactive\":0,\"active\":0}},\"spec\":{\"current\":{\"noLoad\":0,\"max\":0},"
				+ "\"reactance\":{\"value\":0,\"ratio\":0},\"susceptance\":0,\"label\":\"lbl\",\"power\":{\"rated\":{\"ab\":0,\"bc\":0,\"ac\":0}},\"type\":\"line\",\"conductance\":0,"
				+ "\"losses\":{\"noLoad\":0,\"short\":{\"ab\":0,\"bc\":0,\"ac\":0}},\"resistance\":{\"value\":0,\"ratio\":0},\"status\":\"off\",\"voltage\":{\"sec\":{\"rated\":0,"
				+ "\"actual\":0},\"trc\":{\"rated\":0,\"actual\":0},\"pri\":{\"rated\":0,\"actual\":0},\"short\":{\"ab\":0,\"bc\":0,\"ac\":0}}}}";
		
		assertEquals(expected, this.s.createJsonBody());
	}

	@Test
	public void testGetJsonCalcBody() {
		Map<String, Double> calc = new HashMap<String, Double>(); 
		calc.put("current.dst.phase", 0.0);
		calc.put("current.dst.value", 0.0);
		calc.put("current.dst.ratio", 0.0);
		calc.put("current.src.phase", 0.0);
		calc.put("current.src.value", 0.0);
		calc.put("current.src.ratio", 0.0);
		calc.put("losses.active", 0.0);
		calc.put("losses.reactive", 0.0);
		calc.put("power.dst.active", 0.0);
		calc.put("power.dst.reactive", 0.0);
		calc.put("power.src.active", 0.0);
		calc.put("power.src.reactive", 0.0);
		
		this.s.setCalc(calc);
		assertEquals("{\"current\":{\"dst\":{\"phase\":0,\"value\":0,\"ratio\":0},\"src\":{\"phase\":0,\"value\":0,\"ratio\":0}},"
				+ "\"power\":{\"dst\":{\"reactive\":0,\"active\":0},\"src\":{\"reactive\":0,\"active\":0}},\"losses\":{\"reactive\":0,"
				+ "\"active\":0}}", this.s.getJsonCalcBody().toString());
	}

	@Test
	public void testGetJsonSpecBody() {
		Map<String, Object> spec = new HashMap<String, Object>(); 
		spec.put("type", Section.TYPE_LINE);
		spec.put("label", "lbl");
		spec.put("conductance", 0.0);
		spec.put("status", Section.STATUS_OFF);
		spec.put("susceptance", 0.0);
		spec.put("current.max", 0.0);
		spec.put("current.noLoad", 0.0);
		spec.put("reactance.ratio", 0.0);
		spec.put("reactance.value", 0.0);
		spec.put("resistance.ratio", 0.0);
		spec.put("resistance.value", 0.0);
		spec.put("losses.noLoad", 0.0);
		spec.put("losses.short.ab", 0.0);
		spec.put("losses.short.ac", 0.0);
		spec.put("losses.short.bc", 0.0);
		spec.put("power.rated.ab", 0.0);
		spec.put("power.rated.ac", 0.0);
		spec.put("power.rated.bc", 0.0);
		spec.put("voltage.pri.actual", 0.0);
		spec.put("voltage.pri.rated", 0.0);
		spec.put("voltage.sec.actual", 0.0);
		spec.put("voltage.sec.rated", 0.0);
		spec.put("voltage.trc.actual", 0.0);
		spec.put("voltage.trc.rated", 0.0);
		spec.put("voltage.short.ab", 0.0);
		spec.put("voltage.short.ac", 0.0);
		spec.put("voltage.short.bc", 0.0);
		
		this.s.setSpec(spec);
		assertEquals("{\"current\":{\"noLoad\":0,\"max\":0},\"reactance\":{\"value\":0,\"ratio\":0},\"susceptance\":0,\"label\":\"lbl\",\"power\":{\"rated\":{\"ab\":0,"
				+ "\"bc\":0,\"ac\":0}},\"type\":\"line\",\"conductance\":0,\"losses\":{\"noLoad\":0,\"short\":{\"ab\":0,\"bc\":0,\"ac\":0}},\"resistance\":{\"value\":0,"
				+ "\"ratio\":0},\"status\":\"off\",\"voltage\":{\"sec\":{\"rated\":0,\"actual\":0},\"trc\":{\"rated\":0,\"actual\":0},\"pri\":{\"rated\":0,\"actual\":0},"
				+ "\"short\":{\"ab\":0,\"bc\":0,\"ac\":0}}}", this.s.getJsonSpecBody().toString());
	
	}

	@Test
	public void testGetJsonSchemeBody() {
		this.s.setScheme(1);
		assertEquals("{\"scheme\":1}", this.s.getJsonSchemeBody().toString());
	}

	@Test
	public void testGetJsonNodesBody() {
		Map<String, Integer> nodes = new HashMap<String, Integer>(); 
		nodes.put("dst", 1);
		nodes.put("src", 2);
		nodes.put("trc", 3);
		
		this.s.setNodes(nodes);
		assertEquals("{\"dst\":1,\"trc\":3,\"src\":2}", this.s.getJsonNodesBody().toString());
	}

	@Test
	public void testBuildSection() {
		String response = "{\"_meta\":{\"id\":\"1\"},\"calc\":{\"current\":{\"dst\":{\"phase\":\"0\",\"ratio\":\"0\",\"value\":\"0\"},\"src\":{\"phase\":\"0\",\"ratio\":\"0\","
				+ "\"value\":\"0\"}},\"losses\":{\"active\":\"0\",\"reactive\":\"0\"},\"power\":{\"dst\":{\"active\":\"0\",\"reactive\":\"0\"},\"src\":{\"active\":\"0\","
				+ "\"reactive\":\"0\"}}},\"spec\":{\"type\":\"line\",\"label\":null,\"conductance\":\"0\",\"status\":\"off\",\"susceptance\":\"0\",\"current\":{\"max\":\"0\","
				+ "\"noLoad\":\"0\"},\"reactance\":{\"ratio\":\"0\",\"value\":\"0\"},\"resistance\":{\"ratio\":\"0\",\"value\":\"0\"},\"losses\":{\"noLoad\":\"0\","
				+ "\"short\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}},\"power\":{\"rated\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}},\"voltage\":{\"pri\":{\"actual\":\"0\","
				+ "\"rated\":\"0\"},\"sec\":{\"actual\":\"0\",\"rated\":\"0\"},\"trc\":{\"actual\":\"0\",\"rated\":\"0\"},\"short\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}}},"
				+ "\"nodes\":{\"dst\":{\"_meta\":{\"id\":\"1\"}},\"src\":{\"_meta\":{\"id\":\"2\"}},\"trc\":null},\"scheme\":{\"_meta\":{\"id\":\"1\"}}}";
		this.s = Section.buildSection(response);
		
		Map<String, Double> calc = new HashMap<String, Double>(); 
		calc.put("current.dst.phase", 0.0);
		calc.put("current.dst.value", 0.0);
		calc.put("current.dst.ratio", 0.0);
		calc.put("current.src.phase", 0.0);
		calc.put("current.src.value", 0.0);
		calc.put("current.src.ratio", 0.0);
		calc.put("losses.active", 0.0);
		calc.put("losses.reactive", 0.0);
		calc.put("power.dst.active", 0.0);
		calc.put("power.dst.reactive", 0.0);
		calc.put("power.src.active", 0.0);
		calc.put("power.src.reactive", 0.0);
		
		Map<String, Object> spec = new HashMap<String, Object>(); 
		spec.put("type", Section.TYPE_LINE);
		spec.put("label", null);
		spec.put("conductance", 0.0);
		spec.put("status", Section.STATUS_OFF);
		spec.put("susceptance", 0.0);
		spec.put("current.max", 0.0);
		spec.put("current.noLoad", 0.0);
		spec.put("reactance.ratio", 0.0);
		spec.put("reactance.value", 0.0);
		spec.put("resistance.ratio", 0.0);
		spec.put("resistance.value", 0.0);
		spec.put("losses.noLoad", 0.0);
		spec.put("losses.short.ab", 0.0);
		spec.put("losses.short.ac", 0.0);
		spec.put("losses.short.bc", 0.0);
		spec.put("power.rated.ab", 0.0);
		spec.put("power.rated.ac", 0.0);
		spec.put("power.rated.bc", 0.0);
		spec.put("voltage.pri.actual", 0.0);
		spec.put("voltage.pri.rated", 0.0);
		spec.put("voltage.sec.actual", 0.0);
		spec.put("voltage.sec.rated", 0.0);
		spec.put("voltage.trc.actual", 0.0);
		spec.put("voltage.trc.rated", 0.0);
		spec.put("voltage.short.ab", 0.0);
		spec.put("voltage.short.ac", 0.0);
		spec.put("voltage.short.bc", 0.0);
		
		Map<String, Integer> nodes = new HashMap<String, Integer>(); 
		nodes.put("dst", 1);
		nodes.put("src", 2);
		nodes.put("trc", null);
		
		assertEquals(1, this.s.getId().intValue());
		assertEquals(1, this.s.getScheme().intValue());
		assertEquals(calc, this.s.getCalc());
		assertEquals(spec, this.s.getSpec());
		assertEquals(nodes, this.s.getNodes());
	}

	@Test(expected = JSONException.class)
	public void testBuildSectionEmptyResponse() {
		this.s = Section.buildSection("");
	}
	
	@Test(expected = JSONException.class)
	public void testBuildSectionInvalidResponse() {
		this.s = Section.buildSection("{}");
	}
	
	@Test
	public void testBuildSections() {
		String response = "{\"items\":[{\"_meta\":{\"id\":\"1\"},\"calc\":{\"current\":{\"dst\":{\"phase\":\"0\",\"ratio\":\"0\",\"value\":\"0\"},\"src\":{\"phase\":\"0\",\"ratio\":\"0\","
				+ "\"value\":\"0\"}},\"losses\":{\"active\":\"0\",\"reactive\":\"0\"},\"power\":{\"dst\":{\"active\":\"0\",\"reactive\":\"0\"},\"src\":{\"active\":\"0\","
				+ "\"reactive\":\"0\"}}},\"spec\":{\"type\":\"line\",\"label\":null,\"conductance\":\"0\",\"status\":\"off\",\"susceptance\":\"0\",\"current\":{\"max\":\"0\","
				+ "\"noLoad\":\"0\"},\"reactance\":{\"ratio\":\"0\",\"value\":\"0\"},\"resistance\":{\"ratio\":\"0\",\"value\":\"0\"},\"losses\":{\"noLoad\":\"0\","
				+ "\"short\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}},\"power\":{\"rated\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}},\"voltage\":{\"pri\":{\"actual\":\"0\","
				+ "\"rated\":\"0\"},\"sec\":{\"actual\":\"0\",\"rated\":\"0\"},\"trc\":{\"actual\":\"0\",\"rated\":\"0\"},\"short\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}}},"
				+ "\"nodes\":{\"dst\":{\"_meta\":{\"id\":\"1\"}},\"src\":{\"_meta\":{\"id\":\"2\"}},\"trc\":null},\"scheme\":{\"_meta\":{\"id\":\"1\"}}},"
				+ "{\"_meta\":{\"id\":\"2\"},\"calc\":{\"current\":{\"dst\":{\"phase\":\"0\",\"ratio\":\"0\",\"value\":\"0\"},\"src\":{\"phase\":\"0\",\"ratio\":\"0\","
				+ "\"value\":\"0\"}},\"losses\":{\"active\":\"0\",\"reactive\":\"0\"},\"power\":{\"dst\":{\"active\":\"0\",\"reactive\":\"0\"},\"src\":{\"active\":\"0\","
				+ "\"reactive\":\"0\"}}},\"spec\":{\"type\":\"line\",\"label\":null,\"conductance\":\"0\",\"status\":\"off\",\"susceptance\":\"0\",\"current\":{\"max\":\"0\","
				+ "\"noLoad\":\"0\"},\"reactance\":{\"ratio\":\"0\",\"value\":\"0\"},\"resistance\":{\"ratio\":\"0\",\"value\":\"0\"},\"losses\":{\"noLoad\":\"0\","
				+ "\"short\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}},\"power\":{\"rated\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}},\"voltage\":{\"pri\":{\"actual\":\"0\","
				+ "\"rated\":\"0\"},\"sec\":{\"actual\":\"0\",\"rated\":\"0\"},\"trc\":{\"actual\":\"0\",\"rated\":\"0\"},\"short\":{\"ab\":\"0\",\"ac\":\"0\",\"bc\":\"0\"}}},"
				+ "\"nodes\":{\"dst\":{\"_meta\":{\"id\":\"1\"}},\"src\":{\"_meta\":{\"id\":\"2\"}},\"trc\":null},\"scheme\":{\"_meta\":{\"id\":\"1\"}}}]}";
		
		List<Section> s = Section.buildSections(response);
		
		assertEquals(1, s.get(0).getId().intValue());
		assertEquals(1, s.get(0).getScheme().intValue());
		
		assertEquals(2, s.get(1).getId().intValue());
		assertEquals(1, s.get(1).getScheme().intValue());
	}

	@Test
	public void testBuildCalcMap() {
		Map<String, Double> expected = new HashMap<String, Double>(); 
		expected.put("current.dst.phase", 0.0);
		expected.put("current.dst.value", 0.0);
		expected.put("current.dst.ratio", 0.0);
		expected.put("current.src.phase", 0.0);
		expected.put("current.src.value", 0.0);
		expected.put("current.src.ratio", 0.0);
		expected.put("losses.active", 0.0);
		expected.put("losses.reactive", 0.0);
		expected.put("power.dst.active", 0.0);
		expected.put("power.dst.reactive", 0.0);
		expected.put("power.src.active", 0.0);
		expected.put("power.src.reactive", 0.0);
		
		assertEquals(expected, Section.buildCalcMap(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
	}

	@Test
	public void testBuildNodesMap() {
		Map<String, Integer> expected = new HashMap<String, Integer>(); 
		expected.put("dst", 1);
		expected.put("src", 2);
		expected.put("trc", 3);
		
		assertEquals(expected, Section.buildNodesMap(1, 2, 3));
	}

	@Test
	public void testBuildSpecMap() {
		Map<String, Object> expected = new HashMap<String, Object>(); 
		expected.put("type", Section.TYPE_LINE);
		expected.put("label", "lbl");
		expected.put("conductance", 0.0);
		expected.put("status", Section.STATUS_OFF);
		expected.put("susceptance", 0.0);
		expected.put("current.max", 0.0);
		expected.put("current.noLoad", 0.0);
		expected.put("reactance.ratio", 0.0);
		expected.put("reactance.value", 0.0);
		expected.put("resistance.ratio", 0.0);
		expected.put("resistance.value", 0.0);
		expected.put("losses.noLoad", 0.0);
		expected.put("losses.short.ab", 0.0);
		expected.put("losses.short.ac", 0.0);
		expected.put("losses.short.bc", 0.0);
		expected.put("power.rated.ab", 0.0);
		expected.put("power.rated.ac", 0.0);
		expected.put("power.rated.bc", 0.0);
		expected.put("voltage.pri.actual", 0.0);
		expected.put("voltage.pri.rated", 0.0);
		expected.put("voltage.sec.actual", 0.0);
		expected.put("voltage.sec.rated", 0.0);
		expected.put("voltage.trc.actual", 0.0);
		expected.put("voltage.trc.rated", 0.0);
		expected.put("voltage.short.ab", 0.0);
		expected.put("voltage.short.ac", 0.0);
		expected.put("voltage.short.bc", 0.0);
		
		assertEquals(expected, Section.buildSpecMap(Section.TYPE_LINE, "lbl", 0.0, Section.STATUS_OFF, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
	}

}
