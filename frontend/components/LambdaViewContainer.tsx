import { ReactNode } from "react";

export const LambdaViewContainer = (props: {
  title: string;
  children: ReactNode;
}): JSX.Element => {
  return (
    <div id="lambda-view">
      <div className="w-full flex">
        <div className="text-3xl font-bold">{props.title}</div>
        <div className="ml-auto justify-end pr-5">
          <a href="/">
            <img className="home-icon-image" src="/logo-icon.png"></img>
          </a>
        </div>
      </div>
      {props.children}
    </div>
  );
};
