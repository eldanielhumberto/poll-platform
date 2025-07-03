'use client';

import { BarChart3, Plus } from 'lucide-react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import Button from './ui/Button';

export default function Navbar() {
  const pathname = usePathname();

  const isActive = (path: string) => pathname === path;

  return (
    <header className="border-b border-b-amber-50 bg-white/80 shadow-md sticky top-0 z-50">
      <div className="container mx-auto px-4 py-4 flex items-center justify-between">
        <div className="flex items-center space-x-8">
          <Link href="/dashboard" className="flex items-center space-x-2">
            <BarChart3 className="h-8 w-8 text-blue-600" />
            <span className="text-2xl font-bold text-gray-900">
              Pool Platform
            </span>
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

        <div className="flex items-center space-x-4">
          <Link href="/create">
            <Button size="md" className="hidden sm:flex items-center">
              <Plus className="h-4 w-4 mr-2" />
              Nueva Encuesta
            </Button>
          </Link>

          <Link href="/create">
            <Button variant="ghost" size="md">
              Profile
            </Button>
          </Link>
        </div>
      </div>
    </header>
  );
}
