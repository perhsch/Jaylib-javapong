import static com.raylib.Jaylib.*;

enum GameState {
    TITLE, GAME, GAME_OVER, CREDITS
}

public class Main {

    public static void main(String[] args) {
        InitWindow(600, 400, "JavaPong");
        SetTargetFPS(60);

        GameState currentState = GameState.TITLE;

        Character c1 = new Character(10, 160, 5, 80);
        Character c2 = new Character(585, 160, 5, 80);
        Character ball = new Character(300, 200, 7, 7, 5, 5);

        int winValue = 5; // Value to win the game
        boolean vsAI = true;

        // Button properties
        int buttonWidth = 160;
        int buttonHeight = 40;
        int startButtonX = (600 - buttonWidth) / 2; // Center horizontally
        int startButtonY = 200; // Adjust vertical position
        int creditsButtonX = (600 - buttonWidth) / 2; // Same horizontal center for both buttons
        int creditsButtonY = 250; // Adjust vertical position below Start

        int restartButtonX = (600 - buttonWidth) / 2;
        int restartButtonY = 250;

        int backButtonX = (600 - buttonWidth) / 2;
        int backButtonY = 300;

        // Box properties for the "vs AI" toggle
        int boxX = (600 - 150) / 2 + 80;
        int boxY = 300;
        int boxWidth = 20;
        int boxHeight = 20;

        // Main game loop
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(GRAY);

            switch (currentState) {
                case TITLE:
                    ClearBackground(BLUE);
                    DrawText("JavaPong", (600 - MeasureText("JavaPong", 40)) / 2, 100, 40, WHITE);

                    DrawRectangle(startButtonX, startButtonY, buttonWidth, buttonHeight, DARKGRAY);
                    DrawText("Start Game", startButtonX + 25, startButtonY + 10, 20, WHITE);

                    DrawRectangle(creditsButtonX, creditsButtonY, buttonWidth, buttonHeight, DARKGRAY);
                    DrawText("Credits", creditsButtonX + 45, creditsButtonY + 10, 20, WHITE);

                    DrawText("Play vs AI:", startButtonX - 70, boxY, 20, WHITE);
                    DrawRectangle(boxX, boxY, boxWidth, boxHeight, WHITE);
                    if (vsAI) {
                        DrawRectangle(boxX + 4, boxY + 4, boxWidth - 8, boxHeight - 8, GREEN);
                    }

                    if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                        int mouseX = GetMouseX();
                        int mouseY = GetMouseY();

                        if (mouseX >= boxX && mouseX <= boxX + boxWidth &&
                                mouseY >= boxY && mouseY <= boxY + boxHeight) {
                            vsAI = !vsAI;
                        }

                        if (mouseX >= startButtonX && mouseX <= startButtonX + buttonWidth &&
                                mouseY >= startButtonY && mouseY <= startButtonY + buttonHeight) {
                            currentState = GameState.GAME;
                        }

                        if (mouseX >= creditsButtonX && mouseX <= creditsButtonX + buttonWidth &&
                                mouseY >= creditsButtonY && mouseY <= creditsButtonY + buttonHeight) {
                            currentState = GameState.CREDITS;
                        }
                    }
                    break;

                case GAME:
                    DrawLine(300, 0, 300, 400, WHITE);
                    DrawText(c1.getScore() + "", 200, 10, 20, WHITE);
                    DrawText(c2.getScore() + "", 400, 10, 20, WHITE);

                    if (vsAI) {
                        c1.aidraw(ball);
                    } else {
                        c1.draw(KEY_W, KEY_S);
                    }
                    c2.draw(KEY_UP, KEY_DOWN);
                    ball.balldraw();
                    ball.collision(c1, c2);

                    if (c1.getScore() == winValue || c2.getScore() == winValue) {
                        currentState = GameState.GAME_OVER;
                    }
                    break;

                case GAME_OVER:
                    DrawText("Game Over!", (600 - MeasureText("Game Over!", 40)) / 2, 100, 40, WHITE);

                    DrawRectangle(restartButtonX, restartButtonY, buttonWidth, buttonHeight, DARKGRAY);
                    DrawText("Restart Game", restartButtonX + 10, restartButtonY + 10, 20, WHITE);

                    if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                        int mouseX = GetMouseX();
                        int mouseY = GetMouseY();

                        if (mouseX >= restartButtonX && mouseX <= restartButtonX + buttonWidth &&
                                mouseY >= restartButtonY && mouseY <= restartButtonY + buttonHeight) {
                            c1.resetpaddle();
                            c2.resetpaddle();
                            ball.resetball();
                            currentState = GameState.TITLE;
                        }
                    }
                    break;

                case CREDITS:
                    ClearBackground(SKYBLUE);
                    DrawText("Credits", (600 - MeasureText("Credits", 40)) / 2, 100, 40, WHITE);
                    DrawText("Made by: Periklis Chatzikyriakou", (600 - MeasureText("Made by: Periklis Chatzikyriakou", 20)) / 2, 150, 20, WHITE);
                    DrawText("Source code on https://github.com/perhsch", (470 - MeasureText("Source code on https://github.com/perhsch", 14)) / 2, 180, 20, WHITE);
                    DrawRectangle(backButtonX, backButtonY, buttonWidth, buttonHeight, DARKGRAY);
                    DrawText("Back to Title", backButtonX + 15, backButtonY + 10, 20, WHITE);

                    if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                        int mouseX = GetMouseX();
                        int mouseY = GetMouseY();

                        if (mouseX >= backButtonX && mouseX <= backButtonX + buttonWidth &&
                                mouseY >= backButtonY && mouseY <= backButtonY + buttonHeight) {
                            currentState = GameState.TITLE;
                        }
                    }
                    break;
            }

            EndDrawing();
        }

        CloseWindow();
    }
}
