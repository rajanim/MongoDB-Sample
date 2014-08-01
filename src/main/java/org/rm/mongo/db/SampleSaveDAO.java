package org.rm.mongo.db;

import java.util.List;
import java.util.logging.Logger;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * <p> This class is a raw dao class, to save plain java object to mongo db
 * </p>
 *
 * @author rajani.maski
 * @version 1.0
 *
 */
public class SampleSaveDAO {

	/**
	 * Logger object
	 */
	private static final Logger logger = Logger.getLogger("SampleSaveDAO");

	/**
	 * <p>
	 * To push the plain old java objects[pojo] to Mongo Sample Db Database
	 * </p>
	 * 
	 * @param pojo
	 * @return true : data transfer was success otherwise false.
	 */
	public static boolean pushDataToMongo(List<Pojo> pojos) {
		DBUtil mongoDbUtils = new DBUtil();
		String dbId = null;
		if (!ApplicationConstants.DATA_BASE_NAME.isEmpty() && (null != pojos)) {
			try {
				DBCollection collection = mongoDbUtils.getDB().getCollection(
						ApplicationConstants.DATA_COLLECTION_NAME);
				if (null != collection) {
					for (Pojo pojo : pojos) {
						DBObject dbObject = convertFeedToDBObject(pojo);
						collection.save(dbObject);
						ObjectId id = (ObjectId) dbObject.get("_id");
						dbId = id.toString();
						logger.info("object saved: " + dbId);
					}
				} else {
					return false;
				}
			} catch (MongoException e) {
				throw new RuntimeException(e);
			} finally {
				mongoDbUtils.disconnect();
			}
		}
		return true;
	}

	private static DBObject convertFeedToDBObject(Pojo pojo) {
		Gson gson = new Gson();
		String json = gson.toJson(pojo);
		return (DBObject) JSON.parse(json);
	}
}
