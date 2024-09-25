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

export const LambdaNumericView = (): JSX.Element => {
  // Constants
  const idTag = "LambdaNumericView";
  const [expression, setExpression] = useState("");
  const [argument, setArgument] = useState("0");
  const [output, setOutput] = useState("");

  // Handler Methods
  const handleExpressionChange = (event: any) => {
    setExpression(event.target.value)
  }

  const handleArgumentChange = (event: any) => {
    setArgument(event.target.value)
  }

  const handleOutputChange = (event: any) => {
    setOutput(event.target.value)
  }

  return (
    <LambdaViewContainer>
      <LambdaViewGrid>
        <LabeledTextField 
          title="Function f(x):"
          containerHeight="100px"
          value={expression}
          onKeyDown={doNothing}
          onChange={handleExpressionChange}
        />
        <LabeledTextField 
          title="x:"
          containerHeight="100px"
          value={argument}
          onKeyDown={doNothing}
          onChange={handleArgumentChange}
        />
        <LabeledTextArea
          title="Output:"
          containerHeight="600px"
          textAreaHeight="600px"
          disabled
          value={output}
          onChange={handleOutputChange}
        />
      </LambdaViewGrid>
    </LambdaViewContainer>
  );
};

const LambdaViewContainer = (props: { children: ReactNode }): JSX.Element => {
  return (
    <div id="lambda-view">
      <div className="text-3xl font-bold">
        Lambda Application - Numeric Console
      </div>
      {props.children}
    </div>
  );
};

const LambdaViewGrid = (props: { children: ReactNode }): JSX.Element => {
  return <div className="flex w-full">{props.children}</div>;
};
