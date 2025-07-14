import { Settings } from 'lucide-react';

import SecuritySection from './sections/SecuritySection';
import DangerSection from './sections/DangerSection';

import { Separator } from '@/components/ui/separator';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

export default function Configuration() {
  return (
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
            <DangerSection />
          </div>
        </CardContent>
      </Card>
    </div>
  );
}
