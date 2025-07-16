'use client';

import { useParams } from 'next/navigation';
import { ArrowLeft } from 'lucide-react';
import useSWR, { Fetcher } from 'swr';
import { useState, useTransition } from 'react';
import Link from 'next/link';

import SurveyUnavailable from '../_components/SurveyUnavailable';
import SubmittedMessage from '../_components/SubmittedMessage';

import { SurveyView } from '@/components/SurveyView';
import Loading from '@/components/Loading';

import { ServerResponse } from '@/interfaces/ServerResponse';
import { Survey } from '@/interfaces/Survey';
import submitAnswersAction from '@/actions/surveys/submitAnswers';

const fetcher: Fetcher<ServerResponse<Survey>> = (url: string) =>
  fetch(url).then((r) => r.json());

export default function SurveyPage() {
  const params = useParams();
  const [isPending, startTransition] = useTransition();
  const { data, isLoading } = useSWR(
    `${process.env.NEXT_PUBLIC_API_URL}/surveys/get?id=${params.id}`,
    fetcher
  );

  const [submitted, setSubmitted] = useState(false);

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
  if (!data || !data.data || !data.data.questions) return <SurveyUnavailable />;

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

      <SurveyView survey={data.data} handleSubmit={handleSubmit} />
    </main>
  );
}
