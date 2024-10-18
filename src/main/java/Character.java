import com.raylib.Jaylib;
import java.util.Random;

import static com.raylib.Jaylib.*;

public class Character {
    private int posX,posY,ballX,ballY,score;
    private final int w,h;
    public Character(int posX, int posY,int w,int h){
        this.posX = posX;
        this.posY = posY;
        this.w = w;
        this.h = h;
        this.score = 0;
    }

    public int getPosY() {
        return posY;
    }

    public Character(int posX, int posY, int w, int h, int ballX, int ballY){
        this.posX = posX;
        this.posY = posY;
        this.w = w;
        this.h = h;
        this.ballX = ballX;
        this.ballY = ballY;
    }
    public int getScore(){
        return score;
    }

    public void balldraw(){
        DrawCircle(posX,posY,w,WHITE);
        posX += ballX;
        posY += ballY;
    }
    public void draw(int Key, int Key2){
        DrawRectangle(posX,posY,w,h,WHITE);
        if (IsKeyDown(Key)) {
            this.move(-5);
        }
        if (IsKeyDown(Key2)) {
            this.move(5);
        }
    }
    public void aidraw(Character ball){
        DrawRectangle(posX,posY,w,h,WHITE);
        int BallPosY = ball.getPosY() - h/2;
        if (posY>BallPosY) {
            this.move(-5);
        }
        if (posY<BallPosY) {
            this.move(5);
        }
    }
    public void resetball() {
        Random rand = new Random();
        this.posX = 300;
        this.posY = 200;
        this.ballX = rand.nextBoolean() ? -5 : 5;
        this.ballY = rand.nextBoolean() ? -5 : 5;
    }
    public void resetpaddle(){
        this.score = 0;
        this.posY = 160;
    }
    public void move(int y){
        posY += y;
        if (posY < 0){
            posY = 0;
        }
        if (posY > 400 - h){
            posY = 400 - h;
        }
    }

    public void collision(Character c1, Character c2){
        if(CheckCollisionCircleRec(new Jaylib.Vector2(posX,posY),w,new Jaylib.Rectangle(c2.posX,c2.posY,c2.w,c2.h))){
            ballX = -ballX;
            posX -= 5;
            ballX++;
            ballY++;
        }
        if(CheckCollisionCircleRec(new Jaylib.Vector2(posX,posY),w,new Jaylib.Rectangle(c1.posX,c1.posY,c1.w,c1.h))){
            ballX = -ballX;
            posX += 5;
            ballX++;
            ballY++;
        }
        if (posY < 0){
            posY = 0;
            ballY = -ballY;
        }
        if (posY > 400 - w){
            posY = 400 - w;
            ballY = -ballY;
        }
        if (posX<0){
           this.resetball();
            c2.score++;
        }
        if (posX>600){
            this.resetball();
            c1.score++;
        }
    }
}