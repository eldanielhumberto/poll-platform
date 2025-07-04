import Link from 'next/link';
import {
  BarChart3,
  Calendar,
  Eye,
  MoreHorizontal,
  Plus,
  Users,
} from 'lucide-react';

import { Button } from '@/components/ui/button';
import Navbar from '@/components/Navbar';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';

export default function DashboardPage() {
  const stats = [
    {
      title: 'Total de Encuestas',
      value: '12',
      icon: BarChart3,
      color: 'text-blue-600',
    },
    {
      title: 'Vistas Totales',
      value: '5,678',
      icon: Eye,
      color: 'text-purple-600',
    },
  ];

  const surveys = [
    {
      id: 1,
      title: 'Satisfacción del Cliente 2024',
      description: 'Encuesta para medir la satisfacción de nuestros clientes',
      responses: 156,
      createdAt: '2024-01-15',
      views: 324,
    },
    {
      id: 2,
      title: 'Feedback del Producto',
      description: 'Recopilación de opiniones sobre nuestro nuevo producto',
      responses: 89,
      createdAt: '2024-01-10',
      views: 198,
    },
    {
      id: 3,
      title: 'Encuesta de Empleados',
      description: 'Evaluación del clima laboral interno',
      responses: 45,
      createdAt: '2024-01-08',
      views: 67,
    },
  ];

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
          <Button className="mt-4 sm:mt-0">
            <Plus className="h-4 w-4 mr-2" />
            Nueva Encuesta
          </Button>
        </div>

        {/* Stats Cards */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {stats.map((stat, index) => (
            <Card key={index} className="border-0 shadow-sm">
              <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                <CardTitle className="text-sm font-medium text-gray-600">
                  {stat.title}
                </CardTitle>
                <stat.icon className={`h-4 w-4 ${stat.color}`} />
              </CardHeader>
              <CardContent>
                <div className="text-2xl font-bold text-gray-900">
                  {stat.value}
                </div>
              </CardContent>
            </Card>
          ))}
        </div>

        {/* Surveys Section */}
        <Card className="border-0 shadow-sm">
          <CardHeader>
            <div className="flex justify-between items-center">
              <div>
                <CardTitle>Mis Encuestas</CardTitle>
                <CardDescription>
                  Gestiona y analiza tus encuestas creadas
                </CardDescription>
              </div>
            </div>
          </CardHeader>
          <CardContent>
            <div className="space-y-4">
              {surveys.map((survey) => (
                <div
                  key={survey.id}
                  className="flex items-center justify-between p-4 border rounded-lg hover:bg-gray-50 transition-colors"
                >
                  <div className="flex-1">
                    <div className="flex items-center space-x-3 mb-2">
                      <h3 className="font-semibold text-gray-900">
                        {survey.title}
                      </h3>
                    </div>
                    <p className="text-gray-600 text-sm mb-3">
                      {survey.description}
                    </p>
                    <div className="flex items-center space-x-6 text-sm text-gray-500">
                      <div className="flex items-center space-x-1">
                        <Users className="h-4 w-4" />
                        <span>{survey.responses} respuestas</span>
                      </div>
                      <div className="flex items-center space-x-1">
                        <Eye className="h-4 w-4" />
                        <span>{survey.views} vistas</span>
                      </div>
                      <div className="flex items-center space-x-1">
                        <Calendar className="h-4 w-4" />
                        <span>
                          Creada el{' '}
                          {new Date(survey.createdAt).toLocaleDateString()}
                        </span>
                      </div>
                    </div>
                  </div>
                  <div className="flex items-center space-x-2">
                    <Link href={`/my-survey/${survey.id}`}>
                      <Button variant="outline" size="sm">
                        Ver Resultados
                      </Button>
                    </Link>
                    <DropdownMenu>
                      <DropdownMenuTrigger asChild>
                        <Button variant="ghost" size="sm">
                          <MoreHorizontal className="h-4 w-4" />
                        </Button>
                      </DropdownMenuTrigger>
                      <DropdownMenuContent align="end">
                        <Link href={`/edit-survey/${survey.id}`}>
                          <DropdownMenuItem>Editar</DropdownMenuItem>
                        </Link>
                        <DropdownMenuItem>Duplicar</DropdownMenuItem>
                        <DropdownMenuItem>Compartir</DropdownMenuItem>
                        <DropdownMenuItem className="text-red-600">
                          Eliminar
                        </DropdownMenuItem>
                      </DropdownMenuContent>
                    </DropdownMenu>
                  </div>
                </div>
              ))}
            </div>
          </CardContent>
        </Card>
      </main>
    </div>
  );
}
