// this project utilizes libraries taught in my computer science classes. 
// a video will be updated in the github to show the functionality of the game. 
// this is mostly for fun. 
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

class Graph {

}




class Player {
  String name; 
  int health; // starts at 100 
  boolean death; // is the player alive?
  int x, y; // coordinates of the player; 
  Color color; 

  Player(String name, int x, int y, Color color) {
    this.name = name;
    this.health = 100; 
    this.death = false;
    this.x = x; 
    this.y = y; 
    this.color = color;

  }

  public void addCharacter(String s) {
    this.name += s;
  }

  public String deduct() {
    if(this.name.length() > 0) {
      this.name = this.name.substring(0, this.name.length() - 1);
    }
    return name;
  }

  public void decrementHealth(int value) {
    this.health -= value;
  }

  public void setCoordinates(int x, int y) {
    this.x = x; 
    this.y = y;
  }

  public WorldImage draw() {
    return new CircleImage(25, OutlineMode.SOLID, this.color);
  }

}

class FallingWalls extends World {

  boolean name1 = false;
  boolean name2 = false; 
  Player player1 = new Player("", 100, 200, Color.red);
  Player player2 = new Player("", 300, 200, Color.blue);

  @Override
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(500,500);


    // first player is typing their name in 
    if(!this.name1 && !this.name2) {
      this.addTypeText(scene, player1);
    }


    // second player is typing their name in 
    if(this.name1 && !this.name2) {
      this.addTypeText(scene, player2);
    }

    this.introductionScene(scene);

    return scene;
  }


  public void addTypeText(WorldScene scene, Player player) {
    scene.placeImageXY(new TextImage(player.name, 50, Color.black), 50, 300);
  }




  // introduction scene 
  public void introductionScene(WorldScene scene) {
    scene.placeImageXY(new TextImage("Welcome to FallingWalls", 30, Color.orange),225,50);
    scene.placeImageXY(new TextImage("pLayer1 uses WaSD an pLayer2 uses the ARrOwS", 18, Color.blue),250,85);


    if(!this.name1 && !this.name2) {
      scene.placeImageXY(new TextImage("Hello, pLayer1, please type your name and click enter", 12, Color.magenta), 250, 250);
    }

    if(this.name1 && !this.name2) {
      scene.placeImageXY(new TextImage("Hello pLayer2, please type your name and click enter", 12, Color.orange), 260, 350);
    }


  }





  public void onKeyEvent(String key) {

    System.out.println("Name1 " + this.name1);
    System.out.println("Name2 " + this.name2);
    System.out.println();

    // the start of the game 
    if(!this.name1 && !this.name2) {
      if(key.equals("backspace")) {
        this.player1.deduct(); 
      } else {
        if(key.equals("enter")) {
          this.name1 = true;
        }
        this.player1.addCharacter(key);
      }
    } else if (this.name1 && !this.name2) {
      if(key.equals("backspace")) {
        this.player2.deduct();
      } else {
        if(key.equals("enter")) {
          this.name2 = true;
        }
        this.player2.addCharacter(key);
      }
      
    }


  }
}


class ExamplesGame {

  // runs the game 
  void testGame(Tester t) {
    new FallingWalls().bigBang(500, 500, (1 / 28.0));
  }
}

