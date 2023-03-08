package zeldaminiclone;

import java.awt.image.BufferedImage; //Importa��o da subclasse BufferedImage da biblioteca do java
import java.io.IOException; //Importa��o da classe IOException da biblioteca do java

import javax.imageio.ImageIO; //Importa��o da classe ImageIO da biblioteca do java

public class Spritesheet { //Classe que cont�m toda estrutura de armazenamento, leitura e sele��o dos sprites da spritesheet

	/*public static List<Spritesheet> spritesheets = new ArrayList<Spritesheet>();*/
	
	//Declara��o da vari�vel spritesheet a partir da subclasse BufferedImage
	public static BufferedImage char_spritesheet;
	//Tal classe � uma implementa��o da interface Image 
	//A qual corresponde a imagens representadas por uma sequ�ncia de pixels armazenada inteiramente na mem�ria
	
	//Declara��o da vari�vel scene_spritesheet a partir da subclasse BufferedImage
	public static BufferedImage scene_spritesheet;
	
	//Declara��o das vari�veis player_front, player_back, player_leftProfile e player_RightProfile a partir da subclasse BufferedImage
	public static BufferedImage[] player_front, player_back, player_leftProfile, player_rightProfile;
	//S�o do tipo BufferedImage e tamb�m uma lista, podendo receber uma lista de imagens a serem armazenadas
	//Cada uma guarda uma respectiva pose do personagem
	
	//Declara��o das vari�veis player_front, player_back, player_leftProfile e player_RightProfile a partir da subclasse BufferedImage
	public static BufferedImage[] enemy_front, enemy_back, enemy_leftProfile, enemy_rightProfile;
	
	//Declara��o das vari�veis arrow_downwards, arrow_upwards, arrow_rightwards, arrow_leftwards a partir da subclasse BufferedImage
	public static BufferedImage arrow_downwards, arrow_upwards, arrow_rightwards, arrow_leftwards;
	
	//Declara��o da vari�vel tileWall a partir da subclasse BufferedImage
	public static BufferedImage tileWall;
	
	//Declara��o da vari�vel arrow a partir da subclasse BufferedImage
	public static BufferedImage arrow;
	
	//M�todo construtor da classe Spritesheet, que conter� a passagem do arquivo da spritesheet para a vari�vel spritesheet
	public Spritesheet() {
		try {
			//A vari�vel spritesheet recebe o arquivo char_spritesheet.png, a partir da chamada da classe ImageIO
			//Tal classe � usada para ler, editar e salvar imagens, e aqui, com o m�todo read, a imagem ser� decodificada
			/*O m�todo getClass retorna uma classe do tipo Class<?>, sendo, neste caso, Class<? extends Spritesheet>
			Este m�todo busca a classe do objeto durante o seu tempo de execu��o, j� que ela pode mudar e ser acessada de tempos em tempos*/
			//O m�todo getResource buscar� um recurso (resource / URL), sendo, neste caso, o arquivo PNG da spritesheet
			char_spritesheet = ImageIO.read(getClass().getResource("/char_spritesheet.png")); //Spritesheet prim�ria para o personagem
			scene_spritesheet = ImageIO.read(getClass().getResource("/scene_spritesheet.png")); //Spritesheet prim�ria para o cen�rio
		} catch (IOException e) { //A classe IOException � utilizada ao ocorrer um erro com opera��es do tipo I/O
			//� importante a utiliza��o de um try / catch devido � possibilidade de o diret�rio estar errado
			//Ou de ainda o arquivo necess�rio n�o existir, por exemplo
			e.printStackTrace();
		}
		
		//As vari�veis das poses do personagem recebem o instanciamento da classe BufferedImage, sendo para todas elas, uma lista de dois elementos
		player_front = new BufferedImage[2];
		player_back = new BufferedImage[2];
		player_leftProfile = new BufferedImage[2];
		player_rightProfile = new BufferedImage[2];
		
		enemy_front = new BufferedImage[2];
		enemy_back = new BufferedImage[2];
		enemy_leftProfile = new BufferedImage[2];
		enemy_rightProfile = new BufferedImage[2];
		
		//A vari�vel player_front recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose da frente do personagem � composta de dois sprites
		player_front[0] = Spritesheet.getCharSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getCharSprite(16, 11, 16, 16);
		
		//A vari�vel player_back recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose das costas do personagem � composta de dois sprites
		player_back[0] = Spritesheet.getCharSprite(69, 11, 16, 16);
		player_back[1] = Spritesheet.getCharSprite(86, 11, 16, 16);
		
		//A vari�vel player_leftProfile recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose do perfil esquerdo do personagem � composta de dois sprites
		player_leftProfile[0] = Spritesheet.getCharSprite(103, 11, 16, 16);
		player_leftProfile[1] = Spritesheet.getCharSprite(119, 11, 16, 16);
		
		//A vari�vel player_rightProfile recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose do perfil direito do personagem � composta de dois sprites
		player_rightProfile[0] = Spritesheet.getCharSprite(34, 11, 16, 16);
		player_rightProfile[1] = Spritesheet.getCharSprite(50, 11, 16, 16);
		
		//A vari�vel player_front recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose da frente do personagem � composta de dois sprites
		enemy_front[0] = Spritesheet.getCharSprite(133, 209, 16, 16);
		enemy_front[1] = Spritesheet.getCharSprite(149, 209, 16, 16);
		
		//A vari�vel player_back recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose das costas do personagem � composta de dois sprites
		enemy_back[0] = Spritesheet.getCharSprite(202, 209, 16, 16);
		enemy_back[1] = Spritesheet.getCharSprite(219, 209, 16, 16);
		
		//A vari�vel player_leftProfile recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose do perfil esquerdo do personagem � composta de dois sprites
		enemy_leftProfile[0] = Spritesheet.getCharSprite(236, 209, 16, 16);
		enemy_leftProfile[1] = Spritesheet.getCharSprite(252, 209, 16, 16);
		
		//A vari�vel player_rightProfile recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		//A pose do perfil direito do personagem � composta de dois sprites
		enemy_rightProfile[0] = Spritesheet.getCharSprite(167, 209, 16, 16);
		enemy_rightProfile[1] = Spritesheet.getCharSprite(183, 209, 16, 16);
		
		//A vari�vel tileWall recebe o sprite selecionado da scene_spritesheet a partir do m�todo getSceneSprite
		tileWall = Spritesheet.getSceneSprite(35, 171, 16, 16);
		
		//A vari�vel arrow_rightwards recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		arrow_rightwards = Spritesheet.getCharSprite(10, 185, 16, 5);
		
		//A vari�vel arrow_upwards recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		arrow_upwards = Spritesheet.getCharSprite(3, 185, 5, 16);
		
		//A vari�vel arrow_leftwards recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		arrow_leftwards = Spritesheet.getCharSprite(10, 191, 16, 5);
		
		//A vari�vel arrow_downwards recebe os sprites selecionados da char_spritesheet a partir do m�todo getCharSprite
		arrow_downwards = Spritesheet.getCharSprite(31, 185, 5, 16);
	}
	
	//M�todo usado para selecionar um sprite espec�fico da char_spritesheet, dando suas coordenadas e suas dimens�es como par�metros
	public static BufferedImage getCharSprite(int x, int y, int width, int height) {
		//O m�todo retorn�ra uma �rea da spritesheet que pode ser determinada na declara��o da vari�vel
		return char_spritesheet.getSubimage(x, y, width, height);
	}
	//M�todo usado para selecionar um sprite espec�fico da scene_spritesheet, dando suas coordenadas e suas dimens�es como par�metros
	public static BufferedImage getSceneSprite(int x, int y, int width, int height) {
		//O m�todo retorn�ra uma �rea da spritesheet que pode ser determinada na declara��o da vari�vel
		return scene_spritesheet.getSubimage(x, y, width, height);
	}
	
}
