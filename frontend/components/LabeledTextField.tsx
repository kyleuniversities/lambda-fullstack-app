import { Form } from "semantic-ui-react";
import { LabeledComponent } from "./LabeledComponent";

type LabeledTextFieldProps = {
  title: string;
  containerHeight: string;
  placeholder: string;
  value: string;
  onKeyDown: (event: any) => void;
  onChange: (event: any) => void;
};

export const LabeledTextField = (props: LabeledTextFieldProps): JSX.Element => {
  return (
    <LabeledComponent title={props.title} height={props.containerHeight}>
      <input
        className="lambda-text-box"
        style={{ width: "100%" }}
        placeholder={props.placeholder}
        value={props.value}
        onKeyDown={props.onKeyDown}
        onChange={props.onChange}
      />
    </LabeledComponent>
  );
};
