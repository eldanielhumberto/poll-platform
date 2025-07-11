import { Search } from 'lucide-react';

import { getSurveys } from '@/lib/api/surveys';

import { Input } from '@/components/ui/input';

import SurveysList from './_components/surveys/SurveysList';
import Navbar from '@/components/Navbar';
import FeaturedSurveys from './_components/FeaturedSurveys';

export default async function ExplorePage() {
  const { data: surveys } = await getSurveys();

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />

      <main className="container mx-auto px-4 py-8">
        {/* Header */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 mb-2">
            Explorar Encuestas
          </h1>
          <p className="text-gray-600">
            Descubre y participa en encuestas de la comunidad
          </p>
        </div>

        {/* Featured surveys */}
        <FeaturedSurveys surveys={surveys} />

        {/* Search and Filters */}
        <div className="flex flex-col sm:flex-row gap-4 mb-6">
          <div className="relative flex-1">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-4 w-4" />
            <Input placeholder="Buscar encuestas..." className="pl-10" />
          </div>
        </div>

        {/* Survey Grid */}
        <SurveysList surveys={surveys} />
      </main>
    </div>
  );
}
