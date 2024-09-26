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
import { LambdaSubmitButton } from "./LambdaSubmitButton";
import { LabeledDropdown } from "./LabeledDropdown";
import { ArrayHelper } from "@/common/helper/ArrayHelper";

export const LambdaNumericView = (): JSX.Element => {
  // Constants
  const idTag = "LambdaNumericView";
  const functionOptions = collectFunctionOptions();
  const [functionOptionValue, setFunctionOptionValue] = useState("normal-bi");
  const [expression, setExpression] = useState("x^2 + y^2");
  const [argument1, setArgument1] = useState("6");
  const [argument2, setArgument2] = useState("8");
  const [output, setOutput] = useState("");
  const selectedFunctionOptionIndex = ArrayHelper.indexOf(
    functionOptions,
    (option) => option.value === functionOptionValue,
  );
  const selectedFunctionOption = functionOptions[selectedFunctionOptionIndex];
  const isBiFunction = selectedFunctionOption.value.includes("-bi");

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

  const handleFunctionOptionChange = (event: any) => {
    setFunctionOptionValue(event.target.value);
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
            title={`Function ${selectedFunctionOption?.functionTitle}`}
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
            containerHeight={isBiFunction ? "70px" : "0px"}
            placeholder="Enter value for y"
            value={argument2}
            onKeyDown={doNothing}
            onChange={handleArgumentChange2}
          />
          <LabeledDropdown
            title="Function Options"
            containerHeight="70px"
            options={functionOptions}
            value={selectedFunctionOption?.value || "null"}
            onChange={handleFunctionOptionChange}
          />
          <LambdaSubmitButton title="Evaluate" />
          <LabeledTextArea
            title="Output"
            containerHeight={`${330 + (isBiFunction ? 0 : 70)}px`}
            textAreaHeight={`${230 + (isBiFunction ? 0 : 70)}px`}
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

const collectFunctionOptions = () => {
  return [
    {
      functionTitle: "f(x)",
      text: "Evaluate Normal Function",
      value: "normal",
    },
    {
      functionTitle: "f²(x)",
      text: "Evaluate from Squared Function",
      value: "square",
    },
    {
      functionTitle: "f³(x)",
      text: "Evaluate from Cubed Function",
      value: "cube",
    },
    {
      functionTitle: "√[f(x)]",
      text: "Evaluate from Square Root Function",
      value: "square-root",
    },
    {
      functionTitle: "³√[f(x)]",
      text: "Evaluate from Cube Root Function",
      value: "cube-root",
    },
    {
      functionTitle: "f(x,y)",
      text: "Evaluate Normal Bi-Function",
      value: "normal-bi",
    },
    {
      functionTitle: "f²(x,y)",
      text: "Evaluate from Squared Bi-Function",
      value: "square-bi",
    },
    {
      functionTitle: "f³(x,y)",
      text: "Evaluate from Cubed Bi-Function",
      value: "cube-bi",
    },
    {
      functionTitle: "√[f(x,y)]",
      text: "Evaluate from Square Root Bi-Function",
      value: "square-root-bi",
    },
    {
      functionTitle: "³√[f(x,y)]",
      text: "Evaluate from Cube Root Bi-Function",
      value: "cube-root-bi",
    },
  ];
};
