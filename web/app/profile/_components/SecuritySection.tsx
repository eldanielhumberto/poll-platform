'use client';

import { Eye, EyeOff, Lock, Shield } from 'lucide-react';
import { useState } from 'react';

import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog';

export default function SecuritySection() {
  const [isPasswordModalOpen, setIsPasswordModalOpen] = useState(false);
  const [showPasswords, setShowPasswords] = useState(false);

  return (
    <div>
      <h3 className="text-lg font-semibold text-gray-900 mb-4 flex items-center">
        <Shield className="h-5 w-5 mr-2 text-blue-600" />
        Seguridad
      </h3>
      <div className="space-y-4">
        <div className="flex items-center justify-between p-4 bg-gray-50 rounded-lg hover:bg-gray-100 transition-colors">
          <div className="flex items-center space-x-4">
            <div className="p-2 bg-blue-100 rounded-lg">
              <Lock className="h-5 w-5 text-blue-600" />
            </div>
            <div>
              <h4 className="font-medium text-gray-900">Cambiar Contraseña</h4>
              <p className="text-sm text-gray-600">
                Actualiza tu contraseña para mantener tu cuenta segura
              </p>
            </div>
          </div>
          <Dialog
            open={isPasswordModalOpen}
            onOpenChange={setIsPasswordModalOpen}
          >
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
                {/* Current Password */}
                <div className="space-y-2">
                  <Label htmlFor="currentPassword">Contraseña actual</Label>
                  <div className="relative">
                    <Input
                      id="currentPassword"
                      type={showPasswords ? 'text' : 'password'}
                      placeholder="Tu contraseña actual"
                    />
                    <Button
                      type="button"
                      variant="ghost"
                      size="sm"
                      className="absolute right-0 top-0 h-full px-3 hover:bg-transparent"
                      onClick={() => setShowPasswords(!showPasswords)}
                    >
                      {showPasswords ? (
                        <EyeOff className="h-4 w-4 text-gray-400" />
                      ) : (
                        <Eye className="h-4 w-4 text-gray-400" />
                      )}
                    </Button>
                  </div>
                </div>

                {/* New Password */}
                <div className="space-y-2">
                  <Label htmlFor="newPassword">Nueva contraseña</Label>
                  <div className="relative">
                    <Input
                      id="newPassword"
                      type={showPasswords ? 'text' : 'password'}
                      placeholder="Tu nueva contraseña"
                    />
                    <Button
                      type="button"
                      variant="ghost"
                      size="sm"
                      className="absolute right-0 top-0 h-full px-3 hover:bg-transparent"
                      onClick={() => setShowPasswords(!showPasswords)}
                    >
                      {showPasswords ? (
                        <EyeOff className="h-4 w-4 text-gray-400" />
                      ) : (
                        <Eye className="h-4 w-4 text-gray-400" />
                      )}
                    </Button>
                  </div>
                </div>

                {/* Confirm Password */}
                <div className="space-y-2">
                  <Label htmlFor="confirmPassword">
                    Confirmar nueva contraseña
                  </Label>
                  <div className="relative">
                    <Input
                      id="confirmPassword"
                      type={showPasswords ? 'text' : 'password'}
                      placeholder="Confirma tu nueva contraseña"
                    />
                    <Button
                      type="button"
                      variant="ghost"
                      size="sm"
                      className="absolute right-0 top-0 h-full px-3 hover:bg-transparent"
                      onClick={() => setShowPasswords(!showPasswords)}
                    >
                      {showPasswords ? (
                        <EyeOff className="h-4 w-4 text-gray-400" />
                      ) : (
                        <Eye className="h-4 w-4 text-gray-400" />
                      )}
                    </Button>
                  </div>

                  {/* {passwordData.confirmPassword &&
                                passwordData.newPassword ===
                                  passwordData.confirmPassword && (
                                  <p className="text-green-600 text-sm flex items-center">
                                    <CheckCircle className="h-4 w-4 mr-1" />
                                    Las contraseñas coinciden
                                  </p>
                                )}

                              {passwordErrors.confirmPassword && (
                                <p className="text-red-600 text-sm flex items-center">
                                  <AlertCircle className="h-4 w-4 mr-1" />
                                  {passwordErrors.confirmPassword}
                                </p>
                              )} */}
                </div>

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
        </div>
      </div>
    </div>
  );
}
