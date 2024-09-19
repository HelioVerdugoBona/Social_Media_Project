package programa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Comentario {

	//Variables de la Clase
	private String nombreUsuario;
	private String nameYoutuber;
	private String dateCreation;
	private String coment;
	private int likes; // Extra, ahora puedes dar a like a comentarios.
	
	//Builder
	public Comentario (String nombreUsuario, String coment, String nameYoutuber) {
		setUserName(nombreUsuario);
		setcoment(coment);
		setNameYoutuber(nameYoutuber);
		setdateCreation();
	}
		
	//Nombre de Usuario
	private void setUserName(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getUserName() {
		return nombreUsuario;
	}
	
	
	//Setter / Getter Nombre del Youtuber
	private void setNameYoutuber(String nameYoutuber) {
		this.nameYoutuber = nameYoutuber;
	}
	
	public String getNameYoutuber() {
		return nameYoutuber;
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
	
	
	//Setter / Getter Comentarios.
	public void setcoment(String coment) {
		this.coment = coment;

	}
	
	
	public String getcoment() {
		return coment;
	}
	
	//Setter / Getter Dislikes.
	public void setlikes(int likes) {
		this.likes = likes;
	}
	
	
	public int getlikes() {
		return likes;
	}

	//Creamos un comentario nuevo y lo guardamos en el ArrayList de comentarios.
	public static void newComent(Scanner read, ArrayList<Video> videoYT, ArrayList<Comentario> comentariosYT, int idx ) {
		Video video = videoYT.get(idx);
		System.out.println("Introduce su nombre de Usuario");
		String usuario = read.nextLine();// Esto se puede cambiar para que te lo pregunte al Iniciar el programa.
		System.out.println("Introduce su comentario");
		String comentario = read.nextLine();
		Comentario newComent = new Comentario(usuario, comentario, video.getNameCreator());
		comentariosYT.add(newComent);
	}

	//Función para dar like a cada comentario posible que tiene cada video.
	@SuppressWarnings("resource")
	public void checklikes(int idx, ArrayList<Comentario> comentariosYT){
		Scanner read = new Scanner(System.in);
		boolean bucle = true;

		while(bucle) {
			System.out.println("1-Dar like al comentario\n"
			   +"0- Ir al sigueiente comentario / Volver al menu de video");
			switch(read.nextLine()) {
			case("1"):
				updatelikes(idx,comentariosYT);
				System.out.println();
				System.out.println("...Yendo al siguiente comentario...");
				System.out.println();
				Youtube.waiting2500();
				bucle = false;
				break;
			case("0"):
			System.out.println();
			System.out.println("...Yendo al siguiente comentario...");
				System.out.println();
				Youtube.waiting2500();
				bucle = false;
				break;
			default:
				System.out.println();
				System.out.println("¡Error, introduce un valor valido!");
				System.out.println();
				continue;
			}
		break;
		}
	}
	
	//Actualiza el numero de likes que tiene el video.
	public void updatelikes(int idx, ArrayList<Comentario> comentariosYT) {
		int likes = this.likes;
		likes ++;
		comentariosYT.get(idx).setlikes(likes);
		System.out.println();
		System.out.println(Video.ANSI_RED + "Me GUSTA");
		System.out.println(Video.ANSI_RESET);
	}
		
	//To string para que printe las estadisticas
	 public String toString(){
		 return "---Comentraio: " + this.coment + " del usuario " + this.nombreUsuario + " en fecha " +  this.dateCreation + " con " + this.likes + " likes";
	 }

}
