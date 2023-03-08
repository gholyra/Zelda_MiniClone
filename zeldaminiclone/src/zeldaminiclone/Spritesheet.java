package zeldaminiclone;

import java.awt.image.BufferedImage; //Importação da subclasse BufferedImage da biblioteca do java
import java.io.IOException; //Importação da classe IOException da biblioteca do java

import javax.imageio.ImageIO; //Importação da classe ImageIO da biblioteca do java

public class Spritesheet { //Classe que contém toda estrutura de armazenamento, leitura e seleção dos sprites da spritesheet

	/*public static List<Spritesheet> spritesheets = new ArrayList<Spritesheet>();*/
	
	//Declaração da variável spritesheet a partir da subclasse BufferedImage
	public static BufferedImage char_spritesheet;
	//Tal classe é uma implementação da interface Image 
	//A qual corresponde a imagens representadas por uma sequência de pixels armazenada inteiramente na memória
	
	//Declaração da variável scene_spritesheet a partir da subclasse BufferedImage
	public static BufferedImage scene_spritesheet;
	
	//Declaração das variáveis player_front, player_back, player_leftProfile e player_RightProfile a partir da subclasse BufferedImage
	public static BufferedImage[] player_front, player_back, player_leftProfile, player_rightProfile;
	//São do tipo BufferedImage e também uma lista, podendo receber uma lista de imagens a serem armazenadas
	//Cada uma guarda uma respectiva pose do personagem
	
	//Declaração das variáveis player_front, player_back, player_leftProfile e player_RightProfile a partir da subclasse BufferedImage
	public static BufferedImage[] enemy_front, enemy_back, enemy_leftProfile, enemy_rightProfile;
	
	//Declaração das variáveis arrow_downwards, arrow_upwards, arrow_rightwards, arrow_leftwards a partir da subclasse BufferedImage
	public static BufferedImage arrow_downwards, arrow_upwards, arrow_rightwards, arrow_leftwards;
	
	//Declaração da variável tileWall a partir da subclasse BufferedImage
	public static BufferedImage tileWall;
	
	//Declaração da variável arrow a partir da subclasse BufferedImage
	public static BufferedImage arrow;
	
	//Método construtor da classe Spritesheet, que conterá a passagem do arquivo da spritesheet para a variável spritesheet
	public Spritesheet() {
		try {
			//A variável spritesheet recebe o arquivo char_spritesheet.png, a partir da chamada da classe ImageIO
			//Tal classe é usada para ler, editar e salvar imagens, e aqui, com o método read, a imagem será decodificada
			/*O método getClass retorna uma classe do tipo Class<?>, sendo, neste caso, Class<? extends Spritesheet>
			Este método busca a classe do objeto durante o seu tempo de execução, já que ela pode mudar e ser acessada de tempos em tempos*/
			//O método getResource buscará um recurso (resource / URL), sendo, neste caso, o arquivo PNG da spritesheet
			char_spritesheet = ImageIO.read(getClass().getResource("/char_spritesheet.png")); //Spritesheet primária para o personagem
			scene_spritesheet = ImageIO.read(getClass().getResource("/scene_spritesheet.png")); //Spritesheet primária para o cenário
		} catch (IOException e) { //A classe IOException é utilizada ao ocorrer um erro com operações do tipo I/O
			//É importante a utilização de um try / catch devido à possibilidade de o diretório estar errado
			//Ou de ainda o arquivo necessário não existir, por exemplo
			e.printStackTrace();
		}
		
		//As variáveis das poses do personagem recebem o instanciamento da classe BufferedImage, sendo para todas elas, uma lista de dois elementos
		player_front = new BufferedImage[2];
		player_back = new BufferedImage[2];
		player_leftProfile = new BufferedImage[2];
		player_rightProfile = new BufferedImage[2];
		
		enemy_front = new BufferedImage[2];
		enemy_back = new BufferedImage[2];
		enemy_leftProfile = new BufferedImage[2];
		enemy_rightProfile = new BufferedImage[2];
		
		//A variável player_front recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose da frente do personagem é composta de dois sprites
		player_front[0] = Spritesheet.getCharSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getCharSprite(16, 11, 16, 16);
		
		//A variável player_back recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose das costas do personagem é composta de dois sprites
		player_back[0] = Spritesheet.getCharSprite(69, 11, 16, 16);
		player_back[1] = Spritesheet.getCharSprite(86, 11, 16, 16);
		
		//A variável player_leftProfile recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose do perfil esquerdo do personagem é composta de dois sprites
		player_leftProfile[0] = Spritesheet.getCharSprite(103, 11, 16, 16);
		player_leftProfile[1] = Spritesheet.getCharSprite(119, 11, 16, 16);
		
		//A variável player_rightProfile recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose do perfil direito do personagem é composta de dois sprites
		player_rightProfile[0] = Spritesheet.getCharSprite(34, 11, 16, 16);
		player_rightProfile[1] = Spritesheet.getCharSprite(50, 11, 16, 16);
		
		//A variável player_front recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose da frente do personagem é composta de dois sprites
		enemy_front[0] = Spritesheet.getCharSprite(133, 209, 16, 16);
		enemy_front[1] = Spritesheet.getCharSprite(149, 209, 16, 16);
		
		//A variável player_back recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose das costas do personagem é composta de dois sprites
		enemy_back[0] = Spritesheet.getCharSprite(202, 209, 16, 16);
		enemy_back[1] = Spritesheet.getCharSprite(219, 209, 16, 16);
		
		//A variável player_leftProfile recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose do perfil esquerdo do personagem é composta de dois sprites
		enemy_leftProfile[0] = Spritesheet.getCharSprite(236, 209, 16, 16);
		enemy_leftProfile[1] = Spritesheet.getCharSprite(252, 209, 16, 16);
		
		//A variável player_rightProfile recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		//A pose do perfil direito do personagem é composta de dois sprites
		enemy_rightProfile[0] = Spritesheet.getCharSprite(167, 209, 16, 16);
		enemy_rightProfile[1] = Spritesheet.getCharSprite(183, 209, 16, 16);
		
		//A variável tileWall recebe o sprite selecionado da scene_spritesheet a partir do método getSceneSprite
		tileWall = Spritesheet.getSceneSprite(35, 171, 16, 16);
		
		//A variável arrow_rightwards recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		arrow_rightwards = Spritesheet.getCharSprite(10, 185, 16, 5);
		
		//A variável arrow_upwards recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		arrow_upwards = Spritesheet.getCharSprite(3, 185, 5, 16);
		
		//A variável arrow_leftwards recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		arrow_leftwards = Spritesheet.getCharSprite(10, 191, 16, 5);
		
		//A variável arrow_downwards recebe os sprites selecionados da char_spritesheet a partir do método getCharSprite
		arrow_downwards = Spritesheet.getCharSprite(31, 185, 5, 16);
	}
	
	//Método usado para selecionar um sprite específico da char_spritesheet, dando suas coordenadas e suas dimensões como parâmetros
	public static BufferedImage getCharSprite(int x, int y, int width, int height) {
		//O método retornára uma área da spritesheet que pode ser determinada na declaração da variável
		return char_spritesheet.getSubimage(x, y, width, height);
	}
	//Método usado para selecionar um sprite específico da scene_spritesheet, dando suas coordenadas e suas dimensões como parâmetros
	public static BufferedImage getSceneSprite(int x, int y, int width, int height) {
		//O método retornára uma área da spritesheet que pode ser determinada na declaração da variável
		return scene_spritesheet.getSubimage(x, y, width, height);
	}
	
}
