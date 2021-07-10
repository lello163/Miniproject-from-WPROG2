import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AlertApparater extends Alert {
    private TextField namnt;
    private TextField antalt;
    private TextField skickt;
    public AlertApparater(){
        super(AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        Label namn = new Label("Namn: ");
        namnt = new TextField();
        grid.addRow(0, namn, namnt);
        Label antal = new Label("Antal: ");
        antalt = new TextField();
        grid.addRow(1, antal, antalt);
        Label skick = new Label("Skick: ");
        skickt = new TextField();
        grid.addRow(2, skick, skickt);
        setHeaderText(null);
        setTitle("Ny apparat");
        getDialogPane().setContent(grid);
    }
    public String getNamn(){
        return namnt.getText();
    }
    public int getAntal(){
        return Integer.parseInt(antalt.getText());
    }
    public int getSkick(){
        return Integer.parseInt(skickt.getText());
    }
}
