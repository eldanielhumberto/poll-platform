import { Plus } from 'lucide-react';
import Navbar from '../components/Navbar';
import Button from '../components/ui/Button';

export default function DashboardPage() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <main className="container mx-auto px-4 py-8">
        {/* Header */}
        <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-8">
          <div>
            <h1 className="text-3xl font-bold text-gray-900 mb-2">Dashboard</h1>
            <p className="text-gray-600">
              Gestiona tus encuestas y analiza los resultados
            </p>
          </div>
          <Button className="mt-4 sm:mt-0 flex items-center">
            <Plus className="h-4 w-4 mr-2" />
            Nueva Encuesta
          </Button>
        </div>
      </main>
    </div>
  );
}
