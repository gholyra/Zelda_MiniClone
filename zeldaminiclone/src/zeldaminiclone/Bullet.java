package zeldaminiclone;

import java.awt.Color; //Importa��o da classe Color da biblioteca do java
import java.awt.Graphics; //Importa��o da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importa��o da classe Rectangle da biblioteca do java

public class Bullet extends Rectangle { //Herda as propriedades, colis�es e os vetores de um ret�ngulo

	public boolean vert, horzt; //Vari�veis que indicam a dire��o do jogador e, consequentemente, do proj�til
	public int sent = 1; //Vari�vel de sentido do proj�til, por padr�o deixada com o valor 1, dizendo que ele � positivo
	public int speed = 5; //Vari�vel da velocidade do proj�til, cujo valor atual, para o jogo deste caso a 60 fps, funciona bem
	
	public int frames = 0; //Vari�vel que indica os frames que est�o sendo renderizados no momento 
	
	//M�todo construtor
	public Bullet(int x, int y, int sent, boolean vert, boolean horzt) { //O m�todo construtor possui as coordenadas das balas e a dire��o delas como par�metros
		super(x + 16, y + 16, 32, 32); //O super � respons�vel por chamar o construtor da superclasse / classe m�e, ou seja, a classe Rectangle
		this.sent = sent; //Referencia a vari�vel da classe dizendo que o sent antes setado deve receber a vari�vel sent da inst�ncia atual m�todo construtor
		this.vert = vert; //Referencia a inst�ncia atual do m�todo construtor dizendo que o vert atual deve receber a vari�vel vert antes setada
		this.horzt = horzt; //Referencia a inst�ncia atual do m�todo construtor dizendo que o horzt atual deve receber a vari�vel horzt antes setada
	}
	
	public void tick() {
		if(horzt) { //Se a dire��o do jogador for horizontal, a l�gica de movimenta��o do proj�til se dar� no eixo x
			x += speed * sent; //O sentido multiplicar� a velocidade e determinar� se o proj�til ir� para o lado esquerdo ou direito
			//Ap�s isso, o proj�til se mover� a partir da soma x = x + (speed *sent)
		}
		
		else if(vert) { //Se a dire��o do jogador for horizontal, a l�gica de movimenta��o do proj�til se dar� no eixo y
			y += speed * sent; //O sentido multiplicar� a velocidade e determinar� se o proj�til ir� para cima ou baixo
			//Ap�s isso, o proj�til se mover� a partir da soma y = y + (speed *sent)
		}
		
		//A cada frame a vari�vel frames ser� somada em 1
		frames++;
		if(frames == 60) { //Se frames chegar a 60, ou seja, ap�s completar 1 segundo que o proj�til foi atirado, o mesmo ser� apagado
			Player.bullets.remove(this);
		}
		
	}
	
	public void render(Graphics g) {
		//Se o sentido for positivo e a dire��o for horizontal, o sprite renderizado ser� o da flecha apontada para a direita
		if(sent == 1 && vert == false && horzt == true) {
			g.drawImage(Spritesheet.arrow_rightwards, x, y, 32, 10, null); //
		}
		//Se o sentido for negativo e a dire��o for horizontal, o sprite renderizado ser� o da flecha apontada para a esquerda
		else if(sent == -1 && vert == false && horzt == true) {
			g.drawImage(Spritesheet.arrow_leftwards, x, y, 32, 10, null);
		}
		//Se o sentido for positivo e a dire��o for vertical, o sprite renderizado ser� o da flecha apontada para baixo
		else if(sent == 1 && vert == true && horzt == false) {
			g.drawImage(Spritesheet.arrow_downwards, x, y, 10, 32, null);
		}
		//Se o sentido for negativo e a dire��o for vertical, o sprite renderizado ser� o da flecha apontada para cima
		else if(sent == -1 && vert == true && horzt == false) {
			g.drawImage(Spritesheet.arrow_upwards, x, y, 10, 32, null);
		}
	}
	
}
