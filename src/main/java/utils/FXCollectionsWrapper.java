package utils;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.List;
import java.util.RandomAccess;

public class FXCollectionsWrapper {

    public static <E> ObservableList<E> observableList(List<E> list, Callback<E, Observable[]> extractor) {
        if (list == null || extractor == null) {
            throw new NullPointerException();
        }
        return list instanceof RandomAccess ? new ObservableListWrapper<E>(list, extractor) :
                new ObservableFilterableSequentialListWrapper<>(list, extractor);
    }

}
