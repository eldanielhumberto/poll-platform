import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import AuthProvider from '@/context/AuthContext';
import { getUser } from '@/lib/api/auth';

const inter = Inter({
  subsets: ['latin'],
});

export const metadata: Metadata = {
  title: 'Poll Platform - Crea encuestas increíbles',
  description:
    'Diseña, comparte y analiza encuestas de manera fácil y rápida. Obtén insights valiosos de tu audiencia con gráficos en tiempo real.',
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const user = await getUser();

  return (
    <html lang="en" className={inter.className}>
      <body>
        <AuthProvider user={user}>{children}</AuthProvider>
      </body>
    </html>
  );
}
