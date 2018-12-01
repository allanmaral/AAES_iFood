package br.ufjf.dcc078.Memento;

import java.util.ArrayList;

public class MementoKeeper {
    private final ArrayList<MementoPedido> mementos = new ArrayList<>();

    public void addMemento(MementoPedido m) {
        mementos.add(m);
    }

    public MementoPedido getMemento() {
        return mementos.get(1);
    }
}
