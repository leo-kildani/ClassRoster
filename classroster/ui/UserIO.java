package classroster.ui;

public interface UserIO {
	public void print(String prompt);
	
	public String readString(String prompt);
	
	public String readString(String prompt, String target);
	
	public int readInt(String prompt);
	
	public int readInt(String prompt, int min, int max);
	
	public long readLong(String prompt);
	
	public long readLong(String prompt, long min, long max);
	
	public double readDouble(String prompt);
	
	public double readDouble(String prompt, int min, int max);
	
	public float readFloat(String prompt);
	
	public float readFloat(String prompt, float min, float max);
	
	public void close();
}
