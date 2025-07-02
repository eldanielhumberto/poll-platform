'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';

export default function Navbar() {
  const pathname = usePathname();

  const isActive = (path: string) => pathname === path;

  return (
    <nav className="border-b bg-white/80 backdrop-blur-sm sticky top-0 z-50">
      <div className="container mx-auto px-4 py-4 flex items-center justify-between">
        <div className="flex items-center space-x-8">
          <Link href="/dashboard" className="flex items-center space-x-2">
            {/* <BarChart3 className='h-8 w-8 text-blue-600' /> */}
            <span className="text-2xl font-bold text-gray-900">SurveyPro</span>
          </Link>

          <nav className="hidden md:flex items-center space-x-6">
            <Link
              href="/dashboard"
              className={`text-sm font-medium transition-colors hover:text-blue-600 ${
                isActive('/dashboard') ? 'text-blue-600' : 'text-gray-600'
              }`}
            >
              Dashboard
            </Link>
            <Link
              href="/explore"
              className={`text-sm font-medium transition-colors hover:text-blue-600 ${
                isActive('/explore') ? 'text-blue-600' : 'text-gray-600'
              }`}
            >
              Explorar
            </Link>
          </nav>
        </div>
      </div>
    </nav>
  );
}
