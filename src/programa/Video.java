package programa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Video {
	
	//Variables de la Clase
	private String nameVideo;
	private String dateCreation;
	private String nameCreator;
	private int likes;
	private int dislikes; //Extra, también puedes dar dislike
	private static ArrayList<Comentario> comentariosYT;
	
	//Builder de Video.
	public Video(String nameVideo, String nameCreator){
		setnameVideo(nameVideo);
		setdateCreation();
		setNameCreator(nameCreator);
		setlikes(0);
		comentariosYT = new ArrayList<Comentario>();
		
	}
	
	
	//Nombre del Vídeo.
	private void setnameVideo(String nameVideo) {
		this.nameVideo = nameVideo;
	}
	
	
	public String getnameVideo() {
		return nameVideo;
	}
	
		
	//Setter / Getter Fecha de Creación.
	private void setdateCreation() {
		LocalDate dia = LocalDate.now();
		String dateCreation = dia.toString();
		this.dateCreation = dateCreation;
	}
	
	
	public String getdateCreation() {
		return dateCreation;
	}
	
	//Setter / Getter nombre del canal que lo creó.
	private void setNameCreator(String nameCreator) {
		this.nameCreator = nameCreator;
	}
	
	
	public String getNameCreator() {
		return nameCreator;
	}
	
	
	//Setter / Getter Comentarios.
	public static void setcoment(String comentario,String nombreUsuario, String nameYoutuber) {
		Comentario coment;
		coment = new Comentario(nombreUsuario, comentario, nameYoutuber);
		comentariosYT.add(coment);
	}
	
	
	public static ArrayList<Comentario> getcoment() {
		return comentariosYT;
	}
	
	//Setter / Getter Numero de Likes.
	public void setlikes(int likes) {
		this.likes = likes;
	}
	
	
	public int getlikes() {
		return likes;
	}

	//Setter / Getter  Numero de Disikes.
	public void setdislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	
	
	public int getdislikes() {
		return dislikes;
	}
	
	public void videoMenu(Scanner read, int idx, ArrayList<Video> videoYT){
		boolean bucle = true;
		while(bucle) {//Printea el menu del Video Seleccionado
			printMenuSelectedVideo(videoYT,idx);	
			String selccionVideo = read.nextLine();
			switch(selccionVideo){
			case ("1")://Crea un uevo comentario
				Comentario.newComent(read,videoYT,comentariosYT,idx);
				continue;
						
			case ("2")://Actualiza cuantos likes tiene el video.
				updatelikes(idx, videoYT);
				continue;
						
			case ("3")://Muestra los comentarios del video (Es bastante tedioso de comprobar por culpa de los waiting2500()).
				comentsVideo();
				continue;
						
			case ("4")://Muestra las estadisticas del video.
				statsVideo(videoYT);
				continue;
			case ("5")://Actualiza cuantos dislikes tiene el video.
				updatedislikes(idx,videoYT);
				continue;
						
			case ("0"):
				System.out.println();
				System.out.println("...Volviendo al Menu de Canales...");
				System.out.println();
				Youtube.waiting2500();
				bucle = false;
				break;
				
			default:
				System.out.println();
				System.out.println("¡Error, introduce un valor valido!");
				System.out.println();
			}
			break;
		}
	}
	
	//Menu del Video seleccionado.
	public void printMenuSelectedVideo(ArrayList<Video> VideoYT,int idx) {
		System.out.println("|---"+ this.nameVideo +"---|\n"
		+"1- Nuevo Comentario\n"
		+"2- Like\n"
		+"3- Mostrar Comentarios\n"
		+"4- Mostrar Estadisticas\n"
		+"5- Dislike\n"
		+"0- Salir\n"
		+"|-----------------------|\n");
	}
	
	//Crea un video y lo guarda en su ArrayList.
	public static int createVideo(Scanner read, String nameChanel, ArrayList<Video> videoYT) {
		System.out.println("Introduce el nombre del Video");
		String nameVideo = read.nextLine();
		Video video = new Video(nameVideo,nameChanel);
		videoYT.add(video);
		return videoYT.indexOf(video);
	}

	//Menu para seleccionar que video quieres ver.
	public static int selectVideoMenu(int n, String nameChanel, ArrayList<Canal> canalYT, ArrayList<Video> videoYT){
		Scanner read = new Scanner (System.in);
		Video video;
		int idx;
		while(true) {// Si no hay videos en este canal, te manda a crear uno.
			if(videoYT.size() == 0) {
				System.out.println("Actualmente no hay ningun video en este canal");
				System.out.println();
				System.out.println("Se te redireccionara a crear un nuevo video");
				Youtube.waiting2500();
				idx = createVideo(read,nameChanel,videoYT);
			}
			if(comentariosYT.size() == 0) {
				for (int i = 0; i < videoYT.size(); i++) {
					video = videoYT.get(i);
					System.out.println(i + " " + video);
				}
			}else {
				for (int i = 0; i < videoYT.size(); i++) {
					video = videoYT.get(i);
					System.out.println(i + " " + video);
				}
			}idx = read.nextInt();

			if(idx < 0 || idx > 4) {
				System.out.println("¡Error, ese numero de canal no exsiste!");
				continue;
				
			}video = videoYT.get(idx);
			
			if (video.getnameVideo() != null) {
				return idx;
			}
			System.out.println("¡Error, introduce un valor valido!");
		}
	}	
	
	//Actualiza los likes del video.
	public void updatelikes(int idx, ArrayList<Video> videoYT) {
		int likes = this.likes;
		likes ++;
		videoYT.get(idx).setlikes(likes);
		System.out.println();
		System.out.println(ANSI_RED + "Me GUSTA");
		System.out.println(ANSI_RESET);
	}
	
	//Actualiza los dislikes del video.
	public void updatedislikes(int idx, ArrayList<Video> videoYT) {
		int dislikes = this.dislikes;
		dislikes ++;
		videoYT.get(idx).setdislikes(dislikes);
		System.out.println();
		System.out.println(ANSI_BLUE + "NO ME GUSTA");
		System.out.println(ANSI_RESET);
	}
	
	//Printea los comentarios del video.
	public static void comentsVideo() {
		Comentario comentarios;
		for(int i = 0; i <comentariosYT.size();i++) {
			comentarios = comentariosYT.get(i);
			System.out.println(comentarios);
			comentarios.checklikes(comentariosYT.indexOf(comentarios),comentariosYT);
		}
		System.out.println();
		System.out.println("...Volviendo al Menu de Videos...");
		System.out.println();//Si quieres hacer la comprobación de los comentarios te recomiedo eliminar o comentar el waiting2500().
		Youtube.waiting2500();
		
	}
	
	//Muestra las estadisticas del video.
	public static void statsVideo(ArrayList<Video> videoYT) {
		Video video;
			for(int i = 0; i <videoYT.size();i++) {
				video = videoYT.get(i);
				System.out.println(video);		
			}
	}
	
	//Lo mismo que las dos veces anteriores, rellena los videos con comentarios.
	public static void  fillCometsYoutube(ArrayList<Video> videoYT) {
		String [] nameCreator = {"Marcos","Biron","Martin","Aenea",""};
		String [] coment = {"Buen video, sigue así" ,
							"¡Que historia más curiosa!",
							"Este canal se ve muy real",
							"El libro del top 3 no me termina de convencer, pero buen video",
							"Totalmente de acuerdo"};
		
		String [] nameYoutuber = {"Pedro02","ElYoutuberby2002","Canal100%Real","L_MeGustaLeer_L","L_MeGustaLeer_L"};
		for(int i = 0; i< 4;i++) {
			setcoment(nameCreator[i],coment[i],nameYoutuber[i]);
		}
	}
	
	//Calcula el numero de comentarios que tiene un video.
	public int getNumComentsVideo(String nameYoutuber) {
		Comentario comentario;
		int counterComentsYoutuber = 0;
		for(int i = 0; i <comentariosYT.size();i++) {
			comentario = comentariosYT.get(i);
			if(nameYoutuber.compareTo(comentario.getNameYoutuber()) == 0) {
				counterComentsYoutuber++;
			}
		}
		return counterComentsYoutuber;
	}
	
	
	//Color Azul para los Likes.
	public static final String ANSI_BLUE = "\u001B[34m";
	
	//Color Rojo para los Likes.
	public static final String ANSI_RED = "\u001B[31m";
		
	//Color normal.
	public static final String ANSI_RESET = "\u001B[0m";
	
	//To string para que printe las estadisticas
	public String toString(){
		return "--Video: " + this.nameVideo + " en fecha " + this.dateCreation + " con "+  this.likes + " likes, "+ this.dislikes + " dislikes y " + getNumComentsVideo(this.nameCreator) + " comentario/s";
	}
	 

}
