import com.raylib.Jaylib;

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
    public Character(int posX, int posY,int w,int h,int ballX,int ballY){
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
        }
        if(CheckCollisionCircleRec(new Jaylib.Vector2(posX,posY),w,new Jaylib.Rectangle(c1.posX,c1.posY,c1.w,c1.h))){
            ballX = -ballX;
            posX += 5;
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
            posX = 300;
            posY = 200;
            c2.score++;
        }
        if (posX>600){
            posX = 300;
            posY = 200;
            c1.score++;
        }
    }

}
