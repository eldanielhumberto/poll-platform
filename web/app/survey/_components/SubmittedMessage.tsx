import Navbar from '@/components/Navbar';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { CheckCircle } from 'lucide-react';
import Link from 'next/link';

export default function SubmittedMessage() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <main className="container mx-auto px-4 py-8">
        <div className="max-w-2xl mx-auto text-center">
          <Card className="border-0 shadow-lg">
            <CardContent className="pt-8 pb-8">
              <CheckCircle className="h-16 w-16 text-green-600 mx-auto mb-4" />
              <h1 className="text-2xl font-bold text-gray-900 mb-2">
                ¡Gracias por tu participación!
              </h1>
              <p className="text-gray-600 mb-6">
                Tu respuesta ha sido registrada exitosamente. Los resultados se
                actualizarán en tiempo real.
              </p>
              <div className="flex flex-col sm:flex-row gap-4 justify-center">
                <Link href="/explore" className="w-full sm:w-auto">
                  <Button className="w-full sm:w-auto" variant="outline">
                    Explorar más encuestas
                  </Button>
                </Link>
                <Button>Ver resultados</Button>
              </div>
            </CardContent>
          </Card>
        </div>
      </main>
    </div>
  );
}
