package logica;
import datatypesWS.LogEntryWS;
public interface ILogger {
	public void addLogs(LogEntryWS [] entries);
	public LogEntryWS [] getLogs();
}
