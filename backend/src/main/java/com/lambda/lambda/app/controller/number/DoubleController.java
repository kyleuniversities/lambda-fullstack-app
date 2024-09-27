package com.lambda.lambda.app.controller.number;

import java.util.function.BiConsumer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.utility.LambdaArguments;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.IterationHelper;
import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.helper.number.DoubleHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.wrapper.DoubleWrapper;
import com.lambda.lambda.common.utility.number.expression.NumberExpressionEvaluator;

@CrossOrigin
@RestController
@RequestMapping("/number/double")
public final class DoubleController {
    @PostMapping("/add")
    public String add(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, 0, 0, DoubleWrapper::increment);
    }

    @PostMapping("/divide")
    public String divide(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, lambdaArguments.getDoubleArgument(0), 1,
                DoubleWrapper::divide);
    }

    @PostMapping("/evaluate")
    public String evaluate(@RequestBody LambdaArguments lambdaArguments) {
        int numberOfArguments = lambdaArguments.getArgumentsSize();
        String expression = lambdaArguments.getArgument(0);
        try {
            ComplexNumber x = ConditionalHelper.newTernaryOperation(numberOfArguments < 2,
                    () -> ComplexNumber.newInstance(),
                    () -> ComplexNumberHelper.parseComplexNumber(lambdaArguments.getArgument(1)));
            ComplexNumber y = ConditionalHelper.newTernaryOperation(numberOfArguments < 3,
                    () -> ComplexNumber.newInstance(),
                    () -> ComplexNumberHelper.parseComplexNumber(lambdaArguments.getArgument(2)));
            String result = NumberExpressionEvaluator.newInstance().evaluate(expression, x, y);
            return CodeHelper.toCode(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CodeHelper.toCode(
                    "Error: Unable to parse function arguments. Please check the formatting of the function arguments");
        }
    }

    @PostMapping("/multiply")
    public String multiply(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, 1, 0, DoubleWrapper::multiply);
    }

    @PostMapping("/subtract")
    public String subtract(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, lambdaArguments.getDoubleArgument(0), 1,
                DoubleWrapper::decrement);
    }

    private String synthesizeArguments(LambdaArguments lambdaArguments, double baseValue,
            int startIndex, BiConsumer<DoubleWrapper, Double> iterationAction) {
        DoubleWrapper synthesized = DoubleWrapper.newInstance(baseValue);
        IterationHelper.forEach(startIndex, lambdaArguments.getArgumentsSize(),
                (Integer i) -> iterationAction.accept(synthesized,
                        lambdaArguments.getDoubleArgument(i)));
        return CodeHelper.toCode(DoubleHelper.toDecimalTextInHundredths(synthesized.getValue()));
    }
}
