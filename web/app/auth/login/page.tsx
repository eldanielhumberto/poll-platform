'use client';

import { BarChart3, Eye, EyeOff } from 'lucide-react';
import { useActionState, useState } from 'react';
import Link from 'next/link';

import { loginUser } from '@/actions/auth';

import ErrorCard from '@/components/Error';
import { Label } from '@/components/ui/label';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

export default function LoginPage() {
  const [showPassword, setShowPassword] = useState(false);
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const [state, formAction, pending] = useActionState(loginUser, {
    error: '',
  });

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-purple-50 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        {/* Logo */}
        <div className="text-center mb-8">
          <Link href="/" className="inline-flex items-center space-x-2">
            <BarChart3 className="h-8 w-8 text-blue-600" />
            <span className="text-2xl font-bold text-gray-900">
              Pool Platform
            </span>
          </Link>
        </div>

        {/* Error Message */}
        {state.error && <ErrorCard error={state.error} />}

        {/* Login Form */}
        <Card className="border-0 shadow-xl p-8">
          <CardHeader className="flex flex-col items-center text-center mb-3">
            <CardTitle className="text-2xl">Iniciar Sesión</CardTitle>
            <CardDescription>
              Ingresa a tu cuenta para continuar
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-6">
            <form action={formAction} className="space-y-6 flex flex-col ">
              <div className="space-y-2 flex flex-col">
                <Label htmlFor="email">Correo electrónico</Label>
                <Input
                  id="email"
                  name="email"
                  type="email"
                  placeholder="tu@email.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>

              <div className="space-y-2 flex flex-col">
                <Label htmlFor="password">Contraseña</Label>
                <div className="relative">
                  <Input
                    id="password"
                    name="password"
                    type={showPassword ? 'text' : 'password'}
                    placeholder="Tu contraseña"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                  <Button
                    variant="ghost"
                    className="absolute right-0 top-0 h-full px-3 py-2 hover:bg-transparent"
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                  >
                    {showPassword ? (
                      <EyeOff className="h-4 w-4 text-gray-400" />
                    ) : (
                      <Eye className="h-4 w-4 text-gray-400" />
                    )}
                  </Button>
                </div>
              </div>

              <Button disabled={pending} className="text-center">
                Iniciar Sesión
              </Button>
            </form>

            <div className="text-center text-sm">
              <span className="text-gray-500">¿No tienes una cuenta? </span>
              <Link
                href="/auth/register"
                className="text-blue-600 hover:underline"
              >
                Regístrate
              </Link>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
