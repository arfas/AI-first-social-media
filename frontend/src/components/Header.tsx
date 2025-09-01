import Link from 'next/link';
import { ThemeToggle } from './ThemeToggle';

export default function Header() {
  return (
    <header className="bg-gray-800 text-white p-4">
      <div className="container mx-auto flex justify-between items-center">
        <Link href="/" className="text-xl font-bold">
          AI Social
        </Link>
        <nav className="flex items-center space-x-4">
          <Link href="/feed">Feed</Link>
          <Link href="/upload">Upload</Link>
          <Link href="/login">Login</Link>
          <Link href="/signup">Sign Up</Link>
          <ThemeToggle />
        </nav>
      </div>
    </header>
  );
}
