import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Execute {
	int clock=1;
	String S1currInstruction;
	String S2currInstruction;
	String S3currInstruction;
	InsMemory x;
	DataMemory y;
	String LastFetched;
	Instruction LastDecoded;
	Instruction LastExecuted;
	Register PC;
	static boolean NEV=false;
	Register SREG;
	ArrayList<Register> Registers;
	static HashMap< String,Integer> negNums= new HashMap<>() ;
	static HashMap< String,Integer> regists= new HashMap<>() ;
	int go;
	boolean lastclock=false;
//ArrayList <Register> reg=new ArrayList <> ();
public Execute() throws FileNotFoundException {
	 x= new InsMemory();	
	 y= new DataMemory();
	 this.LastExecuted=new Instruction(this,"","1011010101101010",1505);
	  Registers = new ArrayList<>();
	for(int i = 0;i<64;i++) {
		String binary= Integer.toBinaryString(i);
		if(binary.length()<6) {
			for(int j =binary.length();j<6;j++) {
				binary="0"+binary;
			}
		}
			else {
				if(binary.length()>6) {
					binary=binary.substring(binary.length()-6, binary.length());
				}
			
		}
		Register curr=new Register("R"+i);
		Registers.add(curr);
		regists.put(binary, i);
		//reg.add(curr);
	}
	SREG = new Register("SREG");
	PC = new Register("PC");
	//Scanner filename = new Scanner(System.in);
	FileReader reader = new FileReader("one.txt");
	Scanner scan = new Scanner(reader);
	int i =0;
	String binaryized;
	while(scan.hasNextLine()&&i<1024){
		binaryized=binary(scan.nextLine());
		if(binaryized.length()>16) {
	//		System.out.println("The inputs can have only 8 bits no more! Please check your inputs!");
		//	System.exit(1);
			binaryized=binaryized.substring(0,10)+binaryized.substring(binaryized.length()-6,binaryized.length());
			x.rows.add(binaryized);
			 i++;		
			 continue;
		}
		else {
			//System.out.println(binaryized+"      BBBBBBBBBBB");
			 x.rows.add(binaryized);
			 i++;		
			 continue;
		}
		}
	//int numofC=2+x.rows.length;
	while(PC.pcBits<x.rows.size()) {		
			fetch(PC.pcBits);
			if(!this.LastExecuted.name.equals("BEQZ")) {
				PC.pcBits++;
			}
	}
	System.out.println(ClocktoString());
		
	
	decode(this.LastFetched);
	this.clock++;
	
	System.out.println(ClocktoString());
	 execute(this.LastDecoded);
	 lastclock=true;
	 decode(this.LastFetched);
}



public void getAllRegistersContent() {
	System.out.println("----------- All Registers Content -----------");
	for (int i = 0; i<Registers.size(); i++) {
		System.out.println(Registers.get(i).toString());
//		System.out.println("SREG Content: " + Registers.get(i).SREGbits);
	}
	
	System.out.println("PC Content: " + PC.pcBits);
	
	
}

public void fetch(int PC) {
	System.out.println(ClocktoString());
 String s = x.rows.get(PC);
 if(this.clock!=1) {
	 
	 if(!lastclock) {
	 
	 decode(this.LastFetched);	 
	 } else {
		 
		 decode(this.LastFetched);
		
		 getAllRegistersContent();
		 x.MemtoString();
		 y.DataMemtoString();
		
		 
	 }
 }
 this.LastFetched = s;
 this.clock++;


}

public void decode(String ins) {	
	String ma=ins.substring(0, 4);
//	System.out.println(ma);
	int t=0b1111111111111111111&this.StringtoDec(ins);
	int m = 0b1111111111111111&t;
	Instruction x = new Instruction(this,ma,ins,m);
	if(this.clock!=2) {
		execute(this.LastDecoded);
	}
	this.LastDecoded=x;
}
public void execute(Instruction I) {
	I.R1.name=this.Registers.get(I.R1.bits).name;
	I.R2.name=this.Registers.get(I.R2.bits).name;

	if(go==0) {
	if(lastclock) {
	getAllRegistersContent();
	 x.MemtoString();
	 y.DataMemtoString();
	
	 
	}
	
	if (I.type == 'R') {
		System.out.println(I.typeRtoString());
	} else {
		System.out.println(I.typeItoString());
	}
	executehelper(I);
	}
	else {
		go--;
	}
	
}
public void executehelper(Instruction I) {
//I.name=this.Registers.get(I.R1.bits).name;
	I.R1.name=this.Registers.get(I.R1.bits).name;
//	I.R2=this.Registers.get(I.R2.bits);
	switch(I.name){
	case "ADD" : Register.ADD(this.Registers.get(I.R1.bits),this.Registers.get(I.R2.bits)); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "SUB": Register.SUB(this.Registers.get(I.R1.bits),this.Registers.get(I.R2.bits)); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "MUL": Register.MUL(this.Registers.get(I.R1.bits),this.Registers.get(I.R2.bits)); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "LDI": Register.LDI(this.Registers.get(I.R1.bits),I.IMMEDIATE); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "BEQZ":
		if(this.Registers.get(I.R1.bits).bits==0) 
		{
			go=2;
		}
		Register.BEQZ(this.Registers.get(I.R1.bits),I.IMMEDIATE,this.PC);
	break;
	case "AND" : Register.AND(this.Registers.get(I.R1.bits),this.Registers.get(I.R2.bits)); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "OR" : Register.OR(this.Registers.get(I.R1.bits),this.Registers.get(I.R2.bits)); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "JR" : go=2; Register.JR(this.Registers.get(I.R1.bits),this.Registers.get(I.R2.bits),this.PC);
	break;
	case "SLC" : Register.SLC(this.Registers.get(I.R1.bits),I.IMMEDIATE); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "SRC" : Register.SRC(this.Registers.get(I.R1.bits),I.IMMEDIATE); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "LB" : Register.LB(this.Registers.get(I.R1.bits),I.IMMEDIATE); System.out.println(this.Registers.get(I.R1.bits).announceChange());
	break;
	case "SB" : Register.SB(this.Registers.get(I.R1.bits),I.IMMEDIATE);
	I.R1.bits=this.Registers.get(I.R1.bits).bits;
	System.out.println(I.announceChangeToMem());
	break;
	
	//0011  0111
	}
}
public static String binary(String s) {

	String result = "";
	int v1=0;
	int v2=0;
	int imm=0;
	String[] elements = s.split(" ");

	
	if(elements[2].charAt(0)=='R') {
	String [] values =elements[1].split("R");
	String [] values2 =elements[2].split("R");	
	 v1=Integer.parseInt(values[1]);
	 v2=Integer.parseInt(values2[1]);
	

	switch(elements[0]) {
	case "ADD":
	result="0000"+decimalToBinary(v1)+decimalToBinary(v2);							
	break;
	case "SUB":
	result="0001"+decimalToBinary(v1)+decimalToBinary(v2);
		break;
	case "MUL":
		result="0010"+decimalToBinary(v1)+decimalToBinary(v2);
		break;	
	case "AND":
		result="0101"+decimalToBinary(v1)+decimalToBinary(v2);
		break;
	case "OR" :
		result="0110"+decimalToBinary(v1)+decimalToBinary(v2);
		break;
	case "JR" :
		result="0111"+decimalToBinary(v1)+decimalToBinary(v2);
		break;
	}
	}
	else {
		String [] values =elements[1].split("R");
		v1=Integer.parseInt(values[1]);
		
		if (elements[2].charAt(0) == '-') {
			String [] blaa=elements[2].split("-");
			imm=-1*Integer.parseInt(blaa[1]);	
			
		}
		else {
			imm=Integer.parseInt(elements[2]);
		}
	switch(elements[0]) {
	case "SLC":
		result="1000"+decimalToBinary(v1)+decimalToBinary(imm);
		break;
	case "SRC" :
		result="1001"+decimalToBinary(v1)+decimalToBinary(imm);
		break;
	case "LB" :
		result="1010"+decimalToBinary(v1)+decimalToBinary(imm);
		break;
	case "SB" :
		result="1011"+decimalToBinary(v1)+decimalToBinary(imm);
		break;
	case "LDI":
		result="0011"+decimalToBinary(v1)+decimalToBinary(imm);
		break;
	case "BEQZ":
		result="0100"+decimalToBinary(v1)+decimalToBinary(imm);
		break;
	}				
	}
	
	return result;
}

public static int StringtoDec(String s) {
	int result=0;
	int power=0;
	for(int i = s.length()-1;i>=0;i--) {
		if(s.charAt(i)=='1') {
			result+=0b1111111111111111&(int)Math.pow(2, power);
		}
		power++;
	}
	return 0b111111111111111111&result;
}



public String ClocktoString() {
	return "--------Clock Cycle: " + ""+clock + "--------" ;
}

public static String decimalToBinary(int x) {
	String r = "";
	int count=0;
	if(x<-255) {
		String y = Integer.toBinaryString(x);
	}
	NEV=false;
	if(x<0) {	
		NEV=true;
		String z =Integer.toBinaryString(-1*x);
		StringBuffer neg=new StringBuffer();
		if(z.length()<6) {
			for(int i =z.length();i<6;i++) {
				z="0"+z;
			}
		}
		neg.append(z);
		String twos= findTwoscomplement(neg);
		
		
		if(twos.length()<6) {
		for(int i =twos.length();i<6;i++) {
			twos="0"+twos;
		}
		}
		// 010101001001
		else {
			twos=twos.substring(twos.length()-6,twos.length());
		}
		negNums.put(twos, x);
	//	System.out.println("neeeeeeeeeg"+twos);
		// Printing the hashmap
//		for (int i : negNums.values()) {
//			  System.out.println(i);
//			}
//		for (String i : negNums.keySet()) {
//			  System.out.println("key: " + i + " value: " + negNums.get(i));
//			}
		////////////////
		return twos;
		
	}
	String y = Integer.toBinaryString(x);
	if(y.length()<6) {
	for(int i =y.length();i<6;i++) {
		y="0"+y;
	}
	}
	return y;
}
public static String decimalToBinary2(int x) {
    String r = "";
    int count=0;
//    if(x<-255) {
//        System.out.println("The inputs can have only 8 bits no more! Please check your inputs!");
//        System.exit(1);
//    }
    NEV=false;
    if(x<0) {
        NEV=true;
        String z =Integer.toBinaryString(-1*x);
        StringBuffer neg=new StringBuffer();
        if(z.length()<8) {
            for(int i =z.length();i<8;i++) {
                z="0"+z;
            }
        }
        neg.append(z);
        String twos= findTwoscomplement(neg);


        if(twos.length()<8) {
        for(int i =twos.length();i<8;i++) {
            twos="0"+twos;
        }
        }
        // 010101001001
        else {
        	if(twos.length()>8){
            twos=twos.substring(twos.length()-8,twos.length());
        	}
        }
        negNums.put(twos, x);
    //    System.out.println("neeeeeeeeeg"+twos);
        // Printing the hashmap
//        for (int i : negNums.values()) {
//              System.out.println(i);
//            }
//        for (String i : negNums.keySet()) {
//              System.out.println("key: " + i + " value: " + negNums.get(i));
//            }
        ////////////////
        return twos;

    }
    String y = Integer.toBinaryString(x);
    if(y.length()<8) {
    for(int i =y.length();i<8;i++) {
        y="0"+y;
    }
    }
    else {
    	if(y.length()>8){
    	y=y.substring(y.length()-8,y.length());
    	}   }
    return y;
}
public static void trace(String s) {
	String[] elements = s.split(" ");
	if(elements[2].charAt(0)=='R') {
	String [] values =elements[1].split("R");
	String [] values2 =elements[2].split("R");	

	System.out.println(Integer.parseInt(values[0]));
	System.out.println(Integer.parseInt(values2[0]));
	}
}

//An efficient Java program to find 2's complement

	// Method to find two's complement
	static String findTwoscomplement(StringBuffer str)
	{
		int n = str.length();
	
		// Traverse the string to get first '1' from
		// the last of string
		int i;
		for (i = n-1 ; i >= 0 ; i--)
			if (str.charAt(i) == '1')
				break;
	
		// If there exists no '1' concat 1 at the
		// starting of string
		if (i == -1)
			return "1" + str;
	
		// Continue traversal after the position of
		// first '1'
		for (int k = i-1 ; k >= 0; k--)
		{
			//Just flip the values
			if (str.charAt(k) == '1')
				str.replace(k, k+1, "0");
			else
				str.replace(k, k+1, "1");
		}
	
		// return the modified string
		return str.toString();
	}
	
	// Driver method



public static void main(String[] args) throws FileNotFoundException {
//	int dec=5;
//String r="ADD R1 R2";
//String r1="LDI R1 3";
//String []s=r.split(" ");
//String z = s[1];
//String []v=z.split("R");

//System.out.println(v[1]);
//	System.out.println(binary(r));
	
	Execute x = new Execute();
	/////////////////////////////////
//	int m=-300;
//	System.out.println(m+"  m");
//	StringBuffer z= new StringBuffer();
//	String s=decimalToBinary(m);
//	System.out.println(s +" s");
//	System.out.println(StringtoDec(s) +" s");
//	z.append(s);
//	System.out.println(z+" z");
//	String res= findTwoscomplement(z);
//	System.out.println(res+" res");
//	int re=StringtoDec(res);
//	System.out.println(re+" re");
	/////////////////////////////// 11010100
	//  System.out.println(findTwoscomplement(z));
	//String y = Integer.toBinaryString(-2);
//	String m="010101";
//	for(int i=0; i<m.length(); i++) {
//	System.out.println(m.charAt(0));
//	 StringBuffer str = new StringBuffer("00000101");
//     System.out.println(findTwoscomplement(str));
//	}
//	String y = Integer.toBinaryString(7);
//	System.out.println(y);
//System.out.println(StringtoDec("1111"));
}
}
