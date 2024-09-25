import { TextArea } from "semantic-ui-react";
import { LabeledComponent } from "./LabeledComponent";

type LabeledTextAreaProps = {
  title: string;
  containerHeight: string;
  textAreaHeight: string;
  disabled: boolean;
  value: string;
  onChange: (event: any) => void;
};

export const LabeledTextArea = (props: LabeledTextAreaProps): JSX.Element => {
  const labeledTextAreaStyle = {
    position: "relative",
    width: "100%",
    height: props.textAreaHeight,
  };
  return (
    <LabeledComponent title={props.title} height={props.containerHeight}>
      <TextArea
        className="lambda-text-box lambda-text-area"
        disabled={props.disabled}
        style={labeledTextAreaStyle}
        value={props.value}
        onChange={props.onChange}
      />
    </LabeledComponent>
  );
};
