package edu.pe.ulima.stage;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> mStates;

    public GameStateManager(){
        mStates = new Stack<State>();
    }

    public void push(State state){
        mStates.push(state);
    }

    public void pop(){
        mStates.pop().dispose();
    }

    public void set(State state){
        pop();
        push(state);
    }

    public void update(float dt){
        mStates.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        mStates.peek().render(sb);
    }
}
