package ru.mvrlrd.smokersinstagram;

import org.junit.Test;
import org.mockito.Mockito;
import ru.mvrlrd.smokersinstagram.model.retrofit.ApiHelper;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

        @Test
        public void emptyViewAtAttach() {
            ApiHelper apiHelper = Mockito.mock(ApiHelper.class);
            ru.mvrlrd.smokersinstagram.presenter.MainPresenter mainPresenter = Mockito.mock(ru.mvrlrd.smokersinstagram.presenter.MainPresenter.class);
            mainPresenter.getAllPhoto();

        }
}