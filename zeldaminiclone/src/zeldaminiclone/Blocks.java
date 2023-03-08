package zeldaminiclone;

import java.awt.Color; //Importação da classe Color da biblioteca do java
import java.awt.Graphics; //Importação da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importação da classe Rectangle da biblioteca do java

//Com esta classe, os blocos que compõem o mundo tem suas propriedades determinadas
public class Blocks extends Rectangle{ //Assim como o player, herda as propriedades, colisões e os vetores de um retângulo
	
	//Método construtor
	public Blocks(int x, int y) { //O método contrutor possui as coordenadas do bloco como parâmetros
		super(x, y, 32, 32); //O super é responsável por chamar o construtor da superclasse / classe mãe, ou seja, a classe Rectangle
	}
	
	public void render(Graphics g) { //O parâmetro do método acaba sendo a variável g que recebe o instanciamento da classe Graphics
		/*
		//Setando a cor e o retângulo que forma um bloco
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		//Setando a cor e o contorno de um retângulo que forma a borda do bloco
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		*/
		
		//Para a renderização dos blocos, o método drawImage chama a variável tileWall
		g.drawImage(Spritesheet.tileWall, x, y, 32, 32, null);
		
	}
	
}
