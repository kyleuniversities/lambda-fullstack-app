import React from "react";

type ConsoleCardParameters = {
  image: string;
  title: string;
};

export default function SiteConsoleCard(parameters: ConsoleCardParameters) {
  return (
    <div className="site-console-card flex">
      <div>
        <img className="site-console-card-image" src={parameters.image} />
      </div>
      <div className="site-console-card-text p-2">
        <div className="font-bold text-3xl">{parameters.title}</div>
      </div>
    </div>
  );
}
