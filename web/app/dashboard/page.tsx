import { Plus } from 'lucide-react';

import SurveysList from './_components/surveys/SurveysList';
import StatsList from './_components/stats/StatsList';

import { getUserSurveys } from '@/lib/api/surveys';
import { Button } from '@/components/ui/button';
import Navbar from '@/components/Navbar';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

export default async function DashboardPage() {
  const { data } = await getUserSurveys();

  // Calculate total visits and count
  const surveysVisits = data.reduce((acc, survey) => acc + survey.visits, 0);
  const surveysCount = data.length;

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
        <StatsList surveysCount={surveysCount} surveysVisits={surveysVisits} />

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
            <SurveysList surveys={data} />
          </CardContent>
        </Card>
      </main>
    </div>
  );
}
