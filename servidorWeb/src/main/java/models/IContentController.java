package models;

import java.io.IOException;

public interface IContentController {
	public byte[] get(String type, String id) throws IOException;
	public void post(String type, String id, byte[] content) throws IOException;
}
