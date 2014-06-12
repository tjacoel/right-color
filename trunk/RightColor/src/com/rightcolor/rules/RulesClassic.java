package com.rightcolor.rules;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.rightcolor.gameobjects.ColorButton;

public class RulesClassic extends RulesSet {

    private final float TOTAL_TIMER = 10f;
    private final Color TEXT_COLOR = Color.WHITE;
    private final String PREFERENCES_KEY = "preferencesRulesClassic";
    
    private float timer = TOTAL_TIMER;
    private int score = 0;
    
    private Random r = new Random();
    
    @Override
    public Color generateNewTargetColor() {
        Color newColor = AVAILABLE_COLORS[r.nextInt(AVAILABLE_COLORS.length)];
        while (newColor.equals(targetColor)) {
            newColor = AVAILABLE_COLORS[r.nextInt(AVAILABLE_COLORS.length)];
        }
        targetColor = newColor;
        return targetColor;
    }

    @Override
    public void assignColorToButtons(ColorButton topLeft,
            ColorButton topRight, ColorButton bottomLeft,
            ColorButton bottomRight) {

        topLeft.setColor(AVAILABLE_COLORS[0]);
        topRight.setColor(AVAILABLE_COLORS[1]);
        bottomLeft.setColor(AVAILABLE_COLORS[2]);
        bottomRight.setColor(AVAILABLE_COLORS[3]);
    }

    @Override
    public void buttonClicked(ColorButton button) {
        if (targetColor != null && targetColor.equals(button.getColor())) {
            score++;
            dispatchEvent(EVENT_ROUND_FINISHED);
            
        } else if (!targetColor.equals(button.getColor())) {
            dispatchEvent(EVENT_GAME_END_DEFEAT);
        }
    }

    @Override
    public void decreaseTimer(float delta) {
        timer -= delta;
        if (timer <= 0) {
            dispatchEvent(EVENT_GAME_END_VICTORY);
        }
    }

    @Override
    public float getRemainingTime() {
        return timer;
    }

    @Override
    public float getTotalTime() {
        return TOTAL_TIMER;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getPreferencesKey() {
        return PREFERENCES_KEY;
    }

    @Override
    public Color getTextColor() {
        return TEXT_COLOR;
    }
}
