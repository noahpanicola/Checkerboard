/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge1;

import challenge1.Startable;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Noah
 */
public class FXMLDocumentController implements Initializable, Startable {
    
    private static final int DEFAULT_ROWS = 8;
    private static final int DEFAULT_COLS = 8;
    
    private Stage stage;
    private Checkerboard cb;
    
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void start(Stage stage) {
        
        // set the stage
        this.stage = stage;
        
        // create the new checkerboard
        this.cb = new Checkerboard(DEFAULT_ROWS, DEFAULT_COLS, anchorPane.getWidth(), anchorPane.getHeight());
        
        // associate the anchorpane to it
        this.cb.setBoard(this.anchorPane);
        
        // build the board on the screen
        this.cb.build();
        
        // create a listener to watch for resize events
        ChangeListener resizeListener = new ChangeListener<Number>(){
            @Override 
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneDim, Number newSceneDim) {
                anchorPane.setPrefHeight((double)newSceneDim);
                cb.build();
                
            }
        };
        
        // set the listeners for window resize events
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Init");
    }
    
    /* ----- Event Handlers ----- */

    @FXML
    private void handleGridChange(ActionEvent event){
        
        // get the menu item so we can access the text
        MenuItem item = (MenuItem) event.getSource();
        
        // find the dimension of the clicked menu item
        int dimension = parseDimension(item.getText());
        
        // reset the rows and columns
        cb.setNumRows(dimension);
        cb.setNumCols(dimension);
        
        // build the new board
        cb.build();
    }
    
    @FXML
    private void handleColorChange(ActionEvent event){
        
        MenuItem item = (MenuItem) event.getSource();
            
        if(item.getText().equals("Blue")){
            // set the new colors
            cb.setDarkColor(Color.DARKBLUE);
            cb.setLightColor(Color.SKYBLUE);

        } else {
            cb.setDarkColor(Color.BLACK);
            cb.setLightColor(Color.RED);
        }
        
        // build the board on the screen
        cb.build();
    }
    
    
    
    /* Utility Functions */
    
    public int parseDimension(String dimensionStr){
        return Integer.parseInt(dimensionStr.split(" ")[0]);
    }
    
}
