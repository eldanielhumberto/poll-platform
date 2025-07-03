'use client';

import { Eye, EyeOff, BarChart3 } from 'lucide-react';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import Link from 'next/link';

import { register } from '@/app/actions/auth';
import ErrorCard from '@/app/components/ui/Error';
import Button from '@/app/components/ui/Button';
import Input from '@/app/components/ui/Input';
import Label from '@/app/components/ui/Label';

import {
  Card,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/app/components/ui/Card';

export default function RegisterPage() {
  const [error, setError] = useState<string | null>(null);
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
  });
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      await register(formData.name, formData.email, formData.password);
      router.push('/dashboard');
    } catch (error) {
      if (error instanceof Error) {
        setError(
          error.message.charAt(0).toUpperCase() + error.message.slice(1)
        );
      } else {
        setError('Ocurrió un error al registrarse. Inténtalo de nuevo.');
      }
    }
  };
  const handleInputChange = (field: string, value: string | boolean) => {
    setFormData((prev) => ({ ...prev, [field]: value }));
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-purple-50 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        {/* Logo */}
        <div className="text-center">
          <Link href="/" className="inline-flex items-center space-x-2">
            <BarChart3 className="h-8 w-8 text-blue-600" />
            <span className="text-2xl font-bold text-gray-900">
              Pool Platform
            </span>
          </Link>
        </div>

        {/* Error Message */}
        {error && <ErrorCard error={error} />}

        {/* Registration Form */}
        <Card className="border-0 shadow-xl p-8">
          <CardHeader className="text-center">
            <CardTitle className="text-2xl">Crear Cuenta</CardTitle>
            <CardDescription>
              Únete a Pool Platform y comienza a crear encuestas increíbles
            </CardDescription>
          </CardHeader>
          <div className="space-y-6 flex flex-col">
            <form onSubmit={handleSubmit} className="space-y-4 flex flex-col">
              <div className="space-y-2 flex flex-col">
                <Label htmlFor="name">Nombre completo</Label>
                <Input
                  id="name"
                  type="text"
                  placeholder="Tu nombre completo"
                  value={formData.name}
                  onChange={(e) => handleInputChange('name', e.target.value)}
                  required
                />
              </div>

              <div className="space-y-2 flex flex-col">
                <Label htmlFor="email">Correo electrónico</Label>
                <Input
                  id="email"
                  type="email"
                  placeholder="tu@email.com"
                  value={formData.email}
                  onChange={(e) => handleInputChange('email', e.target.value)}
                  required
                />
              </div>

              <div className="space-y-2 flex flex-col">
                <Label htmlFor="password">Contraseña</Label>
                <div className="relative">
                  <Input
                    id="password"
                    type={showPassword ? 'text' : 'password'}
                    placeholder="Mínimo 8 caracteres"
                    value={formData.password}
                    onChange={(e) =>
                      handleInputChange('password', e.target.value)
                    }
                    required
                  />
                  <Button
                    type="button"
                    variant="ghost"
                    size="sm"
                    className="absolute right-0 top-0 h-full px-3 py-2 hover:bg-transparent"
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

              <div className="space-y-2 flex flex-col">
                <Label htmlFor="confirmPassword">Confirmar contraseña</Label>
                <div className="relative">
                  <Input
                    id="confirmPassword"
                    type={showPassword ? 'text' : 'password'}
                    placeholder="Confirma tu contraseña"
                    value={formData.confirmPassword}
                    onChange={(e) =>
                      handleInputChange('confirmPassword', e.target.value)
                    }
                    required
                  />
                  <Button
                    type="button"
                    variant="ghost"
                    size="sm"
                    className="absolute right-0 top-0 h-full px-3 py-2 hover:bg-transparent"
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

              <Button type="submit" className="w-full">
                Crear Cuenta
              </Button>
            </form>

            <div className="text-center text-sm">
              <span className="text-gray-500">¿Ya tienes una cuenta? </span>
              <Link
                href="/auth/login"
                className="text-blue-600 hover:underline"
              >
                Inicia sesión
              </Link>
            </div>
          </div>
        </Card>
      </div>
    </div>
  );
}
