import { TextArea } from "semantic-ui-react";
import { LabeledComponent } from "./LabeledComponent";

type Option = {
  text: string;
  value: string;
};

type LabeledDropdownProps = {
  title: string;
  containerHeight: string;
  options: Option[];
  value: string;
  onChange: (event: any) => void;
};

export const LabeledDropdown = (props: LabeledDropdownProps): JSX.Element => {
  return (
    <LabeledComponent title={props.title} height={props.containerHeight}>
      <select
        className="lambda-dropdown"
        value={props.value}
        onChange={props.onChange}
      >
        {props.options.map((option) => (
          <option
            key={props.title.replaceAll(" ", "_").toLowerCase() + option.value}
            value={option.value}
          >
            {option.text}
          </option>
        ))}
      </select>
    </LabeledComponent>
  );
};
