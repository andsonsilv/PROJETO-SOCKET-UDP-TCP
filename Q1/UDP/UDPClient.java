import java.net.*;
import java.io.*;
public class UDPClient{
    public static void main(String args[]){ 
		/* cria o datagrama inicialmente nulo */
		DatagramSocket aSocket = null;
		try {
			/* inicializao  datagrama */
			aSocket = new DatagramSocket();
			/* cria um array do tipo byte cujo contem uma string */
			byte [] m = ("Engenharia da Computacao").getBytes();
			/* ip do host recebe o ip de localhost */
			InetAddress aHost = InetAddress.getByName("localhost");
			/* cria uma variavel inteira que recebe a porta de comunicação */
			int serverPort = 6789;		                                  
				/* cria uma datagrama pacote chamado solicitação passando como parametro
				   o array de bytes, tamanho do array, o endereço e porta */
			DatagramPacket request = new DatagramPacket(m,  m.length, aHost, serverPort);
			/* envia a solicitação */
			aSocket.send(request);
			/* cria um array de byter buffer de tamanho 1000 */
			byte[] buffer = new byte[1000];
			/* cria um datagrama de resposta passando buffer e tamanho do buffer */
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			/* recebe o datagrama de resposta */
			aSocket.receive(reply);
			/* imprime apos receber a resposta */
			System.out.println("Reply: " + new String(reply.getData()));	
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      	
}