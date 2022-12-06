
public class DataMemory {
static int rows[];
public DataMemory() {
	rows=new int[2048];
}
public void DataMemtoString() {
	System.out.println("----------- All Data Memory Content -----------");
	for( int i = 0; i<rows.length; i++) {
		System.out.println(rows[i]);
	}
}
}
