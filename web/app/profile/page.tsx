'use client';

import { useContext } from 'react';
import { Settings } from 'lucide-react';

import SecuritySection from './_components/SecuritySection';
import StatsList from '../../components/stats/StatsList';
import DangerZone from './_components/DangerZone';

import { AuthContext } from '@/context/AuthContext';
import Navbar from '@/components/Navbar';

import { Avatar, AvatarFallback } from '@/components/ui/avatar';
import { Separator } from '@/components/ui/separator';
import { Badge } from '@/components/ui/badge';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

export default function ProfilePage() {
  const { user } = useContext(AuthContext);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />

      <main className="container mx-auto px-4 py-8">
        {/* Header */}
        <div className="relative bg-gradient-to-r from-blue-600 to-purple-600 rounded-2xl p-8 mb-8 text-white overflow-hidden">
          <div className="flex items-center space-x-6 mb-6 md:mb-0 z-10">
            <div className="relative">
              <Avatar className="h-24 w-24 border-4 border-white/20">
                <AvatarFallback className="text-2xl bg-white/20">
                  {user?.username
                    .split(' ')
                    .map((n) => n[0])
                    .join('')}
                </AvatarFallback>
              </Avatar>
            </div>

            <div>
              <h1 className="text-3xl font-bold mb-2">{user?.username}</h1>
              <p className="text-white/80 mb-2">{user?.email}</p>
              <Badge
                variant="secondary"
                className="bg-white/20 text-white border-white/30"
              >
                Miembro desde{' '}
                {/* {new Date(userData.joinDate).toLocaleDateString()} */}
              </Badge>
            </div>
          </div>
        </div>

        {/* Stats Cards */}
        <StatsList />

        {/* Configuration */}
        <div className="w-full">
          <Card className="border-0 shadow-lg">
            <CardHeader>
              <div className="flex items-center space-x-3">
                <div className="p-2 bg-blue-100 rounded-lg">
                  <Settings className="h-6 w-6 text-blue-600" />
                </div>
                <div>
                  <CardTitle className="text-2xl">
                    Configuración de Cuenta
                  </CardTitle>
                  <CardDescription className="text-base">
                    Gestiona la seguridad y configuración de tu cuenta
                  </CardDescription>
                </div>
              </div>
            </CardHeader>

            <CardContent className="p-8">
              <div className="space-y-6">
                <SecuritySection />
                <Separator />
                <DangerZone />
              </div>
            </CardContent>
          </Card>
        </div>
      </main>
    </div>
  );
}
