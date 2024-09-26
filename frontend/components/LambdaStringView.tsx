"use client";

import { ReactNode, useState } from "react";
import { LambdaViewUserColumn } from "./LambdaViewUserColumn";
import { LambdaViewOutputColumn } from "./LambdaViewOutputColumn";
import StringViewControlsModel from "@/models/StringViewControlsModel";
import { LabeledTextField } from "./LabeledTextField";
import { doNothing } from "@/util/event";
import { LabeledTextArea } from "./LabeledTextArea";
import { LabeledComponent } from "./LabeledComponent";
import { Container, Menu, MenuItem } from "semantic-ui-react";

export const LambdaStringView = (): JSX.Element => {
  // Constants
  const idTag = "LambdaStringView";
  const [model, setModel] = useState(StringViewControlsModel.newInitialInstance())
  const [message, setMessage] = useState("");
  const [input1, setInput1] = useState("");
  const [input2, setInput2] = useState("");
  const [body, setBody] = useState("");
  const [output, setOutput] = useState("");
  const inputTitle1 = model.getFieldTitle1()
  const inputTitle2 = model.getFieldTitle2()

  // Handler Methods
  const handleInputChange1 = (event: any) => {
    setInput1(event.target.value)
  }

  const handleInputChange2 = (event: any) => {
    setInput2(event.target.value)
  }

  const handleBodyChange = (event: any) => {
    setBody(event.target.value)
  }

  return (
    <LambdaViewContainer>
      <LambdaViewGrid>
        <div className="w-1/2 p-3 h-full">
          <LambdaStringViewControlsPane model={model} />
          <br />
          {inputTitle1 && (
            <LabeledTextField 
              title={inputTitle1}
              containerHeight="70px"
              value={input1}
              onKeyDown={doNothing}
              onChange={handleInputChange1}
            />
          )}
          {inputTitle2 && (
            <LabeledTextField 
              title={inputTitle2}
              containerHeight="70px"
              value={input2}
              onKeyDown={doNothing}
              onChange={handleInputChange2}
            />
          )}
          <LabeledTextArea
            title="Body"
            containerHeight="320px"
            textAreaHeight="270px"
            disabled={false}
            value={body}
            onChange={handleBodyChange}
          />
        </div>
      </LambdaViewGrid>
    </LambdaViewContainer>
  );
};

const LambdaViewContainer = (props: { children: ReactNode }): JSX.Element => {
  return (
    <div id="lambda-view">
      <div className="text-3xl font-bold">
        Lambda String Console
      </div>
      {props.children}
    </div>
  );
};

const LambdaViewGrid = (props: { children: ReactNode }): JSX.Element => {
  return <div className="flex w-full">{props.children}</div>;
};

const LambdaStringViewControlsPane = (props: { model: StringViewControlsModel }): JSX.Element => {
  return (
    <LabeledComponent
      title="Control Panel"
      height="180px"
    >
      <div className="controls-pane w-full h-full">
        {props.model && (
          <>
            <Container>
            <Menu secondary>
              <MenuItem 
                name="Resplace"
                active={true}
                onClick={doNothing}
              />
              <MenuItem 
                name="Code"
                active={props.model.getPrimaryMode() === "code"}
                onClick={doNothing}
              />
              <MenuItem 
                name="Tab"
                active={props.model.getPrimaryMode() === "tab"}
                onClick={doNothing}
              />
          </Menu>
            </Container>
          </>
        )}
      </div>
    </LabeledComponent>
  )
}
