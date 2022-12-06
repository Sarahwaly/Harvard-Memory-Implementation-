import java.util.ArrayList;

public class InsMemory {
ArrayList <String> rows ;
public InsMemory() {
	rows=new ArrayList <>();
}

public void MemtoString() {
	System.out.println("----------- All Instruction Memory Content -----------");
	for( int i = 0; i<rows.size(); i++) {
		System.out.println(rows.get(i));
	}
}

}
