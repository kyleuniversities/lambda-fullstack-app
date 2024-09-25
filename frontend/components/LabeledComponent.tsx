import { ReactNode } from "react";
import { Container } from "semantic-ui-react";

type LabeledComponentProps = {
  title: string;
  height: string;
  children: ReactNode;
};

export const LabeledComponent = (props: LabeledComponentProps): JSX.Element => {
  const labeledComponentStyle = {
    position: "relative",
    width: "100%",
    height: props.height,
  };
  return (
    <Container fluid style={labeledComponentStyle}>
      <div className="font-bold">{props.title}: </div>
      {props.children}
    </Container>
  );
};
