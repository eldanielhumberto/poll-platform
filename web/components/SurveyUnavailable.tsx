import Link from 'next/link';

import { Card, CardContent } from './ui/card';
import { Button } from './ui/button';

export default function SurveyUnavailable() {
  return (
    <main className="flex flex-1 items-center justify-center">
      <div className="max-w-2xl mx-auto text-center">
        <Card className="border-0 shadow-lg">
          <CardContent className="pt-8 pb-8">
            <h1 className="text-2xl font-bold text-gray-900 mb-2">
              Uyy al parecer ocurrio algo inesperado
            </h1>
            <p className="text-gray-600 mb-6">
              Lo sentimos, esta encuesta no está disponible o no tiene
              preguntas.
            </p>
            <Link href="/explore">
              <Button variant="outline">Volver a explorar</Button>
            </Link>
          </CardContent>
        </Card>
      </div>
    </main>
  );
}
