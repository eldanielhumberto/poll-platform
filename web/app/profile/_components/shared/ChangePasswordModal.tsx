'use client';

import { Lock } from 'lucide-react';
import { useState } from 'react';

import PasswordInput from './PasswordInput';

import { Button } from '@/components/ui/button';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog';

export default function ChangePasswordModal() {
  const [isPasswordModalOpen, setIsPasswordModalOpen] = useState(false);

  return (
    <Dialog open={isPasswordModalOpen} onOpenChange={setIsPasswordModalOpen}>
      <DialogTrigger asChild>
        <Button
          variant="outline"
          className="bg-white hover:bg-gray-50"
          // onClick={resetPasswordForm}
        >
          Cambiar
        </Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-md">
        <DialogHeader>
          <DialogTitle className="flex items-center">
            <Lock className="h-5 w-5 mr-2 text-blue-600" />
            Cambiar Contraseña
          </DialogTitle>
          <DialogDescription>
            Elige una nueva contraseña segura.
          </DialogDescription>
        </DialogHeader>
        <form className="space-y-4">
          <PasswordInput type="currentPassword" />
          <PasswordInput type="newPassword" />
          <PasswordInput type="confirmPassword" />

          {/* Action Buttons */}
          <div className="flex justify-end space-x-3 pt-4">
            <Button
              variant="outline"
              type="button"
              onClick={() => setIsPasswordModalOpen(false)}
            >
              Cancelar
            </Button>
            <Button className="bg-blue-600 hover:bg-blue-700">
              {/* {isChangingPassword
                                    ? 'Cambiando...'
                                    : 'Cambiar Contraseña'} */}
              Cambiar contraseña
            </Button>
          </div>
        </form>

        {/* {passwordSuccess ? (
                            <div className="py-8 text-center">
                              <CheckCircle className="h-16 w-16 text-green-600 mx-auto mb-4" />
                              <h3 className="text-lg font-semibold text-gray-900 mb-2">
                                ¡Contraseña actualizada!
                              </h3>
                              <p className="text-gray-600">
                                Tu contraseña ha sido cambiada exitosamente.
                              </p>
                            </div>
                          ) : (
                          )} */}
      </DialogContent>
    </Dialog>
  );
}
