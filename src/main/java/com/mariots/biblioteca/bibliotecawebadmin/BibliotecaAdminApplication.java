package com.mariots.biblioteca.bibliotecawebadmin;

import com.mariots.biblioteca.bibliotecawebadmin.api.appinfo.PerfilActivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BibliotecaAdminApplication {

	@Autowired
	PerfilActivo perfilActivo;
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaAdminApplication.class, args);

	}
	@PostConstruct
	public void mostrarInformacionAplicacion(){
		System.out.println("Inicio de la aplicación");
		perfilActivo.imprimirPerfilActivo();
	}

}
