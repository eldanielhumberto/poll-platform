'use client';

import { useState, useTransition } from 'react';
import { useParams } from 'next/navigation';
import { ArrowLeft } from 'lucide-react';
import Link from 'next/link';

import submitAnswersAction from '@/actions/surveys/submitAnswers';

import SurveyUnavailable from '../../../components/SurveyUnavailable';
import SubmittedMessage from '../_components/SubmittedMessage';

import { SurveyView } from '@/components/SurveyView';
import Loading from '@/components/Loading';

import { useFetch } from '@/hooks/useFetch';
import { useAuth } from '@/hooks/useAuth';
import { Survey } from '@/interfaces/Survey';

export default function SurveyPage() {
  const [isPending, startTransition] = useTransition();
  const [submitted, setSubmitted] = useState(false);
  const { id: surveyId } = useParams();
  const { token } = useAuth();

  const { data, isLoading } = useFetch<Survey>(
    `/surveys/get?id=${surveyId}`,
    token
  );

  const handleSubmit = (answers: Record<string, string>) => {
    const options = Object.entries(answers).map(([, optionId]) => optionId);

    startTransition(async () => {
      await submitAnswersAction(options);
    });

    setSubmitted(true);
  };

  // If loading, show a loading state
  if (isLoading || isPending)
    return <Loading message="Cargando y evaluando datos..." />;

  // If no data or no questions, show a message
  if (!data || !data || !data.questions) return <SurveyUnavailable />;

  // Show submitted message if the survey is submitted
  if (submitted) return <SubmittedMessage />;

  return (
    <main className="container mx-auto px-4 py-8">
      {/* Back Button */}
      <Link
        href="/explore"
        className="inline-flex items-center text-blue-600 hover:text-blue-800 mb-6"
      >
        <ArrowLeft className="h-4 w-4 mr-2" />
        Volver a explorar
      </Link>

      <SurveyView survey={data} handleSubmit={handleSubmit} />
    </main>
  );
}
