package programa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Canal {

	//Variables de la Clase
	private String nameChanel;
	private String dateCreation;
	private String description;// Extra, cada youtuber tendra una descipción.
	private static ArrayList<Video> videoYT;
	
	//Builder de la clase
	public Canal(String nameChanel,String description){
		setNameChanel(nameChanel);
		setdateCreation();
		setdescription(description);
		videoYT = new ArrayList<Video>();
	}
	
	
	//Setter / Getter Nombre del canal
	private void setNameChanel(String nameChanel){
		this.nameChanel = nameChanel;
	}
	
	
	public String getNameChanel(){
		return nameChanel;
	}
	
	
	//Setter / Getter Fecha Creación
	private void setdateCreation(){
		LocalDate dia = LocalDate.now();
		String dateCreation = dia.toString();
		this.dateCreation = dateCreation;
	}
	
	
	public String getdateCreation(){
		return dateCreation;
	}
	
	
	//Setter / Getter Descripción del canal
	private void setdescription(String description){
		this.description = description;
	}
	
	
	public String getdescription(){
		return description;
	}
	

	//Setter / Getter Videos Publicados (Max 10) 
	public static void setvideoYT(Video video,Canal canal){
        videoYT.add(video);
	
	}
	  

	public static ArrayList<Video> getvideoYT(){
		return videoYT;
	}
	
	
	//Menu del canal seleccionado
	public void selectedChanel( ArrayList<Canal> canalYT, int idx) {
		Scanner read = new Scanner(System.in);
		Canal canal = canalYT.get(idx);
		Video video = null;
		boolean bucle = true;
		int n;
		while(bucle) {//Mostamos el menu de Canal.
			canal.printMenuChanel(canalYT,idx);
			switch(read.nextLine()){
			
			case ("1")://Creamos un video.
				n = Video.createVideo(read,this.nameChanel,videoYT);
				video = videoYT.get(n);
				continue;
				
			case ("2")://Seleccionamos un video.
				n = Video.selectVideoMenu(idx,this.nameChanel,canalYT,videoYT);
				video = videoYT.get(n);//Llamamos a la función de mostrar menu video de la clase video.
				video.videoMenu(read,n,videoYT);
				continue;
					
			case ("3")://Mostramos las estadisticas de un canal.
				canal.stadisticChanel(idx,canalYT);
				continue;
					
			case ("4")://Mostramos las estadisticas de los videos de un canal.
				Video.statsVideo(videoYT);
				continue;
				
			case ("5")://Mostramos la descripción del canal.
				System.out.println(getdescription());
				continue;
				
			case ("0"):
				System.out.println();
				System.out.println("...Volviendo al Menu principal...");
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
	
	//Menu del canal
	public void printMenuChanel(ArrayList<Canal> canalYT,int idx) {
		System.out.println("|---"+ this.nameChanel +"---|\n"
		+"1- Nuevo Video\n"
		+"2- Seleccionar Video\n"
		+"3- Mostrar Estadisticas\n"
		+"4- Mostrar info videos\n"
		+"5- Mostrar descripción canal\n"
		+"0- Salir\n"
		+"|-----------------------|\n");
	}
	
	//Printea las estadisticas del canal
	public void stadisticChanel(int idx, ArrayList<Canal> canalYT){
		Canal canal = canalYT.get(idx);
		System.out.println(canal);
	}
	
	//Lo mismo que fillChanelsYoutube
	public static void fillVideosYoutube(ArrayList<Canal> canalYT) {
		Video video;
		String [] videoName = {"Unas Partidas de chill" ,"StoryTime","Mi primer Video","Top 5 libros de Fantasia 2024"};
		String [] nameCreator = {"Pedro02","ElYoutuberby2002","Canal100%Real","L_MeGustaLeer_L"};
		for(int i = 0; i< videoName.length;i++) {
			video = new Video(videoName[i],nameCreator[i]);
			videoYT.add(video);
		}
		//Lo mismo pero para los comentarios.
		Video.fillCometsYoutube(videoYT);
	}
	
	//Calcula el numero de Videos que ha hecho un youtuber.
	public int getNumVideosYoutuber(String nameYoutuber) {
		Video video;
		int counterVideosYoutuber = 0;
		for(int i = 0; i <videoYT.size();i++) {
			video = videoYT.get(i);
			if(nameYoutuber.compareTo(video.getNameCreator()) == 0) {
				counterVideosYoutuber++;
			}
		}
		return counterVideosYoutuber;
	}
	
	//To string para que printe las estadisticas
	public String toString(){
		return "-Nombre del canal: " + this.nameChanel + " creado en fecha " + this.dateCreation + " con "+ getNumVideosYoutuber(this.nameChanel) + " video/s";
	}
}
