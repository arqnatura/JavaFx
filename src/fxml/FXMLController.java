/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package fxml;
 
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Equipo;
import modelo.Jugador;
import modelo.dao.AccesoDatos;
 
public class FXMLController {
	@FXML
	private ComboBox<Equipo> miCombo;
	@FXML
	private ListView<Jugador> lv_jugadores;
	@FXML
	private Button bt_clasificacion;
	@FXML
	private ImageView iv_estadio;

	public void cargaEquipos() {
		miCombo.getItems().clear();
		miCombo.getItems().addAll(AccesoDatos.getAllTeams());
		
	}
 
	public void limpiaJugadoresEquipo() {
		lv_jugadores.getItems().clear();
	}

	public void muestraEquipo() {
		System.out.println("Ha seleccionado un equipo...");
		System.out.println(miCombo.getValue().getIdEquipo());
	}

	public void cargaJugadoresEquipo() {
		lv_jugadores.getItems().clear();
		lv_jugadores.getItems().addAll(AccesoDatos.getPlayersByTeam(miCombo.getValue().getIdEquipo()));
		// System.out.println(miCombo.getValue().getId());
		try {
			File file = new File("img/" + miCombo.getValue().getIdEquipo() + ".jpg");
			Image image = new Image(file.toURI().toString());
			iv_estadio.setImage(image);
		} catch (NullPointerException e) {
			
		}
	}
	
    
	public void clasificacion() {
		bt_clasificacion.setOnMouseClicked((event) -> {
		    try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("clasificacion.fxml"));

		        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
		        Stage stage = new Stage();
		        stage.setTitle("CLASIFICACION LIGA 2018-2019");
		        stage.setScene(scene);
		        stage.show();
		    } catch (IOException e) {
		        Logger logger = Logger.getLogger(getClass().getName());
		        logger.log(Level.SEVERE, "Failed to create new Window.", e);
		    }
		});
	}
    
}
