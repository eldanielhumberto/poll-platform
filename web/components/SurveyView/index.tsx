'use client';

import { useState } from 'react';

import { Card, CardContent } from '@/components/ui/card';

import SurveyPendingQuestions from './SurveyPendingQuestions';
import SurveyResponses from './SurveyResponses';
import SurveyDetails from './SurveyDetails';

import { useFetch } from '@/hooks/useFetch';
import { useAuth } from '@/hooks/useAuth';

import { Response } from '@/interfaces/Response';
import { Survey } from '@/interfaces/Survey';

import Loading from '@/app/loading';

interface SurveyPreviewProps {
  survey: Survey;
  handleSubmit?: (answers: Record<string, string>) => void;
  isEditor?: boolean;
}

// REFACTOR THIS COMPONENT
export function SurveyView({
  survey,
  handleSubmit,
  isEditor,
}: SurveyPreviewProps) {
  const [answers, setAnswers] = useState({} as Record<string, string>);
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const { user } = useAuth();

  const { data: surveyResponses, isLoading } = useFetch<Response[]>(
    !isEditor && user
      ? `/answers/get?surveyId=${survey.id}&userId=${user?.id}`
      : null
  );

  const handleSingleChoice = (questionText: string, value: string) => {
    setAnswers((prev) => ({ ...prev, [questionText]: value }));
  };

  if (
    isEditor &&
    (survey.questions.length === 0 ||
      survey.title.length === 0 ||
      survey.description.length === 0 ||
      survey.category.name.length === 0)
  ) {
    return (
      <Card>
        <CardContent className="py-12 text-center">
          <p className="text-gray-500">
            Rellena la informacion basica y agrega preguntas para ver la vista
            previa de tu encuesta
          </p>
        </CardContent>
      </Card>
    );
  }

  return (
    <div className="max-w-4xl mx-auto space-y-8">
      {/* Survey details */}
      <SurveyDetails survey={survey} />

      {!isLoading ? (
        surveyResponses && surveyResponses?.length > 0 ? (
          <SurveyResponses />
        ) : (
          <SurveyPendingQuestions
            setCurrentQuestion={setCurrentQuestion}
            currentQuestion={currentQuestion}
            handleSingleChoice={handleSingleChoice}
            handleSubmit={handleSubmit}
            answers={answers}
            survey={survey}
            user={user}
          />
        )
      ) : (
        <Loading />
      )}
    </div>
  );
}
