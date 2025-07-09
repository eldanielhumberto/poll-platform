import { NextResponse, NextRequest } from 'next/server';
import { getSession } from './lib/session';

// Specify public and protected routes
const protectedRoutes = ['/dashboard', '/profile'];
const publicRoutes = ['/auth/login', '/auth/register'];

export default async function middleware(request: NextRequest) {
  // Check if the request is for a protected or public route
  const path = request.nextUrl.pathname;
  const isProtectedRoute = protectedRoutes.includes(path);
  const isPublicRoute = publicRoutes.includes(path);

  // Get session cookie
  const sessionCookie = await getSession();

  // Redirect to login if accessing protected route without session
  if (isProtectedRoute && !sessionCookie) {
    return NextResponse.redirect(new URL('/auth/login', request.url));
  }

  // Redirect to dashboard if session exists
  if (sessionCookie && isPublicRoute && !path.startsWith('/dashboard')) {
    return NextResponse.redirect(new URL('/dashboard', request.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: ['/((?!api|_next/static|_next/image|.*\\.png$).*)'],
};
