'use client';

import { BarChart3, LogOut, Plus, Search, User } from 'lucide-react';
import { usePathname } from 'next/navigation';
import Link from 'next/link';

import { Avatar, AvatarFallback } from './ui/avatar';
import { Button } from './ui/button';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from './ui/dropdown-menu';

export default function Navbar() {
  const pathname = usePathname();

  const isActive = (path: string) => pathname === path;

  return (
    <header className="border-b bg-white/80 backdrop-blur-sm sticky top-0 z-50">
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
            <Button size="sm" className="hidden sm:flex">
              <Plus className="h-4 w-4 mr-2" />
              Nueva Encuesta
            </Button>
          </Link>

          <Button variant="ghost" size="sm" className="hidden sm:flex">
            <Search className="h-4 w-4" />
          </Button>

          <DropdownMenu>
            <DropdownMenuTrigger>
              <Avatar className="h-8 w-8">
                <AvatarFallback>JD</AvatarFallback>
              </Avatar>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuLabel>
                <div className="flex items-center justify-start gap-2 p-2">
                  <div className="flex flex-col space-y-1 leading-none">
                    <p className="font-medium">Juan Pérez</p>
                    <p className="w-[200px] truncate text-sm text-muted-foreground">
                      juan@ejemplo.com
                    </p>
                  </div>
                </div>
              </DropdownMenuLabel>
              <DropdownMenuSeparator />
              <Link href="/profile">
                <DropdownMenuItem>
                  <User className="mr-2 h-4 w-4" />
                  <span>Perfil</span>
                </DropdownMenuItem>
              </Link>
              <DropdownMenuSeparator />
              <DropdownMenuItem>
                <LogOut className="mr-2 h-4 w-4" />
                <span>Cerrar sesión</span>
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      </div>
    </header>
  );
}
