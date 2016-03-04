package cz.sd2.cpdn.api.connector.launchers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cz.sd2.cpdn.api.connector.resources.Task;
import cz.sd2.cpdn.api.connector.utils.Connection;

public class DemoExecutorLauncher {
	public static final String CLIENT_ID = "xxx";
	public static final String USER_NAME = "xxx";
	public static final String USER_PASSWORD = "xxx";
	public static final String JAVA_EXECUTOR_ID = "xxx";

	public static void main(String[] args) {
		try {
			Connection.authenticate(DemoExecutorLauncher.CLIENT_ID, DemoExecutorLauncher.USER_NAME, DemoExecutorLauncher.USER_PASSWORD);

			List<Task> list = Task.buildTasks(EntityUtils.toString(Task.getAll(DemoExecutorLauncher.JAVA_EXECUTOR_ID, Task.STATUS_NEW, 100).getEntity()));
			System.out.println(Arrays.toString(list.toArray()));
			
			if(list.isEmpty() == false){
				Task t = list.get(0);
				
				JSONObject update = new JSONObject(EntityUtils.toString(Task.update(t).getEntity()));
				System.out.println(update.toString());
			}
			
		} catch (KeyManagementException | JSONException | ParseException | NoSuchAlgorithmException | KeyStoreException
				| IOException | AuthenticationException e) {
			e.printStackTrace();
		}
	}

}
