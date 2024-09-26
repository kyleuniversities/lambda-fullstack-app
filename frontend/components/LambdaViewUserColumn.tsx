import { Form, Grid } from "semantic-ui-react";
import { LabeledTextArea } from "./LabeledTextArea";
import { LabeledTextField } from "./LabeledTextField";
import { doNothing } from "../util/event";
import { LambdaSubmitButton } from "./LambdaSubmitButton";

export type LambdaUserColumnProps = {
  message: string;
  input: string;
  body: string;
  onInputKeyDown: (event: any) => Promise<void>;
  onInputChange: (event: any) => Promise<void>;
  onBodyChange: (event: any) => Promise<void>;
};

export const LambdaViewUserColumn = (
  props: LambdaUserColumnProps,
): JSX.Element => {
  return (
    <div className="w-1/2 p-3" style={{ height: "100%" }}>
      <form>
        <LambdaMessageArea value={props.message} />
        <LambdaInputField
          value={props.input}
          onKeyDown={props.onInputKeyDown}
          onChange={props.onInputChange}
        />
        <LambdaBodyArea value={props.body} onChange={props.onBodyChange} />
        <LambdaSubmitButton title="Perform Action" />
      </form>
    </div>
  );
};

const LambdaMessageArea = (props: { value: string }): JSX.Element => {
  return (
    <LabeledTextArea
      title="Message"
      containerHeight="220px"
      textAreaHeight="175px"
      placeholder=""
      disabled={true}
      value={props.value}
      onChange={doNothing}
    />
  );
};

const LambdaInputField = (props: {
  value: string;
  onKeyDown: (event: any) => void;
  onChange: (event: any) => void;
}): JSX.Element => {
  return (
    <LabeledTextField
      title="Input"
      containerHeight="80px"
      placeholder="Enter the command you would like run"
      value={props.value}
      onKeyDown={props.onKeyDown}
      onChange={props.onChange}
    />
  );
};

const LambdaBodyArea = (props: {
  value: string;
  onChange: (event: any) => void;
}): JSX.Element => {
  return (
    <LabeledTextArea
      title="Body"
      containerHeight="230px"
      textAreaHeight="190px"
      disabled={false}
      placeholder="Enter the body parameter of your command"
      value={props.value}
      onChange={props.onChange}
    />
  );
};
