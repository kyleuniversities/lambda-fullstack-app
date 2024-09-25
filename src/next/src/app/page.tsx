import SiteBody from "@/components/SiteBody";
import SiteHeader from "@/components/SiteHeader";
import Image from "next/image";

export default function Home() {
  return (
    <div className="site w-full h-screen justify-center">
      <SiteHeader />
      <SiteBody />
    </div>
  );
}
