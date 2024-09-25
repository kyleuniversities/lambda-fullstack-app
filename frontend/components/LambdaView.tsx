"use client";

import { Grid } from "semantic-ui-react";
import { ReactNode, useEffect, useState } from "react";
import { LambdaViewUserColumn } from "./LambdaViewUserColumn";
import { LambdaViewOutputColumn } from "./LambdaViewOutputColumn";
import { PromiseHelper } from "@/common/helper/js/PromiseHelper";
import { RequestHelper } from "@/helper/RequestHelper";
import { lambdaRequest } from "@/services/lambda-request";
import { nextLambdaMessage } from "@/services/lambda-message";

export const LambdaView = (): JSX.Element => {
  // Constants
  const idTag = "LambdaView";
  const [message, setMessage] = useState("");
  const [input, setInput] = useState("");
  const [body, setBody] = useState("");
  const [output, setOutput] = useState("");

  // Use Effects
  useEffect(() => {
    setMessage(nextLambdaMessage());
  }, [idTag]);

  // Helper Methods
  const performCommand = async () => {
    const responseText = await lambdaRequest(input, body);
    setOutput(responseText);
    return PromiseHelper.newConservativeVoidPromise();
  };

  // Handler Methods
  const handleOnInputKeyDown = async (event: any) => {
    switch (event.code) {
      case "Enter":
        event.preventDefault();
        await performCommand();
        break;
    }
    return PromiseHelper.newConservativeVoidPromise();
  };

  const handleOnInputChange = async (event: any) => {
    setInput(event.target.value);
    return PromiseHelper.newConservativeVoidPromise();
  };

  const handleOnBodyChange = async (event: any) => {
    setBody(event.target.value);
    return PromiseHelper.newConservativeVoidPromise();
  };

  return (
    <LambdaViewContainer>
      <LambdaViewGrid>
        <LambdaViewUserColumn
          message={message}
          input={input}
          body={body}
          onInputKeyDown={handleOnInputKeyDown}
          onInputChange={handleOnInputChange}
          onBodyChange={handleOnBodyChange}
        />
        <LambdaViewOutputColumn output={output} />
      </LambdaViewGrid>
    </LambdaViewContainer>
  );
};

const LambdaViewContainer = (props: { children: ReactNode }): JSX.Element => {
  return (
    <div id="lambda-view">
      <div className="text-3xl font-bold">
        Lambda Application - Custom Console
      </div>
      {props.children}
    </div>
  );
};

const LambdaViewGrid = (props: { children: ReactNode }): JSX.Element => {
  return <div className="flex w-full">{props.children}</div>;
};
