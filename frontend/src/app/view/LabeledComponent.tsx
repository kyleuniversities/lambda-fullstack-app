import { ReactNode } from 'react';
import { Container } from 'semantic-ui-react';

type LabeledComponentProps = {
  title: string;
  height: string;
  children: ReactNode;
};

export const LabeledComponent = (props: LabeledComponentProps): JSX.Element => {
  const labeledComponentStyle = { position: 'relative', height: props.height };
  return (
    <Container style={labeledComponentStyle}>
      <h2>{props.title}: </h2>
      {props.children}
    </Container>
  );
};
