package Analizadores;

import java.util.Stack;

public class Generador 
{

                  // int a ; a = 5 * 7 + 3 ; imprimir ( a ) ;
                  // int a , b , c ; leer b leer c a = b + b ; do { a = a + b ; } while ( a < c ) imprimir ( a ) ;
	// int edad , lim ; lim = 18 ; leer edad if ( edad < lim ) { imprimir ( "menor de edad " edad ) ; } else { imprimir ( "mayor de edad " edad ) ; }
	private String reservadas[]={  // se definen las palabras reservadas		
			"int","float","char","if","while","do","else","!=","==","<=",">=","imprimir","leer"}; 	
	private char simbolos[]={ // se definen los simbolos- No se pueden declarar simbolos que java no acepte, como @ ! y comillas dobles
			'+','-','*','/','=','(',')',',',';','{','}','<','>'
	};	
	private String CodSalida="int main () { \n", Tipo="";
	boolean id =false;
	String ids[][]=new String[30][2];
	int can=0;
	Stack<String> idpr=new Stack<String>();
	Stack<String> idnpr=new Stack<String>();
	
	Stack<Integer> muldivpr=new Stack<Integer>();
	Stack<Integer> sumrespr=new Stack<Integer>();
	
	Stack<Integer> smuldivpr=new Stack<Integer>();
	Stack<Integer> ssumrespr=new Stack<Integer>();
	
	Stack<String> pareidpr=new Stack<String>();
	Stack<String> pareidnpr=new Stack<String>();
	
	Stack<Integer> paremuldivpr=new Stack<Integer>();
	Stack<Integer> paresumrespr=new Stack<Integer>();
	
	Stack<Integer> paresmuldivpr=new Stack<Integer>();
	Stack<Integer> paressumrespr=new Stack<Integer>();
	
	boolean cad=false,leer=false,i=false,asig=false,
			asig2=false,sumres=false,muldiv=false, 
			pare=false, paresumres=false, paremuldiv=false, sum=false;
	int numelem=0,v=0;
	String idr="",car="";
	
	public void Analizar(String ent)
	{
		ent+=" $";
		String lexema="";
		for(int i=0; ent.charAt(i)!='$' ;)
		{
			if(ent.charAt(i)=='"')
			{
				if(!cad)
				{
					cad=true;
				}
				else
				{
					cad=false;
				}
			}
			if(cad)
			{
				lexema+=ent.charAt(i);
				i++;
			}
			else
			{
				if(ent.charAt(i)!=' ')
				{
					lexema+=ent.charAt(i);
					i++;
				}
				else
				{
					this.ClasificaLex(lexema);
					i++;
					lexema="";
				}
			}					
		}
		
		CodSalida+="\n printf(\"Fin de programa...\"); \n" + "}";
	}
	
	private void ClasificaLex(String lexema)
	{
		if(this.EsReservada(lexema))
		{
			
			if(asig)
			{
				asig=false;
				this.CodSalida+=idr+" ";
			}

			if(lexema.equals("int")||lexema.equals("float")||lexema.equals("char"))
			{
				id=true;
				this.CodSalida+=lexema+" ";
				if(lexema.equals("int"))
				{
					Tipo="%i";
				}
				else
				{
					if(lexema.equals("float"))
					{
						Tipo="%f";
					}
					else
					{
						if(lexema.equals("char"))
						{
							Tipo="%c";
						}
					}
				}
			}
			else
			{
				if(lexema.equals("imprimir"))
				{
					this.CodSalida+="printf";
					i=true;
				}
				else
				{
					if(lexema.equals("leer"))
					{
                                                                                                            this.CodSalida+="printf(\"Ingrese el valor: \");\n";
						this.CodSalida+="scanf(\""+Tipo+"\",&";
						leer=true;
					}
					else
					{
						this.CodSalida+=lexema+" ";
					}
				}
			}
		
			
		
		}
		else
		{
			if(this.EsSimbolo(lexema))
			{
				if(i)
				{
					if(lexema.equals("("))
					{
						this.CodSalida+=lexema+" ";
					}
					else
					{
						if(lexema.equals(";"))
						{
							this.CodSalida+=")"+lexema+" ";
						}
					}
				}
				if(lexema.equals("("))
				{
					pare=true;
				}
				else
				{
					if(asig)
					{

						if(lexema.equals("="))
						{
							asig2=true;
							asig=false;
							v=numelem;
						}
						else
						{
							if(!pare)
							{
								asig=false;
							}
							this.CodSalida+=idr+" ";

							if(lexema.equals(";"))
							{
								if(sumres)
								{
									for(int i=0;i<idpr.size();i++)
									{
										this.CodSalida+="v"+(muldivpr.elementAt(i))+"="+"v"+(muldivpr.elementAt(i))+
												idpr.elementAt(i)+"v"+(smuldivpr.elementAt(i))+"; \n";
										v=muldivpr.elementAt(i);
									}
									for(int i=0;i<idnpr.size();i++)
									{
										this.CodSalida+="v"+(can-1)+"="+"v"+(can-1)+
												idnpr.elementAt(i)+"v"+(ssumrespr.elementAt(i))+"; \n";
										v=sumrespr.elementAt(i);
										sum=true;
									}
								}
								
								idpr=new Stack<String>();
								idnpr=new Stack<String>();
								muldivpr=new Stack<Integer>();
								sumrespr=new Stack<Integer>();
								
								if(asig2)
								{
									if(!sum)
									{
										this.CodSalida+=idr+"= v"+v;
									}
									else
									{
										this.CodSalida+=idr+"= v"+(can-1);
										sum=false;
									}
								}
								asig=false;
								asig2=false;
								sumres=false;
								muldiv=false;
								this.CodSalida+=lexema+" \n";
								id=false;
							}
							else
							{
								if(lexema.equals(")"))
								{
									if(pare)
									{
										if(paresumres)
										{
											for(int i=0;i<pareidpr.size();i++)
											{
												this.CodSalida+="v"+(paremuldivpr.elementAt(i))+"="+"v"+(paremuldivpr.elementAt(i))+
														pareidpr.elementAt(i)+"v"+(paresmuldivpr.elementAt(i))+"; \n";
												v=paremuldivpr.elementAt(i);
											}
											for(int i=0;i<pareidnpr.size();i++)
											{
												this.CodSalida+="v"+(paresumrespr.elementAt(i))+"="+"v"+(paresumrespr.elementAt(i))+
														pareidnpr.elementAt(i)+"v"+(paressumrespr.elementAt(i))+"; \n";
												v=paresumrespr.elementAt(i);
											}
										}
										
										pareidpr=new Stack<String>();
										pareidnpr=new Stack<String>();
										paremuldivpr=new Stack<Integer>();
										paresumrespr=new Stack<Integer>();
										
										paresumres=false;
										paremuldiv=false;
										pare=false;
									}
									else
									{
										if(i)
										{
											i=false;
										}
										this.CodSalida+=lexema+" ";
									}
									
								}
								else
								{
									if(pare)
									{
										if(lexema.equals("+")||lexema.equals("-"))
										{
											pareidnpr.add(lexema);
											if(paremuldiv)
											{
												paresumrespr.add(0);
											}
											else
											{
												paresumrespr.add(numelem-1);
											}
											paressumrespr.add(numelem);
											paresumres=true; 
											paremuldiv=false; 
										}
										else
										{
											if(lexema.equals("*")||lexema.equals("/"))
											{
												pareidpr.add(lexema);
												if(paremuldiv)
												{
													paremuldivpr.add(numelem-2);
												}
												else
												{
													paremuldivpr.add(numelem-1);
												}
												paresmuldivpr.add(numelem);
												paresumres=true;
												paremuldiv=true;
											}
											else
											{
												this.CodSalida+=lexema+" ";
											}
										}
									}
									else
									{
										if(lexema.equals("+")||lexema.equals("-"))
										{
											idnpr.add(lexema);
											if(muldiv)
											{
												sumrespr.add(0);
											}
											else
											{
												sumrespr.add(numelem-1);
											}
											ssumrespr.add(numelem);
											sumres=true; 
											muldiv=false; 
										}
										else
										{
											if(lexema.equals("*")||lexema.equals("/"))
											{
												idpr.add(lexema);
												if(muldiv)
												{
													muldivpr.add(numelem-2);
												}
												else
												{
													muldivpr.add(numelem-1);
												}
												smuldivpr.add(numelem);
												sumres=true;
												muldiv=true;
											}
											else
											{
												this.CodSalida+=lexema+" ";
											}
										}
									}
								}
							}
						
						}
					}
					else
					{

						if(lexema.equals(";"))
						{
							if(sumres)
							{
								for(int i=0;i<idpr.size();i++)
								{
									this.CodSalida+="v"+(muldivpr.elementAt(i))+"="+"v"+(muldivpr.elementAt(i))+
											idpr.elementAt(i)+"v"+(smuldivpr.elementAt(i))+"; \n";
									v=muldivpr.elementAt(i);
								}
								for(int i=0;i<idnpr.size();i++)
								{
									this.CodSalida+="v"+(can-1)+"="+"v"+(can-1)+
											idnpr.elementAt(i)+"v"+(ssumrespr.elementAt(i))+"; \n";
									v=sumrespr.elementAt(i);
									sum=true;
								}
							}
							
							idpr=new Stack<String>();
							idnpr=new Stack<String>();
							muldivpr=new Stack<Integer>();
							sumrespr=new Stack<Integer>();
							
							if(asig2)
							{
								if(!sum)
								{
									this.CodSalida+=idr+"= v"+v;
								}
								else
								{
									this.CodSalida+=idr+"= v"+(can-1);
									sum=false;
								}
							}
							asig=false;
							asig2=false;
							sumres=false;
							muldiv=false;
							this.CodSalida+=lexema+" \n";
							id=false;
						}
						else
						{
							if(lexema.equals(")"))
							{
								if(pare)
								{

									if(paresumres)
									{
										for(int i=0;i<pareidpr.size();i++)
										{
											this.CodSalida+="v"+(paremuldivpr.elementAt(i))+"="+"v"+(paremuldivpr.elementAt(i))+
													pareidpr.elementAt(i)+"v"+(paresmuldivpr.elementAt(i))+"; \n";
											v=paremuldivpr.elementAt(i);
										}
										for(int i=0;i<pareidnpr.size();i++)
										{
											this.CodSalida+="v"+(paresumrespr.elementAt(i))+"="+"v"+(paresumrespr.elementAt(i))+
													pareidnpr.elementAt(i)+"v"+(paressumrespr.elementAt(i))+"; \n";
											v=paresumrespr.elementAt(i);
										}
									}
									
									pareidpr=new Stack<String>();
									pareidnpr=new Stack<String>();
									paremuldivpr=new Stack<Integer>();
									paresumrespr=new Stack<Integer>();
									
									paresumres=false;
									paremuldiv=false;
									pare=false;
								}
								else
								{
									if(i)
									{
										i=false;
									}
									this.CodSalida+=lexema+" ";
								}
							}
							else
							{
								if(pare)
								{
									if(lexema.equals("+")||lexema.equals("-"))
									{
										pareidnpr.add(lexema);
										if(paremuldiv)
										{
											paresumrespr.add(0);
										}
										else
										{
											paresumrespr.add(numelem-1);
										}
										paressumrespr.add(numelem);
										paresumres=true; 
										paremuldiv=false; 
									}
									else
									{
										if(lexema.equals("*")||lexema.equals("/"))
										{
											pareidpr.add(lexema);
											if(paremuldiv)
											{
												paremuldivpr.add(numelem-2);
											}
											else
											{
												paremuldivpr.add(numelem-1);
											}
											paresmuldivpr.add(numelem);
											paresumres=true;
											paremuldiv=true;
										}
										else
										{
											this.CodSalida+=lexema+" ";
										}
									}
								}
								else
								{

									if(lexema.equals("+")||lexema.equals("-"))
									{
										idnpr.add(lexema);
										if(muldiv)
										{
											sumrespr.add(0);
										}
										else
										{
											sumrespr.add(numelem-1);
										}
										ssumrespr.add(numelem);
										sumres=true; 
										muldiv=false; 
									}
									else
									{
										if(lexema.equals("*")||lexema.equals("/"))
										{
											idpr.add(lexema);
											if(muldiv)
											{
												muldivpr.add(numelem-2);
											}
											else
											{
												muldivpr.add(numelem-1);
											}
											smuldivpr.add(numelem);
											sumres=true;
											muldiv=true;
										}
										else
										{
											this.CodSalida+=lexema+" ";
										}
									}
								}
							}
						}
					}
				}
				
			}
			else
			{
				if(this.EsNumero(lexema))
				{
					if(asig2)
					{
						this.CodSalida+="int v"+numelem+"="+lexema+"; \n";
						numelem++;
					}
					else
					{
						if(asig)
						{
							asig=false;
							this.CodSalida+=idr+" ";
						}
						this.CodSalida+=lexema+" ";
					}
				}
				else
				{
					if(this.EsCadena(lexema))
					{
						if(asig)
						{
							asig=false;
							this.CodSalida+=idr+" ";
						}
						this.CodSalida+=lexema+" ";
						
					}
					else
					{
						if(this.Esid(lexema))
						{
							if(id)
							{
								ids[can][0]=Tipo;
								ids[can][1]=lexema;
								can++;
								System.out.println(can);
							}
							
							if(i)
							{
								this.CodSalida+="\""+Tipo+"\","+lexema +" )";
								i=false;
							}
							else
							{
								if(leer)
								{
									this.CodSalida+=lexema+" ); \n";
									leer=false;
								}
								else
								{
									if(asig2)
									{
										this.CodSalida+="int v"+numelem+"="+lexema+"; \n";
										numelem++;
									}
									else
									{
										if(!asig)
										{
											asig=true;
											idr=lexema;
										}
										else
										{

											asig=false;
											this.CodSalida+=idr+" ";
											if(leer)
											{
												this.CodSalida+=lexema+" ); \n";
												leer=false;
											}
											else
											{
												if(i)
												{
													this.CodSalida+="\""+Tipo+"\","+lexema+" )";
													i=false;
												}
												else
												{
													this.CodSalida+=lexema+" ";
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
	
	private boolean EsReservada(String lexema)
	{
		boolean ban=false;
		for(int i=0; i<reservadas.length;i++){
			if(lexema.equals(reservadas[i]))
			{
				ban=true;
				break;
			}
			ban=false;
		}
		return ban;
	}
	
	private boolean EsSimbolo(String lexema)
	{
		boolean ban=false;
		if(lexema.length()==1){
			for(int i=0; i<simbolos.length;i++){
				if(lexema.charAt(0)==simbolos[i])
				{
					ban=true;
					break;
				}
				ban=false;
			}
		}
		return ban;
	}
	
	private boolean EsNumero(String lexema)
	{
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
			return true;
		else
			return false;
	}
	
	private boolean Esid(String lexema)
	{
		boolean ban=false;
		//no puede empezar con numeros 
		//no puede contener caracteres especiales
		//no puede iniciar con guion medio -
		
		String especiales="!�|#@$%&/()=><{}[]-;,.'��?/";
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
				ban=true;
		}
		return ban;
	}
	
	public String getCodigo()
	{
    	return this.CodSalida;
    }
}