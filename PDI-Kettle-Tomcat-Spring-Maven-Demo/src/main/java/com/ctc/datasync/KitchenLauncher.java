package com.ctc.datasync;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import org.pentaho.di.core.Const;
import org.pentaho.di.kitchen.Kitchen;

public class KitchenLauncher {
	
	private static final String[] ARGS = "/rep:checkjars /dir:/ /job:MainJob -level=Normal".split(" ");
	
	private static Logger LOG = Logger.getLogger(KitchenLauncher.class.getName());

	public void launch() throws Exception {
		LOG.info("here");
		initialize();
		Kitchen.main(ARGS);
	}

	private static boolean initialized = false;
	
	public static void main(String[] args) throws Exception {
		new KitchenLauncher().launch();
	}

	private synchronized static void initialize() {
		if(!initialized) {
			try {

				ClassLoader cl = KitchenLauncher.class.getClassLoader();
				URL kpUrl;
				
				kpUrl = cl.getResource("datasync/kettle-home/" + InetAddress.getLocalHost().getHostName() + "/.kettle/"
						+ Const.KETTLE_PROPERTIES);
				
				LOG.info(Const.KETTLE_PROPERTIES + " = " + kpUrl.toString());

				System.setProperty("KETTLE_HOME", new File(kpUrl.getFile()).getParentFile().getParent());
				
				System.setProperty(Const.PENTAHO_METASTORE_FOLDER, System.getProperty("java.io.tmpdir"));

			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			}
		}
		initialized = true;
	}
}
