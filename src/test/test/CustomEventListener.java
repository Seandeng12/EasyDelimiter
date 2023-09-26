package test;


import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomEventListener<T> extends AnalysisEventListener<T> {

    private final List<T> readResultList = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        System.out.println(data);
        readResultList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("doAfterAllAnalysed");
    }
}
