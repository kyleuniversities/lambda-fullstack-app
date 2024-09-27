import { ReactNode } from "react";
import { Container } from "semantic-ui-react";

type LabeledComponentProps = {
  title: string;
  height: string;
  children: ReactNode;
};

export const LabeledComponent = (props: LabeledComponentProps): JSX.Element => {
  // Constants
  const opacity = props.height.split(/[px%]+/)[0] === "0" ? "0%" : "100%";

  // Style
  const labeledComponentStyle = {
    position: "relative",
    width: "100%",
    height: props.height,
    opacity,
  };

  // Return Component
  return (
    <Container
      className="labeled-component-label"
      fluid
      style={labeledComponentStyle}
    >
      <div className="font-bold">{props.title}: </div>
      {props.children}
    </Container>
  );
};
