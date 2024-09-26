"use client";

import { Grid } from "semantic-ui-react";
import { ReactNode, useEffect, useState } from "react";
import { LambdaViewUserColumn } from "./LambdaViewUserColumn";
import { LambdaViewOutputColumn } from "./LambdaViewOutputColumn";
import { PromiseHelper } from "@/common/helper/js/PromiseHelper";
import { RequestHelper } from "@/helper/RequestHelper";
import { lambdaRequest } from "@/services/lambda-request";
import { nextLambdaMessage } from "@/services/lambda-message";
import { LabeledTextField } from "./LabeledTextField";
import { doNothing } from "@/util/event";
import { LabeledTextArea } from "./LabeledTextArea";
import { LabeledComponent } from "./LabeledComponent";
import { LambdaViewContainer } from "./LambdaViewContainer";

export const LambdaNumericView = (): JSX.Element => {
  // Constants
  const idTag = "LambdaNumericView";
  const [expression, setExpression] = useState("x^2 + y^2");
  const [argument1, setArgument1] = useState("6");
  const [argument2, setArgument2] = useState("8");
  const [output, setOutput] = useState("");

  // Handler Methods
  const handleExpressionChange = (event: any) => {
    setExpression(event.target.value);
  };

  const handleArgumentChange1 = (event: any) => {
    setArgument1(event.target.value);
  };

  const handleArgumentChange2 = (event: any) => {
    setArgument2(event.target.value);
  };

  const handleOutputChange = (event: any) => {
    setOutput(event.target.value);
  };

  const handleSubmit = () => {
    alert("Submit");
  };

  return (
    <LambdaViewContainer title="Lambda Numerical Console">
      <LambdaViewGrid>
        <form onSubmit={handleSubmit}>
          <LabeledTextField
            title="Function f(x,y)"
            containerHeight="70px"
            placeholder="Enter the function you would like to evaluate"
            value={expression}
            onKeyDown={doNothing}
            onChange={handleExpressionChange}
          />
          <LabeledTextField
            title="x"
            containerHeight="70px"
            placeholder="Enter value for x"
            value={argument1}
            onKeyDown={doNothing}
            onChange={handleArgumentChange1}
          />
          <LabeledTextField
            title="y"
            containerHeight="70px"
            placeholder="Enter value for y"
            value={argument2}
            onKeyDown={doNothing}
            onChange={handleArgumentChange2}
          />
          <EvaluateButton />
          <LabeledTextArea
            title="Output"
            containerHeight="400px"
            textAreaHeight="300px"
            disabled
            placeholder=""
            value={output}
            onChange={handleOutputChange}
          />
        </form>
      </LambdaViewGrid>
    </LambdaViewContainer>
  );
};

const LambdaViewGrid = (props: { children: ReactNode }): JSX.Element => {
  return <div className="py-5 w-full">{props.children}</div>;
};

const EvaluateButton = () => {
  return (
    <LabeledComponent title="Evaluate" height="70px">
      <button className="lambda-button w-full" type="submit" value="Evaluate">
        Evaluate
      </button>
    </LabeledComponent>
  );
};
