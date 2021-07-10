import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class Main extends Application {
    private ArrayList <Värdesaker> värde = new ArrayList<>();
    private TextArea area;
    private RadioButton radio1;
    private RadioButton radio2;

    @Override

    public void start(Stage primaryStage) throws Exception{
        BorderPane border = new BorderPane();

        StackPane stack = new StackPane(new Label("Värdesaker"));
        border.setTop(stack);
        area = new TextArea();
        area.setStyle("-fx-text-inner-color: black; -fx-highlight-fill: black; fx-text-fill: true");
        area.setEditable(false);
        border.setCenter(area);

        VBox vbox = new VBox (new Label("Sortering"));
        ToggleGroup toggle = new ToggleGroup();
        radio1 = new RadioButton("Namn");
        radio1.setToggleGroup(toggle);
        radio2 = new RadioButton("Värde");
        radio2.setToggleGroup(toggle);
        radio1.setSelected(true);
        vbox.getChildren().addAll(radio1, radio2);
        border.setRight(vbox);
        vbox.setSpacing(10);

        GridPane grid = new GridPane();
        Label label2 = new Label("Ny: ");
        Button but1 = new Button("Visa");
        but1.setOnAction(new VisaHanterare());
        Button but2 = new Button("Börskrasch");
        but2.setOnAction(new BörsHanterare());
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        border.setBottom(grid);


        FlowPane flow = new FlowPane();
        MenuButton menuButton = new MenuButton("Välj");
        MenuItem item1 = new MenuItem("Smycke");
        Smyckeshanterare dialog = new Smyckeshanterare();
        item1.setOnAction(dialog);
        MenuItem item2 = new MenuItem("Aktie");
        item2.setOnAction((new AktieHanterare()));
        MenuItem item3 = new MenuItem("Apparat");
        item3.setOnAction(new ApparatHanterare());
        menuButton.getItems().addAll(item1, item2, item3);
        flow.getChildren().add(menuButton);
        grid.addRow(0, label2,menuButton, but1, but2);

        allaSmycken();

        Scene scene = new Scene(border);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Sakregister");
    }
    public class Smyckeshanterare implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                AlertSmycke dialog = new AlertSmycke();
                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.trim().isEmpty()) {
                        Alert msg = new Alert(Alert.AlertType.ERROR, "Tomt namn!");
                        msg.setHeaderText(null);
                        msg.showAndWait();
                    } else {
                        int antal = dialog.getStone();
                        boolean material = dialog.isGold();
                        if(antal<0){
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Kan inte ha negativt antal");
                            alert.showAndWait();
                            return;
                        }
                        Smycken sm = new Smycken(name, antal, material);
                        värde.add(sm);
                    }
                }
            } catch (NumberFormatException e) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setContentText("Fel inmatning!");
                msg.setHeaderText(null);
                msg.showAndWait();
            }
        }
    }
    class AktieHanterare implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                AlertAktier dialogalert = new AlertAktier();
                Optional<ButtonType> aktieres = dialogalert.showAndWait();
                if (aktieres.isPresent() && aktieres.get() == ButtonType.OK) {
                    String nameakt = dialogalert.getName();
                    if (nameakt.trim().isEmpty()) {
                        Alert msg = new Alert(Alert.AlertType.ERROR, "Tomt namn");
                        msg.setHeaderText(null);
                        msg.showAndWait();
                    }else{
                        int antalakt = dialogalert.getAntals();
                        int aktpris = dialogalert.getPris();

                        if(antalakt < 0 || aktpris < 0){
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Kan ej ha negativa aktier eller aktpris");
                            alert.showAndWait();
                            return;
                        }
                        Aktier akt = new Aktier(nameakt, antalakt, aktpris);
                        värde.add(akt);
                    }
                }
            } catch (NumberFormatException e) {
                Alert error = new Alert(Alert.AlertType.ERROR,"Fel inmatning");
                error.setHeaderText(null);
                error.showAndWait();
            }
        }
    }
    class VisaHanterare implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){
            area.clear();
            if(radio1.isSelected()) {
                Collections.sort(värde, new NamnComparator());
            } else{
                Collections.sort(värde, new VärdeComparator());
            }
            for(Värdesaker v: värde){
                if(radio1.isSelected() || radio2.isSelected())
                    area.appendText(v.toString() + "\n");
            }
        }
    }

    class NamnComparator implements Comparator<Värdesaker> {
        public int compare(Värdesaker v1, Värdesaker v2){
            return v1.getName().compareTo(v2.getName());
        }
    }
    class VärdeComparator implements Comparator<Värdesaker>{
        public int compare(Värdesaker p1, Värdesaker p2) {
            return (int) (p1.getMoms() - p2.getMoms());
        }
    }


    class ApparatHanterare implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){
            try{
                AlertApparater alapparat = new AlertApparater();
                Optional <ButtonType> appres = alapparat.showAndWait();
                if(appres.isPresent() && appres.get() == ButtonType.OK) {
                    String namn = alapparat.getNamn();
                    if (namn.trim().isEmpty()) {
                        Alert errortyp = new Alert(Alert.AlertType.ERROR, "Tomt namn");
                        errortyp.setHeaderText(null);
                        errortyp.showAndWait();
                    } else {
                        int antal = alapparat.getAntal();
                        int skick = alapparat.getSkick();

                        if(skick > 10 || skick < 0){
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Ska ligga mellan 0-10");
                            alert.showAndWait();
                            return;
                        }
                        if(antal <= 0){
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Antal kan inte vara noll eller ett negativt värde");
                            alert.showAndWait();
                            return;
                        }
                        Apparater apparat = new Apparater(namn, antal, skick);
                        värde.add(apparat);
                    }
                }
            }catch(NumberFormatException e){
                Alert error = new Alert(Alert.AlertType.ERROR, "Fel inmatning");
                error.setHeaderText(null);
                error.showAndWait();
            }
        }
    }
    class BörsHanterare implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){
            for(Värdesaker v: värde){
                if(v instanceof Aktier){
                    ((Aktier)v).setkurs(0);
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void allaSmycken() {
        värde.clear();
        värde.add(new Aktier("Ericsson B", 10, 12));
        värde.add(new Smycken("Halsband", 2, false));
        värde.add(new Apparater("TV", 10, 10));
        värde.add(new Smycken("Ring", 2, true));
    }
}

