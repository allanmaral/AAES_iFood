/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Memento;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Maia
 */
public class MementoKeeper {
    private final ArrayList<MementoPedido> mementos = new ArrayList<>();

    public void addMemento(MementoPedido m) {
        mementos.add(m);
    }

    public MementoPedido getMemento() {
        return mementos.get(1);
    }
}
