import Link from "next/link";

export default function Home() {
  return (
    <>
      <header className="flex items-center justify-between px-4 py-3 sm:px-6 lg:px-8">
        <Link className="flex items-center" href="#">
          <FlagIcon className="h-6 w-6 text-gray-900 dark:text-gray-50" />
          <span className="ml-2 text-lg font-bold text-gray-900 dark:text-gray-50">
            Project Manager
          </span>
        </Link>
        <div className="flex items-center space-x-4">
          <Link
            className="rounded-md bg-gray-900 px-4 py-2 text-sm font-medium text-gray-50 transition-colors hover:bg-gray-900/90 focus:outline-none focus:ring-1 focus:ring-gray-950 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus:ring-gray-300"
            href="/login"
          >
            Login
          </Link>
          <Link
            className="rounded-md border border-gray-200 bg-white px-4 py-2 text-sm font-medium text-gray-900 shadow-sm transition-colors hover:bg-gray-100 focus:outline-none focus:ring-1 focus:ring-gray-950 dark:border-gray-800 dark:bg-gray-950 dark:text-gray-50 dark:hover:bg-gray-800 dark:focus:ring-gray-300"
            href="/projects"
          >
            View Projects
          </Link>
        </div>
      </header>
      <main>
        <section className="bg-gray-100 py-12 sm:py-16 md:py-20 lg:py-24">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8">
            <div className="grid grid-cols-1 items-center gap-8 lg:grid-cols-2">
              <div className="space-y-4">
                <h1 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl md:text-5xl lg:text-6xl">
                  Streamline Your Project Management
                </h1>
                <p className="text-lg text-gray-500 dark:text-gray-400">
                  Our powerful project management app helps you stay organized,
                  collaborate with your team, and deliver projects on time.
                </p>
                <div>
                  <Link
                    className="inline-flex h-10 items-center justify-center rounded-md bg-gray-900 px-6 text-sm font-medium text-gray-50 transition-colors hover:bg-gray-900/90 focus:outline-none focus:ring-1 focus:ring-gray-950 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus:ring-gray-300"
                    href="#"
                  >
                    Get Started
                  </Link>
                </div>
              </div>
              <div>
                <img
                  alt="Project Management"
                  className="mx-auto rounded-lg object-cover"
                  height="400"
                  src="/smile.jpg"
                  style={{
                    aspectRatio: "600/400",
                    objectFit: "cover",
                  }}
                  width="600"
                />
              </div>
            </div>
          </div>
        </section>
        <section className="bg-white py-12 sm:py-16 md:py-20 lg:py-24 dark:bg-gray-950">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8">
            <div className="grid grid-cols-1 gap-8 md:grid-cols-2 lg:grid-cols-3">
              <div className="space-y-4">
                <CalendarIcon className="h-10 w-10 text-gray-900 dark:text-gray-50" />
                <h3 className="text-xl font-bold text-gray-900 dark:text-gray-50">
                  Project Scheduling
                </h3>
                <p className="text-gray-500 dark:text-gray-400">
                  Easily create and manage project schedules, set deadlines, and
                  track progress.
                </p>
              </div>
              <div className="space-y-4">
                <UsersIcon className="h-10 w-10 text-gray-900 dark:text-gray-50" />
                <h3 className="text-xl font-bold text-gray-900 dark:text-gray-50">
                  Team Collaboration
                </h3>
                <p className="text-gray-500 dark:text-gray-400">
                  Invite team members, assign tasks, and communicate in
                  real-time.
                </p>
              </div>
              <div className="space-y-4">
                <ClipboardIcon className="h-10 w-10 text-gray-900 dark:text-gray-50" />
                <h3 className="text-xl font-bold text-gray-900 dark:text-gray-50">
                  Task Management
                </h3>
                <p className="text-gray-500 dark:text-gray-400">
                  Create and assign tasks, set priorities, and track progress.
                </p>
              </div>
            </div>
          </div>
        </section>
        <section className="bg-gray-100 py-12 sm:py-16 md:py-20 lg:py-24 dark:bg-gray-800">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8">
            <div className="grid grid-cols-1 items-center gap-8 lg:grid-cols-2">
              <div>
                <img
                  alt="Reporting"
                  className="mx-auto rounded-lg object-cover"
                  height="400"
                  src="/frown.jpg"
                  style={{
                    aspectRatio: "600/400",
                    objectFit: "cover",
                  }}
                  width="600"
                />
              </div>
              <div className="space-y-4">
                <BarChartIcon className="h-10 w-10 text-gray-900 dark:text-gray-50" />
                <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl md:text-5xl dark:text-gray-50">
                  Powerful Reporting
                </h2>
                <p className="text-lg text-gray-500 dark:text-gray-400">
                  Get detailed insights into your projects with our advanced
                  reporting features.
                </p>
                <div>
                  <Link
                    className="inline-flex h-10 items-center justify-center rounded-md bg-gray-900 px-6 text-sm font-medium text-gray-50 transition-colors hover:bg-gray-900/90 focus:outline-none focus:ring-1 focus:ring-gray-950 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus:ring-gray-300"
                    href="#"
                  >
                    Sign Up
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
      <footer className="bg-gray-900 py-6 text-center text-sm text-gray-400">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          © 2024 Project Manager. All rights reserved.
        </div>
      </footer>
    </>
  );
}

function BarChartIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <line x1="12" x2="12" y1="20" y2="10" />
      <line x1="18" x2="18" y1="20" y2="4" />
      <line x1="6" x2="6" y1="20" y2="16" />
    </svg>
  );
}

function CalendarIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M8 2v4" />
      <path d="M16 2v4" />
      <rect width="18" height="18" x="3" y="4" rx="2" />
      <path d="M3 10h18" />
    </svg>
  );
}

function ClipboardIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <rect width="8" height="4" x="8" y="2" rx="1" ry="1" />
      <path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2" />
    </svg>
  );
}

function FlagIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M4 15s1-1 4-1 5 2 8 2 4-1 4-1V3s-1 1-4 1-5-2-8-2-4 1-4 1z" />
      <line x1="4" x2="4" y1="22" y2="15" />
    </svg>
  );
}

function UsersIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
      <circle cx="9" cy="7" r="4" />
      <path d="M22 21v-2a4 4 0 0 0-3-3.87" />
      <path d="M16 3.13a4 4 0 0 1 0 7.75" />
    </svg>
  );
}
