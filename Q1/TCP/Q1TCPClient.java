import java.net.*;
import java.io.*;
public class Q1TCPClient{
	public static void main (String args[]){
		
		/* cria-se um socket de cliente inicialmente nulo */
		Socket s = null;
		
		try{
			/* cria-se uma variavel do tipo inteira para receber o numero da porta */
			int serverPort = 7896;
			
			/* o socket s criado anteriormente, recebe um novo socket cujo
			tem como parametro "localhost" (IP do servidor) e a variavel da porta */
			s = new Socket("localhost",serverPort);    
			
			/* variveis de entrada e saida de fluxo de dados são criadas*/
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			/* escreve-se no fluxo de saída a mensagem que desejar,
			   cuja mensagem é passada como argumento, em args[0] */
			out.writeUTF(args[0]);      	// UTF é uma string codificada, veja Sn. 4,4
			
			/*  uma string chamada data é criada com a finalidade de receber um fluxo de dados,
				que parte do servidor para o cliente. entrada que é uma uma string que permite caracteres latinos */
			String data = in.readUTF();	    // leia uma linha de dados do fluxo
			
			// imprime a mensagem recebida, que foi retornada pelo servidor
			System.out.println("Received: "+ data) ; 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}
