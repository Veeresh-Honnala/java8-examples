package pattern;

import jdk.nashorn.internal.runtime.regexp.joni.Option;

import java.util.ArrayList;
import java.util.List;

public class PipelinePattern {
    public static void main(String[] args) {
        Pipeline<Input, String> step = new Pipeline<Input, Integer>(new AdditionStep()).step(new MultiplyStep())
                .step(new ConvertToStringStep());
        System.out.println(step.execute(new Input(10,10)));


    }

}


class Pipeline<I, O> {
    private Step<I, O> current;

    public Pipeline(final Step<I, O> step) {
        this.current = step;
    }

    public Pipeline(List<Step<I, O>> steps) {
    }

    public Pipeline() {
    }

    public <R> Pipeline<I, R> step(Step<O, R> next) {
        return new Pipeline<>(input -> next.process(current.process(input)));
    }

    public O execute(I input) {
        return current.process(input);
    }
}

interface Step<I, O> {
    public class StepException extends RuntimeException {
        public StepException(Throwable throwable) {
            super(throwable);
        }
    }

    public O process(I input) throws StepException;
}


class AdditionStep implements Step<Input, Integer> {

    @Override
    public Integer process(Input input) throws StepException {
        return input.getIn1() + input.getIn2();
    }
}

class MultiplyStep implements Step<Integer, Integer> {

    @Override
    public Integer process(Integer input) throws StepException {
        return input * input;
    }
}

class ConvertToStringStep implements Step<Integer, String> {

    @Override
    public String process(Integer input) throws StepException {
        return "converted : ;g"+input.toString();
    }
}


class Input {
    int in1;
    int in2;

    Input(int in1, int in2) {
        this.in1 = in1;
        this.in2 = in2;
    }

    int getIn1() {
        return in1;
    }

    int getIn2() {
        return in2;
    }

}