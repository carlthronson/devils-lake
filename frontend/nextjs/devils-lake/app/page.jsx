// `app/page.js` is the UI for the `/` URL
import Link from "next/link";

export default function Page() {
  return (
    <div>
      <h1>Home page of the job search app</h1>
      <div>
        <Link href="/task-board">
          Tasks Page
        </Link>
      </div>
    </div>
  );
}
