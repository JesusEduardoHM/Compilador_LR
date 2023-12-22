package Analizadores;

import java.util.Stack;

public class Ana_Sint {
	
	private String matriz[][]={
			{"","id","int","float","char",",",";","+","-","*","/","=","(",")","{","}","if","do","while","else","<",">","!=","==","<=",">=","num","P","S","Tipo","V","A","I","D","C","X","N","E","T","F","$"},
			{"I0","P8","I3","I4","I5","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P8","P8","Error","Error","Error","Error","Error","Error","Error","Error","Error","I1","Error","I2","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P8"},
			{"I1","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I6","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I2","I13","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I3","P9","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I4","P10","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I5","P11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I6","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P0"},
			{"I7","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I14","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I8","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I15","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I9","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I16","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I10","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I17","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I18","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I19","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I13","Error","Error","Error","Error","I21","I22","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I20","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I14","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P1","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P1"},
			{"I15","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P2","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P2"},
			{"I16","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P3","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P3"},
			{"I17","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","I23","I24","Error","Error","Error","Error","Error"},
			{"I18","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I27","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I19","I32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I28","I29","I30","Error"},
			{"I20","P7","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P7","P7","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P7"},
			{"I21","I34","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I22","P8","I3","I4","I5","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P8","P8","Error","Error","Error","Error","Error","Error","Error","Error","Error","I35","Error","I2","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P8"},
			{"I23","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I36","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I24","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I37","I38","I39","I40","I41","I42","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P32","Error","Error","Error","Error","Error","Error","P32","P32","P32","P32","P32","P32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I26","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P33","Error","Error","Error","Error","Error","Error","P33","P33","P33","P33","P33","P33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I27","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I43","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I28","Error","Error","Error","Error","Error","I44","I45","I46","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I29","Error","Error","Error","Error","Error","P17","P17","P17","I47","I48","Error","Error","P17","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I30","Error","Error","Error","Error","P20","P20","P20","P20","P20","P20","Error","Error","P20","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I31","I32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I49","I29","I30","Error"},
			{"I32","Error","Error","Error","Error","P22","P22","P22","P22","P22","P22","Error","Error","P22","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I33","Error","Error","Error","Error","P23","P23","P23","P23","P23","P23","Error","Error","P23","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I34","Error","Error","Error","Error","I21","I22","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I50","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I35","P13","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P13","P13","P13","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P13"},
			{"I36","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I51","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I37","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","Error","I52","Error","Error","Error","Error","Error"},
			{"I38","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","Error","I53","Error","Error","Error","Error","Error"},
			{"I39","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","Error","I54","Error","Error","Error","Error","Error"},
			{"I40","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","Error","I55","Error","Error","Error","Error","Error"},
			{"I41","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","Error","I56","Error","Error","Error","Error","Error"},
			{"I42","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","Error","I57","Error","Error","Error","Error","Error"},
			{"I43","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I58","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I44","P14","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P14","P14","P14","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P14"},
			{"I45","I32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I59","I30","Error"},
			{"I46","I32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I60","I30","Error"},
			{"I47","I32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I61","Error"},
			{"I48","I32","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I33","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I62","Error"},
			{"I49","Error","Error","Error","Error","Error","Error","I45","I46","Error","Error","Error","Error","I63","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I50","P12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P12","P12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P12"},
			{"I51","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I64","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I52","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P24","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I53","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I54","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P26","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I55","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P27","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I56","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P28","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I57","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P29","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I58","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I65","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I59","Error","Error","Error","Error","P15","P15","P15","P15","Error","Error","Error","Error","P15","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I60","Error","Error","Error","Error","P16","P16","P16","P16","I47","I48","Error","Error","P16","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I61","Error","Error","Error","Error","P18","P18","P18","P18","Error","Error","Error","Error","P18","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I62","Error","Error","Error","Error","P19","P19","P19","P19","Error","Error","Error","Error","P19","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I63","Error","Error","Error","Error","P21","P21","P21","P21","Error","Error","Error","Error","P21","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I64","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I66","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I65","I25","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I26","Error","Error","Error","Error","Error","Error","Error","I67","I24","Error","Error","Error","Error","Error"},
			{"I66","P31","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P31","P31","P31","Error","I69","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I68","Error","Error","Error","P31"},
			{"I67","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I70","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I68","P5","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P5","P5","P5","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P5"},
			{"I69","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I71","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I70","P6","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P6","P6","P6","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P6"},
			{"I71","I12","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P4","I10","I11","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I72","Error","Error","I9","I7","I8","Error","Error","Error","Error","Error","Error","P4"},
			{"I72","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","I73","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error"},
			{"I73","P30","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P30","P30","P30","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","Error","P30"}
			}
			;
	
	
	//int var1 , var2 ; var1 = var2 ;

	Stack<String> pila=new Stack<String>();
	String pala[]= new String [18];
	int act=1, act2, nl;
	int columna, renglon, palab;
	String cadinter="";
	String pilaS;
	String p[]={"P,S","I,S","D,S","A,S","3","if,(,C,),{,S,},N","do,{,S,},while,(,C,)","Tipo,id,V",
			"3","int","float","char",",,id,V",";,P","id,=,E,;","E,+,T","E,-,T","T","T,*,F",
			"T,/,F","F","(,E,)", "id", "num","X,<,X","X,>,X","X,!=,X","X,==,X","X<=X","X>=X",
			"else,{,S,}","3", "id", "num"
			};
	
	boolean Fin=true;
	Stack<String> pilaerror=new Stack<String>();
	
	public String metodo(Stack<String> ana)
	{
		nl=1;
		pilaS="$ I0 \n";
		pila.push("$");
		pila.push("I0");
		ana.push("$");
		while(Fin)
		{
			columna=0; renglon=0; palab=0;
			pala= new String [18];
			if(ana.elementAt(act2).compareTo("\n")!=0)
			{
				try
				{
					System.out.println(ana.elementAt(act2));
					for(int i=0;matriz[0][i].compareTo(ana.elementAt(act2))!=0;i++)
					{
						columna++;
					}
					System.out.println(pila.elementAt(act));
					
					for(int i=0;matriz[i][0].compareTo(pila.elementAt(act))!=0;i++)
					{
						renglon++;
					}
					cadinter=matriz[renglon][columna];
				}
				catch (Exception e) 
				{
					cadinter="Error";
				}
				String cad="";
				System.out.println(cadinter);
				if(cadinter.compareTo("Error")==0)
				{
					pilaS+="\n"+cadinter+"";
					pilaerror.push((pilaerror.size()+1)+".-Error Sintactico en "+ ana.elementAt(act2)+" numero de linea "+nl);
					return pilaS;
				}
				if((cadinter.charAt(0)+"").compareTo("P")!=0)
				{
					pila.push(matriz[0][columna]);
					pila.push(matriz[renglon][columna]);
					act2++;
				}
				else
				{
					int ps=0;
					if(matriz[renglon][columna].length()==2)
					{
						ps=Integer.parseInt(matriz[renglon][columna].charAt(1)+"");
					}
					else
					{
						if(matriz[renglon][columna].length()==3)
						{
							ps=Integer.parseInt(matriz[renglon][columna].charAt(2)+"");
							ps+=Integer.parseInt(matriz[renglon][columna].charAt(1)+"")*10;
						}
						else
						{
							if(matriz[renglon][columna].length()==4)
							{
								ps=Integer.parseInt(matriz[renglon][columna].charAt(3)+"");
								ps+=Integer.parseInt(matriz[renglon][columna].charAt(2)+"")*10;
								ps+=Integer.parseInt(matriz[renglon][columna].charAt(1)+"")*100;
							}
						}
					}
					
					if(ps!=12)
					{
						cadinter=p[ps];
						for(int i=0;cadinter.length()>i;i++)
						{
							if(cadinter.charAt(i)!=',')
							{
								cad+=cadinter.charAt(i);
							}
							else
							{
								pala[palab]=cad;
								palab++;
								act++;
								System.out.print(cad);
								cad="";
							}
						}
						pala[palab]=cad;
						System.out.println(cad);
						palab++;
					}
					else
					{
						pala[0]=",";
						pala[1]="id";
						pala[2]="V";
						palab+=3;
					}
					
					System.out.println(palab);
					int pan=0;
					boolean ban=true, ban2=false;
					for(int i=0;palab>i;i++)
					{
						if(pila.elementAt(pila.size()-(2*(i+1))).compareTo(pala[palab-i-1])==0)
						{
							System.out.print(pala[palab-i-1]+" ");
							pan=2*(i+1);
							System.out.print(pan+" ");
						}
					}
					System.out.println();
					System.out.println();
					ban=false;
					for(int i=0;i<pan;i++)
					{
						pila.pop();
					}
					if(ps==0)
					{
						pila.push("p'");
					}
					else
					{
						if(ps>0&&ps<5)
						{
							pila.push("S");
						}
						else
						
							{
								if(ps==5)
								{
									pila.push("I");
								}
								else
								{
									if(ps==6)
									{
										pila.push("D");
									}
									else
									{
										if(ps==8||ps==7)
										{
											pila.push("P");
										}
										else
										{
											if(ps>8&&ps<12)
											{
												pila.push("Tipo");
											}
											else
											{
												if(ps==12||ps==13)
												{
													pila.push("V");
												}
												else
												{
													if(ps==14)
													{
														pila.push("A");
													}
													else
													{
														if(ps>14&&ps<18)
														{
															pila.push("E");
														}
														else
														{
															if(ps>17&&ps<21)
															{
																pila.push("T");
															}
															else
															{
																if(ps>20&&ps<24)
																{
																	pila.push("F");
																}
																else
																{
																	if(ps>23&&ps<30)
																	{
																		pila.push("C");
																	}
																	else
																	{
																		if(ps==30||ps==31)
																		{
																			pila.push("N");
																		}
																		else
																		{
																			if(ps==32||ps==33)
																			{
																				pila.push("X");
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							
						}
					}
					columna=0; renglon=0;
					if(pila.peek().compareTo("p'")!=0)
					{
						for(int i=0;matriz[0][i].compareTo(pila.peek())!=0;i++)
						{
							columna++;
						}
						for(int i=0;matriz[i][0].compareTo(pila.elementAt(pila.size()-2))!=0;i++)
						{
							System.out.println(matriz[i][0]);
							System.out.println(pila.elementAt(pila.size()-2));
							renglon++;
						}
						pan++;
						pila.push(matriz[renglon][columna]);
					
						for(int i=0;i<pila.size();i++)
						{
							System.out.print(pila.elementAt(i)+" ");
						}
						System.out.println();
					}
					else
					{
						Fin=false;
					}
				}
				if(Fin)
				{
					act=pila.size()-1;
					pilaS+="\n";
					for(int i=0;i<pila.size();i++)
					{
						pilaS+=pila.elementAt(i)+" ";
						//System.out.println(pila[i]);
					}
				}
				else
				{
					pilaS+="\n";
					pilaS+="$"+" ";
					pilaS+="I0"+" ";
					pilaS+="p'";
					pilaS+="\n";
					pilaS+="\n";
					pilaS+="ACEPTADA";
				}
				pilaS+="\n";
			}
			else
			{
				//en caso de que haya un salto de linea
				act2++;
				nl++;
			}
		}
		return pilaS;
	}
	public String[] getPilaerror(){
		String vec[]=new String[pilaerror.size()];
		int i,j;
		for(i=0,j=this.pilaerror.size()-1; i<=j ;i++){ //pila errores
			vec[i]=this.pilaerror.elementAt(i);
		}
		return vec;		
	}
}