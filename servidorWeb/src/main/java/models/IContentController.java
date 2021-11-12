package models;

import java.io.IOException;

import webservices.LogEntryWS;

public interface IContentController {
	public byte[] get(String type, String id) throws IOException;
	public void post(String type, String id, byte[] content) throws IOException;
	public void sendReport(LogEntryWS entry);
}
