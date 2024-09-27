import React from "react";
import SiteConsoleCard from "./SiteConsoleCard";

export default function SiteBody() {
  return (
    <div className="w-full p-5 h-1/4 flex justify-center items-center">
      <SiteConsoleCard
        image="/number.png"
        title="Numerical Console"
        to="/numeric"
      />
      <SiteConsoleCard
        image="/string.png"
        title="String Console"
        to="/string"
      />
      <SiteConsoleCard
        image="/custom.png"
        title="Custom Console"
        to="/custom"
      />
    </div>
  );
}
