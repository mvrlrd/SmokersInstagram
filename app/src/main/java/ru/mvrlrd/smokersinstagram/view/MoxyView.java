package ru.mvrlrd.smokersinstagram.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;

import moxy.viewstate.strategy.StateStrategyType;

public interface MoxyView extends MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void updateRecyclerView();
}
