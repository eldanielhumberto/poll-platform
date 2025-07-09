import { Button } from '@/components/ui/button';
import { Trash2, UserX } from 'lucide-react';

export default function DangerZone() {
  return (
    <div>
      <h3 className="text-lg font-semibold text-red-600 mb-4 flex items-center">
        <UserX className="h-5 w-5 mr-2" />
        Zona de Peligro
      </h3>
      <div className="border border-red-200 rounded-lg p-6 bg-red-50">
        <div className="flex items-start justify-between">
          <div className="flex-1">
            <h4 className="font-medium text-red-900 mb-2">Eliminar Cuenta</h4>
            <p className="text-sm text-red-700 mb-4">
              Una vez que elimines tu cuenta, no hay vuelta atrás. Por favor,
              ten cuidado. Esta acción eliminará permanentemente:
            </p>
            <ul className="text-sm text-red-700 space-y-1 mb-4">
              <li>• Todas tus encuestas creadas</li>
              <li>• Todas las respuestas recibidas</li>
              <li>• Tu perfil y configuraciones</li>
              <li>• Todos los datos asociados a tu cuenta</li>
            </ul>
          </div>
        </div>
        <div className="flex justify-end">
          <Button variant="destructive" className="bg-red-600 hover:bg-red-700">
            <Trash2 className="h-4 w-4 mr-2" />
            Eliminar Cuenta Permanentemente
          </Button>
        </div>
      </div>
    </div>
  );
}
