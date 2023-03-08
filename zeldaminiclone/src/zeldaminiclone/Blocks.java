package zeldaminiclone;

import java.awt.Color; //Importa��o da classe Color da biblioteca do java
import java.awt.Graphics; //Importa��o da classe Graphics da biblioteca do java
import java.awt.Rectangle; //Importa��o da classe Rectangle da biblioteca do java

//Com esta classe, os blocos que comp�em o mundo tem suas propriedades determinadas
public class Blocks extends Rectangle{ //Assim como o player, herda as propriedades, colis�es e os vetores de um ret�ngulo
	
	//M�todo construtor
	public Blocks(int x, int y) { //O m�todo contrutor possui as coordenadas do bloco como par�metros
		super(x, y, 32, 32); //O super � respons�vel por chamar o construtor da superclasse / classe m�e, ou seja, a classe Rectangle
	}
	
	public void render(Graphics g) { //O par�metro do m�todo acaba sendo a vari�vel g que recebe o instanciamento da classe Graphics
		/*
		//Setando a cor e o ret�ngulo que forma um bloco
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		//Setando a cor e o contorno de um ret�ngulo que forma a borda do bloco
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		*/
		
		//Para a renderiza��o dos blocos, o m�todo drawImage chama a vari�vel tileWall
		g.drawImage(Spritesheet.tileWall, x, y, 32, 32, null);
		
	}
	
}
