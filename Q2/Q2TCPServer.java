import java.net.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.io.*;

public class Q2TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 
			String data = in.readUTF();	                  
			/* verifica se mensagem do cliente
			   é igual a o comando que o servidor espera
			   se for procura qual o dia e a mensagem do dia certa, se nao imprime erro */
			if(data.trim().equals("request")) {
				writeOut();
			}else {
				out.writeUTF("Error: invalid command");
			}
			
			System.out.println("Connection succefull");
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
	
	/* método separado para verificar a data atual e procurar a frase correta p/ o dia atual */
	public void writeOut() throws IOException{
		//criação de um objeto date cujo recebe a data atual juntamente a hora e dia da semana
		Date date = new Date();
		
		/* converte o objeto date em um LocalDate, já que o localDate permite pegarmos
		   os elementos separados da data, como no caso do dia e do mês. Estando o dia
		   do mes atual em mãos, podemos verificar e por fim devolver a frase correspondente
		   ao dia atual */		
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		/* cria uma variavel inteira day que recebe o dia do mês */
		int day   = localDate.getDayOfMonth();
		
		/* faz a verificação de qual o dia atual, e busca a frase correspondente a ele
		   por fim, escreve a frase correta cuja o servidor devolve para o cliente */
		if(day == 1) {
			out.writeUTF(" Quando fazemos da união nossa principal arma na luta por um objetivo comum, vencer é uma tarefa que se torna bem mais fácil. " + date);
		}
		else if(day == 2) {
			out.writeUTF(" Nunca desista porque encontrou um obstáculo! Os desafios são o tempero da vida! " + date);
		}
		else if(day == 3) {
			out.writeUTF(" Com determinação e foco no sucesso todos os sonhos vão se realizar. " + date);
		}
		else if(day == 4) {
			out.writeUTF(" A melhor maneira de melhorar o padrão de vida está em melhorar o padrão de pensamento. " + date);
		}
		else if(day == 5) {
			out.writeUTF(" Serei sempre otimista, pois acreditar é o primeiro passo para fazer acontecer! " + date);
		}
		else if(day == 6) {
			out.writeUTF(" Aquele que tentou e não conseguiu, é superior àquele que não tentou. " + date);
		}
		else if(day == 7) {
			out.writeUTF(" Teste os seus limites, enfrente os seus medos e não deixe que nada impeça você de pelo menos tentar! " + date);
		}
		else if(day == 8) {
			out.writeUTF(" Você é mais forte e mais capaz do que imagina, e a conquista dos seus objetivos depende apenas de você! " + date);
		}
		else if(day == 9) {
			out.writeUTF(" Fique tranquilo! Amanhã você vai achar um jeito de sorrir daquilo que hoje lhe fez chorar. " + date);
		}
		else if(day == 10) {
			out.writeUTF(" Não se desanime diante dos obstáculos, eles são sempre uma oportunidade de você sair mais forte de uma situação. " + date);
		}
		else if(day == 11) {
			out.writeUTF(" Você não só tem o direito de ser feliz como também tem a obrigação de lutar para alcançar a felicidade. " + date);
		}
		else if(day == 12) {
			out.writeUTF(" Encare o que fez de errado com motivação, pois é isso que o ajudará a fazer certo da próxima vez. " + date);
		}
		else if(day == 13) {
			out.writeUTF(" As pessoas dizem frequentemente que a motivação não dura. Bem, nem o banho - e é por isso que ele é recomendado diariamente.  " + date);
		}
		else if(day == 14) {
			out.writeUTF(" Toda ação humana, quer se torne positiva ou negativa, precisa depender de motivação. " + date);
		}
		else if(day == 15) {
			out.writeUTF(" Há dias que você tem que ir para a frente só com o que você tem na mão, não dá para esperar pela motivação. " + date);
		}
		else if(day == 16) {
			out.writeUTF(" Às vezes um pouco de motivação ajuda a superar os maiores desafios. " + date);
		}
		else if(day == 17) {
			out.writeUTF(" A vida tanto lhe pode dar o melhor como o pior, mas é você quem escolhe aquilo que vai permanecer ou ficar para trás. " + date);
		}
		else if(day == 18) {
			out.writeUTF(" Assim como os pássaros, precisamos aprender a superar os desafios que nos são apresentados, para alçarmos voos mais altos.  " + date);
		}
		else if(day == 19) {
			out.writeUTF(" Já experimentou acreditar em você? Tente! Você não faz ideia do que é capaz. " + date);
		}
		else if(day == 20) {
			out.writeUTF(" Sonhos existem para serem realizados, por isso não olhe para trás nem escute palavras de desânimo! " + date);
		}
		else if(day == 21) {
			out.writeUTF(" Se ao enfrentar os problemas da vida lhe parecer que está escalando uma montanha impossível, lembre-se que a paisagem que avistará no topo compensará qualquer esforço seu. " + date);
		}
		else if(day == 22) {
			out.writeUTF(" O poder está dentro de você, na sua mente, pois se acreditar que consegue não haverá obstáculo capaz de impedir o seu sucesso. " + date);
		}
		else if(day == 23) {
			out.writeUTF(" Nunca é tarde demais para ser aquilo que sempre desejou ser.  " + date);
		}
		else if(day == 24) {
			out.writeUTF(" Se quer viver uma vida feliz, amarre-se a uma meta, não a pessoas nem a coisas. " + date);
		}
		else if(day == 25) {
			out.writeUTF(" Não deixe que seus sonhos percam a força de voar! " + date);
		}
		else if(day == 26) {
			out.writeUTF(" Não se perca tentando ser melhor que ninguém, esforce-se apenas por ser e dar o melhor de você. " + date);
		}
		else if(day == 27) {
			out.writeUTF(" Sempre que a meta parecer longe, lembre-se que a distância é relativa e uma questão de perspectiva! " + date);
		}
		else if(day == 28) {
			out.writeUTF(" Acredite que você pode ser como uma árvore forte que se mantém de pé até nas maiores tempestades. " + date);
		}
		else if(day == 29) {
			out.writeUTF(" Não importa a quantidade de adversidades que terá pela frente, pois elas não mudarão seu rumo se você tiver a determinação certa. " + date);
		}
		else if(day == 30) {
			out.writeUTF(" Não importa se seu sonho vai se realizar hoje ou amanhã, mas sim que você trabalhe para o alcançar todos os dias. " + date);
		}
		else if(day == 31) {
			out.writeUTF(" Muitas vezes a vida é arriscar tudo por algo que ninguém mais pode ver além de você. Acredite nos seus sonhos!" + date);
		}
	}
}