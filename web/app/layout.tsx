import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import AuthProvider from '@/context/AuthContext';
import { getUser } from '@/lib/api/auth';

import relativeTime from 'dayjs/plugin/relativeTime';
import dayjs from 'dayjs';
import Navbar from '@/components/Navbar';

const inter = Inter({
  subsets: ['latin'],
});

export const metadata: Metadata = {
  title: 'Poll Platform - Crea encuestas increíbles',
  description:
    'Diseña, comparte y analiza encuestas de manera fácil y rápida. Obtén insights valiosos de tu audiencia con gráficos en tiempo real.',
};

dayjs.extend(relativeTime);

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const user = await getUser();

  return (
    <html lang="en" className={inter.className}>
      <body className="flex flex-col min-h-screen">
        <AuthProvider user={user}>
          <Navbar />
          {children}
        </AuthProvider>
      </body>
    </html>
  );
}
