import java.net.*;
import java.io.*;
public class UDPServer{
    public static void main(String args[]){ 
        DatagramSocket aSocket = null;
        //Cria um datagrama socket inicialmente nulo
        try{
            // o datagrama socket criado recebe como parametro
            // um novo datagrama passando 6789 como a porta
            aSocket = new DatagramSocket(6789);
                    // create socket at agreed port
            //Cria um array do tipo byte chamado buffer de tamanho 1000
            byte[] buffer = new byte[1000];
            while(true){
                // cria um datagrama pacote chamado request(solicitação)
                // passando como parametro o array de bytes criado anteriormente, juntamente ao tamanho do array
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                // o datagrama socket recebe o datagrama de pacote (request)
                aSocket.receive(request);     
                // cria um datagrama reply(resposta) passando como parametro
                // os dados de request, request tamanho, request endereço e request porta
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
                    request.getAddress(), request.getPort());
                // datagrama socket envia o datagrama de pacote reply(resposta)
                aSocket.send(reply);
                // imprime apos enviar a mensagem
                System.out.println("Server: " + "mensagem recebida");
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}