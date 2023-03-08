package zeldaminiclone; //Este é um bom modelo para o desenvolvimento de qualquer jogo em java

import java.awt.Canvas; //Importação da classe Canvas da biblioteca do java
import java.awt.Color; //Importa��o da classe Color da biblioteca do java
import java.awt.Dimension; //Importa��o da classe Dimension da biblioteca do java
import java.awt.Graphics; //Importa��o da classe Graphics da biblioteca do java
import java.awt.event.KeyEvent; //Importa��o da classe KeyEvent da biblioteca do java
import java.awt.event.KeyListener; //Importa��o da classe KeyListener da biblioteca do java
import java.awt.image.BufferStrategy; //Importa��o da classeBufferStrategy da biblioteca do java
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame; //Importa��o do JFrame da biblioteca do java

//Herda o Canvas para a cria��o de uma tela e implementa m�todos para a execu��o do game a partir da classe Runnable
public class Game extends Canvas implements Runnable, KeyListener { //Tamb�m implementa m�todos da classe KeyListener para recebimento de inputs

	//Vari�veis que podem ser acessadas de qualquer lugar e que definem as dimens�es base da tela
	public static int WIDTH = 480, HEIGTH = 480;
	public static int SCALE = 1; //Vari�vel que pode ser usada para multiplicar as dimens�es base da tela
	public static Player player; //Declara��o da vari�vel player a partir da classe de jogador
	
	public World world; //Declara��o da vari�vel world a partir da classe de mundo
	public List<Enemy> enemies = new ArrayList<Enemy>(); //Instanciamento da classe Enemy a colocando dentro de uma lista
	
	//M�todo construtor
	public Game() {
		//Adiciona eventos de teclado, tendo como par�metro a pr�pria classe
		this.addKeyListener(this);
		
		//Set das dimens�es da tela a partir das constantes j� setadas
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE)); //Dimension � uma classe com dois par�metros: altura e largura
		//O this funciona como uma forma de referenciar a inst�ncia atual do m�todo no qual � utilizado
		
		//Chama o m�todo construtor da classe Spritesheet
		new Spritesheet();
		
		/*� importante notar que, quando mudei a posi��o do player para que come�asse no centro da tela, as colis�es tamb�m mudaram*/
		
		world = new World(); //Instanciamento da classe de mundo
		
		player = new Player(32, 32); //Instanciamento da classe de jogador dando suas coordenadas
	
		enemies.add(new Enemy(96, 32)); //Instanciamento da classe de inimigo dando suas coordenadas
		//No caso dos inimigos, por serem um array, devem ser instanciados com o m�todo .add
	}

	//M�todo respons�vel pela l�gica do jogo e que ser� atualizado a cada segundo, composto por diversos ticks
	public void tick() {
		
		player.tick(); //Chamando o m�todo tick da classe de jogador
		
		for(int i = 0; i < enemies.size(); i++) { //Para cada um dos inimigos adicionados a vari�vel i ser� somada em 1 at� que ela seja menor que a pr�pria lista de inimigos
			enemies.get(i).tick(); //Chamando o m�todo tick da classe de inimigo para cada um dos inimigos adicionados
		}
	}
	
	//M�todo respons�vel por renderizar os gr�ficos do jogo
	public void render() {
		//Instanciamento da classe BufferStrategy, usada para organizar mem�ria complexa em um Canvas ou uma Window
		BufferStrategy bs = this.getBufferStrategy();
		
		//Se o valor de bs for nulo, o valor ser� substu�do por 3
		if(bs == null) {
			this.createBufferStrategy(3); //O valor ser� utilizado para multiplicar as dimens�es base da tela
			return;
		}
		//Instanciamento da classe Graphics, usada como um objeto que faz desenho em imagens
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK); //Seta a cor do elemento gr�fico que ser� criado
		g.fillRect(0, 0, WIDTH*SCALE, HEIGTH*SCALE); //Cria um ret�ngulo na tela de acordo com as coordenadas e dimens�es inseridas como par�metros
		
		g.setColor(new Color(252, 216, 168)); //Seta a cor do elemento gr�fico que ser� criado
		g.fillRect(0, 0, WIDTH, HEIGTH); //Cria um ret�ngulo na tela de acordo com as coordenadas e dimens�es inseridas como par�metros
		
		player.render(g); //Chamando o m�todo render da classe de jogador, tendo a vari�vel g como par�metro
		
		for(int i = 0; i < enemies.size(); i++) { //Para cada um dos inimigos adicionados a vari�vel i ser� somada em 1 at� que ela seja menor que a pr�pria lista de inimigos
			enemies.get(i).render(g); //Chamando o m�todo render da classe de inimigo para cada um dos inimigos adicionados
		}
		
		world.render(g); //Chamando o m�todo render da classe de mundo, tendo a vari�vel g como par�metro

		bs.show(); //Mostra o que tem a ser renderizado na tela
	}
	
	//M�todo principal
	public static void main (String[] args) {
		Game game = new Game(); //Instanciamento da classe Game
		JFrame frame = new JFrame(); //Instaciamento da classe JFrame para a cria��o de uma janela
		
		/*A ORDEM DAS AÇÕES COM O FRAME DEVE SER IGUAL A ESSA*/
		frame.add(game); //Adicionando o jogo na tela
		frame.setTitle("Mini Zelda"); //Determinando o t�tulo que aparecer� na janela
		frame.pack(); //Empacota tudo que foi feito com o frame e calcula o tamanho certo da janela
		frame.setLocationRelativeTo(null); //Com o par�metro nulo, a janela estar� centralizada
		frame.setResizable(false); //N�o permite o redimensionamento da janela do game
		
		//Garante que, ao fechar o JFrame, o processo do java tamb�m seja fechado, sem ocupar mais memória do usuário
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true); //Faz com que a janela fique visível
		
		new Thread(game).start(); //Método que iniciar� o game loop
		
		}
	

	//Método implementado que executará o jogo
	@Override
	public void run() {
		
		//Loop infinito que todo jogo tem, no qual todas as a��es que mant�m o seu funcionamento s�o executadas
		while(true) { //Por ser um jogo criado do zero, h� um controle sobre os loops
			tick();
			render();
			
			//O programa tentará executar e renderizar o jogo em 60 fps, se não conseguir ele informará um erro
			try {
				Thread.sleep(1000/60); //Milisegundos é a unidade de medida do numerador, e 1000, neste caso, é igual a 1 segundo
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	/*Os métodos para os eventos do teclado foram criados aqui, enquanto as ações do player foram mantidas dentro de sua própria classe*/
	
	//O método keyTyped não é utilizado neste jogo
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//O m�todo keyPressed guarda toda a lógica que é executada quando uma tecla é pressionada
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
	}
	
	//O m�todo keyReleased guarda toda a l�gica que � executada quando uma tecla � solta
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
			player.lastInput = "RIGHT";
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
			player.lastInput = "LEFT";
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
			player.lastInput = "UP";
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
			player.lastInput = "DOWN";
		}
		
	}
	
}
