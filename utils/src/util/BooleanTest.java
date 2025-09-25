package util;

import java.util.List;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BooleanTest<T, E extends RuntimeException> {
   
    private final List<Predicate<? super T>> pipeline;
    private final Supplier<E> excptCtor;

    private BooleanTest(List<Predicate<? super T>> pipeline, Supplier<E> excptCtor) {
        this.pipeline = pipeline;
        this.excptCtor = excptCtor;
    } 

    public BooleanTest(Predicate<? super T> predicate, Supplier<E> excptCtor) {
        this(List.of(predicate), excptCtor);
    }

    @SuppressWarnings("unchecked")
    public BooleanTest(Predicate<? super T> predicate) {
       this(List.of(predicate), () -> (E) new RuntimeException());
    }

    public boolean passes(T arg) {
        return pipeline.stream().allMatch(p -> p.test(arg));
    }

    public boolean fails(T arg) {
        return pipeline.stream().anyMatch(p -> !p.test(arg));
    }

    public T assertPass(T arg) {
        if(fails(arg)) {throw excptCtor.get();}
        return arg;
    }

    public T assertFail(T arg) {
        if(passes(arg)) {throw excptCtor.get();}
        return arg;
    }
 
    @SuppressWarnings("unchecked")
    public <U extends T> BooleanTest<U,E> narrow() {
        return new BooleanTest<U,E>(
            (List<Predicate<? super U>>) (List<?>) pipeline,
            excptCtor
        );
    }

    public <U extends T, V extends RuntimeException>
    BooleanTest<U,V> compose(
        BooleanTest<U,?> argcheck,
        Supplier<V> excptCtor
    ) {
        List<Predicate<? super U>> newPipeline = Stream.concat(
            pipeline.stream().map(p -> (Predicate<? super U>)p),
            argcheck.pipeline.stream()
        ).toList();
        return new BooleanTest<>(newPipeline, excptCtor);
    }

    public BooleanTest<T,E> compose(BooleanTest<T,?> argcheck) {
        return compose(argcheck, this.excptCtor);
    }
}
