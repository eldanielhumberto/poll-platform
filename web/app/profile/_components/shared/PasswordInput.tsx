'use client';

import { Eye, EyeOff } from 'lucide-react';
import { useState } from 'react';

import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';

interface Props {
  type: 'currentPassword' | 'newPassword' | 'confirmPassword';
}

export default function PasswordInput({ type }: Props) {
  const [showPassword, setShowPassword] = useState(false);

  const inputData = {
    currentPassword: {
      title: 'Contraseña actual',
      palceholder: 'Tu contraseña actual',
    },
    newPassword: {
      title: 'Contraseña actual',
      palceholder: 'Tu contraseña actual',
    },
    confirmPassword: {
      title: 'Contraseña actual',
      palceholder: 'Tu contraseña actual',
    },
  };

  return (
    <div className="space-y-2">
      <Label htmlFor={type}>{inputData[type].title}</Label>
      <div className="relative">
        <Input
          id={type}
          type={showPassword ? 'text' : 'password'}
          placeholder={inputData[type].palceholder}
        />
        <Button
          type="button"
          variant="ghost"
          size="sm"
          className="absolute right-0 top-0 h-full px-3 hover:bg-transparent"
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
  );
}
