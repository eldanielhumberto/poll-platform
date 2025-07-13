import { Plus } from 'lucide-react';

import { getUserSurveys } from '@/lib/api/surveys';

import SurveysList from './_components/surveys/SurveysList';

import StatsList from '@/components/stats/StatsList';

import { Button } from '@/components/ui/button';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

export default async function DashboardPage() {
  const { data: surveys } = await getUserSurveys();

  return (
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
      <StatsList />

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
          <SurveysList surveys={surveys} />
        </CardContent>
      </Card>
    </main>
  );
}
