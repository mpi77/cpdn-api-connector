package cz.sd2.cpdn.api.connector.launchers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Object;

import cz.sd2.cpdn.api.connector.resources.MapPoint;
import cz.sd2.cpdn.api.connector.resources.Node;
import cz.sd2.cpdn.api.connector.resources.Permission;
import cz.sd2.cpdn.api.connector.resources.Scheme;
import cz.sd2.cpdn.api.connector.resources.Section;
import cz.sd2.cpdn.api.connector.utils.Connection;

public class DemoImporterLauncher {
	public static final String CLIENT_ID = "xxx";
	public static final String USER_NAME = "xxx";
	public static final String USER_PASSWORD = "xxx";
	public static final int JAVA_IMPORTER_PROFILE = 2;

	public static void main(String[] args) {
		try {
			Connection.authenticate(DemoImporterLauncher.CLIENT_ID, DemoImporterLauncher.USER_NAME, DemoImporterLauncher.USER_PASSWORD);

			// create a new scheme
			Scheme scheme = new Scheme(null, "HK-ZAPAD", "schema HK zapad", 1, false);
			JSONObject schemeCreate = new JSONObject(EntityUtils.toString(Scheme.create(scheme).getEntity()));
			scheme.setId((schemeCreate.isNull("id") == false) ? schemeCreate.getInt("id") : null);
			System.out.println(scheme);
			
			// create a new permission
			Permission perm = new Permission(null, scheme.getId(), DemoImporterLauncher.JAVA_IMPORTER_PROFILE, Permission.MODE_READ_WRITE_EXECUTE, "2015-12-01 00:00:00", "2017-12-01 00:00:00");
			JSONObject create = new JSONObject(EntityUtils.toString(Permission.create(perm).getEntity()));
			System.out.println(create.toString());
			
			// create new mapPoints
			MapPoint point1 = new MapPoint(null, null, null, null, 111, 111, scheme.getId(), null);
			JSONObject point1Create = new JSONObject(EntityUtils.toString(MapPoint.create(point1).getEntity()));
			point1.setId((point1Create.isNull("id") == false) ? point1Create.getInt("id") : null);
			System.out.println(point1);
			
			MapPoint point2 = new MapPoint(null, null, null, null, 222, 111, scheme.getId(), null);
			JSONObject point2Create = new JSONObject(EntityUtils.toString(MapPoint.create(point2).getEntity()));
			point2.setId((point2Create.isNull("id") == false) ? point2Create.getInt("id") : null);
			System.out.println(point2);
			
			// create new nodes (power node + consumption node)
			Map<String, Double> calc1 = Node.buildCalcMap(.0, .0, .0, .0, .0, .0);
			Map<String, Object> spec1 = Node.buildSpecMap(Node.TYPE_POWER, "label", .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0);
			Node node1 = new Node(null, calc1, spec1, scheme.getId(), point1.getId());
			JSONObject createNode1 = new JSONObject(EntityUtils.toString(Node.create(node1).getEntity()));
			node1.setId((createNode1.isNull("id") == false) ? createNode1.getInt("id") : null);
			System.out.println(node1);
			
			Map<String, Double> calc2 = Node.buildCalcMap(.0, .0, .0, .0, .0, .0);
			Map<String, Object> spec2 = Node.buildSpecMap(Node.TYPE_CONSUMPTION, "label", .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0);
			Node node2 = new Node(null, calc2, spec2, scheme.getId(), point2.getId());
			JSONObject createNode2 = new JSONObject(EntityUtils.toString(Node.create(node2).getEntity()));
			node2.setId((createNode2.isNull("id") == false) ? createNode2.getInt("id") : null);
			System.out.println(node2);
			
			// create new sections (line section between power and consumption node)
			Map<String, Double> calc = Section.buildCalcMap(.0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0);
			Map<String, Object> spec = Section.buildSpecMap(Section.TYPE_LINE, "label", .0, Section.STATUS_UNDEFINED, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0, .0);
			Map<String, Integer> nodes = Section.buildNodesMap(point1.getId(), point2.getId(), null);
			Section section1 = new Section(null, scheme.getId(), calc, nodes, spec);
			JSONObject createSection1 = new JSONObject(EntityUtils.toString(Section.create(section1).getEntity()));
			section1.setId((createSection1.isNull("id") == false) ? createSection1.getInt("id") : null);
			System.out.println(section1);
			
			// create new object
			cz.sd2.cpdn.api.connector.resources.Object obj = new cz.sd2.cpdn.api.connector.resources.Object(null, "HK0123", scheme.getId());
			JSONObject createObject = new JSONObject(EntityUtils.toString(cz.sd2.cpdn.api.connector.resources.Object.create(obj).getEntity()));
			obj.setId((createObject.isNull("id") == false) ? createObject.getInt("id") : null);
			
			// assign node1 into object
			System.out.println(cz.sd2.cpdn.api.connector.resources.Object.createMember(obj.getId(), node1.getId()));
			
		} catch (KeyManagementException | JSONException | ParseException | NoSuchAlgorithmException | KeyStoreException
				| IOException | AuthenticationException e) {
			e.printStackTrace();
		}
	}

}
