package org.rm.mongo.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class DBUtil {

	private Mongo mongo = null;
	private static MongoOptions options;
	static {
		options = new MongoOptions();
		options.connectionsPerHost = 200;
	}

	/**
	 * <p>
	 * Get mongo DB instance.
	 * </p>
	 * 
	 * @return
	 */
	public Mongo getMongo() {
		if (mongo == null) {
			try {
				int port = Integer.valueOf(ApplicationConstants.DB_PORT);
				mongo = new Mongo(new ServerAddress(
						ApplicationConstants.DB_IP_ADDRESS, port), options);

			} catch (MongoException | NumberFormatException
					| UnknownHostException e) {
				throw new RuntimeException(e);
			}
		}
		return mongo;
	}

	/**
	 * <p>
	 * Get the db instance with DB name
	 * </p>
	 * 
	 * @return
	 */
	public DB getDB() {
		Mongo mongo = getMongo();
		return mongo.getDB(ApplicationConstants.DATA_BASE_NAME);
	}

	/**
	 * <p>
	 * Disconnect
	 * </p>
	 * 
	 */
	public void disconnect() {
		if (null != mongo) {
			mongo.close();
		}
		mongo = null;
	}

}
