package zeldaminiclone;

import java.awt.Graphics; //Importa��o da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importa��o da classe Rectangle da biblioteca do java
import java.util.ArrayList; //Importa��o da interface ArrayList da biblioteca do java
import java.util.List; //Importa��o da interface List da biblioteca do java

//Com esta classe, todos os objetos que comp�em o mundo e seus cen�rios ser�o implementados, utilizando-se, para isto, arrays
public class World {

	//Ao declarar uma lista com a interface List, garante-se que a tipo de implementa��o da lista seja alterada com facilidade
	//Assim, de ArrayList para LinkedList, basta uma simples mudan�a
	public static List<Blocks> blocks = new ArrayList<Blocks>(); //Instanciamento da classe Blocks a colocando dentro de uma lista
	//A lista � est�tica, ou seja, est� associada � classe e n�o a uma inst�ncia espec�fica dessa classe
	
	//M�todo construtor da classe de mundo, que conter� a l�gica de posicionamento dos blocos a serem renderizados
	public World() {
		//L�gica para a cria��o dos blocos na linha horizontal do canto superior
		for(int xx = 0; xx < 15; xx++) { //J� que a largura � 480, divide-se 480 por 32, a largura do bloco, o que d� 15
			blocks.add(new Blocks(xx*32, 0));
			//Com a multiplica��o, coordenada x muda de 32 em 32, e o y permanece em 0 (primera linha da tela)
		}
		//L�gica para a cria��o dos blocos na linha horizontal do canto inferior
		for(int xx = 0; xx < 15; xx++) {
			blocks.add(new Blocks(xx*32, 480-32));
			//Com a multiplica��o, coordenada x muda de 32 em 32, e o y permanece em 448 (�ltima linha da tela)
		}
		//L�gica para a cria��o dos blocos na linha vertical do canto esquerdo
		for(int yy = 0; yy < 15; yy++) { //J� que a altura � 480, divide-se 480 por 32, a altura do bloco, o que d� 15
			blocks.add(new Blocks(0, yy*32)); 
			//Com a multiplica��o, coordenada y muda de 32 em 32, e o x permanece em 0 (primeira coluna da tela)
		}
		//L�gica para a cria��o dos blocos na linha horizontal canto direito
		for(int yy = 0; yy < 15; yy++) {
			blocks.add(new Blocks(480-32, yy*32));
			//Com a multiplica��o, coordenada y muda de 32 em 32, e o x permanece em 448 (�ltima coluna da tela)
		}
		
		//Adi��o de um bloco em um local aleat�rio para o teste de colis�o do inimigo
		blocks.add(new Blocks(128, 128)); 
		
	}
	
	//O static determina que toda vari�vel de uma classe / m�todo ter� o mesmo valor para qualquer objeto / varia��o do m�todo
	//Assim, n�o � necess�rio que um objeto seja instanciado, j� que o m�todo est� associado a classe em si, e n�o a uma inst�ncia
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < blocks.size(); i++) {
			//Pega o i atual e o coloca dentro da vari�vel blocoAtual, a qual foi declarada a partir da classe Blocks
			Blocks blocoAtual = blocks.get(i);
			//Se o bloco atual intersectar um novo ret�ngulo (o player), o caminho n�o estar� livre para o player
			if(blocoAtual.intersects(new Rectangle(x, y, 32, 32))) {
				return false;
			}
		}
		//Caso n�o haja intersec��o do bloco com o player, o caminho estar� livre para o player
		return true;
	}
	
	public void render(Graphics g) { //O par�metro do m�todo acaba sendo a vari�vel g que recebe o instanciamento da classe Graphics
		//Com um la�o de repeti��o, cada bloco da lista � renderizado tantas vezes quanto o tamanho da lista exige
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).render(g); //A cada vez que o loop se repete, mais um bloco � renderizado de acordo com o i atual
		}
	}
	
}
