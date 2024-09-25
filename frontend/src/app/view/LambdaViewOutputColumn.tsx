import { Form, Grid } from 'semantic-ui-react';
import { LabeledTextArea } from './LabeledTextArea';
import { doNothing } from '../util/event';

export const LambdaViewOutputColumn = (props: {
  output: string;
}): JSX.Element => {
  return (
    <Grid.Column>
      <Form>
        <LambdaOutputArea value={props.output} />
      </Form>
    </Grid.Column>
  );
};

const LambdaOutputArea = (props: { value: string }): JSX.Element => {
  return (
    <LabeledTextArea
      title="Output"
      containerHeight="570px"
      textAreaHeight="100%"
      disabled={true}
      value={props.value}
      onChange={doNothing}
    />
  );
};
