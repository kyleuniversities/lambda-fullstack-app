import { Form, Grid } from "semantic-ui-react";
import { LabeledTextArea } from "./LabeledTextArea";
import { doNothing } from "../util/event";

export const LambdaViewOutputColumn = (props: {
  output: string;
}): JSX.Element => {
  return (
    <div className="w-1/2 p-3">
      <Form>
        <LambdaOutputArea value={props.output} />
      </Form>
    </div>
  );
};

const LambdaOutputArea = (props: { value: string }): JSX.Element => {
  return (
    <LabeledTextArea
      title="Output"
      containerHeight="570px"
      textAreaHeight="100%"
      disabled={true}
      placeholder=""
      value={props.value}
      onChange={doNothing}
    />
  );
};
