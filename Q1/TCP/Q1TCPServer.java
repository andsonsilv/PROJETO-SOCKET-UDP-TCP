import java.net.*;
import java.io.*;
public class Q1TCPServer{
	public static void main (String args[]){
		try{
			
			int serverPort = 7896; 
			ServerSocket listenSocket = new ServerSocket(serverPort);
			
			while(true) {
				/* cria-se um socket do client que recebe o listenSocket.accept();, ou seja,
					permite a conexão do cliente com o servidor, liberando comunicação  */
				Socket clientSocket = listenSocket.accept();
				
				/* foi criado uma classe para conexão, cuja recebe um socket de client como parametro */
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread{
	/* cria-se variaveis de fluxo de dados de entrada(in) e saida(out) */
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	
	/* método conexão */
	public Connection (Socket aClientSocket){
		try {
			//Socket cliente criado recebe o socket passado por parametro para o metodo conexão
			clientSocket = aClientSocket;
			
			in = new DataInputStream(clientSocket.getInputStream());    //pega fluxo de dados de entrada do socket client
			out =new DataOutputStream(clientSocket.getOutputStream());  //pega fluxo de dados de saida do socket client
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try{
			/*  uma string chamada data é criada com a finalidade de receber um fluxo de dados,
				que parte do servidor para o cliente. entrada que é uma uma string que permite caracteres latinos */
			String data = in.readUTF();	                  
			
			/* manda escrever no fluxo de saída os dados cujo o servidor recebeu do cliente.
			   visto que o servidor espera receber estes dados vindos do cliente */
			out.writeUTF(data);
			
			System.out.println("Connection succefull");
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		
	}
}