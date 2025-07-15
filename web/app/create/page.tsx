'use client';

import { ArrowLeft } from 'lucide-react';
import Link from 'next/link';

import SurveyEditor from '@/components/SurveyEditor';
import { Survey, SurveyDataToSend } from '@/interfaces/Survey';
import { useTransition } from 'react';
import { createSurveyAction } from '@/actions/surveys/createSurvey';
import Loading from '@/components/Loading';

export default function CreateSurveyPage() {
  const [isPending, startTransition] = useTransition();

  const survey: Survey = {
    id: '',
    title: '',
    description: '',
    category: { id: '', name: '', color: '' },
    questions: [],
    createdAt: new Date().toString(),
  };

  const publishSurvey = (newSurvey: Survey) => {
    const dataToSend: SurveyDataToSend = {
      title: newSurvey.title,
      description: newSurvey.description,
      categoryId: newSurvey.category.id,
      questions: newSurvey.questions.map((q) => ({
        questionText: q.questionText,
        options: q.options.map((o) => o.optionText),
      })),
    };

    startTransition(async () => {
      await createSurveyAction(dataToSend);
    });
  };

  if (isPending) return <Loading message="Creando encuesta..." />;

  return (
    <main className="container mx-auto px-4 py-8">
      {/* Header */}
      <Link
        href="/dashboard"
        className="inline-flex items-center text-blue-600 hover:text-blue-800 mb-6"
      >
        <ArrowLeft className="h-4 w-4 mr-2" />
        Volver al Dashboard
      </Link>

      <SurveyEditor initialSurvey={survey} publishSurvey={publishSurvey} />
    </main>
  );
}
