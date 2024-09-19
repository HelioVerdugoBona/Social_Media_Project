package programa;

import java.util.ArrayList;
import java.util.Scanner;

public class Youtube {
	
	static ArrayList<Canal> canalYT;
	
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		
		canalYT = new ArrayList<Canal>();
		Canal canal = null;
		fillChanelsYoutube(canalYT);//Creamos unos cuantos canales-Videos-Comentarios, para que Youtube parezca que tiene más usuarios.

		
		boolean bucle = true;
		int idx;
		
		while(bucle) {
			printMenuYOUTUBE(args);//Llamamaos a la función que printea el menu de Youtube.
			switch(read.nextLine()) {
			
			case("1")://Nuevo Canal
				//Se tiene que cambiar el Canal para que le llame con la Clase canal, no con el fichero Canal.
				idx = createChanel(read,canalYT);
				canal = canalYT.get(idx);
				canal.selectedChanel(canalYT,idx);
				continue;
				
			case("2")://Seleccionar Canal
				System.out.println("Que canal quieres ver?");
				idx = selectChanelMenu(read);
				canal = canalYT.get(idx);				
				canal.selectedChanel(canalYT,idx);
				continue;
				
			case("3")://Mostrar Estadisticas
				viewStadistics();
				continue;
				
			case("4")://Mostrar Estadisticas Complejas
				viewAllStadistics();
				continue;
					
					
			case("0")://Salir
				System.out.println();
				System.out.println("¡Nos vemos pronto!");
				bucle = false;
				break;
				
			default:
				System.out.println();
				System.out.println("¡Error, introduce un valor valido!");
				System.out.println();
			}
					
		}
		
		read.close();
	}
	
		
	private static void printMenuYOUTUBE(String[] args) {
		System.out.println("|---YOUTUBE---|\n"
		+"1. Nuevo Canal\n"
		+"2. Selecionar Canal\n"
		+"3. Mostrar Estadisticas\n"
		+"4. Mostrar Estadisticas Complejas\n"
		+"0. Salir\n"
		+"|-------------|\n");
	}
	
		
	public static void viewStadistics() {
		System.out.println("Youtube tiene "+ canalYT.size() +" canales");
	}
	
	
	public static void viewAllStadistics() {
		ArrayList<Comentario> comentariosYT = Video.getcoment();
		ArrayList<Video> videoYT = Canal.getvideoYT();
		//Como no tengo las ArrayList con sus valores las traigo al main para imprimir sus valores.
		videoYT.addAll(Canal.getvideoYT());
		comentariosYT.addAll(Video.getcoment());
		Canal canal;
		Video video;
		Comentario comentarios;
		System.out.println("Youtube tiene "+ canalYT.size() +" canales");
		
		for(int i = 0; i <canalYT.size();i++) {
			canal = canalYT.get(i);
			video = videoYT.get(i);
			comentarios = comentariosYT.get(i);
			System.out.println(canal);
			System.out.println(video);
			System.out.println(comentarios);
				
			}


		}
		//Creamos un canal nuevo, lo guardamos en el array de CanaleYT y devolvemos su indice.
		public static int createChanel(Scanner read, ArrayList<Canal> canalYT) {
		System.out.println("¡Hola, nuevo usuario! A continuación le pediremos que rellene los siguientes apartados para crear su canal de YOUTUBE");
		
		System.out.println();
		System.out.println("Por favor introduzca el nombre del canal:");
		String nameChanel = read.nextLine();
		
		System.out.println();
		System.out.println("Ahora introduzca una descripción del canal para que la gente pueda conocerle");
		String descriptionChanel = read.nextLine();
		
		Canal Chanel = new Canal(nameChanel,descriptionChanel); //Crea la clase, guarda las variables en la clase y luego lo guarda en el array.
		canalYT.add(Chanel);//De forma que todo lo que queramos lo obtendremos del array.
		
		System.out.println();
		System.out.println();
		System.out.println("¡Enorabuena, ya tienes tu canal de Youtube!");
		return canalYT.indexOf(Chanel);
	}
	
	
	//Menu en el que se selecciona que canal quieres ver.	
	public static int selectChanelMenu(Scanner read){
		Canal canal;
		int idx;
		
		while(true) {
			//Si en YT no hay nigun canal, te manda directamente a crearte uno.
			if (canalYT.size() == 0) {
				System.out.println();
				System.out.println("Lo sentimos, actualmente no existe ningun canal");
				System.out.println();
				System.out.println("....Le rediccionaremos al menu de crear un canal....");
				System.out.println();
				Youtube.waiting2500();
				idx = createChanel(read, canalYT);
				return idx;
				
			}for (int i = 0; i < canalYT.size(); i++) {
				canal = canalYT.get(i);
				System.out.println(i + "-Nombre del Canal: " + canal.getNameChanel() +" en fecha "+ canal.getdateCreation()+" con "+ canal.getNumVideosYoutuber(canal.getNameChanel())+" videos ");
		
			}idx = read.nextInt();

			if(idx < 0 || idx > 4) {
				System.out.println("¡Error, ese numero de canal no exsiste!");
				continue;
				
			}canal = canalYT.get(idx);
			
			if (canal.getNameChanel() != null) {
				return idx;
			}
			System.out.println("¡Error, introduce un valor valido!");
		}
	}
	
	//Los canales de mentira creados para hacer bulto.
	public static void fillChanelsYoutube(ArrayList<Canal> canalYT){
		Canal canal;
		String[] canalesReales = {"Pedro02","ElYoutuberby2002","Canal100%Real","L_MeGustaLeer_L"};
		String[] canalesRealesDescripciones = {"Hola, soy Pedro02 y me gusta jugar videojuegos",
				"Soy un youtuber, nací en el 2002 como dice mi nombre",
				"Este canal es 100% real no fake",
				"Hola, soy un lector empedernido y me dedico a recomendarlos en my canal"};
		for(int i = 0; i< canalesReales.length;i++) {
			canal = new Canal(canalesReales[i],canalesRealesDescripciones[i]);
	 		canalYT.add(canal);
		}//Llama a la funcion fillVideosYoutube que hace exactamete lo mismo pero para los videos.
		Canal.fillVideosYoutube(canalYT); 
		
	}
	
	
	//Es una funcion que se dedica a esperar, cuando han pasado 2500 milisegundos termina, 
	//Sirve basicamente para darle realismo a YT, cuando el ususario vuelve a un menu anterior tarda un rato y así parece que esta cargando algo.
	public static void waiting2500() {
        try {
            Thread.sleep(5*500);
         } catch (Exception e) {
            System.out.println(e);
         }
	}

}
