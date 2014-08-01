package org.rm.mongo.db;

public class Pojo {

	private long pojoId;
	private String content;

	public Pojo() {
	}

	public Pojo(long id, String content) {
		this.pojoId = id;
		this.content = content;

	}

	public long getPojoId() {
		return pojoId;
	}

	public void setPojoId(long pojoId) {
		this.pojoId = pojoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "[id: " + pojoId + " Content: " + content + "]";
	}
}
