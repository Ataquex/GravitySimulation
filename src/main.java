import model.view.controller.Controller;
import model.view.controller.View;
import model.view.controller.Model;

public class main {

    public static void main(String[] args){
        View mainview = new View();
        Model mainmodel = new Model();
        Controller maincontroller = new Controller(mainview, mainmodel);
    }
}
