

//PARA SABER MAIS SOBRE SINGLETON(https://www.youtube.com/watch?v=NZaXM67fxbs)


package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import hotel.HotelController;

/**
 * Classe que representa o BD. 
 * 
 * Por decorrência de representar um objeto muito importante e que não é legal instanciá-lo mais de uma vez, 
 * esta classe esta de acordo com o Design Pattern Singleton
 * @author sampaio
 *
 */
public class BancoDeDados {
	
	private static BancoDeDados instance = null; 
	private static final String path = "arquivos_sistema";
	private static final String file = "hug.dat";
	private HotelController hotelController;
	
			
			
	private ObjectOutputStream hotelControllerOutput; 
	private ObjectInputStream hotelControllerInput;

	/**
	 * Contrutor de BancoDeDados
	 */
	private BancoDeDados(){ }
	
	
	/**
	 * Retorna uma instancia de BancoDedados
	 * @return
	 * 		Uma instancia
	 */		
	public static BancoDeDados getInstance(){
		
		if(instance == null){
			instance = new BancoDeDados();
		}
		return instance;
		
	}
	
	/**
	 * Inicia o sistema e lê o arquivo de persistência do Hotel
	 * @throws IOException
	 */
	public void iniciaSistema() throws IOException {
		try{
			this.hotelControllerInput = new ObjectInputStream(
					new FileInputStream(path + "/" +
			file));
			try{
				hotelController =(HotelController) hotelControllerInput.readObject();
			}catch(Exception e){
				hotelController = new HotelController();
			}
			//se o arquivo nao for encontrado, o que nois faz? 
			
		}catch(FileNotFoundException e){
			hotelController = new HotelController();
			
			File f = new File(path + "/" + file);
			
			f.getParentFile().mkdirs();
			try{
				f.createNewFile();
			}catch(IOException ee){
				System.err.println("IOException: " +  e.getMessage());
			}
		}catch(IOException e){
			System.err.println("IOException: "+ e.getMessage());
		}finally {
			this.hotelControllerInput.close();
		}
		
	}
	
	/**
	 * Salva o estado atual do sistema em hug.dat
	 * @throws IOException 
	 */
	public void fechaSistema() throws IOException{
				
		try{
			this.hotelControllerOutput = new ObjectOutputStream(
					new FileOutputStream(path + "/" + file));
			
			hotelControllerOutput.writeObject(hotelController);
			hotelControllerOutput.close();
			
			
		}catch(FileNotFoundException e){
			File f = new File(path + "/" + file);
			f.getParentFile().mkdirs();
			try{
				f.createNewFile();
			
			}catch(IOException ee){
				System.err.println("IOException: " + e.getMessage());
			}
			
		}catch(IOException e){
			System.err.println("IOException: " + e.getMessage());
		}finally{
			this.hotelControllerOutput.close();
		}
		
		
	}
	
	
	/**
	 * Retorna o objeto HotelController encontrado no hug.dat
	 * @return
	 */
	public HotelController getHotelController(){
		return this.hotelController;
	}
}
