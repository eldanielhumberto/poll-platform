import { Lock, Shield } from 'lucide-react';

import ChangePasswordModal from '../shared/ChangePasswordModal';

export default function SecuritySection() {
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
          <ChangePasswordModal />
        </div>
      </div>
    </div>
  );
}
