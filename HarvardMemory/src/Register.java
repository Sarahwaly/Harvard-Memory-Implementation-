import java.util.HashMap;

public class Register {
String name ;
byte bits; 
static HashMap< String,Byte> RegsString= new HashMap<>() ;
static HashMap< String,Integer> NegvsLDI= new HashMap<>() ;

static String SREGbits; // bits of the SREG register
static char c='0';
static char v='0';
static String s ="0";
static char n ='0';
static char z='0';
static int pcBits;

public Register(String name) {
	this.name = name;
	if(name.equals("SREG")){
		Register.SREGbits= check(); 
	}	
}

public static void ADD(Register R1, Register R2) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	if( (R1.bits<0)&& (R2.bits<0)&& ((R1.bits + R2.bits)>0)||( (R1.bits>0)&& (R2.bits>0)&& ((R1.bits + R2.bits)<0)))
		v='1';
	else {
		v='0';
	}
	
	if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	if(Execute.negNums.get(Execute.decimalToBinary(R2.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R2.bits))) {
				  R2.bits=Execute.negNums.get(i).byteValue();
			  }
			  
			  else {
				  continue;
			  }
			}
	}
	RegsString.put(R1.name, R1.bits);
	RegsString.put(R2.name, R2.bits);
	System.out.println(" first Operand: "+ R1.bits );
	System.out.println( " Second Operand: "+ R2.bits);

     R1.bits = (byte) (R1.bits + R2.bits);
     
 	System.out.println( " Result: "+ R1.bits);

 	  if (R1.bits>257 || R1.bits<-257)
 		  c='1';
 	  else 
 		  c='0';
 	  
 	  if(R1.bits==0)
 			 z='1';
 	  else {
 		  z='0';
 	  }
 	   if(R1.bits<0)
 		   n='1';
 	   else
 		   n='0';
 	   s=(Character.getNumericValue(n)^ Character.getNumericValue(v))+"";
 	   System.out.println("SREG Flag:" + check());
    }


public static void SUB(Register R1, Register R2) {
	
  if( (R1.bits>0)&& (R2.bits<0)&& ((R1.bits - R2.bits)<0) ||( (R1.bits<0)&& (R2.bits>0)&& ((R1.bits - R2.bits)>0)))
	v='1';
  else
	v='0';
 
  
  if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
			for (String i : Execute.negNums.keySet()) {
				  if(i.equals(Execute.decimalToBinary(R1.bits))) {
					  R1.bits=Execute.negNums.get(i).byteValue();
				  }
				  else
					  continue;
				}
		}
		if(Execute.negNums.get(Execute.decimalToBinary(R2.bits) )!=null|| Execute.NEV==true){
			for (String i : Execute.negNums.keySet()) {
				  if(i.equals(Execute.decimalToBinary(R2.bits))) {
					  R2.bits=Execute.negNums.get(i).byteValue();
				  }
				  
				  else {
					  continue;
				  }
				}
		}
		
		RegsString.put(R1.name, R1.bits);
		RegsString.put(R2.name, R2.bits);
		System.out.println(" first Operand: "+ R1.bits );
		System.out.println( " Second Operand: "+ R2.bits);

  R1.bits = (byte) (R1.bits - R2.bits);
	System.out.println( " Result: "+ R1.bits);
  if(R1.bits==0)
		 z='1';
  else {
	  z='0';
  }
   if(R1.bits<0)
	   n='1';
   else
	   n='0';
   s=(Character.getNumericValue(n)^ Character.getNumericValue(v))+"";
   c ='0';
   System.out.println("SREG Flag:" + check());
}

public static void MUL(Register R1, Register R2) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	if(Execute.negNums.get(Execute.decimalToBinary(R2.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R2.bits))) {
				  R2.bits=Execute.negNums.get(i).byteValue();
			  }
			  
			  else {
				  continue;
			  }
			}
	}
	RegsString.put(R1.name, R1.bits);
	RegsString.put(R2.name, R2.bits);
	System.out.println(" first Operand: "+ R1.bits );
	System.out.println( " Second Operand: "+ R2.bits);
   R1.bits = (byte) (R1.bits * R2.bits);
	System.out.println( " Result: "+ R1.bits);

	 
	  
	  if(R1.bits==0)
			 z='1';
	  else {
		  z='0';
	  }
	   if(R1.bits<0)
		   n='1';
	   else
		   n='0';
	   System.out.println("SREG Flag:" + check());

}
public static void LDI(Register R1, int imm) {
	if(R1.name.equals("R14")) {
		System.out.println();
	}
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	 if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
			for (String i : Execute.negNums.keySet()) {
				  if(i.equals(Execute.decimalToBinary(R1.bits))) {
					  R1.bits=Execute.negNums.get(i).byteValue();
				  }
				  else
					  continue;
				}
		}
	 else {	 
	if(imm<0) {
		NegvsLDI.put(R1.name, imm);
	}
   R1.bits = (byte) imm;
	 }
   RegsString.put(R1.name, R1.bits);
 //  System.out.println(Execute.decimalToBinary(R1.bits)+"       YYYYYYYYYYYYYYYYYY");
}

public static void BEQZ(Register R1, int IMM,Register PC) { 
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';

	if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	RegsString.put(R1.name, R1.bits);
	
   if(R1.bits==0) {
	   PC.pcBits-=2;
      PC.pcBits= (PC.pcBits+IMM);
   }
   else
	   PC.pcBits+=1;
   
}
public static void AND(Register R1,Register R2) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	if(Execute.negNums.get(Execute.decimalToBinary(R2.bits) )!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R2.bits))) {
				  R2.bits=Execute.negNums.get(i).byteValue();
			  }
			  
			  else {
				  continue;
			  }
			}
	}
	RegsString.put(R1.name, R1.bits);
	RegsString.put(R2.name, R2.bits);
	System.out.println(" first Operand: "+ R1.bits );
	System.out.println( " Second Operand: "+ R2.bits);
    R1.bits = (byte) (R1.bits & R2.bits);
    System.out.println( " Result: "+ R1.bits);
    if(R1.bits==0)
   	 	z='1';
    else
    	z='0';
    if(R1.bits<0)
    	n='1';
    else
    	n='0';
   
    System.out.println("SREG Flag:" + check());

}
public static void OR(Register R1,Register R2) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	 String split[]=R1.name.split("R"); 
	 Integer x= Execute.negNums.get(Execute.decimalToBinary(Integer.parseInt(split[1])));
	if(x!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	 String split2[]=R2.name.split("R"); 
	 Integer u= Execute.negNums.get(Execute.decimalToBinary(Integer.parseInt(split2[1])));
	if(u!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R2.bits))) {
				  R2.bits=Execute.negNums.get(i).byteValue();
			  }
			  
			  else {
				  continue;
			  }
			}
	}
	RegsString.put(R1.name, R1.bits);
	RegsString.put(R2.name, R2.bits);
	System.out.println(" first Operand: "+ R1.bits );
	System.out.println( " Second Operand: "+ R2.bits);
    R1.bits = (byte) (R1.bits | R2.bits);
    System.out.println( " Result: "+ R1.bits);
    if(R1.bits==0)
   	 	z='1';
    else
    	z='0';
    if(R1.bits<0)
    	n='1'; 
    else
    	n='0';
    System.out.println("SREG Flag:" + check());
}
public static void JR(Register R1,Register R2 ,Register PC) {
	
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	if(Execute.negNums.get(Execute.decimalToBinary(R2.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R2.bits))) {
				  R2.bits=Execute.negNums.get(i).byteValue();
			  }
			  
			  else {
				  continue;
			  }
			}
	}
	RegsString.put(R1.name, R1.bits);
	RegsString.put(R2.name, R2.bits);
	
	String y = Integer.toBinaryString(R1.bits);
	String x = Integer.toBinaryString(R2.bits);
	String re= y+x;
	
   PC.pcBits =  (Integer.parseInt(re,2)-2); 
    

}

public static void SLC(Register R1,int IMM) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	 if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	RegsString.put(R1.name, R1.bits);
	System.out.println(" first Operand: "+ R1.bits );
	System.out.println( "SLC "+IMM );
	if(R1.bits>0) {
   R1.bits = (byte) (R1.bits << IMM | R1.bits >>> 8 - IMM);
	}
	else {
		String binary = Execute.decimalToBinary2(R1.bits);		
		for(int i =0; i<IMM; i++){
		binary= binary.substring(1, binary.length())+binary.charAt(0);
		}		
		R1.bits=(byte)Integer.parseInt(binary,2);
		
	}
   System.out.println( " Result: "+ R1.bits);
   if(R1.bits==0)
		 z='1';
   else
	   z='0';
   if(R1.bits<0)
   		n='1';
   else
	   n='0';
   System.out.println("SREG Flag:" + check());
   }

public static void SRC(Register R1,int IMM) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	 if(Execute.negNums.get(Execute.decimalToBinary(R1.bits) )!=null|| Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	RegsString.put(R1.name, R1.bits);
	System.out.println(" first Operand: "+ R1.bits );
	System.out.println( "SRC "+IMM );
	if(R1.bits>0) {
   R1.bits = (byte) (R1.bits >>> IMM | R1.bits << 8 - IMM);
	}
	else {
		String binary = Execute.decimalToBinary2(R1.bits);		
		for(int i =0; i<IMM; i++){
			binary=binary.charAt(binary.length()-1)+ binary.substring(0, binary.length()-(1));
	
		}		
		R1.bits=(byte)Integer.parseInt(binary,2);
	}
   System.out.println( " Result: "+ R1.bits);
   if(R1.bits==0)
		 z='1';
   else
	   z='0';
   if(R1.bits<0)
   		n='1';
   else
	   n='0';
   System.out.println("SREG Flag:" + check());
}
public static void LB(Register R1,int address) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	 String split[]=R1.name.split("R"); 
	 Integer x= Execute.negNums.get(Execute.decimalToBinary(Integer.parseInt(split[1])));
		if(x!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	int t=DataMemory.rows[address];
    R1.bits=(byte) t;
    RegsString.put(R1.name, R1.bits);
 }
public static void SB(Register R1,int address) {
	 c ='0';
	 v ='0';
	 s ="0";
	 n ='0';
	 z ='0';
	 String split[]=R1.name.split("R"); 
	 Integer x= Execute.negNums.get(Execute.decimalToBinary(Integer.parseInt(split[1])));
		if(x!=null&& Execute.NEV==true){
		for (String i : Execute.negNums.keySet()) {
			  if(i.equals(Execute.decimalToBinary(R1.bits))) {
				  R1.bits=Execute.negNums.get(i).byteValue();
			  }
			  else
				  continue;
			}
	}
	RegsString.put(R1.name, R1.bits);
    DataMemory.rows[address]=R1.bits;
 }

public int chartoint(char i) {
	return Character.getNumericValue(i);
}
public String toString() {
	
	if(RegsString.get(this.name)!=null){
			for (String i : RegsString.keySet()) {
				  if(i.equals(this.name)) {
					 this.bits=RegsString.get(i);
					 return "Register Name: "+this.name+"  Register Content: "+ this.bits;
					 // System.out.println("doneeeeeeeee");
				  }
				  else {
					// System.out.println("NOTTTTTTTdoneeeeeeeee");
					 continue;
				  }
				  }
				}
	
	
	return "Register Name: "+this.name+"  Register Content: "+ this.bits;
}
public static String check() {
	
		return SREGbits= "000"+c+v+n+s+z;
	

}
public String announceChange() {

	  if(Execute.negNums.get(Execute.decimalToBinary(this.bits) )!=null|| Execute.NEV==true){
				for (String i : Execute.negNums.keySet()) {
					  if(i.equals(Execute.decimalToBinary(this.bits))) {
						  return ""+this.name+"'s new value is: " + ""+Execute.negNums.get(i).byteValue()+"   SREG:"+ Register.SREGbits +"\n";
					  }
					  else
						  continue;
					}
			}
	return ""+this.name+"'s new value is: " + ""+this.bits+"   SREG:"+ Register.SREGbits +"\n";
}


public static void main(String[] args) {
//	Register bla=new Register("R1");
//	Register bla1=new Register("R2");
//	Register Sreg=new Register("SREG");
//	LDI(bla,-200000);
//	//bla.bits=200000;
//	bla1.bits=(byte) 200000;
//	ADD(bla,bla1);
//	//bla.SRC(bla, 1);
//	System.out.println(" "+bla.bits);
//	System.out.println(" "+Sreg.SREGbits);
	int x=-3 ;
	//StringBuffer z = new StringBuffer();;
	//z.append(x);
	String binary = Execute.decimalToBinary2(x);		
    System.out.println(binary);
	
	for(int i =0; i<3-1; i++){
		
	}
	System.out.println(binary);

}

}
