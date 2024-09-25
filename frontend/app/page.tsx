import SiteBody from "@/components/SiteBody";
import SiteHeader from "@/components/SiteHeader";

export default function Home() {
  return (
    <div className="home-page w-screen h-screen justify-center">
      <SiteHeader />
      <SiteBody />
    </div>
  );
}
