package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Controller {
    public Canvas canvas;

    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double мм = Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;
        System.out.println(мм);

        int[] палки = new int[]{
                5, 1, 4, 0, 9, 2, 0, 2, 0, 1, 2, 3, 4, 5, 6,
        };


        gc.setFill(Color.BLACK);

        int x0 = 20, y0 = 10;
        double высотаПалки = 22.85 * мм;
        double ширинаОграничивающейПалки = 0.5 * мм;
        double расстояниеМеждуПалками = 0.2 * мм; // ОЧ МАЛО
        int откудаПалка = x0;

        gc.fillRect(откудаПалка, y0, ширинаОграничивающейПалки, высотаПалки + 1.65 * мм);
        откудаПалка += ширинаОграничивающейПалки + расстояниеМеждуПалками;
        gc.fillRect(откудаПалка, y0, ширинаОграничивающейПалки, высотаПалки + 1.65 * мм);
        откудаПалка += ширинаОграничивающейПалки + расстояниеМеждуПалками;

        boolean нарисовалиСредниеПалки = false;
        for (int номерПалки = 0; номерПалки < палки.length; номерПалки++) {
            double ширинаПалки = палки[номерПалки] * 0.15 * мм;
            if (номерПалки == палки.length / 2 && !нарисовалиСредниеПалки) {
                ширинаПалки = ширинаОграничивающейПалки;
                gc.setFill(Color.BLACK);
                gc.fillRect(откудаПалка, y0, ширинаПалки, высотаПалки + 1.65 * мм);
                откудаПалка += ширинаПалки + расстояниеМеждуПалками;
                gc.fillRect(откудаПалка, y0, ширинаПалки, высотаПалки + 1.65 * мм);
                откудаПалка += ширинаПалки + расстояниеМеждуПалками;
                номерПалки--;
                нарисовалиСредниеПалки = true;
                continue;
            }
            if (ширинаПалки == 0) {
                ширинаПалки = 1.35 * мм;
                gc.setFill(Color.WHITE);
            } else {
                gc.setFill(Color.BLACK);
            }
            gc.fillRect(откудаПалка, y0, ширинаПалки, высотаПалки);
            откудаПалка += ширинаПалки + расстояниеМеждуПалками;
        }

        gc.fillRect(откудаПалка, y0, ширинаОграничивающейПалки, высотаПалки + 1.65 * мм);
        откудаПалка += ширинаОграничивающейПалки + расстояниеМеждуПалками;
        gc.fillRect(откудаПалка, y0, ширинаОграничивающейПалки, высотаПалки + 1.65 * мм);
    }
}
