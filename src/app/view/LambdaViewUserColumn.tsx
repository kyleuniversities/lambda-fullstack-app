import { Form, Grid } from 'semantic-ui-react';
import { LabeledTextArea } from './LabeledTextArea';
import { LabeledTextField } from './LabeledTextField';
import { doNothing } from '../util/event';

export type LambdaUserColumnProps = {
  message: string;
  input: string;
  body: string;
  onInputKeyDown: (event: any) => Promise<void>;
  onInputChange: (event: any) => Promise<void>;
  onBodyChange: (event: any) => Promise<void>;
  updateOnChange: (
    action: (event: any) => Promise<void>
  ) => (event: any) => Promise<void>;
};

export const LambdaViewUserColumn = (
  props: LambdaUserColumnProps
): JSX.Element => {
  return (
    <Grid.Column style={{ height: '100%' }}>
      <Form>
        <LambdaMessageArea value={props.message} />
        <LambdaInputField
          value={props.input}
          onKeyDown={props.updateOnChange(props.onInputKeyDown)}
          onChange={props.updateOnChange(props.onInputChange)}
        />
        <LambdaBodyArea
          value={props.body}
          onChange={props.updateOnChange(props.onBodyChange)}
        />
      </Form>
    </Grid.Column>
  );
};

const LambdaMessageArea = (props: { value: string }): JSX.Element => {
  return (
    <LabeledTextArea
      title="Message"
      containerHeight="200px"
      textAreaHeight="150px"
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
      containerHeight="100px"
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
      containerHeight="320px"
      textAreaHeight="270px"
      disabled={false}
      value={props.value}
      onChange={props.onChange}
    />
  );
};
