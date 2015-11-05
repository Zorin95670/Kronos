package com.aits.kronos.apps;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Context {

	private IConfiguration configuration;
	private List<String> saves;
	
	public int init(){
		System.out.println("Context : START INIT");
		File home = new File(System.getProperty("user.home") + java.io.File.separator + "Kronos");
		if(!home.exists() && !home.mkdir()){
			System.err.println("Error on creating home directory for Kronos application");
			System.out.println("Context : STOP INIT");
			return 1;
		}
		System.out.println("Context : init Configuration");
		initConfiguration(home.getAbsolutePath());
		System.out.println("Context : init Sauvegarde");
		initSauvegarde(home.getAbsolutePath());
		System.out.println("Context : FINISH INIT");
		return 0;
	}

	protected void initConfiguration(String path) {
		File config = new File(path, "config.json");
		if(!config.exists()){
			setConfiguration(new Configuration());
			try {
				FileWriter writer = new FileWriter(config);
				writer.write(getConfiguration().toString());
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				FileReader reader = new FileReader(config);
				char[] buff = new char[1024];
				StringBuilder source = new StringBuilder();
				while(reader.ready()){
					reader.read(buff);
					source.append(buff);
				};
				reader.close();

				setConfiguration(new Configuration(source.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void initSauvegarde(String path) {
		this.saves = new ArrayList<String>();
		File saves = new File(path + File.separator + "saves");
		if(!saves.exists() && !saves.mkdir()){
			System.err.println("Error on creating saves directory for Kronos application");
		} else{
			System.out.println("passe");
			for(String save : saves.list()){
				getSaves().add(save);
			}
		}
	}
	public IConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(IConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public List<String> getSaves(){
		return saves;
	}
}
