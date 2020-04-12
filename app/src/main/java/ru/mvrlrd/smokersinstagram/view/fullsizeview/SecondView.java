package ru.mvrlrd.smokersinstagram.view.fullsizeview;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface SecondView extends MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void setImage(String url);

}
