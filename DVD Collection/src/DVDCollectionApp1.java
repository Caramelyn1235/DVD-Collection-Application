import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class DVDCollectionApp1 extends Application {
    DVDCollection model;

    public DVDCollectionApp1() {

        model = DVDCollection.example1();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();


        // Create the view
        DVDCollectionAppView1  view = new DVDCollectionAppView1();
        //DVDCollectionAppView2  view = new DVDCollectionAppView2();
        aPane.getChildren().add(view);

        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String title = javax.swing.JOptionPane.showInputDialog("Please enter the title of the DVD: ");
                Integer year = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Please enter the year of the DVD: "));
                Integer length = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Please enter the length of the DVD: "));
                model.add(new DVD(title,year,length));
                view.update(model,0);
            }
        });
        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String t = view.getTitleList().getSelectionModel().getSelectedItem();
                model.remove(t);
                view.update(model,0);
            }
        });

        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                Integer index = view.getTitleList().getSelectionModel().getSelectedIndex();
                view.update(model, index);
            }
        });

        view.getYearList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                Integer index = view.getYearList().getSelectionModel().getSelectedIndex();
                view.update(model, index);
            }
        });

        view.getLengthList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                Integer index = view.getLengthList().getSelectionModel().getSelectedIndex();
                view.update(model, index);
            }
        });

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        view.update(model,0);

        System.out.println(model);
    }

    public static void main(String[] args) {
        launch(args);
    }
}