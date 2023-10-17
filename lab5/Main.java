package lab5;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

// Абстрактный класс фигуры
abstract class Shape {
    protected Color color;
    protected int x;
    protected int y;

    public Shape(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // Абстрактный метод для отрисовки фигуры
    abstract void draw(Graphics g);
}

// Класс для создания окна
class DrawingFrame extends JFrame {

    private final int NUM_SHAPES = 20;
    private final Shape[] shapes;

    public DrawingFrame(int wid, int hei) {
        setTitle("Рисование случайных фигур");
        setSize(wid, hei);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Генерируем случайные фигуры и сохраняем их в массив
        shapes = new Shape[NUM_SHAPES];
        Random random = new Random();

        for (int i = 0; i < NUM_SHAPES; i++) {
            int x = random.nextInt(wid);
            int y = random.nextInt(hei);
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            if (i % 2 == 0) {
                shapes[i] = new Circle(color, x, y, random.nextInt(50) + 30);
            } else {
                shapes[i] = new Rectangle(color, x, y, random.nextInt(100) + 50, random.nextInt(50) + 30);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Отрисовываем все фигуры из массива
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}

// Класс круга
class Circle extends Shape {
    private int radius;

    public Circle(Color color, int x, int y, int radius) {
        super(color, x, y);
        this.radius = radius;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius * 2, radius * 2);
    }
}

// Класс прямоугольника
class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(Color color, int x, int y, int width, int height) {
        super(color, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите размер окна");
        Scanner scanner = new Scanner(System.in);
        int wid = scanner.nextInt();
        int hei = scanner.nextInt();
        if (wid<0 || wid>1920){
            wid = 800;
        }
        if (hei<0 || hei>1920){
            hei = 600;
        }
        DrawingFrame frame = new DrawingFrame(wid,hei);
        frame.setVisible(true);
    }
}
