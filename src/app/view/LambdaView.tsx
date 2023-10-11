import { Button, Form, Grid } from 'semantic-ui-react';
import { LabeledTextArea } from './LabeledTextArea';
import { LabeledTextField } from './LabeledTextField';
import { LambdaModel } from '../model/LambdaModel';
import { useState } from 'react';

const doNothing = (event: any) => {};

export type LambdaViewProps = {
  model: LambdaModel;
  onInputKeyDown: (event: any) => void;
  onInputChange: (event: any) => void;
  onBodyChange: (event: any) => void;
};

export const LambdaView = (props: LambdaViewProps): JSX.Element => {
  const model = props.model;
  const [message, setMessage] = useState(model.getMessage());
  const [input, setInput] = useState(model.getInput());
  const [body, setBody] = useState(model.getBody());
  const [output, setOutput] = useState(model.getOutput());
  const updateOnChange = (action: (event: any) => void) => {
    return (event: any) => {
      action(event);
      setMessage(model.getMessage());
      setInput(model.getInput());
      setBody(model.getBody());
      setOutput(model.getOutput());
    };
  };
  return (
    <div id="lambda-view">
      <h1>Lambda Application</h1>
      <Grid columns={2} relaxed="very" style={{ height: '100%' }}>
        <Grid.Column style={{ height: '100%' }}>
          <Form>
            <LambdaMessageArea value={message} />
            <LambdaInputField
              value={input}
              onKeyDown={updateOnChange(props.onInputKeyDown)}
              onChange={updateOnChange(props.onInputChange)}
            />
            <LambdaBodyArea
              value={body}
              onChange={updateOnChange(props.onBodyChange)}
            />
            <Grid columns={2} relaxed="very" style={{ height: '50px' }}>
              <Grid.Column>
                <LambdaAttachImageButton />
              </Grid.Column>
              <Grid.Column>
                <LambdaAttachFileButton />
              </Grid.Column>
            </Grid>
          </Form>
        </Grid.Column>
        <Grid.Column>
          <Form>
            <LambdaOutputArea value={output} />
          </Form>
        </Grid.Column>
      </Grid>
    </div>
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
      containerHeight="270px"
      textAreaHeight="200px"
      disabled={false}
      value={props.value}
      onChange={props.onChange}
    />
  );
};

const LambdaAttachImageButton = (): JSX.Element => {
  return (
    <Button
      color="twitter"
      fluid
      onClick={(e: any) => alert('Attach Image Button has been clicked')}
    >
      Attach Image
    </Button>
  );
};

const LambdaAttachFileButton = (): JSX.Element => {
  return (
    <Button
      color="twitter"
      fluid
      onClick={(e: any) => alert('Attach File Button has been clicked')}
    >
      Attach File
    </Button>
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
