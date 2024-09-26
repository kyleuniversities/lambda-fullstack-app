"use client";

import { ReactNode, useEffect, useState } from "react";
import { LambdaViewUserColumn } from "./LambdaViewUserColumn";
import { LambdaViewOutputColumn } from "./LambdaViewOutputColumn";
import StringViewControlsModel from "@/models/StringViewControlsModel";
import { LabeledTextField } from "./LabeledTextField";
import { doNothing } from "@/util/event";
import { LabeledTextArea } from "./LabeledTextArea";
import { LabeledComponent } from "./LabeledComponent";
import { Container, Menu, MenuItem } from "semantic-ui-react";
import { LambdaViewContainer } from "./LambdaViewContainer";
import { toPx } from "@/util/style";
import { StringHelper } from "@/common/helper/string/StringHelper";

export const LambdaStringView = (): JSX.Element => {
  // Constants
  const idTag = "LambdaStringView";
  const [model, setModel] = useState(
    StringViewControlsModel.newInitialInstance(),
  );
  const [message, setMessage] = useState("");
  const [input1, setInput1] = useState("");
  const [input2, setInput2] = useState("");
  const [body, setBody] = useState("");
  const [output, setOutput] = useState("");
  const inputTitle1 = model.getFieldTitle1();
  const inputTitle2 = model.getFieldTitle2();
  const inputPlaceholder1 = model.getFieldPlaceholder1();
  const inputPlaceholder2 = model.getFieldPlaceholder2();
  const bodyPlaceholder = model.getBodyPlaceholder();
  const [latestInputTitle1, setLatestInputTitle1] = useState("");
  const [latestInputTitle2, setLatestInputTitle2] = useState("");

  // View Constants
  const textFieldHeight = 70;
  const textFieldHeightText = toPx(textFieldHeight);

  // Helper Methods
  const numberOfInputFields = (): number => {
    const input1 = inputTitle1 ? 1 : 0;
    const input2 = inputTitle2 ? 1 : 0;
    return input1 + input2;
  };

  // Use Effects
  useEffect(() => {
    if (inputTitle1) {
      setLatestInputTitle1(inputTitle1);
    }
  }, [inputTitle1]);

  useEffect(() => {
    if (inputTitle2) {
      setLatestInputTitle2(inputTitle2);
    }
  }, [inputTitle2]);

  // Handler Methods
  const handleInputChange1 = (event: any) => {
    setInput1(event.target.value);
  };

  const handleInputChange2 = (event: any) => {
    setInput2(event.target.value);
  };

  const handleBodyChange = (event: any) => {
    setBody(event.target.value);
  };

  return (
    <LambdaViewContainer title="Lambda String Console">
      <LambdaViewGrid>
        <div className="w-1/2 p-3 h-full">
          <LambdaStringViewControlsPane model={model} setModel={setModel} />
          <br />
          <LabeledTextField
            title={inputTitle1 || latestInputTitle1}
            containerHeight={inputTitle1 ? textFieldHeightText : "0px"}
            placeholder={inputPlaceholder1}
            value={input1}
            onKeyDown={doNothing}
            onChange={handleInputChange1}
          />
          <LabeledTextField
            title={inputTitle2 || latestInputTitle2}
            containerHeight={inputTitle2 ? textFieldHeightText : "0px"}
            placeholder={inputPlaceholder2}
            value={input2}
            onKeyDown={doNothing}
            onChange={handleInputChange2}
          />
          <LabeledTextArea
            title="Body"
            containerHeight={`${320 + textFieldHeight * (2 - numberOfInputFields())}px`}
            textAreaHeight={`${270 + textFieldHeight * (2 - numberOfInputFields())}px`}
            disabled={false}
            placeholder={bodyPlaceholder}
            value={body}
            onChange={handleBodyChange}
          />
        </div>
      </LambdaViewGrid>
    </LambdaViewContainer>
  );
};

const LambdaViewGrid = (props: { children: ReactNode }): JSX.Element => {
  return <div className="flex w-full">{props.children}</div>;
};

const LambdaStringViewControlsPane = (props: {
  model: StringViewControlsModel;
  setModel: any;
}): JSX.Element => {
  // Constants
  const modeTitle = props.model.getModeTitle();
  const modeDescription = props.model.getModeDescription();

  // Helper Methods
  const getSecondaryModeOptions = () => {
    if (!props.model) {
      return [];
    }
    return StringViewControlsModel.getSecondaryModeChoices(
      props.model.getPrimaryMode(),
    );
  };

  const toOptionCase = (text: string): string => {
    return text
      .split(/[_]+/)
      .map((part) => {
        if (part === "to") {
          return part;
        }
        return StringHelper.capitalizeFirstLetter(part);
      })
      .join(" ");
  };

  // Handler Methods
  const handlePrimaryModeChange = (primaryMode: string) => {
    props.setModel(props.model.copyWithPrimaryModeChange(primaryMode));
  };

  const handleSecondaryModeChange = (secondaryMode: string) => {
    props.setModel(props.model.copyWithSecondaryModeChange(secondaryMode));
  };

  // Return Component
  return (
    <LabeledComponent title="Control Panel" height="180px">
      <div className="controls-pane w-full h-full">
        {props.model && (
          <>
            <div className="controls-pane-menu py-3 w-full">
              <ControlsPaneMenuItem
                name="Replace"
                width="w-1/3"
                active={props.model.getPrimaryMode() === "replace"}
                onClick={() => handlePrimaryModeChange("replace")}
              />
              <ControlsPaneMenuItem
                name="Code"
                width="w-1/3"
                active={props.model.getPrimaryMode() === "code"}
                onClick={() => handlePrimaryModeChange("code")}
              />
              <ControlsPaneMenuItem
                name="Tab"
                width="w-1/3"
                active={props.model.getPrimaryMode() === "tab"}
                onClick={() => handlePrimaryModeChange("tab")}
              />
            </div>
            <div className="controls-pane-menu py-3 w-full">
              {getSecondaryModeOptions().map((option) => (
                <ControlsPaneMenuItem
                  name={toOptionCase(option)}
                  width={`w-1/${getSecondaryModeOptions().length}`}
                  active={props.model.getSecondaryMode() === option}
                  onClick={() => handleSecondaryModeChange(option)}
                />
              ))}
            </div>
            <div className="controls-pane-description py-0">
              <b>{modeTitle}:</b>
              <p>{modeDescription}</p>
            </div>
          </>
        )}
      </div>
    </LabeledComponent>
  );
};

const ControlsPaneMenuItem = (props: {
  name: string;
  active: boolean;
  width: string;
  onClick: () => void;
}): JSX.Element => {
  return (
    <div
      className={`px-3 mx-3 ${props.width} flex justify-center controls-pane-menu${props.active ? "-active" : ""}-item`}
      onClick={props.onClick}
    >
      {props.name}
    </div>
  );
};
