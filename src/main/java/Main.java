import static com.raylib.Jaylib.*;

public class Main {
    public static void main(String[] args) {
        InitWindow(600, 400, "JavaPong");
        SetTargetFPS(60);
        Character c1 = new Character(10,160,5,80);
        Character c2 = new Character(585,160,5,80);
        Character ball = new Character(300,200,7,7,6,6);
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(GRAY);
            DrawLine(300,0,300,400,WHITE);
            DrawText(c1.getScore()+"",200,10,20,WHITE);
            DrawText(c2.getScore()+"",400,10,20,WHITE);
            c1.draw(KEY_W,KEY_S);
            c2.draw(KEY_UP,KEY_DOWN);
            ball.balldraw();
            ball.collision(c1,c2);
            EndDrawing();
        }
        CloseWindow();
    }
}