package ctc.test.checkjars;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckJars {

	@Test
	public void check1() throws KettleException, JsonParseException, JsonMappingException, IOException {

		String resourcesPath = (new File(".").getAbsolutePath()) + "/src/test/resources/checkjars";
		String ktr_path = resourcesPath + "/Transformation 1.ktr";
		String jdbcPropertiesPath = resourcesPath;

		/**
		 * Initialize the Kettle Enviornment
		 */
		System.setProperty("KETTLE_JNDI_ROOT", jdbcPropertiesPath);
		KettleEnvironment.init();

		/**
		 * Create a trans object to properly assign the ktr metadata.
		 * 
		 * @filedb: The ktr file path to be executed.
		 * 
		 */
		TransMeta metadata = new TransMeta(ktr_path);
		Trans trans = new Trans(metadata);

		// Execute the transformation
		trans.execute(null);
		trans.waitUntilFinished();

		// checking for errors
		if (trans.getErrors() > 0) {
			System.out.println("Erroruting Transformation");
		}

		File f = new File(System.getProperty("java.io.tmpdir"), "checkjars.json.js");
		ObjectMapper om = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String,Object> data = om.readValue(f, Map.class);
		
		org.testng.Assert.assertEquals(data.toString(), "{data=[{a=3, b=4}]}");
		
	}
	
	@Test
	public void check2() throws KettleException, JsonParseException, JsonMappingException, IOException {

		String resourcesPath = (new File(".").getAbsolutePath()) + "/src/test/resources/checkjars";
		String ktr_path = resourcesPath + "/Transformation 2.ktr";
		String jdbcPropertiesPath = resourcesPath;

		/**
		 * Initialize the Kettle Enviornment
		 */
		System.setProperty("KETTLE_JNDI_ROOT", jdbcPropertiesPath);
		KettleEnvironment.init();

		/**
		 * Create a trans object to properly assign the ktr metadata.
		 * 
		 * @filedb: The ktr file path to be executed.
		 * 
		 */
		TransMeta metadata = new TransMeta(ktr_path);
		Trans trans = new Trans(metadata);

		// Execute the transformation
		trans.execute(null);
		trans.waitUntilFinished();

		// checking for errors
		if (trans.getErrors() > 0) {
			System.out.println("Erroruting Transformation");
		}

		File f = new File(System.getProperty("java.io.tmpdir"), "checkjars.json.js");
		ObjectMapper om = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String,Object> data = om.readValue(f, Map.class);
		
		org.testng.Assert.assertEquals(data.toString(), "{data=[{a=3, b=4}]}");
		
	}

}
