import { Form } from 'semantic-ui-react';
import { LabeledComponent } from './LabeledComponent';

type LabeledTextFieldProps = {
  title: string;
  containerHeight: string;
  value: string;
  onKeyDown: (event: any) => void;
  onChange: (event: any) => void;
};

export const LabeledTextField = (props: LabeledTextFieldProps): JSX.Element => {
  return (
    <LabeledComponent title={props.title} height={props.containerHeight}>
      <Form.Input
        value={props.value}
        onKeyDown={props.onKeyDown}
        onChange={props.onChange}
      />
    </LabeledComponent>
  );
};
