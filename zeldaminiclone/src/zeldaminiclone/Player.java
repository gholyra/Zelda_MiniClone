package zeldaminiclone;

import java.awt.Graphics; //Importa��o da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importa��o da classe Rectangle da biblioteca do java
import java.util.ArrayList; //Importa��o da classe ArrayList da biblioteca do java
import java.util.List; //Importa��o da classe List da biblioteca do java

public class Player extends Rectangle { //Herda as propriedades, colis�es e os vetores de um ret�ngulo
	
	public int spd = 3; //Vari�vel para a velocidade do jogador
	public boolean right, up, down, left; //Vari�veis para a dire��o dos movimentos do jogador
	
	public int curAnimation = 0; //Vari�vel que guarda o �ndice do sprite da anima��o atual; curAnimation significa "current animation"

	public static List<Bullet> bullets = new ArrayList<Bullet>(); //Instanciamento da classe Bullet a colocando dentro de uma lista
	//A lista � est�tica, ou seja, est� associada � classe e n�o a uma inst�ncia espec�fica dessa classe
	
	public boolean shoot = false; //Vari�vel que diz se o personagem est� atirando ou n�o
	
	public int curFrames = 0, targetFrames = 10; //Vari�veis que guardam n�meros de frames para o funcionamento das anima��es
	//A vari�vel curFrames significa "current frames", e guarda o n�mero do frame atual que est� sendo renderizado
	//A vari�vel targetFrames � o n�mero alvo de frames a ser atingido, para que, assim, o sprite de uma anima��o mude
	//Deve-se pensar que, se o jogo est� sendo executado a 60 frames por segundo, a cada segundo temos 60 frames renderizados
	//Para que haja a mudan�a de um sprite para outro, curFrames ser� aumentada at� que atinja a targetFrames 
	//Quanto maior targetFrames for, mais tempo levar� para a mudan�a de sprites
	//E j� que 60 frames totalizam 1 segundo, 30 frames � o mesmo que 0,5 segundo, 15 frames, 0,25 segundo, e 10 frames, 0,1666 segundo aproximadamente
	
	public String lastInput; //Vari�vel que guarda a �ltima tecla que movimentou o personagem
	//Logo, tamb�m guarda o �ltimo sentido de sua movimenta��o
	public boolean noKeyPressed; //Vari�vel que diz se j� houve alguma tecla pressionada ou n�o
	
	public boolean movedUP; //Vari�vel que diz se o personagem est� se movendo para cima ou n�o
	public boolean movedDOWN; //Vari�vel que diz se o personagem est� se movendo para baixo ou n�o
	public boolean movedLEFT; //Vari�vel que diz se o personagem est� se movendo para a esquerda ou n�o
	public boolean movedRIGHT; //Vari�vel que diz se o personagem est� se movendo para a direita ou n�o
	
	//M�todo construtor
	public Player(int x, int y) { //O m�todo contrutor possui as coordenadas do jogador como par�metros 
		super(x, y, 32, 32); //O super � respons�vel por chamar o construtor da superclasse / classe m�e, ou seja, a classe Rectangle
		//Chamando a classe Rectangle, os par�metros passam a ser as coordenadas e as dimens�es de um ret�ngulo
		noKeyPressed = true;
		//Sempre que a classe player for constru�da, a vari�vel noKeyPressed ser� dada como verdadeira
	}

	public void tick() {
		
		//A todo tick, as vari�veis que indicam se o personagem est� se movendo ser�o dadas como falsas, a n�o ser que haja um input
		movedUP = false;
		movedDOWN = false;
		movedLEFT = false;
		movedRIGHT = false;
		
		//Caso a vari�vel right seja true, a vari�vel spd ser� somada a coordenada x
		if(right && World.isFree(x + spd, y)) { //Al�m do input, � verificado se o caminho est� livre e se o player j� est� indo para a direita
			x += spd; //O equivalente a +=, neste caso, seria x = x + spd
			movedRIGHT = true; //Caso o input recebido seja para a direita e o caminho esteja livre, a vari�vel movedRIGHT ser� true
			noKeyPressed = false; //Se qualquer input de movimento do personagem for recebido, noKeyPressed ser� dada como false
		}
		
		//Caso a vari�vel right seja false, mas a vari�vel left, true, a vari�vel spd ser� subtra�da da coordenada x
		else if(left && World.isFree(x - spd, y)) { //Al�m do input, � verificado se o caminho est� livre e se o player j� est� indo para a esquerda
			x -= spd; //O equivalente a -=, neste caso, seria x = x - spd
			movedLEFT = true; //Caso o input recebido seja para a esquerda e o caminho esteja livre, a vari�vel movedLEFT ser� true
			noKeyPressed = false; //Se qualquer input de movimento do personagem for recebido, noKeyPressed ser� dada como false
		}
		
		//Caso a vari�vel up seja true, a vari�vel spd ser� subtra�da da coordenada y
		if(up && World.isFree(x, y - spd)) { //Al�m do input, � verificado se o caminho est� livre e se o player j� est� indo para cima
			y -= spd; //O equivalente a -=, neste caso, seria y = y - spd
			movedUP = true; //Caso o input recebido seja para cima e o caminho esteja livre, a vari�vel movedUP ser� true
			noKeyPressed = false; //Se qualquer input de movimento do personagem for recebido, noKeyPressed ser� dada como false
		}
		
		//Caso a vari�vel up seja false, mas a vari�vel down, true, a vari�vel spd ser� somada a coordenada y
		else if(down && World.isFree(x, y + spd)) { //Al�m do input, � verificado se o caminho est� livre e se o player j� est� indo para baixo
			y += spd; //O equivalente a +=, neste caso, seria y = y + spd
			movedDOWN = true; //Caso o input recebido seja para baixo e o caminho esteja livre, a vari�vel movedDOWN ser� true
			noKeyPressed = false; //Se qualquer input de movimento do personagem for recebido, noKeyPressed ser� dada como false
		}
		
		//Caso alguma das vari�veis de movimento sejam verdadeiras, a l�gica para o funcionamento das anima��es come�ar�
		if(movedDOWN || movedUP || movedLEFT || movedRIGHT) {
			curFrames++; //A cada tick � somado 1 na vari�vel curFrames
			if(curFrames == targetFrames) { //Se curFrames for igual a targetFrames, as anima��es ser�o executadas efetivamente
				curFrames = 0; //Se curFrames for igual a targetFrames, ela ser� zerada
				curAnimation++; //Se curFrames for igual a targetFrames, curAnimation ser� somada em 1
				//Se curAnimation for igual ao tamanho dos arrays que guardam os sprites do player, curAnimation ser� zerada
				if(curAnimation == Spritesheet.player_front.length
					|| curAnimation == Spritesheet.player_back.length
					|| curAnimation == Spritesheet.player_leftProfile.length
					|| curAnimation == Spritesheet.player_rightProfile.length) {
					//Todos os arrays, no caso das anima��es de movimentos b�sicos do personagem, possuem apendas 2 elementos
					curAnimation = 0;
				}
			}
		}
		
		//Se a vari�vel shoot for verdadeira a l�gica para adicionar um novo proj�til na ArrayList come�ar�
		if(shoot) {
			shoot = false; //Logo ap�s a vari�vel se tornar verdadeira, ela � dada como falsa
			if(lastInput == "DOWN") { //Se o �ltimo input tiver sido para baixo, o sentido ser� positivo, e a dire��o ser� vertical
				bullets.add(new Bullet(x, y, 1, true, false));
			}
			else if(lastInput == "RIGHT") { //Se o �ltimo input tiver sido para a direita, o sentido ser� positivo, e a dire��o ser� horizontal
				bullets.add(new Bullet(x, y, 1, false, true));
			}
			else if(lastInput == "UP") { //Se o �ltimo input tiver sido para cima, o sentido ser� negativo, e a dire��o ser� vertical
				bullets.add(new Bullet(x, y, -1, true, false));
			}
			else if(lastInput == "LEFT") { //Se o �ltimo input tiver sido para a esquerda, o sentido ser� negativo, e a dire��o ser� horizontal
				bullets.add(new Bullet(x, y, -1, false, true));
			}

		}
		
		//Aqui, é chamado o m�todo tick para o proj�til que for atirado
		for(int i = 0; i < bullets.size(); i++) { //� importante notar que o loop soma em 1 a vari�vel i at� que ela seja menor que a pr�pria lista
			bullets.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) { //O par�metro do m�todo acaba sendo a vari�vel g que recebe o instanciamento da classe Graphics

		/*Observa��o: no m�dulo Mini Zelda, n�o s�o feitas as anima��es em que Link anda para os lados para cima, mas apenas para baixo*/
		
		if(movedDOWN) {
			g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);//M�todo usado para desenhar a imagem do player
			//Tem como par�metro a imagem da vari�vel player_front, suas coordenadas, seu tamanho (que pode ser redimensionado, caso preciso)
			//E, por fim, a interface ImageObserver, que possui m�todos para monitorar o carregamento de uma imagem
			//Neste caso, o �ltimo � nulo
			//A vari�vel player_front � uma lista, ent�o recebe a vari�vel curAnimation, que determina qual sprite da lista ser� renderizado
		}
		else if(movedUP) {
			g.drawImage(Spritesheet.player_back[curAnimation], x, y, 32, 32, null);
		}
		else if(movedLEFT) {
			g.drawImage(Spritesheet.player_leftProfile[curAnimation], x, y, 32, 32, null);
		}
		else if(movedRIGHT) {
			g.drawImage(Spritesheet.player_rightProfile[curAnimation], x, y, 32, 32, null);
		}
		//A partir daqui, as condições determinam qual sprite será renderizado após o jogador soltar a tecla que estava pressionando por último
		else if(movedDOWN == false && lastInput == "DOWN") {
			g.drawImage(Spritesheet.player_front[0], x, y, 32, 32, null);
		}
		else if(movedUP == false && lastInput == "UP") {
			g.drawImage(Spritesheet.player_back[0], x, y, 32, 32, null);
		}
		else if(movedLEFT == false && lastInput == "LEFT") {
			g.drawImage(Spritesheet.player_leftProfile[0], x, y, 32, 32, null);
		}
		else if(movedRIGHT == false && lastInput == "RIGHT") {
			g.drawImage(Spritesheet.player_rightProfile[0], x, y, 32, 32, null);
		}
		/*Esta �ltima condi��o determina que, caso o jogo tenha acabado de come�ar, nenhuma tecla tenha sido pressionada
		e o personagem n�o tenha sido movimentado, o primeiro sprite a ser renderizado ser� o primeiro da pose de frente*/
		else if(movedDOWN == false && movedUP == false && movedLEFT == false && movedRIGHT == false && noKeyPressed) {
			g.drawImage(Spritesheet.player_front[0], x, y, 32, 32, null);
		}
		
		//Aqui, � chamado o m�todo render para o proj�til que for atirado
		for(int i = 0; i < bullets.size(); i++) { //� importante notar que o loop soma em 1 a vari�vel i at� que ela seja menor que a pr�pria lista
			bullets.get(i).render(g);
		}

	}
	
}

