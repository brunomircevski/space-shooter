package Bonus;

import java.util.ArrayList;

public class BonusStack implements IStack {

    private ArrayList<Bonus> list = new ArrayList<Bonus>();

    @Override
    public void push(Bonus bonus) {
        list.add(bonus);
    }

    @Override
    public Bonus getBonus() {
        if(list.size() == 0) return null;
        return list.get(list.size()-1);
    }

    @Override
    public void pop() {
        list.remove(getBonus());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean empty() {
        if(list.size() == 0) return true;
        return false;
    }

}
