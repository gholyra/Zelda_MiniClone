package zeldaminiclone;

import java.awt.Color; //Importação da classe Color da biblioteca do java
import java.awt.Graphics; //Importação da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importação da classe Rectangle da biblioteca do java

public class Bullet extends Rectangle { //Herda as propriedades, colisões e os vetores de um retângulo

	public boolean vert, horzt; //Variáveis que indicam a direção do jogador e, consequentemente, do projétil
	public int sent = 1; //Variável de sentido do projétil, por padrão deixada com o valor 1, dizendo que ele é positivo
	public int speed = 5; //Variável da velocidade do projétil, cujo valor atual, para o jogo deste caso a 60 fps, funciona bem
	
	public int frames = 0; //Variável que indica os frames que estão sendo renderizados no momento 
	
	//Método construtor
	public Bullet(int x, int y, int sent, boolean vert, boolean horzt) { //O método construtor possui as coordenadas das balas e a direção delas como parâmetros
		super(x + 16, y + 16, 32, 32); //O super é responsável por chamar o construtor da superclasse / classe mãe, ou seja, a classe Rectangle
		this.sent = sent; //Referencia a variável da classe dizendo que o sent antes setado deve receber a variável sent da instância atual método construtor
		this.vert = vert; //Referencia a instância atual do método construtor dizendo que o vert atual deve receber a variável vert antes setada
		this.horzt = horzt; //Referencia a instância atual do método construtor dizendo que o horzt atual deve receber a variável horzt antes setada
	}
	
	public void tick() {
		if(horzt) { //Se a direção do jogador for horizontal, a lógica de movimentação do projétil se dará no eixo x
			x += speed * sent; //O sentido multiplicará a velocidade e determinará se o projétil irá para o lado esquerdo ou direito
			//Após isso, o projétil se moverá a partir da soma x = x + (speed *sent)
		}
		
		else if(vert) { //Se a direção do jogador for horizontal, a lógica de movimentação do projétil se dará no eixo y
			y += speed * sent; //O sentido multiplicará a velocidade e determinará se o projétil irá para cima ou baixo
			//Após isso, o projétil se moverá a partir da soma y = y + (speed *sent)
		}
		
		//A cada frame a variável frames será somada em 1
		frames++;
		if(frames == 60) { //Se frames chegar a 60, ou seja, após completar 1 segundo que o projétil foi atirado, o mesmo será apagado
			Player.bullets.remove(this);
		}
		
	}
	
	public void render(Graphics g) {
		//Se o sentido for positivo e a direção for horizontal, o sprite renderizado será o da flecha apontada para a direita
		if(sent == 1 && vert == false && horzt == true) {
			g.drawImage(Spritesheet.arrow_rightwards, x, y, 32, 10, null); //
		}
		//Se o sentido for negativo e a direção for horizontal, o sprite renderizado será o da flecha apontada para a esquerda
		else if(sent == -1 && vert == false && horzt == true) {
			g.drawImage(Spritesheet.arrow_leftwards, x, y, 32, 10, null);
		}
		//Se o sentido for positivo e a direção for vertical, o sprite renderizado será o da flecha apontada para baixo
		else if(sent == 1 && vert == true && horzt == false) {
			g.drawImage(Spritesheet.arrow_downwards, x, y, 10, 32, null);
		}
		//Se o sentido for negativo e a direção for vertical, o sprite renderizado será o da flecha apontada para cima
		else if(sent == -1 && vert == true && horzt == false) {
			g.drawImage(Spritesheet.arrow_upwards, x, y, 10, 32, null);
		}
	}
	
}
