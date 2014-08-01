package org.rm.mongo.db;

import java.util.ArrayList;
import java.util.List;


/**
 * <p> Before you run this sample class, ensure that mongo db is up and running.
 * Also in application constants change the  Ip address and the port number accordingly
 * </p>
 *
 * @author rajani.maski
 * @version 1.0
 *
 */
public class Main {

	public static void main(String[] args) {

		List<Pojo> pojos = getPojo();
		SampleSaveDAO.pushDataToMongo(pojos);
	}

	private static List<Pojo> getPojo() {
		List<Pojo> pojos = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Pojo pojo = new Pojo(i, "pojo" + i);
			pojos.add(pojo);
		}
		return pojos;
	}
}
