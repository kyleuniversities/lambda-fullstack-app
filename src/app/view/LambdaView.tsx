import { Grid } from 'semantic-ui-react';
import { LambdaModel } from '../model/LambdaModel';
import { ReactNode, useState } from 'react';
import { LambdaViewUserColumn } from './LambdaViewUserColumn';
import { LambdaViewOutputColumn } from './LambdaViewOutputColumn';

export type LambdaViewProps = {
  model: LambdaModel;
  onInputKeyDown: (event: any) => Promise<void>;
  onInputChange: (event: any) => Promise<void>;
  onBodyChange: (event: any) => Promise<void>;
};

export const LambdaView = (props: LambdaViewProps): JSX.Element => {
  const model = props.model;
  const [message, setMessage] = useState(model.getMessage());
  const [input, setInput] = useState(model.getInput());
  const [body, setBody] = useState(model.getBody());
  const [output, setOutput] = useState(model.getOutput());
  const updateOnChange = (
    action: (event: any) => Promise<void>
  ): ((event: any) => Promise<void>) => {
    return async (event: any) => {
      await action(event);
      setMessage(model.getMessage());
      setInput(model.getInput());
      setBody(model.getBody());
      setOutput(model.getOutput());
    };
  };
  return (
    <LambdaViewContainer>
      <LambdaViewGrid>
        <LambdaViewUserColumn
          message={message}
          input={input}
          body={body}
          onInputKeyDown={props.onInputKeyDown}
          onInputChange={props.onInputChange}
          onBodyChange={props.onBodyChange}
          updateOnChange={updateOnChange}
        />
        <LambdaViewOutputColumn output={output} />
      </LambdaViewGrid>
    </LambdaViewContainer>
  );
};

const LambdaViewContainer = (props: { children: ReactNode }): JSX.Element => {
  return (
    <div id="lambda-view">
      <h1>Lambda Application</h1>
      {props.children}
    </div>
  );
};

const LambdaViewGrid = (props: { children: ReactNode }): JSX.Element => {
  return (
    <Grid columns={2} relaxed="very" style={{ height: '100%' }}>
      {props.children}
    </Grid>
  );
};
