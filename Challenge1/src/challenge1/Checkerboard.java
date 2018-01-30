/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge1;

import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Noah
 */
public class Checkerboard {
    
    private int numRows;
    private int numCols;
    private double boardWidth;
    private double boardHeight;
    
    private Color lightColor;
    private Color darkColor;
    private AnchorPane anchorPane;
    
    /* ----- Constructors ----- */
    
    public Checkerboard(int numRows, int numCols, double boardWidth, double boardHeight) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.anchorPane = null;
        
        this.lightColor = Color.RED;
        this.darkColor = Color.BLACK;
    }
    
    public Checkerboard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor) {
        
        this(numRows, numCols, boardWidth, boardHeight);
        this.lightColor = lightColor;
        this.darkColor = darkColor;
        
    }
    
    /* ----- Public Functions ----- */
    
    public AnchorPane build() {
        
        int row = 0, col = 0;
        Color cellColor = this.darkColor;
        
        // check to see if anchor pane is null
        if(this.anchorPane == null){
            return null;
        }
        
        // clear board and adjust the dimensions
        this.anchorPane.getChildren().clear();
        
        // add rectangles to the board
        for(row = 0; row < this.numRows; row++){
            for(col = 0; col < this.numCols; col++){
                
                // create a new rectangle
                Rectangle rect = new Rectangle(getRectangleWidth(), getRectangleHeight(), 
                        cellColor = alternateColor(cellColor));
                
                // adjust the x and y positions
                rect.setTranslateX(getRectangleWidth() * row);
                rect.setTranslateY(getRectangleHeight() * col);
                
                // add it to the anchor pane
                this.anchorPane.getChildren().add(rect);
            }
            
            // alternate the cell color per column
            if(this.numRows % 2 == 0) {
                cellColor = alternateColor(cellColor);
            }
        }
        
        // return the new anchor pane
        return this.anchorPane;
    }
    
    /* ----- Public Get Functions ----- */
    
    public AnchorPane getBoard() {
        return this.anchorPane;
    }
    
    public int getNumRows(){
        return this.numRows;
    }
    
    public int getNumCols(){
        return this.numCols;
    }
    
    public double getBoardWidth(){
        return this.boardWidth;
    }
    
    public double getBoardHeight(){
        return this.boardHeight;
    }
    
    public Color getLightColor(){
        return this.lightColor;
    }
    
    public Color getDarkColor(){
        return this.darkColor;
    }
    
    /* ----- Setter Functions ----- */
    
    public void setBoard(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
    
    public void setNumRows(int numRows){
        this.numRows = numRows;
    }
    
    public void setNumCols(int numCols){
        this.numCols = numCols;
    }
    
    public void setBoardWidth(double boardWidth){
        this.boardWidth = boardWidth;
    }
    
    public void setBoardHeight(double boardHeight){
        this.boardHeight = boardHeight;
    }
    
    public void setLightColor(Color lightColor){
        this.lightColor = lightColor;
    }
    
    public void setDarkColor(Color darkColor){
        this.darkColor = darkColor;
    }
                    
    
    /* ----- Utility Functions ----- */
    
    private double getRectangleWidth(){
        this.boardWidth = this.anchorPane.getWidth();
        return Math.ceil(this.boardWidth / this.numCols);
    }
    
    private double getRectangleHeight(){
        this.boardHeight = this.anchorPane.getHeight();
        return Math.ceil(this.boardHeight / this.numRows);
    }
    
    private Color alternateColor(Color color){
        if(color == this.lightColor){
            return this.darkColor;
        } else {
            return this.lightColor;
        }
    }
    
}
