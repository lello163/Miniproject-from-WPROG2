import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AlertSmycke extends Alert {
    private TextField field = new TextField();
    private TextField field2 = new TextField();
    private CheckBox check;
    public AlertSmycke() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        Label label = new Label("Namn: ");
        grid.addRow(0, label, field);
        Label label2 = new Label("Stenar");
        grid.addRow(1, label2, field2);
        check = new CheckBox();
        Label label3 = new Label("Av guld");
        grid.addRow(2, check, label3);
        setHeaderText(null);
        setTitle("Nytt smycke");
        getDialogPane().setContent(grid);
    }
    public String getName(){
        return field.getText();
    }
    public int getStone(){
        return Integer.parseInt(field2.getText());
    }
    public boolean isGold(){
        return check.isSelected();
    }




}
