import { Plus } from 'lucide-react';
import { Suspense } from 'react';

import { getUserSurveys } from '@/lib/api/surveys';

import SurveysList from './_components/surveys/SurveysList';
import StatsList from './_components/stats/StatsList';

import StatsLoading from './_components/stats/StatsLoading';
import Navbar from '@/components/Navbar';

import { Button } from '@/components/ui/button';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

export default function DashboardPage() {
  const surveysData = getUserSurveys();

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
        <Suspense fallback={<StatsLoading />}>
          <StatsList surveysData={surveysData} />
        </Suspense>

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
            <Suspense fallback={<h1>Loading...</h1>}>
              <SurveysList surveysData={surveysData} />
            </Suspense>
          </CardContent>
        </Card>
      </main>
    </div>
  );
}
