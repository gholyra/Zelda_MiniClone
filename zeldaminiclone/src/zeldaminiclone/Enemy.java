package zeldaminiclone;

import java.awt.Graphics; //Importação da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importação da classe Rectangle da biblioteca do java
import java.util.ArrayList; //Importação da classe ArrayList da biblioteca do java
import java.util.List; //Importação da classe List da biblioteca do java
import java.util.Random; //Importação da classe Random da biblioteca do java

//A classe de inimigo é basicamente a mesma que a de jogador
public class Enemy extends Rectangle { //Herda as propriedades, colisões e os vetores de um retângulo

	public int spd = 2; //Variável para a velocidade do inimigo
	public int right = 1, up = 0, down = 0, left = 0; //Variáveis para a direção dos movimentos do inimigo
	
	public int curAnimation = 0; //Variável que guarda o índice do sprite da animação atual; curAnimation significa "current animation"

	public static List<Bullet> bullets = new ArrayList<Bullet>(); //Instanciamento da classe Bullet a colocando dentro de uma lista
	//A lista é estática, ou seja, está associada à classe e não a uma instância específica dessa classe
	
	public boolean shoot = false; //Variável que diz se o personagem está atirando ou não
	
	public int curFrames = 0, targetFrames = 10; //Variáveis que guardam números de frames para o funcionamento das animações
	//A variável curFrames significa "current frames", e guarda o número do frame atual que está sendo renderizado
	//A variável targetFrames é o número alvo de frames a ser atingido, para que, assim, o sprite de uma animação mude
	//Deve-se pensar que, se o jogo está sendo executado a 60 frames por segundo, a cada segundo temos 60 frames renderizados
	//Para que haja a mudança de um sprite para outro, curFrames será aumentada até que atinja a targetFrames 
	//Quanto maior targetFrames for, mais tempo levará para a mudança de sprites
	//E já que 60 frames totalizam 1 segundo, 30 frames é o mesmo que 0,5 segundo, 15 frames, 0,25 segundo, e 10 frames, 0,1666 segundo aproximadamente
	
	public String lastInput; //Variável que guarda a última tecla que movimentou o personagem
	//Logo, também guarda o último sentido de sua movimentação
	public boolean noKeyPressed; //Variável que diz se já houve alguma tecla pressionada ou não
	Player p = Game.player; //Declaração da variável p a partir da classe de jogador
	
	public boolean movedUP; //Variável que diz se o personagem está se movendo para cima ou não
	public boolean movedDOWN; //Variável que diz se o personagem está se movendo para baixo ou não
	public boolean movedLEFT; //Variável que diz se o personagem está se movendo para a esquerda ou não
	public boolean movedRIGHT; //Variável que diz se o personagem está se movendo para a direita ou não
	
	//Método construtor
	public Enemy(int x, int y) { //O método contrutor possui as coordenadas do inimigo como parâmetros 
		super(x, y, 32, 32); //O super é responsável por chamar o construtor da superclasse / classe mãe, ou seja, a classe Rectangle
		//Chamando a classe Rectangle, os parâmetros passam a ser as coordenadas e as dimensões de um retângulo
	}

	
	//Neste método está a lógica para a inteligência artificial dos inimigos
	public void perseguirPlayer() {
		//Se a coordenada x do inimigo for menor que a coordenada x do player e o caminho estiver livre, o inimigo se moverá para a direita
		if(x < p.x && World.isFree(x + spd, y)) {
			//Antes, é sorteado um número de 0 a 99, e caso este seja menor que 75, o inimigo então se moverá para a direita
			if(new Random().nextInt(100) < 75) { //Originalmente, o número seria deveria ser menor que 50
				//Porém, após alguns testes com outros valores, foi possível notar que quanto menor o número, mais truncada era a movimentação do inimigo
				//E mais flicks aconteciam nas suas animações
				x += spd;
				movedRIGHT = true;
			}
		//Se a coordenada x do inimigo for maior que a coordenada x do player e o caminho estiver livre, o inimigo se moverá para a direita
		}else if(x > p.x && World.isFree(x - spd, y)) {
			//Antes, é sorteado um número de 0 a 99, e caso este seja menor que 75, o inimigo então se moverá para a esquerda
			if(new Random().nextInt(100) < 75) { //Originalmente, o número seria deveria ser menor que 50
				//Porém, após alguns testes com outros valores, foi possível notar que quanto menor o número, mais truncada era a movimentação do inimigo
				//E mais flicks aconteciam nas suas animações
				x -= spd;
				movedLEFT = true;
			}
		}
		//Se a coordenada y do inimigo for menor que a coordenada y do player e o caminho estiver livre, o inimigo se moverá para baixo
		if(y < p.y && World.isFree(x, y + spd)) {
			//Antes, é sorteado um número de 0 a 99, e caso este seja menor que 75, o inimigo então se moverá para baixo
			if(new Random().nextInt(100) < 75) { //Originalmente, o número seria deveria ser menor que 50
				//Porém, após alguns testes com outros valores, foi possível notar que quanto menor o número, mais truncada era a movimentação do inimigo
				//E mais flicks aconteciam nas suas animações
				y += spd;
				movedDOWN = true;
			}
		//Se a coordenada y do inimigo for maior que a coordenada y do player e o caminho estiver livre, o inimigo se moverá para cima
		}else if(y > p.y && World.isFree(x, y - spd)) {
			//Antes, é sorteado um número de 0 a 99, e caso este seja menor que 75, o inimigo então se moverá para cima
			if(new Random().nextInt(100) < 75) { //Originalmente, o número seria deveria ser menor que 50
				//Porém, após alguns testes com outros valores, foi possível notar que quanto menor o número, mais truncada era a movimentação do inimigo
				//E mais flicks aconteciam nas suas animações
				y -= spd;
				movedUP = true;
			}
		}
		
		//Se as coordenadas x e y do inimigo forem iguais às coordenadas x e y do player, o inimigo estará parado assim como suas animações
		if(x == p.x && y == p.y) {
			movedRIGHT = false;
			movedLEFT = false;
			movedDOWN = false;
			movedUP = false;
		}
		
		/*É IMPORTANTE NOTAR QUE AS ANIMAÇÕES NÃO SE ADAPTARAM BEM AO SISTEMA DE RANDOMIZAÇÃO DAS DIREÇÕES DO INIMIGO*/
		/*No geral, os flicks são muito presentes e não respeitam muito bem as trocas de sentido e direção na movimentação dos inimigos*/
		
	}
	
	public void tick() {
		
		//A todo tick, as variáveis que indicam se o personagem está se movendo serão dadas como falsas, a não ser que haja um input
		movedUP = false;
		movedDOWN = false;
		movedLEFT = false;
		movedRIGHT = false;
		
		//Chamando o método que determina a perseguição ao player para ser executado em todos os ticks
		perseguirPlayer();
		
		/*LÓGICA DA IA DO INIMIGO SEM A ALEATORIEDADE DE SUA MOVIMENTAÇÃO E SEM A PERSEGUIÇÃO AO PLAYER*/
		//Caso a variável right seja true, a variável spd será somada a coordenada x
		/*if(right == 1 && World.isFree(x + spd, y)) { //Além do input, é verificado se o caminho está livre e se o player já está indo para a direita
			x += spd; //O equivalente a +=, neste caso, seria x = x + spd
			movedRIGHT = true;
		}
		
		//Caso a variável right seja false, mas a variável left, true, a variável spd será subtraída da coordenada x
		else if(left == 1 && World.isFree(x - spd, y)) { //Além do input, é verificado se o caminho está livre e se o player já está indo para a esquerda
			x -= spd; //O equivalente a -=, neste caso, seria x = x - spd
			movedLEFT = true;
		}
		
		//Caso a variável up seja true, a variável spd será subtraída da coordenada y
		if(up == 1 && World.isFree(x, y - spd)) { //Além do input, é verificado se o caminho está livre e se o player já está indo para cima
			y -= spd; //O equivalente a -=, neste caso, seria y = y - spd
			movedUP = true; //Caso o input recebido seja para cima e o caminho esteja livre, a variável movedUP será true
		}
		
		//Caso a variável up seja false, mas a variável down, true, a variável spd será somada a coordenada y
		else if(down == 1 && World.isFree(x, y + spd)) { //Além do input, é verificado se o caminho está livre e se o player já está indo para baixo
			y += spd; //O equivalente a +=, neste caso, seria y = y + spd
			movedDOWN = true; //Caso o input recebido seja para baixo e o caminho esteja livre, a variável movedDOWN será true
		}*/
			
		//Caso alguma das variáveis de movimento sejam verdadeiras, a lógica para o funcionamento das animações começará
		if(movedDOWN || movedUP || movedLEFT || movedRIGHT) {
			curFrames++; //A cada tick é somado 1 na variável curFrames
			if(curFrames == targetFrames) { //Se curFrames for igual a targetFrames, as animações serão executadas efetivamente
				curFrames = 0; //Se curFrames for igual a targetFrames, ela será zerada
				curAnimation++; //Se curFrames for igual a targetFrames, curAnimation será somada em 1
				//Se curAnimation for igual ao tamanho dos arrays que guardam os sprites do player, curAnimation será zerada
				if(curAnimation == Spritesheet.enemy_front.length
					|| curAnimation == Spritesheet.enemy_back.length
					|| curAnimation == Spritesheet.enemy_leftProfile.length
					|| curAnimation == Spritesheet.enemy_rightProfile.length) {
					//Todos os arrays, no caso das animações de movimentos básicos do personagem, possuem apendas 2 elementos
					curAnimation = 0;
				}
			}
		}
		
		//Se a variável shoot for verdadeira a lógica para adicionar um novo projétil na ArrayList começará
		if(shoot) {
			shoot = false; //Logo após a variável se tornar verdadeira, ela é dada como falsa
			if(lastInput == "DOWN") { //Se o último input tiver sido para baixo, o sentido será positivo, e a direção será vertical
				bullets.add(new Bullet(x, y, 1, true, false));
			}
			else if(lastInput == "RIGHT") { //Se o último input tiver sido para a direita, o sentido será positivo, e a direção será horizontal
				bullets.add(new Bullet(x, y, 1, false, true));
			}
			else if(lastInput == "UP") { //Se o último input tiver sido para cima, o sentido será negativo, e a direção será vertical
				bullets.add(new Bullet(x, y, -1, true, false));
			}
			else if(lastInput == "LEFT") { //Se o último input tiver sido para a esquerda, o sentido será negativo, e a direção será horizontal
				bullets.add(new Bullet(x, y, -1, false, true));
			}

		}
		
		//Aqui, é chamado o método tick para o projétil que for atirado
		for(int i = 0; i < bullets.size(); i++) { //É importante notar que o loop soma em 1 a variável i até que ela seja menor que a própria lista
			bullets.get(i).tick(); 
		}
		
	}
	
	public void render(Graphics g) { //O parâmetro do método acaba sendo a variável g que recebe o instanciamento da classe Graphics

		/*Observação: no módulo Mini Zelda, não são feitas as animações em que Link anda para os lados para cima, mas apenas para baixo*/
		
		if(movedDOWN) {
			g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);//Método usado para desenhar a imagem do player
			//Tem como parâmetro a imagem da variável player_front, suas coordenadas, seu tamanho (que pode ser redimensionado, caso preciso)
			//E, por fim, a interface ImageObserver, que possui métodos para monitorar o carregamento de uma imagem
			//Neste caso, o último é nulo
			//A variável player_front é uma lista, então recebe a variável curAnimation, que determina qual sprite da lista será renderizado
		}
		else if(movedUP) {
			g.drawImage(Spritesheet.enemy_back[curAnimation], x, y, 32, 32, null);
		}
		else if(movedLEFT) {
			g.drawImage(Spritesheet.enemy_leftProfile[curAnimation], x, y, 32, 32, null);
		}
		else if(movedRIGHT) {
			g.drawImage(Spritesheet.enemy_rightProfile[curAnimation], x, y, 32, 32, null);
		}
		//A partir daqui, as condições determinam qual sprite será renderizado após o jogador soltar a tecla que estava pressionando por último
		//No caso do inimigo, está sendo pego o último input do player a partir da variável p
		else if(movedDOWN == false && p.lastInput == "DOWN") {
			g.drawImage(Spritesheet.enemy_front[0], x, y, 32, 32, null);
		}
		else if(movedUP == false && p.lastInput == "UP") {
			g.drawImage(Spritesheet.enemy_back[0], x, y, 32, 32, null);
		}
		else if(movedLEFT == false && p.lastInput == "LEFT") {
			g.drawImage(Spritesheet.enemy_leftProfile[0], x, y, 32, 32, null);
		}
		else if(movedRIGHT == false && p.lastInput == "RIGHT") {
			g.drawImage(Spritesheet.enemy_rightProfile[0], x, y, 32, 32, null);
		}
		/*Esta última condição determina que, caso o jogo tenha acabado de começar, nenhuma tecla tenha sido pressionada
		e o personagem não tenha sido movimentado, o primeiro sprite a ser renderizado será o primeiro da pose de frente*/
		/*else if(movedDOWN == false && movedUP == false && movedLEFT == false && movedRIGHT == false && noKeyPressed) {
			g.drawImage(Spritesheet.enemy_front[0], x, y, 32, 32, null);
		}*/
		
		//Aqui, é chamado o método render para o projétil que for atirado
		for(int i = 0; i < bullets.size(); i++) { //É importante notar que o loop soma em 1 a variável i até que ela seja menor que a própria lista
			bullets.get(i).render(g);
		}

	}

	
}
