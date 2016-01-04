import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Listing 8-9, im 8-13

public class ChartAppStackedBar extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CategoryAxis xAxis = new CategoryAxis();
        IntStream.range(2011,2020).forEach(t -> xAxis.getCategories().add(String.valueOf(t)));
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart stackedBarChart = new StackedBarChart(xAxis, yAxis, getChartData());
        stackedBarChart.setTitle("speculations");
        primaryStage.setTitle("StackedBarChart example");

        StackPane root = new StackPane();
        root.getChildren().add(stackedBarChart);
        Scene scene = new Scene(root, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<XYChart.Series<String, Double>> getChartData() {
        double javaValue = 17.56;
        double cValue = 17.06;
        double cppValue = 8.25;
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        Series<String, Double> java = new Series<>();
        Series<String, Double> c = new Series<>();
        Series<String, Double> cpp = new Series<>();
        java.setName("java");
        c.setName("C");
        cpp.setName("C++");
        
        for (int i = 2011; i < 2021; i++) {
            java.getData().add(new XYChart.Data(Integer.toString(i), javaValue));
            javaValue = javaValue + Math.random() - .5;
            c.getData().add(new XYChart.Data(Integer.toString(i), cValue));
            cValue = cValue + Math.random() - .5;
            cpp.getData().add(new XYChart.Data(Integer.toString(i), cppValue));
            cppValue = cppValue + Math.random() - .5;
        }
        answer.addAll(java, c, cpp);
        return answer;
    }
}