'use client';

import { useParams } from 'next/navigation';
import { ArrowLeft } from 'lucide-react';
import useSWR, { Fetcher } from 'swr';
import { useState } from 'react';
import Link from 'next/link';

import QuestionsList from '../_components/questions/QuestionsList';
import SurveyUnavailable from '../_components/SurveyUnavailable';
import SubmittedMessage from '../_components/SubmittedMessage';
import ProgressBar from '../_components/ProgressBar';

import { Button } from '@/components/ui/button';

import SurveyDetails from '@/components/SurveyDetails';
import Loading from '@/components/Loading';

import { Question as IQuestion } from '@/interfaces/Question';
import { ServerResponse } from '@/interfaces/ServerResponse';
import { Survey } from '@/interfaces/Survey';

const fetcher: Fetcher<ServerResponse<Survey>> = (url: string) =>
  fetch(url).then((r) => r.json());

export default function SurveyPage() {
  const params = useParams();
  const { data, isLoading } = useSWR(
    `${process.env.NEXT_PUBLIC_API_URL}/surveys/get?id=${params.id}`,
    fetcher
  );

  const [answers, setAnswers] = useState({} as Record<string, string>);
  const [submitted, setSubmitted] = useState(false);
  const [currentQuestion, setCurrentQuestion] = useState(0);

  const handleSingleChoice = (questionId: string, value: string) => {
    setAnswers((prev) => ({ ...prev, [questionId]: value }));
  };

  const handleSubmit = () => {
    console.log('Submitting answers:', answers);
    setSubmitted(true);
  };

  const isQuestionAnswered = (question: IQuestion) => {
    const answer = answers[question.id];
    return answer && answer.trim() !== '';
  };

  // If loading, show a loading state
  if (isLoading) return <Loading message="Cargando encuesta..." />;

  // If no data or no questions, show a message
  if (!data || !data.data || !data.data.questions) return <SurveyUnavailable />;

  // Show submitted message if the survey is submitted
  const canSubmit = data.data.questions.every((q) => isQuestionAnswered(q));
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

      <div className="max-w-4xl mx-auto">
        {/* Survey details */}
        <SurveyDetails survey={data.data} />

        {/* Progress Bar */}
        <ProgressBar
          currentQuestion={currentQuestion}
          questions={data.data.questions}
        />

        {/* Questions */}
        <QuestionsList
          questions={data.data.questions}
          answers={answers}
          currentQuestion={currentQuestion}
          handleSingleChoice={handleSingleChoice}
        />

        {/* Navigation */}
        <div className="flex justify-between items-center mt-8">
          <Button
            variant="outline"
            onClick={() => setCurrentQuestion(Math.max(0, currentQuestion - 1))}
            disabled={currentQuestion === 0}
          >
            Anterior
          </Button>

          <div className="flex space-x-2">
            {data.data.questions.map((_, index) => (
              <div
                key={index}
                className={`w-2 h-2 rounded-full ${
                  index === currentQuestion
                    ? 'bg-blue-600'
                    : index < currentQuestion
                    ? 'bg-green-600'
                    : 'bg-gray-300'
                }`}
              />
            ))}
          </div>

          {currentQuestion < data.data.questions.length - 1 ? (
            <Button
              onClick={() => setCurrentQuestion(currentQuestion + 1)}
              disabled={
                !isQuestionAnswered(data.data.questions[currentQuestion])
              }
            >
              Siguiente
            </Button>
          ) : (
            <Button
              onClick={handleSubmit}
              disabled={!canSubmit}
              className="bg-green-600 hover:bg-green-700"
            >
              Enviar Respuestas
            </Button>
          )}
        </div>
      </div>
    </main>
  );
}
