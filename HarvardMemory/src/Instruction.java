
public class Instruction { // are opcode and r1 and rest variables should be arrays or integers
String name ;             
char type ;
int OPCODE;
Register R1;
Register R2;
int IMMEDIATE;
   
public Instruction(Execute ex,String opcode,String inn,int instruction) {// should this be string or integer
//	if(instruction>1111111111111111) {
//		System.out.println("wrong instruction size!");
//		return;
//	}
	
	this.R1=new Register("");
	this.R2=new Register("");
	this.OPCODE = Execute.StringtoDec(opcode);
	if(this.OPCODE==3)
		System.out.println("");
	String s2=inn.substring(4,10);
	String s3=inn.substring(10,16);
	//System.out.println(s2+" s222222222222222222");
	//System.out.println(s3+" s3333333333333333333");
	
	
	
//	if(Execute.negNums.get(s2 )!=null|| Execute.NEV==true){
//		System.out.println("you hereee??????????");
//		//	System.out.println(Execute.negNums.get(Execute.decimalToBinary(R1.bits)));
//			for (String i : Execute.negNums.keySet()) {
//				System.out.println("foooooooooor");
//				System.out.println(i+"    iiiiiiiiii");
//				System.out.println(s2+ "     cheeeeeeee333333");
//				//  System.out.println("key: " + i + " value: " + Execute.negNums.get(i));
//				  if(i.equals(s2)) {
//					  R1.bits=Execute.negNums.get(i).byteValue();
//					  System.out.println("doneeeeeeeee    "+  R1.bits);
//					  
////					Integer c=Execute.StringtoDec(s3);
////						this.R2.bits= c.byteValue();
////						System.out.println(this.R2.bits+" heelllloo222222222ooooooo");
//				  }
//				  else {
//					  System.out.println("NOTTTTTTdoneeeeeeeee");
//					  continue;
//				  }
//				}
//		}
//	else {
//		Integer v=Execute.StringtoDec(s2);
//		this.R1.bits= v.byteValue();
//		System.out.println(this.R1.bits+" heellllooo111111111oooooo");
//	}	
		
//	
	
	Integer v=Execute.StringtoDec(s2);
	this.R1.bits= v.byteValue();
	//System.out.println(this.R1.bits+" heellllooo111111111oooooo");
	
	
//	if(Execute.negNums.get(s3 )!=null|| Execute.NEV==true){
//		System.out.println("you hereee??????????");
//		//	System.out.println(Execute.negNums.get(Execute.decimalToBinary(R1.bits)));
//			for (String i : Execute.negNums.keySet()) {
//				System.out.println("foooooooooor");
//				System.out.println(i+"    iiiiiiiiii");
//				System.out.println(s3+ "     cheeeeeeee333333");
//				//  System.out.println("key: " + i + " value: " + Execute.negNums.get(i));
//				  if(i.equals(s3)) {
//					  R2.bits=Execute.negNums.get(i).byteValue();
//					  System.out.println("doneeeeeeeee    "+  R2.bits);
//					  
////					Integer c=Execute.StringtoDec(s3);
////						this.R2.bits= c.byteValue();
////						System.out.println(this.R2.bits+" heelllloo222222222ooooooo");
//				  }
//				  else {
//					  System.out.println("NOTTTTTTdoneeeeeeeee");
//					  continue;
//				  }
//				}
//		}
//	else {
//		Integer c=Execute.StringtoDec(s3);
//		this.R2.bits= c.byteValue();
//		System.out.println(this.R2.bits+" heelllloo222222222ooooooo");
//	}
	
//	
    	Integer c=Execute.StringtoDec(s3);
	    this.R2.bits= c.byteValue();
	   // System.out.println(this.R2.bits+" heelllloo222222222ooooooo");
	
//	
//	if(Execute.negNums.get(s3 )!=null|| Execute.NEV==true){
//		System.out.println("you hereee???????weeeeeeeee???");
//		//	System.out.println(Execute.negNums.get(Execute.decimalToBinary(R1.bits)));
//			for (String i : Execute.negNums.keySet()) {
//				System.out.println("foooooooooor");
//				System.out.println(i+"    iiiiiiiiii");
//				System.out.println(s3+ "     cheeeeeeeemmmmmm");
//				//  System.out.println("key: " + i + " value: " + Execute.negNums.get(i));
//				  if(i.equals(s3)) {
//					  this.IMMEDIATE=Execute.negNums.get(i).byteValue();
//					  System.out.println("doneeeeeeeee    "+  this.IMMEDIATE);
//					  
////						Integer m=Execute.StringtoDec(s3);
////						this.IMMEDIATE= m.byteValue();
////						System.out.println(this.IMMEDIATE+" heellllooo33333333oooooo");
//				  }
//				  else {
//					  System.out.println("NOTTTTTTdoneeeeeeeee");
//					  continue;
//				  }
//				}
//		}
//	else {
//		Integer m=Execute.StringtoDec(s3);
//		this.IMMEDIATE= m.byteValue();
//		System.out.println(this.IMMEDIATE+" heellllooo33333333oooooo");	
//		}
		
     	Integer m=Execute.StringtoDec(s3);
     	this.IMMEDIATE= m.byteValue();
    	//System.out.println(this.IMMEDIATE+" heellllooo33333333oooooo");
//		
		
	switch(this.OPCODE) { 
	case 0:
	this.name="ADD";
	this.type='R';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.R2.bits=instruction&0b0000000000111111;
	break;
	case 1:
	this.name="SUB";
	this.type='R';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.R2.bits=instruction&0b0000000000111111;
	break;
	case 2:
	this.name="MUL";
	this.type='R';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.R2.bits=instruction&0b0000000000111111;
	break;
	case 3:
	this.name="LDI";
	this.type='I';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.IMMEDIATE=instruction&0b0000000000111111;
	break;
	case 4:
	this.name="BEQZ";
	this.type='I';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.IMMEDIATE=instruction&0b0000000000111111;
	break;
	case 5:
	this.name="AND";
	this.type='R';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.R2.bits=instruction&0b0000000000111111;
	break;
	case 6:
	this.name="OR";
	this.type='R';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.R2.bits=instruction&0b0000000000111111;
	break;
	case 7:
	this.name="JR";
	this.type='R';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.R2.bits=instruction&0b0000000000111111;
	break;
	case 8:
	this.name="SLC";
	this.type='I';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.IMMEDIATE=instruction&0b0000000000111111;
	break;
	case 9:
	this.name="SRC";
	this.type='I';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.IMMEDIATE=instruction&0b0000000000111111;
	break;
	case 10:
	this.name="LB";
	this.type='I';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.IMMEDIATE=instruction&0b0000000000111111;
	break;
	case 11:
	this.name="SB";
	this.type='I';
//	this.R1.bits=instruction&0b0000111111000000;
//	this.IMMEDIATE=instruction&0b0000000000111111;
	break;
	
	}
	
	
}
	public String typeRtoString() {
	
	return "Instruction name: " + this.name + "\n" + "Instruction Type: " +this.type + "\n" + "Operand Registers: " +this.R1.name +" & "+this.R2.name;   
	
}
	public String typeItoString() {
		
		  if(Register.NegvsLDI.get(Execute.decimalToBinary(this.IMMEDIATE) )!=null|| Execute.NEV==true){
				for (String i : Execute.negNums.keySet()) {
					  if(i.equals(Execute.decimalToBinary(this.IMMEDIATE))) {
						  this.IMMEDIATE=Register.NegvsLDI.get(i).byteValue();
					  }
					  else
						  continue;
					}
			}
		return "Instruction name: " + this.name + "\n" + "Instruction Type: " +this.type + "\n" + "Parameters: Register " +this.R1.name ;   
		
	}
	public String announceChangeToMem() {
		return "Memory cell of address: "+this.IMMEDIATE+" was changed to: " + ""+this.R1.bits+"\n";
	}

}
