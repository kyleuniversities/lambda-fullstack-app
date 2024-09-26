import { LabeledComponent } from "./LabeledComponent";

export const LambdaSubmitButton = (props: { title: string }) => {
  return (
    <LabeledComponent title={props.title} height="70px">
      <button className="lambda-button w-full" type="submit" value="Evaluate">
        {props.title}
      </button>
    </LabeledComponent>
  );
};
