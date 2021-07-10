import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AlertAktier extends Alert{
    private TextField field;
    private TextField antalf;
    private TextField prisf;
    public AlertAktier(){
        super(AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        Label namn = new Label("Namn: ");
        field = new TextField();
        grid.addRow(0, namn, field);
        Label antal = new Label("Antal: ");
        antalf = new TextField();
        grid.addRow(1, antal, antalf);
        Label pris = new Label("Pris");
        prisf = new TextField();
        grid.addRow(2, pris, prisf);

        getDialogPane().setContent(grid);
        setTitle("Ny aktie");
       setHeaderText(null);
    }
    public String getName(){
        return field.getText();
    }
    public int getAntals(){
        return Integer.parseInt(antalf.getText());
    }
    public int getPris(){
        return Integer.parseInt(prisf.getText());
    }
}

