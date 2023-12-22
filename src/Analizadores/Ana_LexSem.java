package Analizadores;

import java.util.Stack;

public class Ana_LexSem
{
	// int a , b , c ; float d ; d = a * b + c - a * ( b + 20 ) ;
	// int a , b , c ; float d ; a = 1 ; b = 2 ; c = 3 ; d = a * b + c - a * ( b + 20 ) ; imprimir ( d ) ;
	private String reservadas[] = { // se definen las palabras reservadas
			"int", "float", "char","if","do","while","else","==","!=","<=",">=","imprimir"
			,"leer"};
	private char simbolos[] = { // se definen los simbolos- No se pueden declarar simbolos que
			// java no acepte, como @ ! y comillas dobles
			',', ';', '+', '-', '*', '/', '(', ')', '=','{','}','<','>'};
	private String semantico[][]= {{"Tipo","Id","Valor"}};
	private String aux[][];
	private String Tipo, id, valor; //variable para guardar el lexema para el analizador semantico
	private String CodSalida="";/*codigo de salida*/ 
	private Stack<String> pilaerror=new Stack<String>();/*pila de error*/
	private Stack<String> pilaerrorsem=new Stack<String>();/*pila de error semantica*/
	private Stack<String> pilalexica=new Stack<String>();/*pila lexica*/
	//para cuando alla salto de linea
	boolean car,bla,sal, dec, asi, asi2, conca, rep,omi,omi2,ban ,exi;
	//indica el numero de linea en el que se encuentra el puntero
	int numl=1;
	
	public void Analizar(String ent)
	{
		ent+=" $";
		String lexema="";
		car=false;
		bla=false;
		sal=false;
		dec=false;
		asi=false;
		asi2=false;
		conca=false;
		rep=false;
		omi=false;
		omi2=false;
		ban=false;
		numl=1;
		valor="";
		
		for(int i=0; ent.charAt(i)!='$' ;)
		{

			if(!(ent.charAt(i)+"").equalsIgnoreCase("\n"))
			{

				if((ent.charAt(i)+"").compareTo("'")==0)
				{
					if(!car)
					{
						car=true;
					}
					else
					{
						car=false;
					}
				}
				
				if(car)
				{
					lexema+=ent.charAt(i);
				}
				else
				{
					if(ent.charAt(i)=='"')
					{
						if(!ban)
						{
							ban=true;
						}
						else
						{
							ban=false;
						}
					}
					if(ent.charAt(i)!=' ')
					{
						lexema+=ent.charAt(i);
						bla=false;
						sal=false;
					}
					else
					{
						if(!bla&&!ban)
						{
							this.ClasificaLex(lexema);
							lexema="";
							bla=true;
							sal=false;
						}
					}
				}
			
					
			}
			else
			{
				numl++;
				System.out.println("Salto");

				//en la parte de arriba la validacion valida que no este entrando un salto de linea,
				//por lo tanto if(sal) valida por si hay multiples saltos de linea
				//en cuanto entre a un salto de linea lo ultimo que esta guardado en lexema lo manda a clasificar
				if(!sal)
				{
					//en el caso de que se pusiera un espacio seguido de un salto de linea no se va a clasificar
					if(!bla)
					{
						
						//aqui se grega el salto de linea para que a la hora de analizar sintacticamente sepamos en que linea estamos
						pilalexica.push("\n");
						//reiniciamos el lexema
						lexema="";
						sal=true;
						bla=true;
						//se pone falso para que no se pueda seguir escribiendo en el caso de las comillas 
						car=false;
						this.ClasificaLex(lexema);
						
					}
				}
			
			}
		
			i++;
		}
	}
	
	private void ClasificaLex(String lexema){
		if(this.EsReservada(lexema))
		{
			if(lexema.compareTo("leer")==0)
			{
				omi=true;
			}
			else
			{
				if(lexema.compareTo("imprimir")==0)
				{
					omi2=true;
				}
				if(!omi&&!omi2)
				{
					pilalexica.push(lexema);
					this.CodSalida+=" "+lexema;
				}
			}
			
		}
		else
		{
			if(this.EsSimbolo(lexema))
			{
				if(!omi2)
				{
				pilalexica.push(lexema);
				this.CodSalida+=" "+lexema;
				}
				else
				{
					if(lexema.compareTo(";")==0)
					{
						omi2=false;
					}
				}
				
			}
			else
			{
				if(this.EsNumero(lexema))
				{
					if(!omi&&!omi2)
					{
						this.CodSalida+=" num";
						pilalexica.push("num");
					}
				}
				else
				{
					if(this.EsCaracter(lexema))
					{
						if(!omi&&!omi2)
						{
							this.CodSalida+=" litcar";
							pilalexica.push("litcar");
						}
					}
					else
					{
						if(this.Esid(lexema))
						{
							if(omi)
							{
								omi=false;
							}
							else
							{
								if(!omi&&!omi2)
								{
									this.CodSalida+=" id";
									pilalexica.push("id");
								}
							}
							
						}
						else
						{
							if(!this.EsCadena(lexema))
							{
								//aqui se agrego el mensaje de "error lexico en mas el numero de linea"
								pilaerror.push("Error lexico en "+ lexema
										+" numero de linea "+numl);
							}
						}
					}
				
				}
			}
		}
	
	}
	
	
	private boolean EsReservada(String lexema){
		boolean ban=false;
		for(int i=0; i<reservadas.length;i++){
			if(lexema.compareTo(reservadas[i])==0)
			{
				ban=true;
				if(lexema.equals("int")||lexema.equals("float")||lexema.equals("char"))
				{
					dec=true;
					Tipo=lexema; //guarda el tipo de dato que es el lexema
				}
				break;
			}
			ban=false;
		}
		return ban;
	}
	
	private boolean EsSimbolo(String lexema){
		boolean ban=false;
		if(lexema.length()==1){
			for(int i=0; i<simbolos.length;i++){
				if(lexema.charAt(0)==simbolos[i])
				{
					ban=true;
					if(lexema.charAt(0)=='=')
					{
						System.out.println("ASIGNADO");
						asi2=true;
					}
					if(lexema.charAt(0)=='+'||lexema.charAt(0)=='-'||lexema.charAt(0)=='*'||lexema.charAt(0)=='/')
					{
						if(asi2)
						{
							conca=true;
							asi2=false;
						}
						for(int j=0;j<semantico.length;j++)
						{
							if(semantico[j][1].equals(id))
							{
								semantico[j][2]+=(" "+lexema+" ");
							}
						}
					}
					if(lexema.charAt(0)=='('||lexema.charAt(0)==')')
					{
						if(conca)
						{
							for(int j=0;j<semantico.length;j++)
							{
								if(semantico[j][1].equals(id))
								{
									semantico[j][2]+=(" "+lexema+" ");
								}
							}
						}
					}
					if(lexema.charAt(0)==';')
					{
						valor="";
						conca=false;
						asi2=false;
						dec=false;
					}
					break;
				}
				ban=false;
			}
		}
		return ban;
	}
	
	private boolean EsNumero(String lexema){
		boolean ban=false;
		boolean ban2=false;
		try
		 {
		   Double.parseDouble(lexema);
		   ban=true;
		   
		 }
		 catch(NumberFormatException nfe)
		 {
		   ban=false;
		 }
		try
		 {
		   Integer.parseInt(lexema);
		   ban2=true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   ban2=false;
		 }
		
		if(ban || ban2)
		{
			if(asi2)
			{
				for(int j=0;j<semantico.length;j++)
				{
					if(semantico[j][1].equals(id))
					{
						if(ban&&!ban2)
						{
							if(semantico[j][0].equals("float"))
							{
								semantico[j][2]=lexema;
							}
							else
							{
								pilaerrorsem.push("Error semantico en "+ lexema+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
							}
						}
						else
						{
							semantico[j][2]=lexema;
						}
					}
				}
			}
			else
			{
				if(conca)
				{
					for(int j=0;j<semantico.length;j++)
					{
						if(semantico[j][1].equals(id))
							if(ban&&!ban2)
							{
								if(semantico[j][0].equals("float"))
								{
									if(semantico[j][2].length()==0)
										
												semantico[j][2]=lexema;
											
									else
										
										semantico[j][2]+=lexema;
								}
								else
								{
									pilaerrorsem.push("Error semantico en "+ lexema+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
								}
							}
							else
							{
								if(semantico[j][2].length()==0)
									
									semantico[j][2]=lexema;
								else
									
									semantico[j][2]+=lexema;
							}
							
					}
				}
			}
			return true;
		}
		else
			return false;
	}
	
	private boolean Esid(String lexema)
	{
		exi=false;
		boolean ban=false;
		//no puede empezar con numeros 
		//no puede contener caracteres especiales
		//no puede iniciar con guion medio -
		
		String especiales="!|#@$%&/()=><{}[]-;,.'?/";
		String numeros="12345678890";
		
		for(int i=0; i<lexema.length();i++){
			if(i==0){
				if(numeros.indexOf(lexema.charAt(i))!=-1){
					ban=false;
					break;
				}
			}				
			if(especiales.indexOf(lexema.charAt(i))!=-1)
			{
				ban=false;
				break;
			}
			else
				if(lexema.charAt(i)=='"')
				{
					ban=false;
					break;
				}
				else
				{
					if(dec)
					{
						rep=false;
						for(int j=0;j<semantico.length;j++)
						{
							if(semantico[j][1].equals(lexema))
							{
								rep=true;
								pilaerrorsem.push("Error semantico en "+ lexema+" id repetido, numero de linea "+numl);
							}
						}
						if(!rep)
						{
							aux=new String[semantico.length+1][3];
							for(int j=0;j<semantico.length;j++)
							{
								for(int k=0;k<3;k++)
								{
									aux[j][k]=semantico[j][k];
								}
							}
							aux[semantico.length][0]=Tipo;
							aux[semantico.length][1]=lexema;
						}
						semantico=aux;
					}
					for(int j=0;j<semantico.length;j++)
					{
						if(semantico[j][1].equals(lexema))
						{
							exi=true;
						}
					}
					if(exi)
					{
						if(asi2)
						{
							for(int j=0;j<semantico.length;j++)
								if(semantico[j][1].equals(lexema))
									for(int k=0;k<semantico.length;k++)
										if(semantico[k][1].equals(id))
										{
											if(semantico[k][0].equals("int"))
											{
												if(semantico[j][0].equals("int"))
												{
													if(id.equals(lexema))
														valor=semantico[k][2];
													semantico[k][2]=semantico[j][2];
												}
											}
											else
											{
												if(semantico[k][0].equals("float"))
												{
													if(!semantico[j][0].equals("char"))
													{
														if(id.equals(lexema))
															valor=semantico[k][2];
														semantico[k][2]=semantico[j][2];
													}
												}
												else
												{
													if(semantico[k][0].equals("char"))
													{
														if(!semantico[j][0].equals("float"))
														{
															if(id.equals(lexema))
																valor=semantico[k][2];
															semantico[k][2]=semantico[j][2];
														}
													}
													else
													{
														pilaerrorsem.push("Error semantico en "+ lexema+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
													}
												}
											}
											
											
										}
												
						}
						else
						{
							if(conca)
							{
								for(int j=0;j<semantico.length;j++)
									if(semantico[j][1].equals(lexema))
										for(int k=0;k<semantico.length;k++)
											if(semantico[k][1].equals(id))
											{
												if(id.equals(lexema))
												{
													if(semantico[k][0].equals("int")&&semantico[j][0].equals("int"))
													{
														semantico[k][2]+=valor;
													}
													else
													{
														if(semantico[k][0].equals("float")&&!semantico[j][0].equals("char"))
														{
															semantico[k][2]+=valor;
														}
														else
														{
															if(semantico[k][0].equals("char")&&!semantico[j][0].equals("float"))
															{
																semantico[k][2]+=valor;
															}
															else
															{
																pilaerrorsem.push("Error semantico en "+ lexema+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
															}
														}
													}
												}
												else
												{
													if(semantico[k][0].equals("int")&&semantico[j][0].equals("int"))
													{
														semantico[k][2]+=semantico[j][2];
													}
													else
													{
														if(semantico[k][0].equals("float")&&!semantico[j][0].equals("char"))
														{
															semantico[k][2]+=semantico[j][2];
														}
														else
														{
															if(semantico[k][0].equals("char")&&!semantico[j][0].equals("float"))
															{
																semantico[k][2]+=semantico[j][2];
															}
															else
															{
																pilaerrorsem.push("Error semantico en "+ lexema+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
															}
														}
													}
													
												}
											}
							}
							else
							{
								asi=true;
								id=lexema;
								for(int k=0;k<semantico.length;k++)
									if(semantico[k][1].equals(id))
									{
										valor=semantico[k][2];
									}
							}
						}
					}
					else
					{
						pilaerrorsem.push("Error semantico en "+ lexema+" id no existente, numero de linea "+numl);
					}
					ban=true;
				}
		}
		return ban;
	}
	
	private boolean EsCaracter(String lexema)
	{
		boolean ban;
		
			if(lexema.length()==3)
			{
				if((lexema.charAt(0)+"").compareTo("'")==0)
				{
					if((lexema.charAt(2)+"").compareTo("'")==0)
					{
						ban=true;
						if(asi2)
						{
							for(int j=0;j<semantico.length;j++)
							{
								if(semantico[j][1].equals(id))
								{
									if(semantico[j][0].equals("char"))
									{
										semantico[j][2]=lexema;
									}
									else
									{
										pilaerrorsem.push("Error semantico en "+ lexema
												+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
									}
								}
							}
						}
						else
						{
							if(conca)
							{
								for(int j=0;j<semantico.length;j++)
								{
									if(semantico[j][1].equals(id))
										if(semantico[j][0].equals("char"))
										{
											if(semantico[j][2].length()==0)
												semantico[j][2]+=lexema;
											else
												semantico[j][2]+=lexema;
										}
										else
										{
											pilaerrorsem.push("Error semantico en "+ lexema
													+" tipo no compatible con "+semantico[j][1]+", numero de linea "+numl);
										}
								}
							}
						}
					}
					else
					{
						ban=false;
					}
				}
				else
				{
					ban=false;
				}
			}
			else
			{
				ban=false;
			}
		return ban;
	}
	
	private boolean EsCadena(String lexema)
	{
		boolean ban=true;
		
		for(int i=0; i<lexema.length();i++)
		{
			if(i!=0&&i!=lexema.length()-1)
			{
				if(lexema.charAt(i)=='"'){
					ban=false;
					break;
				}
			}
			if(i==0||i==lexema.length()-1)
			{
				if(lexema.charAt(i)!='"')
				{
					ban=false;
					break;
				}
			}
				
		}
		return ban;
	}
	
	
	public String tablaSem()
	{
		String tab="";
		for(int i=0; i<semantico.length ;i++){ //pila reservada
			for(int j=0; j<semantico[0].length ;j++)
			{
				tab+=semantico[i][j] + "   ";
			}
			tab+="\n";
		}
		return tab;
	}
	
	 public String[] getPilaerrorS()
	 {
		String vec[]=new String[this.pilaerrorsem.size()];
		int i,j;
		for(i=0,j=this.pilaerrorsem.size()-1; i<=j ;i++){ //pila reservadas
			vec[i]=this.pilaerrorsem.elementAt(i);
		}
		return vec;		
	}
	
    public String[] getPilaerror(){
		String vec[]=new String[this.pilaerror.size()];
		int i,j;
		for(i=0,j=this.pilaerror.size()-1; i<=j ;i++){ //pila reservadas
			vec[i]=this.pilaerror.elementAt(i);
		}
		return vec;		
	}
    
    
    public Stack<String> getComponentesLexicos()
    {
		return pilalexica;		
	}
    
    public String getCodigo(){
    	return this.CodSalida;
    }
}